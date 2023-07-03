package com.Glab.LaboIntelligent.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Articles")

public class TP {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sujet;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "Code")
	    private Laboratoire laboratoire;
	 

	public TP() {
		super();
	}

	public TP(Long id, String sujet, Laboratoire laboratoire) {
		super();
		this.id = id;
		this.sujet = sujet;
		this.laboratoire = laboratoire;
	}

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the articlenom
	 */



	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	/**
	 * @return the laboratoire
	 */
	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	/**
	 * @param laboratoire the laboratoire to set
	 */
	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}

	
}