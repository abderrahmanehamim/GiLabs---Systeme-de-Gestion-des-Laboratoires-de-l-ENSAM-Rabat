package com.Glab.LaboIntelligent.models;

import java.util.List;

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


public class Laboratoires {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private  String Laboname ;
	private String Code ;
	private String activite;
	
	@ManyToOne
	@JoinColumn(name="iddepart")
	private Departments departments;
	
	
	@OneToMany(mappedBy = "laboratoire")
	private List<Articles> articles;
	
	public Laboratoires() {
	}

	public Laboratoires(long idlabs, String laboname, String code, String activite, Departments departments,
			List<Articles> articles, List<String> travauxpratiques) {
		super();
		this.id = idlabs;
		Laboname = laboname;
		Code = code;
		this.activite = activite;
		this.departments = departments;
		this.articles = articles;
	}

	public long getIdlabs() {
		return id;
	}

	public void setIdlabs(long idlabs) {
		this.id = idlabs;
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
	public Departments getDepartments() {
		return departments;
	}

	/**
	 * @param departments the departments to set
	 */
	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	/**
	 * @param travauxpratiques the travauxpratiques to set
	 */
	
	
}
