package com.Glab.LaboIntelligent.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Professeur  implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long matricule;
	private String nom;
	private String prenom;
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Codedpt")
	private Departement Dep;
	

	public Professeur(Long matricule, String nom, String prenom, String email, Departement dep) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		Dep = dep;
	}

	public Professeur() {
		super();

	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Departement getDep() {
		return Dep;
	}

	/*
	 * public void setDep(Departement dep) { Dep.setCodedpt = dep; }
	 */
	
	public void setDep(Departement dep) {
		Dep = dep;
	}

/*
	 * public void setSemestre(Semestre semestre) { this.semestre.codeSemestre =
	 * semestre; }
	 */
	public String toString() {
		return "" + matricule ;
	}
	 //teste l'égalité, sert pour le HashSet
    public final boolean equals(Object e){
	if(! (e instanceof Professeur))
	    return false;
	return (((Professeur)e).matricule == matricule);
    }
    //return le hashCode, sert pour le HashSet
    
    @Override
    public int hashCode() {
        //return Long.valueOf(getMatricule()).hashCode();
    	 return Long.hashCode(matricule);
    }
}
