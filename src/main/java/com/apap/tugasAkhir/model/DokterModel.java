package com.apap.tugasAkhir.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="dokter")
public class DokterModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@OneToMany(mappedBy = "dokterJaga", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
	private List<JadwalJagaModel> listJadwalJaga;
	
	@OneToMany(mappedBy = "dokterPeriksa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PemeriksaanModel> listPemeriksaan;

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

	public List<JadwalJagaModel> getListJadwalJaga() {
		return listJadwalJaga;
	}

	public void setListJadwalJaga(List<JadwalJagaModel> listJadwalJaga) {
		this.listJadwalJaga = listJadwalJaga;
	}

	public List<PemeriksaanModel> getListPemeriksaan() {
		return listPemeriksaan;
	}

	public void setListPemeriksaan(List<PemeriksaanModel> listPemeriksaan) {
		this.listPemeriksaan = listPemeriksaan;
	}	
	
	
}
