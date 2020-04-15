package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class AccVduConsumptionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Long vdu_session_id;
	public String consumption_type;
	public Long consumption_value;
	public Long timestamp_sec;
	
}
