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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="request_pasien")
public class RequestPasienModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "assign", nullable = false)
	private Integer assign;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pasien", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private PasienModel pasienRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAssign() {
		return assign;
	}

	public void setAssign(Integer assign) {
		this.assign = assign;
	}

	public PasienModel getPasienRequest() {
		return pasienRequest;
	}

	public void setPasienRequest(PasienModel pasienRequest) {
		this.pasienRequest = pasienRequest;
	}
	
	
}
