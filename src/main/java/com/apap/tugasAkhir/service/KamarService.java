package com.apap.tugasAkhir.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugasAkhir.model.KamarModel;
import com.apap.tugasAkhir.model.PaviliunModel;

public interface KamarService {

	List<KamarModel> getAllKamar();

	Optional<KamarModel> getKamarById(Long idKamar);

	KamarModel addKamar(KamarModel kamar);

	List<KamarModel> getActiveKamarFromPaviliun(PaviliunModel paviliun);

	List<KamarModel> getActiveKamar();

	List<KamarModel> getInactiveKamar();

}
