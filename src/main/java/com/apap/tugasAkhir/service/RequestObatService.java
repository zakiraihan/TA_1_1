package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugasAkhir.model.RequestObatModel;

public interface RequestObatService {
	Optional<RequestObatModel> findById(Long id);
	List<RequestObatModel> getByIdPasien(Long id);
	List<RequestObatModel> findAll();
}
