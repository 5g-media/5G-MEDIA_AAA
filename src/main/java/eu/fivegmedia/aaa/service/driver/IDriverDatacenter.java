package eu.fivegmedia.aaa.service.driver;

import java.io.IOException;

import eu.fivegmedia.aaa.domain.ResourceUser;

public interface IDriverDatacenter extends IDriverUser{

	public String authenticateAsUserAndGetToken(ResourceUser resourceUserDB) throws IOException;
	
	public void createDatacenterAndAttach(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterURL, String datacenterTenant, String datacenterUsername, String datacenterPassword) throws IOException;

	public void detachDatacenterAndDelete(ResourceUser resourceUserDB, String osmTenant, String datacenterName, String datacenterTenant) throws IOException;

}
