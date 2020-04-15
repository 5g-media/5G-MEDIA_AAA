package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;
import org.springframework.core.env.Environment;

import eu.fivegmedia.aaa.domain.Resource;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DriverOSMr5 implements IDriverUser, IDriverDatacenter {
	
	protected Environment applicationProperties;
	
	public void setApplicationProperties(Environment applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	public String authenticateAsUserAndGetToken(ResourceUser resourceUserDB) {
		String nbi = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("nbi");
		String username = resourceUserDB.getResourceUserLogin().getUsername();
		String password = resourceUserDB.getResourceUserLogin().getPassword();
		
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		
		MediaType mediaTypeAuth = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody bodyAuth = RequestBody.create(mediaTypeAuth, "username="+username+"&password="+password);
		Request requestAuth = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/tokens")
		  .post(bodyAuth)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .build();
		
		String token = null;
		try {
			Response responseAuth = client.newCall(requestAuth).execute();
			String tempToken = responseAuth.body().string();
			int index_id = tempToken.indexOf("_id: ");
			if(index_id > 0) {
				token = tempToken.substring(index_id + "_id: ".length(), tempToken.indexOf("\n", index_id));
			}
	
		}catch(Exception e) {
			throw new RuntimeException("Unable to login", e);
		}
		
		return token;
	}
	
	public String authenticateAndGetToken(ResourceUser resourceUserDB) {
		String nbi = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("nbi");
		String username = resourceUserDB.getResource().getResourceAdminLogin().getUsername();
		String password = resourceUserDB.getResource().getResourceAdminLogin().getPassword();
		
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		
		MediaType mediaTypeAuth = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody bodyAuth = RequestBody.create(mediaTypeAuth, "username="+username+"&password="+password);
		Request requestAuth = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/tokens")
		  .post(bodyAuth)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .build();
		
		String token = null;
		try {
			Response responseAuth = client.newCall(requestAuth).execute();
			String tempToken = responseAuth.body().string();
			int index_id = tempToken.indexOf("_id: ");
			if(index_id > 0) {
				token = tempToken.substring(index_id + "_id: ".length(), tempToken.indexOf("\n", index_id));
			}
	
		}catch(Exception e) {
			throw new RuntimeException("Unable to login", e);
		}
		
		return token;
	}
    
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {
		ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
		Resource resource = resourceUserDB.getResource();
		String nbi = new JSONObject(resource.getAuthConf()).getString("nbi");
		String token = authenticateAndGetToken(resourceUserDB);
		
		//create project (if not exists)
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		MediaType mediaTypeProject = MediaType.parse("application/yaml");
		RequestBody bodyProject = RequestBody.create(mediaTypeProject, "{\n    \"name\" : \""+resourceUser.getTenant()+"\"\n}");
		Request requestProject = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/projects")
		  .post(bodyProject)
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/yaml")
		  .addHeader("cache-control", "no-cache")
		  .build();

		try {
			Response response = client.newCall(requestProject).execute();
			if(response.code() >= 400) {
				throw new IOException(response.message());
			}
		}catch(Exception e) {
			throw new RuntimeException("Unable to create project", e);
		}

		//create user
		MediaType mediaTypeUser = MediaType.parse("application/yaml");
		RequestBody bodyUser = RequestBody.create(mediaTypeUser, "{\n    \"username\" : \""+resourceUserLogin.getUsername()+"\",\n    \"password\" : \""+resourceUserLogin.getPassword()+"\",\n    \"projects\" : [ \n        \""+resourceUser.getTenant()+"\"\n    ]\n}");
		Request requestUser = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/users")
		  .post(bodyUser)
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/yaml")
		  .addHeader("cache-control", "no-cache")
		  .build();
		
		try {
			client.newCall(requestUser).execute();
		}catch(Exception e) {
			throw new RuntimeException("Unable to create user " + resourceUserLogin.getUsername(), e);
		}
    }
	
	
	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {

		ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
		Resource resource = resourceUserDB.getResource();
		String nbi = new JSONObject(resource.getAuthConf()).getString("nbi");
		String token = authenticateAndGetToken(resourceUserDB);

		//delete user
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		Request requestUser = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/users/"+resourceUserLogin.getUsername())
		  .delete(null)
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/yaml")
		  .addHeader("cache-control", "no-cache")
		  .build();
		
		try {
			client.newCall(requestUser).execute();
		}catch(Exception e) {
			throw new RuntimeException("Unable to delete user " + resourceUserLogin.getUsername(), e);
		}
		
		//delete project
		Request requestProject = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/projects/"+resourceUser.getTenant())
		  .delete(null)
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/yaml")
		  .addHeader("cache-control", "no-cache")
		  .build();

		try {
			client.newCall(requestProject).execute();
		}catch(Exception e) {
			throw new RuntimeException("Unable to delete project " + resourceUser.getTenant(), e);
		}

	}
	
	public void createDatacenterAndAttach(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterURL, String datacenterTenant, String datacenterUsername, String datacenterPassword) throws IOException{
		//authenticate
		String nbi = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("nbi");
		String token = authenticateAsUserAndGetToken(resourceUserDB);

		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "name="+datacenterName+"&vim_user="+datacenterUsername+"&vim_password="+datacenterPassword+"&vim_type=openstack&vim_tenant_name="+datacenterTenant+"&vim_url="+datacenterURL);
		Request request = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/vim_accounts")
		  .post(body)
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .build();

		try{
			Response response = client.newCall(request).execute();
			if(response.code() >= 300) {
				throw new Exception("Wrong status code " + response.message());
			}
		}catch(Exception e) {
			throw new RuntimeException("Unable to createOpenstackDatacenterAndAttachOnOSM", e);
		}
	}
	
	public void detachDatacenterAndDelete(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterTenant) throws IOException {
		//authenticate
		String nbi = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("nbi");
		String token = authenticateAsUserAndGetToken(resourceUserDB);

		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		
		Request request = new Request.Builder()
		  .url(nbi + "/osm/admin/v1/vim_accounts")
		  .get()
		  .addHeader("Authorization", "Bearer " + token)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = null;
		try{
			response = client.newCall(request).execute();
			if(response.code() >= 300) {
				throw new Exception("Wrong status code " + response.message());
			}
		}catch(Exception e) {
			throw new RuntimeException("Unable to detachAndDeleteDatacenter", e);
		}
		
		String id = null;
		try {
			String responseString = response.body().string();
			for(String data : responseString.split("_admin:")) {
		
				if(data.indexOf("name: " + datacenterName)>0 && data.indexOf("vim_tenant_name: " + datacenterTenant)>0) {
					int id_index = data.indexOf("_id: ");
					if(id_index > 0) {
						id = data.substring(id_index + "_id: ".length(), data.indexOf("\n", id_index));
					}
					break;
				}
			}
		}catch(Exception e) {
			throw new RuntimeException("Unable to manage vim_accounts list", e);
		}
		if(id == null) {
			throw new RuntimeException("Unable to find id");
		}
		Request requestDelete = new Request.Builder()
				  .url(nbi + "/osm/admin/v1/vim_accounts/" + id)
				  .delete(null)
				  .addHeader("Authorization", "Bearer " + token)
				  .addHeader("Content-Type", "application/x-www-form-urlencoded")
				  .addHeader("cache-control", "no-cache")
				  .build();
		try {
			Response responseDelete = client.newCall(requestDelete).execute();
			if(responseDelete.code() >= 300) {
				throw new Exception("Wrong status code " + response.message());
			}
		}catch(Exception e) {
			throw new RuntimeException("Unable to detachAndDeleteDatacenter", e);
		}
		
	}

	
}
