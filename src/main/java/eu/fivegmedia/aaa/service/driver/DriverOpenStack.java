package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.env.Environment;

import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;
import eu.fivegmedia.aaa.service.catalogue.driver.TokenProvider;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DriverOpenStack implements IDriverUser {
	protected Environment applicationProperties;
	
	public void setApplicationProperties(Environment applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
	
	public String authenticateAndGetToken(ResourceUser resourceUserDB) throws IOException{
		//login
		String openstackUsername = resourceUserDB.getResource().getResourceAdminLogin().getUsername();
		String openstackPassword = resourceUserDB.getResource().getResourceAdminLogin().getPassword();
		String openstackIdentityURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
		String openstackToken = null;
		if(openstackIdentityURL.contains("v2.0")) {
			String[] tokenAndExpiration = TokenProvider.getOpenstackV2tokenAndExpiration(openstackIdentityURL, resourceUserDB.getTenant(), openstackUsername, openstackPassword);
			if(tokenAndExpiration != null) {
				openstackToken = tokenAndExpiration[0];
			}
		}
		else {
			String[] tokenAndExpiration = TokenProvider.getOpenstackV3tokenAndExpiration(openstackIdentityURL, resourceUserDB.getTenant(), openstackUsername, openstackPassword);
			if(tokenAndExpiration != null) {
				openstackToken = tokenAndExpiration[0];
			}
		}
		
		if(openstackToken == null) {
			throw new IOException("Unable to get a valid token");
		}
		
		return openstackToken;
	}
	
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {
		try {
			String openstackIdentityURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
			String token = authenticateAndGetToken(resourceUserDB);
			
			String roleID = null;
				
			
			String openstackRoleURL = openstackIdentityURL + "/roles";
			
			String roleToFind = resourceUser.getPermissions();
			//for example "admin"

			OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();

			//get role list to get admin (or other) id
			Request requestRole = new Request.Builder()
					  .url(openstackRoleURL)
					  .get()
					  .addHeader("Content-Type", "application/json")
					  .addHeader("X-Auth-Token", token)
					  .addHeader("Cache-Control", "no-cache")
					  .build();

			Response responseRole = client.newCall(requestRole).execute();
			if(responseRole.isSuccessful()) {
				JSONObject jObject = new JSONObject(responseRole.body().string());
				JSONArray jArray = jObject.getJSONArray("roles");
				for(int i=0; i<jArray.length(); i++) {
					JSONObject jObjectElem = jArray.getJSONObject(i);
					if(jObjectElem.getString("name").equals(roleToFind)) {
						roleID = jObjectElem.getString("id");
						break;
					}
				}
			}
			
			if(roleID == null) {
				throw new IOException("Unable to find role id for role " + roleToFind);
			}
			
			//create a project
			String openstackProjectURL = openstackIdentityURL + "/projects";

			MediaType mediaTypeProject = MediaType.parse("application/json");
			
			String project = resourceUser.getTenant();
			//for example "example1"

			RequestBody bodyProject = RequestBody.create(mediaTypeProject, "{\"project\": {\"enabled\": true, \"description\": \""+project+"\", \"name\": \""+project+"\", \"domain_id\": \"default\"}}");
			Request requestProject = new Request.Builder()
			  .url(openstackProjectURL)
			  .post(bodyProject)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("X-Auth-Token", token)
			  .addHeader("Cache-Control", "no-cache")
			  .build();
			
			String project_id = null;
			Response responseProject = client.newCall(requestProject).execute();
			if(!responseProject.isSuccessful()) {
				throw new IOException("Unable to create project " + project + ". " + responseProject.message());
			}
			else {
				JSONObject jObject = new JSONObject(responseProject.body().string());
				project_id = jObject.getJSONObject("project").getString("id").toString();
			}
			if(project_id == null) {
				throw new IOException("Unable to create project " + project + ", got id null. ");
			}
			
			//create user
			String openstackUserURL = openstackIdentityURL + "/users" ;
			
			MediaType mediaTypeUser = MediaType.parse("application/json");
			ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();

			String user = resourceUserLogin.getUsername();
			String pass = resourceUserLogin.getPassword();
			//for example test1 pass1
			
			RequestBody bodyUser = RequestBody.create(mediaTypeUser, "{\"user\": {\"name\": \""+user+"\", \"password\": \""+pass+"\"}}");
			Request requestUser = new Request.Builder()
			  .url(openstackUserURL)
			  .post(bodyUser)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("X-Auth-Token", token)
			  .addHeader("Cache-Control", "no-cache")
			  .build();

			String user_id = null;
			Response responseUser = client.newCall(requestUser).execute();
			if(!responseUser.isSuccessful()) {
				throw new IOException("Unable to create user " + user + ". " + responseUser.message());
			}
			else {
				JSONObject jObject = new JSONObject(responseUser.body().string());
				user_id = jObject.getJSONObject("user").getString("id").toString();
			}
			if(user_id == null) {
				throw new IOException("Unable to create user " + user + ", got id null. ");
			}
				
			//authorize user
			String openstackAuthorizeURL = openstackIdentityURL + "/projects/"+project_id+"/users/"+user_id+"/roles/"+roleID;
			MediaType mediaTypeAuthorize = MediaType.parse("application/json");
			RequestBody bodyAuthorize = RequestBody.create(mediaTypeAuthorize, "");
			Request requestAuthorize = new Request.Builder()
			  .url(openstackAuthorizeURL)
			  .put(bodyAuthorize)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("X-Auth-Token", token)
			  .addHeader("Cache-Control", "no-cache")
			  .build();

			Response responseAuthorize = client.newCall(requestAuthorize).execute();
			if(!responseAuthorize.isSuccessful()) {
				throw new IOException("Unable to authorize user " + user + " on project " + project + " as admin. " + responseAuthorize.message());
			}
					
		}catch(Exception e) {
			throw new RuntimeException("Error while setting up user/project permissions OpenStack: " + e);
		}
	}

	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {
		try {
			String openstackIdentityURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
			String token = authenticateAndGetToken(resourceUserDB);

			OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
			
			//delete user
			String openstackUserURL = openstackIdentityURL + "/users";

			ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
			
			String user = resourceUserLogin.getUsername();
			//for example test1
			String user_id = null;
			Request requestListUser = new Request.Builder()
					  .url(openstackUserURL)
					  .get()
					  .addHeader("Content-Type", "application/json")
					  .addHeader("X-Auth-Token", token)
					  .addHeader("Cache-Control", "no-cache")
					  .build();
			Response responseListUser = client.newCall(requestListUser).execute();
			if(!responseListUser.isSuccessful()) {
				throw new IOException("Unable to get user list ");
			}
			JSONObject jObjectUsers = new JSONObject(responseListUser.body().string());
			JSONArray jArrayUsers = jObjectUsers.getJSONArray("users");
			for(int i=0; i<jArrayUsers.length(); i++) {
				JSONObject jObjectElem = jArrayUsers.getJSONObject(i);
				if(jObjectElem.getString("name").equals(user)) {
					user_id = jObjectElem.getString("id");
					break;
				}
			}
			if(user_id == null) {
				throw new IOException("Unable to find user " + user);
			}
			Request requestDeleteUser = new Request.Builder()
			  .url(openstackUserURL+"/"+user_id)
			  .delete(null)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("X-Auth-Token", token)
			  .addHeader("Cache-Control", "no-cache")
			  .build();

			Response responseDeleteUser = client.newCall(requestDeleteUser).execute();
			if(!responseDeleteUser.isSuccessful()) {
				throw new IOException("Unable to delete user " + user + ". " + responseDeleteUser.message());
			}
			
			//delete project
			String openstackProjectURL = openstackIdentityURL + "/projects";

			String project = resourceUser.getTenant();
			//for example "example1"
			String project_id = null;
			Request requestListProject = new Request.Builder()
					  .url(openstackProjectURL)
					  .get()
					  .addHeader("Content-Type", "application/json")
					  .addHeader("X-Auth-Token", token)
					  .addHeader("Cache-Control", "no-cache")
					  .build();
			Response responseListProject = client.newCall(requestListProject).execute();
			if(!responseListProject.isSuccessful()) {
				throw new IOException("Unable to get project list ");
			}
			JSONObject jObjectProject = new JSONObject(responseListProject.body().string());
			JSONArray jArrayProject = jObjectProject.getJSONArray("projects");
			for(int i=0; i<jArrayProject.length(); i++) {
				JSONObject jObjectElem = jArrayProject.getJSONObject(i);
				if(jObjectElem.getString("name").equals(project)) {
					project_id = jObjectElem.getString("id");
					break;
				}
			}
			if(project_id == null) {
				throw new IOException("Unable to find project " + project);
			}
			Request requestDeleteProject = new Request.Builder()
			  .url(openstackProjectURL+"/"+project_id)
			  .delete(null)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("X-Auth-Token", token)
			  .addHeader("Cache-Control", "no-cache")
			  .build();

			Response responseDeleteProject = client.newCall(requestDeleteProject).execute();
			if(!responseDeleteProject.isSuccessful()) {
				throw new IOException("Unable to delete project " + project + ". " + responseDeleteProject.message());
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Error while removing user/project permissions OpenStack: " + e);
		}
	}
	
	

}
