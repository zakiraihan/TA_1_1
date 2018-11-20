package com.apap.tugasAkhir.rest;

import java.io.Serializable;
import java.util.List;

public class DokterAllRestModel implements Serializable{
	
	private String message;
	
	private List<DokterModel> result;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<DokterModel> getResult() {
		return result;
	}

	public void setResult(List<DokterModel> result) {
		this.result = result;
	}
}
