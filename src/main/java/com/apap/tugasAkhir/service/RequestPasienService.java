package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugasAkhir.model.RequestPasienModel;

public interface RequestPasienService {

	List<RequestPasienModel> getAllRequest();

	Optional<RequestPasienModel> getReqPasienById(Long idRequestPasien);

	RequestPasienModel addRequestPasien(RequestPasienModel requestPasien);

	RequestPasienModel getReqByIdPasien(Long idPasien);

}
