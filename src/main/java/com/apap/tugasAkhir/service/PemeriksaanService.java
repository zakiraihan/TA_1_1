package com.apap.tugasAkhir.service;

import java.util.List;

import com.apap.tugasAkhir.model.PemeriksaanModel;

public interface PemeriksaanService {
	PemeriksaanModel addPemeriksaan(PemeriksaanModel pemeriksaan);
	List<PemeriksaanModel> getPemeriksaanByIdPasien(Long id);
	PemeriksaanModel getPemeriksaanByIdPemeriksaan(Long id);
}
