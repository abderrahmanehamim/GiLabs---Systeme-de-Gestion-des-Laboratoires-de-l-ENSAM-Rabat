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

	public Laboratoire(String code, String laboname, String activite, Departement dep, List<Articles> articles) {
		super();
		Code = code;
		Laboname = laboname;
		this.activite = activite;
		Dep = dep;
		this.articles = articles;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return Code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		Code = code;
	}

	/**
	 * @return the laboname
	 */
	public String getLaboname() {
		return Laboname;
	}

	/**
	 * @param laboname the laboname to set
	 */
	public void setLaboname(String laboname) {
		Laboname = laboname;
	}

	/**
	 * @return the activite
	 */
	public String getActivite() {
		return activite;
	}

	/**
	 * @param activite the activite to set
	 */
	public void setActivite(String activite) {
		this.activite = activite;
	}

	/**
	 * @return the dep
	 */
	public Departement getDep() {
		return Dep;
	}

	/**
	 * @param dep the dep to set
	 */
	public void setDep(Departement dep) {
		Dep = dep;
	}

	/**
	 * @return the articles
	 */
	public List<Articles> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}


	
	
}
