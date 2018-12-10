package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class ObatRestModel implements Serializable{
private Integer status;
	
	private String message;
	
	private ObatModel result;

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

	public ObatModel getResult() {
		return result;
	}

	public void setResult(ObatModel result) {
		this.result = result;
	}
	
	
}
