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

public class DokterAllRestModel implements Serializable{
	
	private String message;
	
	private HashMap<Long, DokterModel> result;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<Long, DokterModel> getResult() {
		return result;
	}

	public void setResult(HashMap<Long, DokterModel> result) {
		this.result = result;
	}
	
	@SuppressWarnings("unchecked")
    @JsonProperty("result")
    private void unpackNested(JsonNode result) {
		try {
			String resultString = result.toString();
			ObjectMapper mapper = new ObjectMapper();
			HashMap<Long, DokterModel> hashMap = new HashMap<Long, DokterModel>();
			hashMap = mapper.readValue(resultString, new TypeReference<HashMap<Long, DokterModel>>(){});
			this.result = hashMap;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
