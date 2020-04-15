package eu.fivegmedia.aaa.service.catalogue.driver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;

import org.json.JSONObject;

import eu.fivegmedia.aaa.domain.Resource;
import eu.fivegmedia.aaa.domain.ResourceToken;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.repository.ResourceTokenRepository;
import eu.fivegmedia.aaa.service.catalogue.dto.ResourceCredentialsDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokenProvider {
	
	//v2 example 2018-08-23T01:51:00Z
	private static long getMillisFromOpenStackTimestampV2(String timestamp) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			return sdf.parse(timestamp).getTime();
		}catch(ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	//v3 example 2018-08-23T01:51:00.000000Z
	private static long getMillisFromOpenStackTimestampV3(String timestamp) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000000Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			return sdf.parse(timestamp).getTime();
		}catch(ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void addCredentials(ResourceTokenRepository resourceTokenRepository, ResourceUser resourceUser, ResourceCredentialsDTO result) throws IOException {
		Resource resource = resourceUser.getResource();
		
		if(resource.getAuthDriver().equals("OSMr3Driver")) {
			result.auth_info = resource.getResourceAdminLogin().getUsername() + ":" + resource.getResourceAdminLogin().getPassword();
			result.auth_info = "Basic " + new String(Base64.getEncoder().encode(result.auth_info.getBytes()));
		}
		else if(resource.getAuthDriver().contains("OpenStack") || resource.getAuthDriver().contains("DevStack")) {
			String keystoneURL = new JSONObject(resource.getAuthConf()).getString("keystone");
			if(keystoneURL.contains("v2.0")) {
				result.auth_info = authenticateOpenstackV2(resourceTokenRepository, resourceUser, keystoneURL, resource.getResourceAdminLogin().getUsername(), resource.getResourceAdminLogin().getPassword());	
			}
			else {
				result.auth_info = authenticateOpenstackV3(resourceTokenRepository, resourceUser, keystoneURL, resource.getResourceAdminLogin().getUsername(), resource.getResourceAdminLogin().getPassword());	
			}
			
		}
	}
	
	private static String authenticateOpenstackV2(ResourceTokenRepository resourceTokenRepository, ResourceUser resourceUser, String url, String user, String pass) throws IOException{
		String result = null;
		
		String[] tokenAndExpiration = getOpenstackV2tokenAndExpiration(url, resourceUser.getTenant(), user, pass);
		
		if(tokenAndExpiration != null) {
			ResourceToken resourceToken = new ResourceToken();
			String token = tokenAndExpiration[0];
			resourceToken.setValue(token);
			resourceToken.setType("OpenStack - keystone v2");
			resourceToken.setValid(true);
			resourceToken.setTimestampCreated(System.currentTimeMillis());
			
			String openstackTimestampExpiration = tokenAndExpiration[1];
			resourceToken.setTimestampExpiration(getMillisFromOpenStackTimestampV2(openstackTimestampExpiration));
			resourceToken.setResourceUser(resourceUser);
			resourceTokenRepository.save(resourceToken);
			
			result = token;
		}
		
		return result;
	}

	public static String[] getOpenstackV2tokenAndExpiration(String url, String tenant, String user, String pass) throws IOException{
		String[] result = null;
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{ \"auth\": {\"passwordCredentials\": {\"username\": \""+user+"\", \"password\": \""+pass+"\"}, \"tenantName\":\""+tenant+"\"}}");
		Request request = new Request.Builder()
		  .url(url)
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		if(response.isSuccessful()) {
			JSONObject jObject = new JSONObject(response.body().string());
			result = new String[2];

			result[0] = jObject.getJSONObject("access").getJSONObject("token").getString("id").toString();
			result[1] = jObject.getJSONObject("access").getJSONObject("token").getString("expires").toString();
		}
		
		return result;
	}

	
	private static String authenticateOpenstackV3(ResourceTokenRepository resourceTokenRepository, ResourceUser resourceUser, String url, String user, String pass) throws IOException{
		String result = null;
		
		String[] tokenAndExpiration = getOpenstackV3tokenAndExpiration(url, resourceUser.getTenant(), user, pass);
		
		if(tokenAndExpiration != null) {
			
			ResourceToken resourceToken = new ResourceToken();
			String token = tokenAndExpiration[0];
			resourceToken.setValue(token);
			resourceToken.setType("OpenStack - keystone v3");
			resourceToken.setValid(true);
			resourceToken.setTimestampCreated(System.currentTimeMillis());
			
			String openstackTimestampExpiration = tokenAndExpiration[1];
			resourceToken.setTimestampExpiration(getMillisFromOpenStackTimestampV3(openstackTimestampExpiration));
			resourceToken.setResourceUser(resourceUser);
			resourceTokenRepository.save(resourceToken);
			
			result = token;
		}
		
		return result;
	}
	
	public static String[] getOpenstackV3tokenAndExpiration(String url, String tenant, String user, String pass) throws IOException{
		String[] result = null;
		
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{ \"auth\": {\"identity\": {  \"methods\": [\"password\"],  \"password\": { \"user\": {  \"name\": \""+user+"\", \"domain\": { \"name\": \"Default\" },  \"password\": \""+pass+"\"}  }},\"scope\": {\"project\": {\"domain\": {\"name\": \"Default\"},\"name\": \"admin\"}} }}");
		Request request = new Request.Builder()
		  .url(url + "/auth/tokens")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		if(response.isSuccessful()) {
			JSONObject jObject = new JSONObject(response.body().string());
			result = new String[2];
			result[0] = response.header("X-Subject-Token");
			result[1] = jObject.getJSONObject("token").getString("expires_at").toString();
		}
		
		return result;
	}
}
