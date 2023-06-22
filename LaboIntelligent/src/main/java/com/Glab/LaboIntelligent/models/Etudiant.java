package com.Glab.LaboIntelligent.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Etudiant  implements Serializable {
	@Id
	private Long matricule;
	private String nom;
	private String prenom;
	private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Codedpt")
	private Departments Dep;
	
	private String semestre;

	public Etudiant(Long matricule, String nom, String prenom, String email, Departments dep, String semestre) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		Dep = dep;
		this.semestre = semestre;
	}

	public Etudiant() {
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

	public Departments getDep() {
		return Dep;
	}

	/*
	 * public void setDep(Departement dep) { Dep.setCodedpt = dep; }
	 */
	
	public void setDep(Departments dep) {
		Dep = dep;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getSemestre() {
		return semestre;
	}

	/*
	 * public void setSemestre(Semestre semestre) { this.semestre.codeSemestre =
	 * semestre; }
	 */
	public void setSemestreString(String sem) {
		this.semestre =sem;

	}

	@Override
	public String toString() {
		return "" + matricule ;
	}
	 //teste l'égalité, sert pour le HashSet
    public final boolean equals(Object e){
	if(! (e instanceof Etudiant))
	    return false;
	return (((Etudiant)e).matricule == matricule);
    }
    //return le hashCode, sert pour le HashSet
    
    @Override
    public int hashCode() {
        //return Long.valueOf(getMatricule()).hashCode();
    	 return Long.hashCode(matricule);
    }
}
