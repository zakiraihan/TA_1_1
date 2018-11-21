package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugasAkhir.model.JadwalJagaModel;

public interface JadwalJagaService {
	JadwalJagaModel addJadwalJaga(JadwalJagaModel jadwalJaga);
	List<JadwalJagaModel> viewAll();
	void updateJadwalJaga(JadwalJagaModel jadwalJaga);
	Optional<JadwalJagaModel> findById(Long id);
}
