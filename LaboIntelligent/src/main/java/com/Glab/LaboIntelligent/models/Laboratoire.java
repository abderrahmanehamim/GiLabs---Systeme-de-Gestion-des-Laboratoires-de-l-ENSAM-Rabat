package com.Glab.LaboIntelligent.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name="laboratoires")


public class Laboratoire {
	
	
	
	@Id
	private String Code ;
	private  String Laboname ;
	private String activite;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codedpt")
	private Departement Dep;
	
	@OneToMany(mappedBy = "laboratoire")
	private List<Articles> articles;
	
	public Laboratoire() {
	}

	public Laboratoire(long idlabs, String laboname, String code, String activite, Departement department,
			List<Articles> articles, List<String> travauxpratiques) {
		super();
	//	this.id = idlabs;
		Laboname = laboname;
		Code = code;
		this.activite = activite;
		this.Dep = department;
		this.articles = articles;
	}



	public String getLaboname() {
		return Laboname;
	}

	public void setLaboname(String laboname) {
		Laboname = laboname;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}



	/**
	 * @return the departments
	 */
	public Departement getDepartments() {
		return Dep;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(Departement department) {
		this.Dep = department;
	}

	/**
	 * @param travauxpratiques the travauxpratiques to set
	 */
	
	
}
