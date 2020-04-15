package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class AccVduSessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Long vnf_session_id;
	public String nfvipop_id;
	public int flavorCpuCount;
	public int flavorMemoryMb;
	public int flavorDiskGb;
	public String vdu_id;
	public String vdu_type;
	public Long timestamp_sec;
	
}
