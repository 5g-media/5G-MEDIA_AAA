package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class UtilOSMr3 {
    private final Logger log = LoggerFactory.getLogger(UtilOSMr3.class);

	private static String readAuthorizationJson(String json) throws IOException{
		return parseAuthorizationJson(json, null, null, null, null);
	}
	private static String removeAuthorizationJson(String json, String userName, String projectName) throws IOException{
		return parseAuthorizationJson(json, userName, projectName, null, "REMOVE");
	}
	private static String addAuthorizationJson(String json, String userName, String projectName, String[] roles) throws IOException{
		return parseAuthorizationJson(json, userName, projectName, roles, "ADD");
	}

	private static String parseAuthorizationJson(String json, String userName, String projectName, String[] roles, String operation) throws IOException{
		StringBuilder result = new StringBuilder();
		JsonObject jsonObject = new com.google.gson.JsonParser().parse(json).getAsJsonObject();
		if(jsonObject.get("project") != null) {
			JsonArray myArray = jsonObject.get("project").getAsJsonArray();

			for(JsonElement myElem : myArray) {
				JsonObject myObj = myElem.getAsJsonObject();

				String nameValue = myObj.get("name").getAsString();
				if(projectName == null || nameValue.equals(projectName)) {
					if(result.length() > 0) {
						result.append("&");
					}
					result.append("name=");
					result.append(URLEncoder.encode(nameValue, "UTF-8"));
					result.append("&description=");
					result.append(URLEncoder.encode(myObj.get("description").getAsString(), "UTF-8"));

					int userCounter = 0;
					JsonArray projectConfigArray = myObj.get("project-config").getAsJsonObject().get("user").getAsJsonArray();
					for(JsonElement myConfigElem : projectConfigArray) {
						JsonObject myConfigObj = myConfigElem.getAsJsonObject();
						System.out.println(myConfigObj.get("user-name").getAsString());
						if(!"REMOVE".equals(operation) || !myConfigObj.get("user-name").getAsString().equals(userName)) {

							result.append("&");
							result.append(URLEncoder.encode("project-config[user]["+userCounter+"][user-name]", "UTF-8"));
							result.append("=");
							result.append(URLEncoder.encode(myConfigObj.get("user-name").getAsString(), "UTF-8"));

							result.append("&");
							result.append(URLEncoder.encode("project-config[user]["+userCounter+"][user-domain]", "UTF-8"));
							result.append("=");
							result.append(URLEncoder.encode(myConfigObj.get("user-domain").getAsString(), "UTF-8"));

							int roleCounter = 0;
							JsonArray roleArray = myConfigObj.get("role").getAsJsonArray();
							for(JsonElement myRoleElem : roleArray) {
								JsonObject myRoleObj = myRoleElem.getAsJsonObject();

								result.append("&");
								result.append(URLEncoder.encode("project-config[user]["+userCounter+"][role][" + roleCounter + "][role]", "UTF-8"));
								result.append("=");
								result.append(URLEncoder.encode(myRoleObj.get("role").getAsString(), "UTF-8"));

								roleCounter ++;
							}

							userCounter++;
						}
					}
					if("ADD".equals(operation)) {
						result.append("&");
						result.append(URLEncoder.encode("project-config[user]["+userCounter+"][user-name]", "UTF-8"));
						result.append("=");
						result.append(URLEncoder.encode(userName, "UTF-8"));

						result.append("&");
						result.append(URLEncoder.encode("project-config[user]["+userCounter+"][user-domain]", "UTF-8"));
						result.append("=");
						result.append(URLEncoder.encode("localhost", "UTF-8"));

						int roleCounter = 0;
						for(String role : roles) {

							result.append("&");
							result.append(URLEncoder.encode("project-config[user]["+userCounter+"][role][" + roleCounter + "][role]", "UTF-8"));
							result.append("=");
							result.append(URLEncoder.encode(role, "UTF-8"));

							roleCounter ++;
						}
					}
				}
			}
		}


		return result.toString();
	}

	String authenticate(OkHttpClient client, String host, int port, String idp) throws Exception{
		if(client == null) {
			client = getUnsafeOkHttpClient();
		}
		
		//login1/2
		MediaType mediaType1 = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body1 = RequestBody.create(mediaType1, "username=admin&password=admin&state=%257B%2522query%2522%253A%2520%2522response_type%253Dcode%2526redirect_uri%253Dhttps%25253A%25252F%25252F"+host+"%25253A"+port+"%25252Fcallback%2526scope%253Dopenid%2526client_id%253DcncudWkub3BlbmlkY2xpZW50%2522%252C%2520%2522authn_class_ref%2522%253A%2520%2522password%2522%257D");
		String url = "https://"+idp+"/authorization?response_type=code&redirect_uri=https%3A%2F%2F"+host+"%3A"+port+"%2Fcallback&scope=openid&client_id=cncudWkub3BlbmlkY2xpZW50";
		System.out.println(url);
		Request request1 = new Request.Builder()
				.url(url)
				.get()
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();

		Response response1 = client.newCall(request1).execute();
		System.out.println(response1);

		//login2/2
		MediaType mediaType2 = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body2 = RequestBody.create(mediaType2, "username=admin&password=admin&state=%257B%2522query%2522%253A%2520%2522response_type%253Dcode%2526redirect_uri%253Dhttps%25253A%25252F%25252F"+host+"%25253A"+port+"%25252Fcallback%2526scope%253Dopenid%2526client_id%253DcncudWkub3BlbmlkY2xpZW50%2522%252C%2520%2522authn_class_ref%2522%253A%2520%2522password%2522%257D");
		url = "https://"+idp+"/verify";
		System.out.println(url);
		Request request2 = new Request.Builder()
				.url(url)
				.post(body2)
				.addHeader("Accept-Language", "it-IT,it;q=0.9,en-US;q=0.8,en;q=0.7")
				.addHeader("Accept-Encoding", "gzip, deflate, br")
				.addHeader("Referer", "https://"+idp+"/authorization?response_type=code&redirect_uri=https%3A%2F%2F"+host+"%3A"+port+"%2Fcallback&scope=openid&client_id=cncudWkub3BlbmlkY2xpZW50")
				.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Upgrade-Insecure-Requests", "1")
				.addHeader("Origin", "https://"+idp)
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();

		Response response2 = client.newCall(request2).execute();
		
		/*
		//check access
		Request request3 = new Request.Builder()
				.url("https://"+host+":"+port+"/user_management?api_server=https://localhost#/?_k=5w6gpn")
				.get()
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response3 = client.newCall(request3).execute();
		System.out.println(response3);
		*/
		String result = new Gson().toJson(client.cookieJar().loadForRequest(HttpUrl.get(new java.net.URL("https://"+host+":"+port))));
		return result;
	}

	void authorizeUserOnOSM(OkHttpClient client, String host, int port, String idp, String username, String project, String permissions) throws Exception{
		if(client == null) {
			client = getUnsafeOkHttpClient();
		}

		authenticate(client, host, port, idp);

		//create user
		MediaType mediaType4 = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body4 = RequestBody.create(mediaType4, "password=&user-domain=localhost&user-name="+username);
		Request request4 = new Request.Builder()
				.url("https://"+host+":"+port+"/user?api_server=https://localhost")
				.post(body4)
				.addHeader("X-Requested-With", "XMHttpRequest")
				.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Referer", "https://"+host+":"+port+"/user_management/index.html?api_server=https://localhost")
				.addHeader("Accept-Encoding", "Accept-Encoding")
				.addHeader("Accept-Language", "it-IT,it;q=0.9,en-US;q=0.8,en;q=0.7")
				.addHeader("Accept", "*/*")
				.addHeader("Origin", "https://"+host+":"+port)
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response4 = client.newCall(request4).execute();
		System.out.println(response4);

		//get current authorized users
		Request request5 = new Request.Builder()
				.url("https://"+host+":"+port+"/project?api_server=https://localhost#/?_k=5w6gpn")
				.get()
				.addHeader("content-type", "multipart/form-data")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response5 = client.newCall(request5).execute();
		String response5ToString = response5.body().string();
		System.out.println(response5ToString);

		//authorize user
		MediaType mediaType6 = MediaType.parse("application/x-www-form-urlencoded");

		String authorizationStringAdd = addAuthorizationJson(response5ToString, username, project, new String[] {permissions});
		System.out.println("authorizationStringAdd" + authorizationStringAdd);
		RequestBody body6 = RequestBody.create(mediaType6, authorizationStringAdd);
		Request request6 = new Request.Builder()
				.url("https://"+host+":"+port+"/project?api_server=https://localhost")
				.put(body6)
				.addHeader("X-Requested-With", "XMHttpRequest")
				.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Referer", "https://"+host+":"+port+"/user_management/index.html?api_server=https://localhost")
				.addHeader("Accept-Encoding", "Accept-Encoding")
				.addHeader("Accept-Language", "it-IT,it;q=0.9,en-US;q=0.8,en;q=0.7")
				.addHeader("Accept", "*/*")
				.addHeader("Origin", "https://"+host+":"+port)
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response6 = client.newCall(request6).execute();
		System.out.println(response6);

	}

	void deauthorizeUserOnOSM(OkHttpClient client, String host, int port, String idp, String username, String project) throws Exception{
		if(client == null) {
			client = getUnsafeOkHttpClient();
		}

		authenticate(client, host, port, idp);

		MediaType mediaType7 = MediaType.parse("application/x-www-form-urlencoded");

		//get current authorized users
		Request request5 = new Request.Builder()
				.url("https://"+host+":"+port+"/project?api_server=https://localhost#/?_k=5w6gpn")
				.get()
				.addHeader("content-type", "multipart/form-data")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response5 = client.newCall(request5).execute();
		String response5ToString = response5.body().string();
		System.out.println(response5ToString);

		String authorizationStringRemove = removeAuthorizationJson(response5ToString, username, project);
		System.out.println("authorizationStringRemove" + authorizationStringRemove);

		RequestBody body7 = RequestBody.create(mediaType7, authorizationStringRemove);

		Request request7 = new Request.Builder()
				.url("https://"+host+":"+port+"/project?api_server=https://localhost")
				.put(body7)
				.addHeader("X-Requested-With", "XMHttpRequest")
				.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Referer", "https://"+host+":"+port+"/user_management/index.html?api_server=https://localhost")
				.addHeader("Accept-Encoding", "Accept-Encoding")
				.addHeader("Accept-Language", "it-IT,it;q=0.9,en-US;q=0.8,en;q=0.7")
				.addHeader("Accept", "*/*")
				.addHeader("Origin", "https://"+host+":"+port)
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response7 = client.newCall(request7).execute();
		System.out.println(response7);

		//delete user
		Request request8 = new Request.Builder()
				.url("https://"+host+":"+port+"/user/"+username+"/localhost?api_server=https%3A%2F%2Flocalhost")
				.delete(null)
				.addHeader("X-Requested-With", "XMHttpRequest")
				.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.addHeader("Referer", "https://"+host+":"+port+"/user_management/?api_server=https://localhost")
				.addHeader("Accept-Encoding", "Accept-Encoding")
				.addHeader("Accept-Language", "it-IT,it;q=0.9,en-US;q=0.8,en;q=0.7")
				.addHeader("Accept", "*/*")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Cookie", "mycookie=OSM4ever")
				.build();
		Response response8 = client.newCall(request8).execute();
		System.out.println(response8);	
	}
	
	void createOpenstackDatacenterAndAttachOnOSMr3(OkHttpClient client, String osmUrl, String osmTenant, String datacenterName, String datacenterURL, String datacenterTenant, String datacenterUsername, String datacenterPassword) throws Exception{
		System.out.println("osmUrl: "+osmUrl + "; datacenterName:"+datacenterName+"; datacenterURL: " + datacenterURL + "; datacenterUsername: " + datacenterUsername + "; datacenterPassword: "+ datacenterPassword);
		
		MediaType mediaType = MediaType.parse("application/json");
		
		Request requestTenant = new Request.Builder()
		  .url(osmUrl + "/openmano/tenants")
		  .get()
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseTenant = client.newCall(requestTenant).execute();
		if(!responseTenant.isSuccessful()) {
			throw new IOException("Unable to get tenant list");
		}
		
		//TODO "default" and "osm" are used respectively as project and tenant
		osmTenant = "osm";
		
		String tenantID = null;
		JSONObject jObject = new JSONObject(responseTenant.body().string());
		JSONArray jArray = jObject.getJSONArray("tenants");
		for(int i=0; i<jArray.length(); i++) {
			JSONObject jObjectElem = jArray.getJSONObject(i);
			if(jObjectElem.getString("name").equals(osmTenant)) {
				tenantID = jObjectElem.getString("uuid");
				break;
			}
		}
		
		if(tenantID == null) {
			throw new IOException("Unable to find tenant " + osmTenant);
		}
		
		RequestBody bodyCreate = RequestBody.create(mediaType, "{\n       \"datacenter\":{\n       \"name\": \""+datacenterName+"\",\n       \"type\": \"openstack\",\n       \"vim_url\": \""+datacenterURL+"\",\n       \"vim_url_admin\": \""+datacenterURL+"\",\n       \"description\": \""+datacenterName+"\"\n       }\n}\n       ");
		Request requestCreate = new Request.Builder()
		  .url(osmUrl + "/openmano/datacenters")
		  .post(bodyCreate)
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseCreate = client.newCall(requestCreate).execute();
		if(!responseCreate.isSuccessful()) {
			throw new IOException("Unable to create datacenter " + datacenterName);
		}

		RequestBody bodyAttach = RequestBody.create(mediaType, "{\n       \"datacenter\":{\n       \"name\": \""+datacenterName+"\",\n       \"vim_username\": \""+datacenterUsername+"\",\n       \"vim_password\": \""+datacenterPassword+"\",\n       \"vim_tenant_name\": \""+datacenterTenant+"\"\n       }\n}\n       ");
		Request requestAttach = new Request.Builder()
		  .url(osmUrl + "/openmano/"+tenantID+"/datacenters/" + datacenterName)
		  .post(bodyAttach)
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseAttach = client.newCall(requestAttach).execute();
		if(!responseAttach.isSuccessful()) {
			throw new IOException("Unable to attach datacenter " + datacenterName);
		}
		
	}
	void detachAndDeleteDatacenter(OkHttpClient client, String osmUrl, String osmTenant, String datacenterName, String datacenterTenant) throws Exception{
		System.out.println("osmUrl: "+osmUrl + "; datacenterName:"+datacenterName);
		
		MediaType mediaType = MediaType.parse("application/json");
		
		Request requestTenant = new Request.Builder()
		  .url(osmUrl + "/openmano/tenants")
		  .get()
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseTenant = client.newCall(requestTenant).execute();
		if(!responseTenant.isSuccessful()) {
			throw new IOException("Unable to get tenant list");
		}
		
		//TODO "default" and "osm" are used respectively as project and tenant
		osmTenant = "osm";
		
		String tenantID = null;
		JSONObject jObject = new JSONObject(responseTenant.body().string());
		JSONArray jArray = jObject.getJSONArray("tenants");
		for(int i=0; i<jArray.length(); i++) {
			JSONObject jObjectElem = jArray.getJSONObject(i);
			if(jObjectElem.getString("name").equals(osmTenant)) {
				tenantID = jObjectElem.getString("uuid");
				break;
			}
		}
		
		if(tenantID == null) {
			throw new IOException("Unable to find tenant " + osmTenant);
		}
		
		RequestBody bodyDetach = RequestBody.create(mediaType, "{\n       \"datacenter\":{\n       \"name\": \""+datacenterName+"\"\n       }\n}\n       ");
		Request requestDetach = new Request.Builder()
		  .url(osmUrl + "/openmano/"+tenantID+"/datacenters/" + datacenterName)
		  .delete(bodyDetach)
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseDetach = client.newCall(requestDetach).execute();
		if(!responseDetach.isSuccessful()) {
			throw new IOException("Unable to detach datacenter " + datacenterName);
		}
		
		RequestBody bodyDelete = RequestBody.create(mediaType, "{\n       \"datacenter\":{\n       \"name\": \""+datacenterName+"\"\n       }\n}\n       ");
		Request requestDelete = new Request.Builder()
		  .url(osmUrl + "/openmano/datacenters/" + datacenterName)
		  .delete(bodyDelete)
		  .addHeader("Authorization", "Basic YWRtaW46YWRtaW4=")
		  .addHeader("Accept", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response responseDelete = client.newCall(requestDelete).execute();
		if(!responseDelete.isSuccessful()) {
			throw new IOException("Unable to delete datacenter " + datacenterName);
		}
		
	}

	static OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
						}

						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return new java.security.cert.X509Certificate[]{};
						}
					}
			};

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			builder.cookieJar(new CookieJar() {
				private List<Cookie> storage = new ArrayList<Cookie>();

				public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
					storage.addAll(cookies);
				}

				public List<Cookie> loadForRequest(HttpUrl url) {

					// Remove expired Cookies
					//storage.removeIf(cookie -> cookie.expiresAt() < System.currentTimeMillis());

					// Only return matching Cookies
					return storage.stream().filter(cookie -> cookie.matches(url)).collect(Collectors.toList());
				}
			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		OkHttpClient client = UtilOSMr3.getUnsafeOkHttpClient();

		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String idp = args[2];
		String username = args[3];
		String project = args[4];
		String permissions = args[5];
		
		UtilOSMr3 driver = new UtilOSMr3();
		//driver.authorizeUserOnOSM(client, host, port, idp, username, project, permissions);
		driver.deauthorizeUserOnOSM(client, host, port, idp, username, project);
	}


}
