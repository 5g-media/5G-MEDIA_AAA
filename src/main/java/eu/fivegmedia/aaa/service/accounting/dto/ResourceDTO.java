package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class ResourceDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public String resource_id;
	public String resource_type;
	public String resource_user;
	public String resource_tenant;
	public String resource_conf;
}