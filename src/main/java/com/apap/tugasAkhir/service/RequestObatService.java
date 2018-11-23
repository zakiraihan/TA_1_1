package com.apap.tugasAkhir.service;

import java.util.Optional;

import com.apap.tugasAkhir.model.RequestObatModel;

public interface RequestObatService {
	Optional<RequestObatModel> findById(Long id);
}
