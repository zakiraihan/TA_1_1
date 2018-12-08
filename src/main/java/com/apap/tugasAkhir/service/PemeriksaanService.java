package com.apap.tugasAkhir.service;

import java.util.List;

import com.apap.tugasAkhir.model.PemeriksaanModel;

public interface PemeriksaanService {
	PemeriksaanModel addPemeriksaan(PemeriksaanModel pemeriksaan);
	List<PemeriksaanModel> getPemeriksaanByIdPasien(Long idPasien);
	PemeriksaanModel getPemeriksaanByIdPemeriksaan(Long id);
}
