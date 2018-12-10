package com.apap.tugasAkhir.rest;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DokterAllRestMapModel {
	private Integer status;
	
	private String message;
	
	private HashMap<Long, DokterModel> resultMap;

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

	public HashMap<Long, DokterModel> getResult() {
		return resultMap;
	}

	public void setResult(HashMap<Long, DokterModel> resultMap) {
		this.resultMap = resultMap;
	}
	
	@SuppressWarnings("unchecked")
    @JsonProperty("result")
    private void unpackNested(JsonNode result) {
		try {
			String resultString = result.toString();
			ObjectMapper mapper = new ObjectMapper();
			HashMap<Long, DokterModel> hashMap = new HashMap<Long, DokterModel>();
			hashMap = mapper.readValue(resultString, new TypeReference<HashMap<Long, DokterModel>>(){});
			this.resultMap = hashMap;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
