package com.apap.tugasAkhir.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.DokterModel;
import com.apap.tugasAkhir.repository.DokterDb;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{
	@Autowired
	private DokterDb dokterDb; 
	@Override
	public List<DokterModel> viewAll() {
		return dokterDb.findAll();
	}

}
