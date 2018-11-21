package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.PemeriksaanModel;

@Repository
public interface PemeriksaanDb extends JpaRepository<PemeriksaanModel, Long>{
	List<PemeriksaanModel> findByIdDokter(Long idDokter);
	List<PemeriksaanModel> findByIdPasien(Long idPasien);
}
