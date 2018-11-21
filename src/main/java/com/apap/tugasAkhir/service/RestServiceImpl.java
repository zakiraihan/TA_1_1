package com.apap.tugasAkhir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
