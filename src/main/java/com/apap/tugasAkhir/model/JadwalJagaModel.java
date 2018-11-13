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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jadwal_jaga")
public class JadwalJagaModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "status_dokter", nullable = false)
	private String statusDokter;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "daftar_hari_jaga", nullable = false)
	private String daftarHariJaga;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private DokterModel dokterJaga;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paviliun", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PaviliunModel paviliunJaga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusDokter() {
		return statusDokter;
	}

	public void setStatusDokter(String statusDokter) {
		this.statusDokter = statusDokter;
	}

	public String getDaftarHariJaga() {
		return daftarHariJaga;
	}

	public void setDaftarHariJaga(String daftarHariJaga) {
		this.daftarHariJaga = daftarHariJaga;
	}

	public DokterModel getDokterJaga() {
		return dokterJaga;
	}

	public void setDokterJaga(DokterModel dokterJaga) {
		this.dokterJaga = dokterJaga;
	}

	public PaviliunModel getPaviliunJaga() {
		return paviliunJaga;
	}

	public void setPaviliunJaga(PaviliunModel paviliunJaga) {
		this.paviliunJaga = paviliunJaga;
	}
	
	
}
