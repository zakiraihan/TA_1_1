package com.apap.tugasAkhir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasAkhir.rest.DokterAllRestMapModel;
import com.apap.tugasAkhir.rest.DokterAllRestModel;
import com.apap.tugasAkhir.rest.DokterRestModel;
import com.apap.tugasAkhir.rest.ObatAllRestModel;
import com.apap.tugasAkhir.rest.PatienAllRestModel;
import com.apap.tugasAkhir.rest.PatienModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.Setting;
import com.apap.tugasAkhir.rest.TanggalModel;

@Service
@Transactional
public class RestServiceImpl implements RestService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public PatienRestModel getPasienById(long idPasien) {
		String pathPasien = Setting.getPasienByIdUrl + Long.toString(idPasien);
		PatienRestModel patienIdResponse = restTemplate.getForObject(pathPasien, PatienRestModel.class);
		return patienIdResponse;
	}
	
	@Override
	public DokterRestModel getDokterById(long idDokter) {
		String pathDokter = Setting.getDokterByIdUrl + Long.toString(idDokter);
		DokterRestModel dokterIdResponse = restTemplate.getForObject(pathDokter, DokterRestModel.class);
		return dokterIdResponse;
	}
	
	@Override
	public ObatAllRestModel getAllObat() {
		return restTemplate.getForObject(Setting.getAllObatUrl, ObatAllRestModel.class);
	}
	
	@Override
	public PatienAllRestModel getAllPasien() {
		return restTemplate.getForObject(Setting.getAllPasienUrl, PatienAllRestModel.class);
	}
	
	@Override
	public DokterAllRestModel getAllDokter() {
		return restTemplate.getForObject(Setting.getAllDokterUrl, DokterAllRestModel.class);
	}
	
	@Override
	public PatienAllRestModel getListOfPasien(String[] listOfString) {
		String pathPasien = Setting.getPasienByListIdUrl;
		String listId = listOfString[0];
		for (int i = 1; i < listOfString.length; i++) {
			listId += "," + listOfString[i];
		}
		String path = pathPasien + listId + "&resultType=Map";
		return restTemplate.getForObject(path, PatienAllRestModel.class);
	}
	
	@Override
	public DokterAllRestMapModel getListOfDokter(String[] listOfString) {
		String pathPasien = Setting.getDokterByListIdUrl;
		String listId = listOfString[0];
		for (int i = 1; i < listOfString.length; i++) {
			listId += "," + listOfString[i];
		}
		String path = pathPasien + listId + "&resultType=Map";
		return restTemplate.getForObject(path, DokterAllRestMapModel.class);
	}
	
	@Override
	public String postPasienStatus(PatienModel pasien) {
		RestTemplate template = new RestTemplate();
	     HttpEntity<PatienModel> requestEntity= new HttpEntity<PatienModel>(pasien);
	     System.out.println(requestEntity.toString());
	    String response = "";
	     try{
	        ResponseEntity<String> responseEntity = template.exchange(Setting.postPasienStatusUrl, HttpMethod.POST, requestEntity,  String.class);
	        response = responseEntity.getBody();
	    }
	    catch(Exception e){
	        response = e.getMessage();
	    }
	    return response;
	}
	
	@Override
	public String getJadwalDokter(TanggalModel tanggal) {
		RestTemplate template = new RestTemplate();
	    HttpEntity<TanggalModel> requestEntity= new HttpEntity<TanggalModel>(tanggal);
	    System.out.println(requestEntity.getBody().toString());
	    String response = "";
	     try{
	        ResponseEntity<String> responseEntity = template.exchange(Setting.getAllIUnAssignedDokterUrl, HttpMethod.GET, requestEntity,  String.class);
	        response = responseEntity.getBody();
	    }
	    catch(Exception e){
	        response = e.getMessage();
	    }
	    return response;
	}
	
}
