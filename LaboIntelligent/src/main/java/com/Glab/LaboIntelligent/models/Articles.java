package com.Glab.LaboIntelligent.models;

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
	
	 @ManyToOne
	    @JoinColumn(name = "laboratoire_id")
	    private Laboratoires laboratoire;

 
public Articles() {
}
public Articles(Long idarticles, String articlenom, String domaine, String reference, String categorie,
		Integer quantite, String visuel, String documentation, String description, Laboratoires laboratoires) {
	super();
	this.id = idarticles;
	this.articlenom = articlenom;
	this.domaine = domaine;
	this.reference = reference;
	this.categorie = categorie;
	this.quantite = quantite;
	this.visuel = visuel;
	Documentation = documentation;
	this.description = description;
	this.laboratoire = laboratoires;
}
public Long getIdarticles() {
	return id;
}
public void setIdarticles(Long idarticles) {
	this.id = idarticles;
}
public String getArticlenom() {
	return articlenom;
}
public void setArticlenom(String articlenom) {
	this.articlenom = articlenom;
}
public String getDomaine() {
	return domaine;
}
public void setDomaine(String domaine) {
	this.domaine = domaine;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
public String getCategorie() {
	return categorie;
}
public void setCategorie(String categorie) {
	this.categorie = categorie;
}
public Integer getQuantite() {
	return quantite;
}
public void setQuantite(Integer quantite) {
	this.quantite = quantite;
}
public String getVisuel() {
	return visuel;
}
public void setVisuel(String visuel) {
	this.visuel = visuel;
}
public String getDocumentation() {
	return Documentation;
}
public void setDocumentation(String documentation) {
	Documentation = documentation;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Laboratoires getLaboratoires() {
	return laboratoire;
}
public void setLaboratoires(Laboratoires laboratoires) {
	this.laboratoire = laboratoires;
}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	