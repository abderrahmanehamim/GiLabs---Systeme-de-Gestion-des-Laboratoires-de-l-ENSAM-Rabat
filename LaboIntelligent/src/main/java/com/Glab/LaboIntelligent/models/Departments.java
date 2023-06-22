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
public class Departments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long iddepart;
	
	private String DepartmentName;
	
	
	
    @OneToMany(mappedBy = "departments")
	private List<Laboratoires> laboratoire;
    
    
	public Departments() {
	}
	public Departments(long iddepart, String departmentName, List<Laboratoires> laboratoire) {
		super();
		this.iddepart = iddepart;
		DepartmentName = departmentName;
		this.laboratoire = laboratoire;
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
	public List<Laboratoires> getLaboratoire() {
		return laboratoire;
	}
	/**
	 * @param laboratoire the laboratoire to set
	 */
	public void setLaboratoire(List<Laboratoires> laboratoire) {
		this.laboratoire = laboratoire;
	}
	
	
}