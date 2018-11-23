package com.apap.tugasAkhir.service;

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
}
