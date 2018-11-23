package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugasAkhir.model.PaviliunModel;

public interface PaviliunService {

	List<PaviliunModel> getAllPaviliun();

	List<PaviliunModel> getActivePaviliun();
	
	public Optional<PaviliunModel> findPaviliundById(Long idPaviliun);

}
