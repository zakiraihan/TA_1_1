package com.apap.tugasAkhir.service;

import com.apap.tugasAkhir.rest.PatienModel;
import com.apap.tugasAkhir.rest.PatienRestModel;

public interface RestService {

	PatienRestModel getPasienById(long idPasien);
}
