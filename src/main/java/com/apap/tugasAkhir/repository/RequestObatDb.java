package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.PemeriksaanModel;
import com.apap.tugasAkhir.model.RequestObatModel;

@Repository
public interface RequestObatDb extends JpaRepository<RequestObatModel, Long>{
	RequestObatModel findByPemeriksaan(PemeriksaanModel pemeriksaan);
	List<RequestObatModel> findByIdPasien(Long idPasien);
	List<RequestObatModel> findByNamaObat(String namaObat);
}
