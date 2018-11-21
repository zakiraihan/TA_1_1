package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasAkhir.model.JadwalJagaModel;
import com.apap.tugasAkhir.repository.JadwalJagaDb;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
	@Autowired
	private JadwalJagaDb jadwalJagaDb;
	
	@Override
	public JadwalJagaModel addJadwalJaga(JadwalJagaModel jadwalJaga) {
		return jadwalJagaDb.save(jadwalJaga);
	}

	@Override
	public List<JadwalJagaModel> viewAll() {
		return jadwalJagaDb.findAll();
	}

	@Override
	public void updateJadwalJaga(JadwalJagaModel jadwalJaga) {
		
	}

	@Override
	public Optional<JadwalJagaModel> findById(Long id) {
		return jadwalJagaDb.findById(id);
	}
	
}
