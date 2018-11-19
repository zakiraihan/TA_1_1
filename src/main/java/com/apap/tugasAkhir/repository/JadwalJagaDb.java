package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.JadwalJagaModel;
import com.apap.tugasAkhir.model.PaviliunModel;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long>{
	List<JadwalJagaModel> findByIdDokter(Long idDokter);
	List<JadwalJagaModel> findByPaviliunJaga(PaviliunModel paviliun);
}
