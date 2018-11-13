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
@Table(name="pasien")
public class PasienModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status", referencedColumnName="id", nullable=false)
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    private StatusPasienModel statusPasien;
    
    @OneToMany(mappedBy="pasienPeriksa", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JsonIgnore
    private List<PemeriksaanModel> pemeriksaanPasien;
    
	@OneToOne(mappedBy = "pasienRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private RequestPasienModel requestPasien;
	
	@OneToMany(mappedBy="pasienObat", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JsonIgnore
    private List<RequestObatModel> obatPasien;
	
	@OneToOne(mappedBy = "pasienKamar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private KamarModel kamarPasien;

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

	public StatusPasienModel getStatusPasien() {
		return statusPasien;
	}

	public void setStatusPasien(StatusPasienModel statusPasien) {
		this.statusPasien = statusPasien;
	}

	public List<PemeriksaanModel> getPemeriksaanPasien() {
		return pemeriksaanPasien;
	}

	public void setPemeriksaanPasien(List<PemeriksaanModel> pemeriksaanPasien) {
		this.pemeriksaanPasien = pemeriksaanPasien;
	}

	public RequestPasienModel getRequestPasien() {
		return requestPasien;
	}

	public void setRequestPasien(RequestPasienModel requestPasien) {
		this.requestPasien = requestPasien;
	}

	public List<RequestObatModel> getObatPasien() {
		return obatPasien;
	}

	public void setObatPasien(List<RequestObatModel> obatPasien) {
		this.obatPasien = obatPasien;
	}

	public KamarModel getKamarPasien() {
		return kamarPasien;
	}

	public void setKamarPasien(KamarModel kamarPasien) {
		this.kamarPasien = kamarPasien;
	}
	
	
}
