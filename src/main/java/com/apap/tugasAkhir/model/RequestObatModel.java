package com.apap.tugasAkhir.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="request_obat")
public class RequestObatModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama_obat", nullable = false)
	private String namaObat;
	
	@NotNull
	@Column(name = "jumlah", nullable = false)
	private Long jumlah;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PemeriksaanModel pemeriksaan;
	
	@NotNull
	@Column(name = "id_pasien", nullable = false)
	private Long idPasien;
	
	@NotNull
	@Column(name = "status")
	private Integer statusObat;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNamaObat() {
		return namaObat;
	}


	public void setNamaObat(String namaObat) {
		this.namaObat = namaObat;
	}


	public Long getJumlah() {
		return jumlah;
	}


	public void setJumlah(Long jumlah) {
		this.jumlah = jumlah;
	}


	public PemeriksaanModel getPemeriksaan() {
		return pemeriksaan;
	}


	public void setPemeriksaan(PemeriksaanModel pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}


	public Long getIdPasien() {
		return idPasien;
	}


	public void setIdPasien(Long idPasien) {
		this.idPasien = idPasien;
	}


	public Integer getStatusObat() {
		return statusObat;
	}


	public void setStatusObat(Integer statusObat) {
		this.statusObat = statusObat;
	}
	
}
