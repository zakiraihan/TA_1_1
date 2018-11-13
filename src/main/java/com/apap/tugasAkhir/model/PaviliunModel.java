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
@Table(name="paviliun")
public class PaviliunModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama_paviliun", nullable = false)
	private String namaPaviliun;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "tipe_pasien", nullable = false)
	private String tipePasien;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private Integer status;

	@OneToMany(mappedBy = "paviliunKamar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<KamarModel> listKamar;
	
	@OneToMany(mappedBy = "paviliunJaga", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<JadwalJagaModel> listJadwalJaga;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaPaviliun() {
		return namaPaviliun;
	}

	public void setNamaPaviliun(String namaPaviliun) {
		this.namaPaviliun = namaPaviliun;
	}

	public String getTipePasien() {
		return tipePasien;
	}

	public void setTipePasien(String tipePasien) {
		this.tipePasien = tipePasien;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
