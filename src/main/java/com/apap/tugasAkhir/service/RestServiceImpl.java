package com.apap.tugasAkhir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasAkhir.rest.DokterAllRestModel;
import com.apap.tugasAkhir.rest.DokterRestModel;
import com.apap.tugasAkhir.rest.PatienAllRestModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.Setting;

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
	
}
