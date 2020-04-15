package eu.fivegmedia.aaa.service.catalogue.dto;

import java.io.Serializable;

public class ResourceCredentialsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public String catalog_user;
	public String catalog_tenant;
	
	public String resource_type;
	public String auth_info;
	
}
