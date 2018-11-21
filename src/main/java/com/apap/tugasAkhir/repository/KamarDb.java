package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.PaviliunModel;

@Repository
public interface KamarDb extends JpaRepository<KamarModel, Long> {
	List<KamarModel> findByPaviliunKamar(PaviliunModel paviliun);
	KamarModel findByIdPasien(Long idPasien);
}
