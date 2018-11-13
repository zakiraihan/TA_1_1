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
	private Integer jumlah;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pemeriksaan", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PemeriksaanModel pemeriksaan;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PasienModel pasienObat;


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


	public Integer getJumlah() {
		return jumlah;
	}


	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}


	public PemeriksaanModel getPemeriksaan() {
		return pemeriksaan;
	}


	public void setPemeriksaan(PemeriksaanModel pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}


	public PasienModel getPasienObat() {
		return pasienObat;
	}


	public void setPasienObat(PasienModel pasienObat) {
		this.pasienObat = pasienObat;
	}
}
