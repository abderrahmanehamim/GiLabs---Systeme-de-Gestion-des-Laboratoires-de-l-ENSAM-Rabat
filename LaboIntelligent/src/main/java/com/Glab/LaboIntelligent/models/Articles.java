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

public class Articles {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String articlenom;
	private String domaine;
	private String reference;
	private String categorie;
	private Integer quantite;
	private String visuel;
	private String Documentation;
	private String description ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "Code")
	    private Laboratoire laboratoire;
	 

	public Articles() {
		super();
	}

	public Articles(Long id, String articlenom, String domaine, String reference, String categorie, Integer quantite,
			String visuel, String documentation, String description, Laboratoire laboratoire) {
		super();
		this.id = id;
		this.articlenom = articlenom;
		this.domaine = domaine;
		this.reference = reference;
		this.categorie = categorie;
		this.quantite = quantite;
		this.visuel = visuel;
		Documentation = documentation;
		this.description = description;
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
	public String getArticlenom() {
		return articlenom;
	}

	/**
	 * @param articlenom the articlenom to set
	 */
	public void setArticlenom(String articlenom) {
		this.articlenom = articlenom;
	}

	/**
	 * @return the domaine
	 */
	public String getDomaine() {
		return domaine;
	}

	/**
	 * @param domaine the domaine to set
	 */
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the quantite
	 */
	public Integer getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the visuel
	 */
	public String getVisuel() {
		return visuel;
	}

	/**
	 * @param visuel the visuel to set
	 */
	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return Documentation;
	}

	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation(String documentation) {
		Documentation = documentation;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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