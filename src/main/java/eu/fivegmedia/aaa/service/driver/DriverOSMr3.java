package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import eu.fivegmedia.aaa.domain.Resource;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DriverOSMr3 implements IDriverUser, IDriverDatacenter{
    private final Logger log = LoggerFactory.getLogger(DriverOSMr3.class);

    protected Environment applicationProperties;
	
	public void setApplicationProperties(Environment applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
    
    //there is not any need to authenticate on OSM3 as admin/admin is hardcoded
    public String authenticateAsUserAndGetToken(ResourceUser resourceUserDB) {
		return authenticateAndGetToken(resourceUserDB);
	}
    public String authenticateAndGetToken(ResourceUser resourceUser) {
		return null;
	}
    
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {

		//create user
		log.info("Trying to add a new user with id " + resourceUserLoginId);
		ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
		
		Resource resource = resourceUserDB.getResource();
		
		Properties properties = UtilString.getPropertiesFromString(resource.getAuthConf());
		
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		String createPamUserURL = "CONF_MANAGER_URL/create-pam-user?username=USERNAME&password=PASSWORD";
		createPamUserURL = createPamUserURL.replace("CONF_MANAGER_URL", properties.getProperty("confManagerURL"));
		createPamUserURL = createPamUserURL.replace("USERNAME", resourceUserLogin.getUsername());
		createPamUserURL = createPamUserURL.replace("PASSWORD", resourceUserLogin.getPassword());
		Request requestCreatePamUser = new Request.Builder().url(createPamUserURL).get().build();
		try(Response response = client.newCall(requestCreatePamUser).execute()){
			int responseCode = response.code();
			log.info("creating PAM user returned code " + responseCode);
		}catch(IOException e) {
			throw new RuntimeException("Unable to create PAM user " + resourceUserLogin.getUsername(), e);
		}

		UtilOSMr3 driverHttp = new UtilOSMr3();
		try{
			URL url = new URL(resource.getUrl());
			driverHttp.authorizeUserOnOSM(client, url.getHost(), url.getPort(), properties.getProperty("idp"), resourceUserLogin.getUsername(), resourceUser.getTenant(), resourceUser.getPermissions());
		}catch(Exception e) {
			throw new RuntimeException("Unable to authorize on OSM user " + resourceUserLogin.getUsername(), e);
		}
    		
    }
	
	
	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {

        log.info("Trying to remove a user with id " + resourceUserLoginId);
		ResourceUserLogin resourceUserLogin = resourceUserDB.getResourceUserLogin();
		
		Resource resource = resourceUserDB.getResource();
		
		Properties properties = UtilString.getPropertiesFromString(resource.getAuthConf());
		
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		
		UtilOSMr3 driverHttp = new UtilOSMr3();
		try{
			URL url = new URL(resource.getUrl());
			driverHttp.deauthorizeUserOnOSM(client, url.getHost(), url.getPort(), properties.getProperty("idp"), resourceUserLogin.getUsername(), resourceUser.getTenant());
		}catch(Exception e) {
			throw new RuntimeException("Unable to deauthorize on OSM user " + resourceUserLogin.getUsername());
		}
		
		String removePamUserURL = "CONF_MANAGER_URL/delete-pam-user?username=USERNAME";
		removePamUserURL = removePamUserURL.replace("CONF_MANAGER_URL", properties.getProperty("confManagerURL"));
		removePamUserURL = removePamUserURL.replace("USERNAME", resourceUserLogin.getUsername());
		Request requestDeletePamUser = new Request.Builder().url(removePamUserURL).get().build();
		try (Response response = client.newCall(requestDeletePamUser).execute()){
			int responseCode = response.code();
			log.info("deleting PAM user returned code " + responseCode);
		}catch(IOException e) {
			throw new RuntimeException("Unable to delete PAM user " + resourceUserLogin.getUsername());
		}
	}
	
	private static String getOsmURL(Resource resource) {
		Properties properties = UtilString.getPropertiesFromString(resource.getAuthConf());
		String osmUrl = "http://"+properties.getProperty("idp").replace("8009", "9090");
		
		return osmUrl;
	}
	
	public void createDatacenterAndAttach(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterURL, String datacenterTenant, String datacenterUsername, String datacenterPassword) {
		OkHttpClient client = new OkHttpClient();
		String osmUrl = getOsmURL(resourceUserDB.getResource());
		
		UtilOSMr3 driverHttp = new UtilOSMr3();
		try{
			driverHttp.createOpenstackDatacenterAndAttachOnOSMr3(client, osmUrl, osmTenant, datacenterName, datacenterURL, datacenterTenant, datacenterUsername, datacenterPassword);
		}catch(Exception e) {
			throw new RuntimeException("Unable to createOpenstackDatacenterAndAttachOnOSM", e);
		}
	}
	public void detachDatacenterAndDelete(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterTenant) {
		OkHttpClient client = UtilHttpClient.getUnsafeOkHttpClient();
		String osmUrl = getOsmURL(resourceUserDB.getResource());

		UtilOSMr3 driverHttp = new UtilOSMr3();
		try{
			driverHttp.detachAndDeleteDatacenter(client, osmUrl, osmTenant, datacenterName, datacenterTenant);
		}catch(Exception e) {
			throw new RuntimeException("Unable to detachAndDeleteDatacenter", e);
		}
	}


	
	
}
