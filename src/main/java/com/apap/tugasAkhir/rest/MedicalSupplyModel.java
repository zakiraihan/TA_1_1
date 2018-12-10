package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class MedicalSupplyModel implements Serializable{
	
	private MedicineModel medicalSupplies;
	
	private Long jumlah;

	public MedicineModel getMedicalSupplies() {
		return medicalSupplies;
	}

	public void setMedicalSupplies(MedicineModel medicalSupplies) {
		this.medicalSupplies = medicalSupplies;
	}

	public Long getJumlah() {
		return jumlah;
	}

	public void setJumlah(Long jumlah) {
		this.jumlah = jumlah;
	}
	
}
