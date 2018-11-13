package com.apap.tugasAkhir.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Date waktu;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "pemeriksaan", nullable = false)
	private String pemeriksaan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private DokterModel dokterPeriksa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PasienModel pasienPeriksa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWaktu() {
		return waktu;
	}

	public void setWaktu(Date waktu) {
		this.waktu = waktu;
	}

	public String getPemeriksaan() {
		return pemeriksaan;
	}

	public void setPemeriksaan(String pemeriksaan) {
		this.pemeriksaan = pemeriksaan;
	}

	public DokterModel getDokterPeriksa() {
		return dokterPeriksa;
	}

	public void setDokterPeriksa(DokterModel dokterPeriksa) {
		this.dokterPeriksa = dokterPeriksa;
	}

	public PasienModel getPasienPeriksa() {
		return pasienPeriksa;
	}

	public void setPasienPeriksa(PasienModel pasienPeriksa) {
		this.pasienPeriksa = pasienPeriksa;
	}	
	
	
}
