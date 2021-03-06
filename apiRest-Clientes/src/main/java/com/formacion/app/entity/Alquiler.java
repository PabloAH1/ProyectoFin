package com.formacion.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="alquileres")
public class Alquiler {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente cliente;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="coche_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Coche coche;
	
	@Column(name="fechaIni")
	@Temporal(TemporalType.DATE)
	private Date fechaIni;
	
	@Column(name="fechaFin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
	