package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.RequestObatModel;
import com.apap.tugasAkhir.repository.RequestObatDb;

@Service
@Transactional
public class RequestObatServiceImpl implements RequestObatService{
	@Autowired
	private RequestObatDb requestObatDb;
	
	@Override
	public Optional<RequestObatModel> findById(Long id) {
		return requestObatDb.findById(id);
	}

	@Override
	public List<RequestObatModel> getByIdPasien(Long id) {
		// TODO Auto-generated method stub
		return requestObatDb.findByIdPasien(id);
	}

	@Override
	public List<RequestObatModel> findAll() {
		// TODO Auto-generated method stub
		return requestObatDb.findAll();
	}
}
