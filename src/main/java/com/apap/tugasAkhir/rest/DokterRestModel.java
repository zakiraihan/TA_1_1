package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class DokterRestModel implements Serializable{
private Integer status;
	
	private String message;
	
	private DokterModel result;

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

	public DokterModel getResult() {
		return result;
	}

	public void setResult(DokterModel result) {
		this.result = result;
	}
	
	
}
