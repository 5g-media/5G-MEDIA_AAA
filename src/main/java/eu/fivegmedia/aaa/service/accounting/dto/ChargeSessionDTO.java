package eu.fivegmedia.aaa.service.accounting.dto;

import java.io.Serializable;

public class ChargeSessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public Long id;
	public String qualityProfile;
	public String optimisation;
	public Float resourceCost;

}
