package eu.fivegmedia.aaa.service.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.RoleScopeResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class UtilKeycloak {
	private static final String ADMIN_CLIENT = "admin-cli";

	public static Keycloak getAdminKeycloakClient(String url, String realm, String adminUsername, String adminPassword) {
		Keycloak keycloakClient = Keycloak.getInstance(url, realm, adminUsername, adminPassword, ADMIN_CLIENT);
	    return keycloakClient;
	}
	
	public static void createKeycloakUserIfNotExists(Keycloak keycloakClient, String realm, String username, String password) {
		RealmResource realmResource = keycloakClient.realm(realm);
		
		boolean userAlreadyExists = false;
		for(UserRepresentation myUserRepresentation : realmResource.users().list()) {
			if(myUserRepresentation.getUsername().equals(username)){
				userAlreadyExists = true;
				break;
			}
		}
		
		if(!userAlreadyExists) {
			UserRepresentation userRepresentationNew = new UserRepresentation();
			userRepresentationNew.setUsername(username);
			userRepresentationNew.setEnabled(true);
			realmResource.users().create(userRepresentationNew);
			
			for(UserRepresentation myUserRepresentation : realmResource.users().list()) {
				if(myUserRepresentation.getUsername().equals(username)){
					UserResource myUserResource = realmResource.users().get(myUserRepresentation.getId());
					CredentialRepresentation newCredential = new CredentialRepresentation();
					newCredential.setValue(password);
					newCredential.setType(CredentialRepresentation.PASSWORD);
					newCredential.setTemporary(false);
					myUserResource.resetPassword(newCredential);
					myUserRepresentation.getRequiredActions().remove("UPDATE_PASSWORD");
					myUserResource.update(myUserRepresentation);
				}
			}
		}
	}
	
	public static void deleteKeycloakUserIfExists(Keycloak keycloakClient, String realm, String username) {
		RealmResource realmResource = keycloakClient.realm(realm);

		String userToRemove = null;
		for(UserRepresentation myUserRepresentation : realmResource.users().list()) {
			if(myUserRepresentation.getUsername().equals(username)) {
				userToRemove = myUserRepresentation.getId();
				break;
			}
		}
		if(userToRemove != null) {
			realmResource.users().delete(userToRemove);
		}
	}
	
	public static void setAttributeOnUser(Keycloak keycloakClient, String realm, String oidcuser, String attributeName, String attributeValue, boolean isAdd) {
		RealmResource realmResource = keycloakClient.realm(realm);
		
		//get user
		String userToFind = oidcuser;
		String userID = null;
		UserRepresentation userRepresentationFound = null;
		for(UserRepresentation userRepresentation : realmResource.users().list()) {
			if(userToFind.equals(userRepresentation.getUsername())){
				userID = userRepresentation.getId();
				userRepresentationFound = userRepresentation;
				break;
			}
		}
		if(userID == null) {
			throw new RuntimeException("User not found " + oidcuser);
		}
		
		//get the client attributes
		UserResource userResource = realmResource.users().get(userID);
		
		//add/remove attributeValue to the list
		if(attributeName != null && attributeValue != null) {
			if(userRepresentationFound.getAttributes() == null) {
				userRepresentationFound.setAttributes(new HashMap<String, List<String>>());
			}
			if(userRepresentationFound.getAttributes().get(attributeName) == null) {
				userRepresentationFound.getAttributes().put(attributeName, new ArrayList<String>());
			}
			if(isAdd) {
				userRepresentationFound.getAttributes().get(attributeName).add(attributeValue);
			}
			else {
				userRepresentationFound.getAttributes().get(attributeName).remove(attributeValue);
			}
			userResource.update(userRepresentationFound);

		}
	}
	
	public static void addRoleOnUserForClient(Keycloak keycloakClient, String realm, String role, String oidcuser, String client) {
		RealmResource realmResource = keycloakClient.realm(realm);
	
		//get client
	    String clientToFind = client;
		String clientID = null;
		for(ClientRepresentation clientRepresentation : realmResource.clients().findAll()) {
			if(clientToFind.equals(clientRepresentation.getClientId())){
				clientID = clientRepresentation.getId();
			}
		}
		if(clientID == null) {
			throw new RuntimeException("Client not found " + client);
		}
		
		//get user
		String userToFind = oidcuser;
		String userID = null;
		UserRepresentation userRepresentationFound = null;
		for(UserRepresentation userRepresentation : realmResource.users().list()) {
			if(userToFind.equals(userRepresentation.getUsername())){
				userID = userRepresentation.getId();
				userRepresentationFound = userRepresentation;
				break;
			}
		}
		if(userID == null) {
			throw new RuntimeException("User not found " + oidcuser);
		}

		//get the client roles
		UserResource userResource = realmResource.users().get(userID);
		RoleMappingResource roleMappingResource = userResource.roles();
		RoleScopeResource roleScopeResource = roleMappingResource.clientLevel(clientID);
		
		//add role 

		//first check if role is already assigned
		boolean roleAssigned = false;
		for(RoleRepresentation existingRoleRepresentation : roleScopeResource.listEffective()) {
			if(existingRoleRepresentation.getName().equals(role)) {
				roleAssigned = true;
				break;
			}
		}
		
		if(!roleAssigned) {
			//if not assigned, check if the role is among availables and assign it
			for(RoleRepresentation availableRoleRepresentation : roleScopeResource.listAvailable()) {
				if(availableRoleRepresentation.getName().equals(role)) {
					roleAssigned = true;
					
					List<RoleRepresentation> newRoleRepresentationList = new ArrayList<RoleRepresentation>();
					newRoleRepresentationList.add(availableRoleRepresentation);
					roleScopeResource.add(newRoleRepresentationList);
					userResource.update(userRepresentationFound);
					
					break;
				}
			}
			
			//if yet not assigned, then the role is not into the available list and...
			if(!roleAssigned) {
				
				//... must be created ...
				RolesResource clientRoles = keycloakClient.realm(realm).clients().get(clientID).roles();
				try {
					clientRoles.get(role).toRepresentation();
				}catch(Exception e) {
					RoleRepresentation roleRepresentation = new RoleRepresentation();
			        roleRepresentation.setName(role);
			        roleRepresentation.setClientRole(true);
			        clientRoles.create(roleRepresentation);
				}
				
				//... then assigned
		        for(RoleRepresentation availableRoleRepresentation : roleScopeResource.listAvailable()) {
					if(availableRoleRepresentation.getName().equals(role)) {
						
						List<RoleRepresentation> newRoleRepresentationList = new ArrayList<RoleRepresentation>();
						newRoleRepresentationList.add(availableRoleRepresentation);
						roleScopeResource.add(newRoleRepresentationList);
						userResource.update(userRepresentationFound);
						
						break;
					}
				}
			}
		}
	}
	
	public static void removeRoleOnUserForClient(Keycloak keycloakClient, String realm, String role, String oidcuser, String client) {
		RealmResource realmResource = keycloakClient.realm(realm);
		
		//get client
	    String clientToFind = client;
		String clientID = null;
		for(ClientRepresentation clientRepresentation : realmResource.clients().findAll()) {
			if(clientToFind.equals(clientRepresentation.getClientId())){
				clientID = clientRepresentation.getId();
			}
		}
		if(clientID == null) {
			throw new RuntimeException("Client not found " + client);
		}
		
		//get user
		String userToFind = oidcuser;
		String userID = null;
		UserRepresentation userRepresentationFound = null;
		for(UserRepresentation userRepresentation : realmResource.users().list()) {
			if(userToFind.equals(userRepresentation.getUsername())){
				userID = userRepresentation.getId();
				userRepresentationFound = userRepresentation;
				break;
			}
		}
		if(userID == null) {
			throw new RuntimeException("User not found " + oidcuser);
		}

		//get the client roles
		UserResource userResource = realmResource.users().get(userID);
		RoleMappingResource roleMappingResource = userResource.roles();
		RoleScopeResource roleScopeResource = roleMappingResource.clientLevel(clientID);
		
		for(RoleRepresentation existingRoleRepresentation : roleScopeResource.listEffective()) {
			if(existingRoleRepresentation.getName().equals(role)) {
				List<RoleRepresentation> roleRepresentationToBeRemoved = new ArrayList<RoleRepresentation>();
				roleRepresentationToBeRemoved.add(existingRoleRepresentation);
				roleScopeResource.remove(roleRepresentationToBeRemoved);
				break;
			}
		}
		
	}

	public static void removeRoleForClient(Keycloak keycloakClient, String realm, String role, String client) {
		RealmResource realmResource = keycloakClient.realm(realm);
		
		//get client
	    String clientToFind = client;
		String clientID = null;
		for(ClientRepresentation clientRepresentation : realmResource.clients().findAll()) {
			if(clientToFind.equals(clientRepresentation.getClientId())){
				clientID = clientRepresentation.getId();
			}
		}
		if(clientID == null) {
			throw new RuntimeException("Client not found " + client);
		}
		
		RolesResource clientRoles = keycloakClient.realm(realm).clients().get(clientID).roles();
		try{
			clientRoles.deleteRole(role);
		}catch(Exception e) {}

	}
}
