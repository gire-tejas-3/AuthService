package com.microservices.dto;

public class PolicyDto {
	private String policyName;
	private String status;
	private String type;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PolicyDto(String policyName, String status, String type) {
		super();
		this.policyName = policyName;
		this.status = status;
		this.type = type;
	}

	public PolicyDto() {
		super();
	}

}