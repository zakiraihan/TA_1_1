package com.apap.tugasAkhir.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.PemeriksaanModel;
import com.apap.tugasAkhir.repository.PemeriksaanDb;

@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService{
	
	@Autowired
	private PemeriksaanDb pemeriksaanDb;
	
	@Override
	public PemeriksaanModel addPemeriksaan(PemeriksaanModel pemeriksaan) {
		return pemeriksaanDb.save(pemeriksaan);
	}
	

}
