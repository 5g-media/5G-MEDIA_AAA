package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;

import org.springframework.core.env.Environment;

import eu.fivegmedia.aaa.domain.ResourceUser;
import eu.fivegmedia.aaa.repository.ResourceUserLoginRepository;

public interface IDriverUser {
	
	public void setApplicationProperties(Environment env);
	
	public String authenticateAndGetToken(ResourceUser resourceUserDB) throws IOException;
	
	public void createUser(ResourceUser resourceUser, ResourceUser resourceUserDB, long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository);
	
	public void deleteUser(ResourceUser resourceUser, ResourceUser resourceUserDB, Long resourceUserLoginId, ResourceUserLoginRepository resourceUserLoginRepository);
	
}
