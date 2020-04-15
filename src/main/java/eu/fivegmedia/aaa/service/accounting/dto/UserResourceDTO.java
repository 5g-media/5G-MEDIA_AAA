package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class UserResourceDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public String catalog_user;
	public String catalog_tenant;
	public ResourceDTO[] resources;
	
}
