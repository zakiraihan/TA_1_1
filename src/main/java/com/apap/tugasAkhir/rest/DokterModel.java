package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class DokterModel implements Serializable{
	private Long id;
	
	private String nama;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	
}
