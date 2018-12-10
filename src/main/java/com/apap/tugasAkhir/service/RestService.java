package com.apap.tugasAkhir.service;

import com.apap.tugasAkhir.rest.DokterAllRestMapModel;
import com.apap.tugasAkhir.rest.DokterAllRestModel;
import com.apap.tugasAkhir.rest.DokterRestModel;
import com.apap.tugasAkhir.rest.ObatAllRestModel;
import com.apap.tugasAkhir.rest.PatienAllRestModel;
import com.apap.tugasAkhir.rest.PatienModel;
import com.apap.tugasAkhir.rest.PatienRestModel;
import com.apap.tugasAkhir.rest.PermintaanObatModel;
import com.apap.tugasAkhir.rest.TanggalModel;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RestService {

	PatienRestModel getPasienById(long idPasien);

	DokterRestModel getDokterById(long idDokter);

	PatienAllRestModel getAllPasien();

	DokterAllRestModel getAllDokter();
	
	ObatAllRestModel getAllObat();

	PatienAllRestModel getListOfPasien(String[] listOfString);
	
	String postPasienStatus(PatienModel pasien);

	DokterAllRestMapModel getListOfDokter(String[] listOfString);

	String postObat(PermintaanObatModel permintaan) throws JsonProcessingException;
}
