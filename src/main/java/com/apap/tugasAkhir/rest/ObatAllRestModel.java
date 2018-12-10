package com.apap.tugasAkhir.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObatAllRestModel implements Serializable{
	
	private String message;
	
	private List<ObatModel> result;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ObatModel> getResult() {
		return result;
	}

	public void setResult(List<ObatModel> result) {
		this.result = result;
	}
}