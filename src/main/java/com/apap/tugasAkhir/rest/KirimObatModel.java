package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class KirimObatModel implements Serializable{
	
	private ObatRequestedModel obat;
	
	private Long idPasien;

	public ObatRequestedModel getObat() {
		return obat;
	}

	public void setObat(ObatRequestedModel obat) {
		this.obat = obat;
	}

	public Long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(Long idPasien) {
		this.idPasien = idPasien;
	}
	
	
}
