package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class ObatRequestedModel implements Serializable{
	
	private String nama;
	
	private Integer jumlah;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
	
	
}
