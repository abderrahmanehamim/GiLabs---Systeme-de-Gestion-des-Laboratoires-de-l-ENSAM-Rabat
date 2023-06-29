package com.Glab.LaboIntelligent.controllers;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Laboratoire;

import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.ArticlesService;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.impl.ArticlesServiceImpl;

import java.io.File;


@Controller

public class Articlescontroller {
	@Autowired
	private ArticlesServiceImpl articleService;
@Autowired
	private ArticlesRepository articlesRepository;
@Autowired
private LaboratoiresRepository laboratoiresRepository;
	public Articlescontroller() {

		super();
	}
	
	
	@GetMapping("/articles")
	public String listArticle(Model model) {	
		model.addAttribute("articles", articleService.getAllArticles());
		return "AllArticles";
	}
	
	
	@GetMapping("/addarticles")
	public String createArticleForm(Model model) {

		model.addAttribute("labs", laboratoiresRepository.findAll());
     return "Addarticle";
	}
	/*
	 * String nom
file img
String domaine
String desc
String catg
String lab
String doc
String ref
Long qte
	 */
	  @PostMapping("/addarticle")
	    public String saveArticle(Model model, @RequestParam("nom") String nom,@RequestParam("domaine") String domaine,@RequestParam("desc") String desc,
	    		@RequestParam("catg") String catg,@RequestParam("lab") String lab,@RequestParam("doc") String doc,@RequestParam("ref") String ref,
	                              @RequestParam("img") MultipartFile file) throws IOException {
	     Articles article = new Articles();
		  // Save the file

	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            String filePath = "gilab\\LaboIntelligent\\src\\main\\resources\\static\\articleimg" + fileName; // Update the path to your images folder
	            File dest = new File(filePath);
	            file.transferTo(dest);
	            article.setVisuel(fileName); // Set the file name in the model object
	        }
article.setArticlenom(nom);
article.setCategorie(catg);
article.setDescription(desc);
article.setDocumentation(doc);
article.setDomaine(domaine);
article.setLaboratoires(laboratoiresRepository.getLabByCode(lab));
	        // Save the article
	        articlesRepository.save(article);
	        return "redirect:/articles";
	    }
		


	

	
	@GetMapping("/articles/edit/{id}")
	public String editArticlesForm(@PathVariable Long id, Model model) {
		model.addAttribute("article", articleService.getArticleById(id));
		return "edit_article";
	}
    @PostMapping("/articles/{id}")
    public String updateArticle(@PathVariable Long id,
    		@ModelAttribute("article") Articles article,
    		 Model model) {
    	//get article from database by id 
    	Articles existingArticle = articleService.getArticleById(id);
    	existingArticle.setIdarticles(id);
    	existingArticle.setArticlenom(article.getArticlenom());
    	existingArticle.setCategorie(article.getCategorie());
    	existingArticle.setDescription(article.getDescription());
    	existingArticle.setDocumentation(article.getDocumentation());
    	existingArticle.setDomaine(article.getDomaine());
    	existingArticle.setLaboratoires(article.getLaboratoires());
    	existingArticle.setQuantite(article.getQuantite());
    	existingArticle.setReference(article.getReference());
    	existingArticle.setVisuel(article.getVisuel());
    	
    	//save updated Article Object
    	articleService.updateArticle(existingArticle);
    	return "redirect:/articles";
    	
    }
    @GetMapping("/articles/{id}")
    public String deleteArticle(@PathVariable Long id) {
    	articleService.deleteArticleById(id);
    	return "redirect:/articles";
    	
    }
	
}
