package com.apap.tugasAkhir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.RequestPasienModel;

@Repository
public interface RequestPasienDb extends JpaRepository<RequestPasienModel, Long>{
	RequestPasienModel findByIdPasien(Long idPasien);
	List<RequestPasienModel> findByAssign(Integer assign);
}
