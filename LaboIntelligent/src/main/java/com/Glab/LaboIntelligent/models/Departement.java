package com.Glab.LaboIntelligent.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Departments")
public class Departement {
	
	@Id
	private long iddepart;
	
	private String DepartmentName;
	
	@OneToMany(mappedBy = "Dep")
	private List<Laboratoire> laboratoires;
	
	@OneToMany(mappedBy = "Dep")
	private List<Etudiant> etudiants;
	
	public List<Laboratoire> getLaboratoires() {
		return laboratoires;
	}
	public void setLaboratoires(List<Laboratoire> laboratoires) {
		this.laboratoires = laboratoires;
	}
	public List<Professeur> getProfesseurs() {
		return professeurs;
	}
	public void setProfesseurs(List<Professeur> professeurs) {
		this.professeurs = professeurs;
	}
	@OneToMany(mappedBy = "Dep")
	private List<Professeur> professeurs;
    
	public Departement() {
	}
	public Departement(long iddepart, String departmentName, List<Laboratoire> laboratoires) {
		super();
		this.iddepart = iddepart;
		DepartmentName = departmentName;
		this.laboratoires = laboratoires;
	}
	/**
	 * @return the iddepart
	 */
	public long getIddepart() {
		return iddepart;
	}
	/**
	 * @param iddepart the iddepart to set
	 */
	public void setIddepart(long iddepart) {
		this.iddepart = iddepart;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return DepartmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	/**
	 * @return the laboratoire
	 */
	public List<Laboratoire> getLaboratoire() {
		return laboratoires;
	}
	/**
	 * @param laboratoire the laboratoire to set
	 */
	public void setLaboratoire(List<Laboratoire> laboratoires) {
		this.laboratoires = laboratoires;
	}
	
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
	/**
	 * @param laboratoire the laboratoire to set
	 */
	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
}