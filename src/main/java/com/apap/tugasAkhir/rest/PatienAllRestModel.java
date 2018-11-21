package com.apap.tugasAkhir.rest;

import java.io.Serializable;
import java.util.List;

public class PatienAllRestModel implements Serializable{
	
	private Integer status;
	
	private String message;
	
	private List<PatienModel> result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PatienModel> getResult() {
		return result;
	}

	public void setResult(List<PatienModel> result) {
		this.result = result;
	}
	
	
	
}
