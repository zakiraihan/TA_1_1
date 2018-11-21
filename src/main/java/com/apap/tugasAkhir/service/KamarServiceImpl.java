package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.repository.KamarDb;

@Service
@Transactional
public class KamarServiceImpl implements KamarService{
	@Autowired
	private KamarDb kamarDb;
	
	@Override
	public List<KamarModel> getAllKamar(){
		return kamarDb.findAll();
	}
	
	@Override
	public Optional<KamarModel> getKamarById(Long idKamar) {
		return kamarDb.findById(idKamar);
	}
}
