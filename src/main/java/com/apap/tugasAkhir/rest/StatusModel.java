package com.apap.tugasAkhir.rest;

import java.io.Serializable;

public class StatusModel implements Serializable{

	private Long id;
	
	private String jenis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
}
