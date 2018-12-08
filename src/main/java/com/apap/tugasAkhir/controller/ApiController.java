package com.apap.tugasAkhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.RequestObatModel;
import com.apap.tugasAkhir.model.RequestPasienModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.Setting;
import com.apap.tugasAkhir.rest.StatusModel;
import com.apap.tugasAkhir.service.KamarService;
import com.apap.tugasAkhir.service.RequestObatService;
import com.apap.tugasAkhir.service.RequestPasienService;
import com.apap.tugasAkhir.service.RestService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private RequestPasienService requestPasienService;
	
	@Autowired
	private RequestObatService requestObatService;
	
	@Autowired
	private KamarService kamarService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestService restService;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@PostMapping(value = "/daftar-ranap")
	private String addRequestPasien(@RequestBody JsonNode req) {
		String returnMessage = "";
		if (req.get("idPasien") !=  null) {
			Long resultString = Long.parseLong(req.get("idPasien").toString());
			System.out.println(resultString);
			RequestPasienModel inSystem = requestPasienService.getReqByIdPasien(resultString);
			
			if (inSystem == null) {
				RequestPasienModel newReq = new RequestPasienModel();
				newReq.setAssign(0);
				newReq.setIdPasien(resultString);
				requestPasienService.addRequestPasien(newReq);
				PatienRestModel patienIdResponse = restService.getPasienById(resultString);
				if (patienIdResponse.getStatus() == 200) {
					System.out.println(patienIdResponse.getResult().getNama());
					StatusModel status = new StatusModel();
					status.setId((long)4);
					status.setJenis("Mendaftar di Rawat Inap");
					patienIdResponse.getResult().setStatusPasien(status);
					String result = restService.postPasienStatus(patienIdResponse.getResult());
					System.out.println(result);
				}
				returnMessage = "{\"status\" : 200, \"message\" : \"success\"}";
			}
			
			else if (inSystem.getAssign() == 1) {
				returnMessage = "{\"status\" : 500, \"message\" : \"pasien sudah berada didalam kamar\"}";
			}
			
			else if (inSystem.getAssign() == 0) {
				returnMessage = "{\"status\" : 500, \"message\" : \"pasien sudah berada mendaftar di rawat inap\"}";
			}
			
			else if (inSystem.getAssign() == 2) {
				inSystem.setAssign(0);
				inSystem.setIdPasien(resultString);
				requestPasienService.addRequestPasien(inSystem);
				PatienRestModel patienIdResponse = restService.getPasienById(resultString);
				if (patienIdResponse.getStatus() == 200) {
					System.out.println(patienIdResponse.getResult().getNama());
					StatusModel status = new StatusModel();
					status.setId((long)4);
					status.setJenis("Mendaftar di Rawat Inap");
					patienIdResponse.getResult().setStatusPasien(status);
					String result = restService.postPasienStatus(patienIdResponse.getResult());
					System.out.println(result);
				}
				returnMessage = "{\"status\" : 200, \"message\" : \"success\"}";
			}
		}
		else {
			returnMessage = "{\"status\" : 500, \"message\" : \"format masukan salah\"}";
		}
		
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
