package com.apap.tugasAkhir.rest;

import java.io.Serializable;
import java.util.List;

public class PermintaanObatModel implements Serializable{
	
	private List<MedicalSupplyModel> listPermintaanMedicalSupplies;
	
	private Long idPasien;

	public List<MedicalSupplyModel> getListPermintaanMedicalSupplies() {
		return listPermintaanMedicalSupplies;
	}

	public void setListPermintaanMedicalSupplies(List<MedicalSupplyModel> listPermintaanMedicalSupplies) {
		this.listPermintaanMedicalSupplies = listPermintaanMedicalSupplies;
	}

	public Long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(Long idPasien) {
		this.idPasien = idPasien;
	}
	
	
	
}
