package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.PaviliunModel;

@Repository
public interface PaviliunDb extends JpaRepository<PaviliunModel, Long>{
	PaviliunModel findByNamaPaviliun(String namaPaviliun);
	List<PaviliunModel> findByStatus(Integer status);
	
}
