package com.Glab.LaboIntelligent.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.services.impl.ArticlesServiceImpl;


@Controller

public class Articlescontroller {
	@Autowired
	private ArticlesServiceImpl articleService;

	
	
public Articlescontroller() {


		super();
	}
	
	
	@GetMapping("/articles")
	public String listArticle(Model model) {	
		model.addAttribute("articles", articleService.getAllArticles());
	
		return "AllArticles";
	}
	

	
	
	@GetMapping("/addarticles")

	public String addNew(Model model) {
		Articles article = new Articles();
		model.addAttribute("article", article);

     return "Addarticle";
	}
	@PostMapping("/addarticles")
	public String articleForm(Articles article) {
		articleService.saveArticle(article);
     return "Addarticle";
	}
	
	
	  @PostMapping("/addarticles/uploadimg")
	    public String uploadimg(@ModelAttribute("article") Articles article,
	                              @RequestParam("file") MultipartFile file) throws IOException {
		    if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            String filePath = "gilab/LaboIntelligent/src/main/resources/static/articleimg/" + fileName; // Update the path to your images folder
	            File dest = new File(filePath);
	            file.transferTo(dest);
	            article.setVisuel(fileName); // Set the file name in the model object
	        }

	        // Save the article
	        articleService.saveArticle(article);
	        return "redirect:/Addarticles";

	

	    
	    }
		


	

	
	@GetMapping("/articles/edit/{id}")
	public String editArticlesForm(@PathVariable Long id, Model model) {
		model.addAttribute("article", articleService.getArticleById(id));
		return "edit_article";
	}
    @PostMapping("/articles/edit/{id}")
    public String updateArticle(@PathVariable Long id,
    		@ModelAttribute("article") Articles article,
    		 Model model) {
    	//get article from database by id 
    	Articles existingArticle = articleService.getArticleById(id);
    	existingArticle.setId(id);
    	existingArticle.setArticlenom(article.getArticlenom());
    	existingArticle.setCategorie(article.getCategorie());
    	existingArticle.setDescription(article.getDescription());
    	existingArticle.setDocumentation(article.getDocumentation());
    	existingArticle.setDomaine(article.getDomaine());
    	existingArticle.setLaboratoire(article.getLaboratoire());
    	existingArticle.setQuantite(article.getQuantite());
    	existingArticle.setReference(article.getReference());
    	existingArticle.setVisuel(article.getVisuel());
    	
    	//save updated Article Object
    	articleService.updateArticle(existingArticle);
    	return "redirect:/articles";
    	
    }
    @GetMapping("articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
    	articleService.deleteArticleById(id);
    	return "redirect:/articles";
    	
    }
	
}
