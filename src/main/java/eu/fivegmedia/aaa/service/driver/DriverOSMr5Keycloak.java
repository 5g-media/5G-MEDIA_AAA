package eu.fivegmedia.aaa.service.driver;

import java.util.Properties;

import org.keycloak.admin.client.Keycloak;

import eu.fivegmedia.aaa.domain.Resource;
import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;

public class DriverOSMr5Keycloak extends DriverOSMr5{
	
	//OIDC M2M login to get a token
	public String authenticateAndGetToken(ResourceUser resourceUserDB) {
		//return super.authenticateAndGetToken(resourceUserDB);
		Resource resource = resourceUserDB.getResource();
		
		String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
        
		Properties properties = UtilString.getPropertiesFromJson(resource.getAuthConf());
		String client = properties.getProperty("keycloak.client");
		String secret = properties.getProperty("keycloak.secret");

		Keycloak keycloakClient = null;
		try {
			keycloakClient = Keycloak.getInstance(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass, client, secret);
			String token = keycloakClient.tokenManager().getAccessToken().getToken();
			return token;
		}
		finally {
			if(keycloakClient != null) {
				keycloakClient.close();
			}
		}
	}
	
	//OIDC M2M user login to get a token
	public String authenticateAsUserAndGetToken(ResourceUser resourceUserDB) {
		//return super.authenticateAndGetToken(resourceUserDB);
		
		Resource resource = resourceUserDB.getResource();
		String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        
		Properties properties = UtilString.getPropertiesFromJson(resource.getAuthConf());
		String client = properties.getProperty("keycloak.client");
		String secret = properties.getProperty("keycloak.secret");

		String username = resourceUserDB.getCatalogUser().getUser().getLogin();
		String password = resourceUserDB.getCatalogUser().getUser().getPassword();
		
		Keycloak keycloakClient = null;
		try {
			keycloakClient = Keycloak.getInstance(keycloakUrl, keycloakRealm, username, password, client, secret);
			String token = keycloakClient.tokenManager().getAccessToken().getToken();
			return token;
		}
		finally {
			if(keycloakClient != null) {
				keycloakClient.close();
			}
		}
		
	}
	
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository){
		//1: create user on OSM 
		super.createUser(resourceUser, resourceUserDB, resourceUserLoginId, resourceUserLoginRepository);
		
		ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
		Resource resource = resourceUserDB.getResource();
		
        String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
		
		Keycloak keycloakClient = UtilKeycloak.getAdminKeycloakClient(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
		String username = resourceUserDB.getCatalogUser().getUser().getLogin();
		String password = resourceUserDB.getCatalogUser().getUser().getPassword();
		
		//2: create user on Keycloak DB - NO MORE NEEDED
		//UtilKeycloak.createKeycloakUserIfNotExists(keycloakClient, keycloakRealm, username, password);
		
		//3: assign role to Keycloak user
		String role = resourceUserLogin.getUsername();
		Properties properties = UtilString.getPropertiesFromJson(resource.getAuthConf());
		String client = properties.getProperty("keycloak.client");
		try{
			UtilKeycloak.addRoleOnUserForClient(keycloakClient, keycloakRealm, role, username, client);
		}
		finally {
			if(keycloakClient != null) {
				keycloakClient.close();
			}
		}
	}
	
	

	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository){
		//1: delete user from OSM
		super.deleteUser(resourceUser, resourceUserDB, resourceUserLoginId, resourceUserLoginRepository);
		
		ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
		Resource resource = resourceUserDB.getResource();
		
		
        String keycloakUrl = applicationProperties.getProperty("keycloak.url");
        String keycloakRealm = applicationProperties.getProperty("keycloak.realm");
        String keycloakAdminUser = applicationProperties.getProperty("keycloak.adminUser");
        String keycloakAdminPass = applicationProperties.getProperty("keycloak.adminPass");
		
		Keycloak keycloakClient = UtilKeycloak.getAdminKeycloakClient(keycloakUrl, keycloakRealm, keycloakAdminUser, keycloakAdminPass);
		String username = resourceUserDB.getCatalogUser().getUser().getLogin();

		String role = resourceUserLogin.getUsername();
		Properties properties = UtilString.getPropertiesFromJson(resource.getAuthConf());
		String client = properties.getProperty("keycloak.client");
		
		//2: delete role from Keycloak user
		try{
			UtilKeycloak.removeRoleOnUserForClient(keycloakClient, keycloakRealm, role, username, client);
		}
		finally {
			if(keycloakClient != null) {
				keycloakClient.close();
			}
		}
		
		//3: delete user from Keycloak - NO MORE NEEDED
		//UtilKeycloak.deleteKeycloakUserIfExists(keycloakClient, keycloakRealm, username);
	}

}
