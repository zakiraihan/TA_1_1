package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.RequestPasienModel;
import com.apap.tugasAkhir.repository.RequestPasienDb;

@Service
@Transactional
public class RequestPasienServiceImpl implements RequestPasienService{

	@Autowired
	private RequestPasienDb requestPasienDb;
	
	@Override
	public List<RequestPasienModel> getAllRequest(){
		return requestPasienDb.findAll();
	}
	
	@Override
	public Optional<RequestPasienModel> getReqPasienById(Long idRequestPasien){
		return requestPasienDb.findById(idRequestPasien);
	}
	
	@Override 
	public RequestPasienModel addRequestPasien(RequestPasienModel requestPasien) {
		return requestPasienDb.save(requestPasien);
	}
	
	@Override
	public RequestPasienModel getReqByIdPasien(Long idPasien) {
		return requestPasienDb.findByIdPasien(idPasien);
	}
	
	@Override
	public List<RequestPasienModel> getPendingPasien(){
		return requestPasienDb.findByAssign(0);
	}
	
}
