package com.apap.tugasAkhir.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pemeriksaan")
public class PemeriksaanModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "waktu", nullable = false)
	private Timestamp waktu;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "pemeriksaan", nullable = false)
	private String pemeriksaan;
	
	@NotNull
	@Column(name = "id_dokter", nullable = false)
	private Long idDokter;
	
	@NotNull
	@Column(name = "id_pasien", nullable = false)
	private Long idPasien;
	
	@OneToMany(mappedBy = "pemeriksaan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RequestObatModel> listObat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getWaktu() {
		return waktu;
	}

	public void setWaktu(Timestamp waktu) {
		this.waktu = waktu;
	}

	public String getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(String pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public Long getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(Long idDokter) {
		this.idDokter = idDokter;
	}

	public Long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(Long idPasien) {
		this.idPasien = idPasien;
	}

	public List<RequestObatModel> getListObat() {
		return listObat;
	}

	public void setListObat(List<RequestObatModel> listObat) {
		this.listObat = listObat;
	}

	
		
}
