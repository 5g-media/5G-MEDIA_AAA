package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;
import org.springframework.core.env.Environment;

import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.domain.ResourceUserLogin;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;

/**
 * this class is not used
 */
public class DriverOpenStack4J implements IDriverUser{
	protected Environment applicationProperties;
	
	public void setApplicationProperties(Environment applicationProperties) {
		this.applicationProperties = applicationProperties;
	}
	
	//OSClient is used instead of this method, so I will leave it as is
	public String authenticateAndGetToken(ResourceUser resourceUser) {
		return null;
	}
	
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {
		try {

			//login
			String openstackUsername = resourceUserDB.getResource().getResourceAdminLogin().getUsername();
			String openstackPassword = resourceUserDB.getResource().getResourceAdminLogin().getPassword();
			String openstackIdentityURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
			
			OSClientV3 os3 = OSFactory.builderV3()
	                  .endpoint(openstackIdentityURL)
	                  .credentials(openstackUsername, openstackPassword, Identifier.byId("default")) 
	                  .scopeToDomain(Identifier.byId("default"))
	                  .authenticate();
			
			//find role
			String roleToFind = resourceUser.getPermissions();
			List<? extends Role> roleList = os3.identity().roles().getByName(roleToFind);
			if(roleList.isEmpty()) {
				throw new IOException("Unable to find role id for role " + roleToFind);
			}
			
			//create a project
			String project = resourceUser.getTenant();
			Project projectCreated = os3.identity().projects().create("default", project, "", true);
			
			//create user
			ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
			String user = resourceUserLogin.getUsername();
			String pass = resourceUserLogin.getPassword();
			User userCreated = os3.identity().users().create("default", user, pass, "", true);
				
			//authorize user
			os3.identity().roles().grantProjectUserRole(projectCreated.getId(), userCreated.getId(), roleList.get(0).getId());
					
		}catch(Exception e) {
			throw new RuntimeException("Error while setting up user/project permissions OpenStack: " + e);
		}
	}

	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository) {
		try {
			//login
			String openstackUsername = resourceUserDB.getResource().getResourceAdminLogin().getUsername();
			String openstackPassword = resourceUserDB.getResource().getResourceAdminLogin().getPassword();
			String openstackIdentityURL = new JSONObject(resourceUserDB.getResource().getAuthConf()).getString("keystone");
			
			
			OSClientV3 os3 = OSFactory.builderV3()
	                  .endpoint(openstackIdentityURL)
	                  .credentials(openstackUsername, openstackPassword, Identifier.byName("Default"))
	                  .authenticate();
	
			
			//delete user
			ResourceUserLogin resourceUserLogin = resourceUserLoginRepository.findById(resourceUserLoginId).get();
			String user = resourceUserLogin.getUsername();

			List<? extends User> userList = os3.identity().users().getByName(user);
			if(userList.size() > 0) {
				os3.identity().users().delete(userList.get(0).getId());
			}
			
			//delete project
			String project = resourceUser.getTenant();
			List<? extends Project> projectList = os3.identity().projects().getByName(project);
			if(projectList.size() > 0) {
				os3.identity().projects().delete(projectList.get(0).getId());
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Error while removing user/project permissions OpenStack: " + e);
		}
	}

	

}
