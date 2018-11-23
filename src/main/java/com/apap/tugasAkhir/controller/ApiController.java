package com.apap.tugasAkhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.RequestPasienModel;
import com.apap.tugasAkhir.service.KamarService;
import com.apap.tugasAkhir.service.RequestPasienService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private RequestPasienService requestPasienService;
	
	@Autowired
	private KamarService kamarService;
	
	@PostMapping(value = "/daftar-ranap")
	private String addRequestPasien(@RequestBody JsonNode req) {
		String resultString = req.get("idPasien").toString();
		System.out.println(resultString);
		RequestPasienModel newReq = new RequestPasienModel();
		newReq.setAssign(0);
		newReq.setIdPasien(Long.parseLong(resultString));
		requestPasienService.addRequestPasien(newReq);
		String returnMessage = "{\"status\" : 200, \"message\" : \"success\"}";
		return returnMessage;
	}
	
	@GetMapping(value = "/get-all-kamar")
	private String getAllActivePasien() {
		String sent = "{\"status\" : 200, \"message\" : \"success\", \"idPasien\" :[";
		List<KamarModel> listOfActiveKamar = kamarService.getActiveKamar();
		String listOfId = Long.toString(listOfActiveKamar.get(0).getIdPasien());
		for(int i = 1; i < listOfActiveKamar.size(); i++) {
			listOfId += "," + Long.toString(listOfActiveKamar.get(i).getIdPasien());
		}
		sent += listOfId + "]}";
		return sent;
	}

}
