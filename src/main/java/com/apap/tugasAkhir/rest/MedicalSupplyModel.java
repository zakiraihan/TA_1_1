package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class MedicalSupplyModel implements Serializable{
	
	private MedicineModel medicalSupply;
	
	private Long jumlah;

	public MedicineModel getMedicalSupply() {
		return medicalSupply;
	}

	public void setMedicalSupply(MedicineModel medicalSupply) {
		this.medicalSupply = medicalSupply;
	}

	public Long getJumlah() {
		return jumlah;
	}

	public void setJumlah(Long jumlah) {
		this.jumlah = jumlah;
	}
	
	
	
}
