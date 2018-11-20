package com.apap.tugasAkhir.rest;

import java.io.Serializable;
import java.sql.Date;

public class PatienModel implements Serializable{
	
	private Long id;
	
	private String nama;
	
	private Date tanggalRujukan;
	
	private StatusModel statusPasien;
	
	private String poliRujukan;

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

	public Date getTanggalRujukan() {
		return tanggalRujukan;
	}

	public void setTanggalRujukan(Date tanggalRujukan) {
		this.tanggalRujukan = tanggalRujukan;
	}

	public StatusModel getStatusPasien() {
		return statusPasien;
	}

	public void setStatusPasien(StatusModel statusPasien) {
		this.statusPasien = statusPasien;
	}

	public String getPoliRujukan() {
		return poliRujukan;
	}

	public void setPoliRujukan(String poliRujukan) {
		this.poliRujukan = poliRujukan;
	}
	
	

}
