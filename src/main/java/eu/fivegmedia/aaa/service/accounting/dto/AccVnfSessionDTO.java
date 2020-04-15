package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class AccVnfSessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Long ns_session_id;
	public String vnf_name;
	public String vnf_id;
	public Long timestamp_sec;
	
}
