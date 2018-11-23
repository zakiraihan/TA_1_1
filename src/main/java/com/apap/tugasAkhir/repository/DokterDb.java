package com.apap.tugasAkhir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugasAkhir.model.DokterModel;

public interface DokterDb extends JpaRepository<DokterModel, Long>{
	//Optional<DokterModel> findById(Long idDokter);
}
