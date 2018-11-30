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
	
//	@Autowired
//	private RestService restService;
	
	@PostMapping(value = "/daftar-ranap")
	private String addRequestPasien(@RequestBody JsonNode req) {
		String resultString = req.get("idPasien").toString();
		System.out.println(resultString);
		RequestPasienModel newReq = new RequestPasienModel();
		newReq.setAssign(0);
		newReq.setIdPasien(Long.parseLong(resultString));
		requestPasienService.addRequestPasien(newReq);
//		PatienRestModel patienIdResponse = restService.getPasienById(Long.parseLong(resultString));
//		if (patienIdResponse.getStatus() == 200) {
//			System.out.println(patienIdResponse.getResult().getNama());
//			StatusModel status = new StatusModel();
//			status.setId((long)4);
//			status.setJenis("Mendaftar di Rawat Inap");
//			patienIdResponse.getResult().setStatusPasien(status);
//			String result = restService.postPasienStatus(patienIdResponse.getResult());
//			System.out.println(result);
//		}
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
	
    /**
	 * TODO: Request obat ke Farmasi IS
	 */
	
    @PostMapping(value = "/obat/request/{requestObatId}")
	private String postRequest(@PathVariable ("requestObatId") Long requestObatId) throws Exception{
		String path = Setting.obatRequestUrl;
		RequestObatModel requestObat = requestObatService.findById(requestObatId).get();
		//DealerDetail detail = restTemplate.postForObject(path,requestObat, RequestObatModel.class);
		return "request-success";
	}
	
    /*
    @GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") Long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path,dealer, DealerDetail.class);
		return detail;
	}
	*/
}
