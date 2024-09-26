package com.microservices.dto;

import java.util.Date;

public class ClaimDto {
	private String policyName;
	private String status;
	private String type;
	private Date issuedDate;

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

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public ClaimDto(String policyName, String status, String type, Date issuedDate) {
		super();
		this.policyName = policyName;
		this.status = status;
		this.type = type;
		this.issuedDate = issuedDate;
	}

	public ClaimDto() {
		super();
	}

}
