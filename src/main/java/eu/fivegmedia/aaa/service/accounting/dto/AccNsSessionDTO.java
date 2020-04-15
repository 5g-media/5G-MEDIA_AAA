package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class AccNsSessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public String catalog_user;
	public String catalog_tenant;
	public String mano_id;
	public String mano_user;
	public String mano_project;
	public String nfvipop_id;
	public String ns_id;
	public String ns_name;
	public Long timestamp_sec;
	public String quality_profile;
	public String optimisation;
	
}
