package com.Glab.LaboIntelligent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.services.ArticlesService;

@Controller

public class Articlescontroller {
	private ArticlesService articleService;

	public Articlescontroller(ArticlesService articleService) {
		super();
		this.articleService = articleService;
	}
	
	
	@GetMapping("/articles")
	public String listArticle(Model model) {	
		model.addAttribute("articles", articleService.getAllArticles());
		return "AllArticles";
	}
	
	
	@GetMapping("/addarticles")
	public String createArticleForm(Model model) {
/*	Articles article = new Articles();
	article.setArticlenom("article");
	article.setCategorie("cat");
	article.setDescription("descp");
	article.setDocumentation("documentation");
	article.setDomaine("Domain");
	article.setIdarticles(123456L);
	article.setLaboratoires(null);
	article.setQuantite(22);
	article.setReference(null);
	article.setVisuel(null);
	model.addAttribute("article", article);
	*/
	//	model.addAttribute("articles", articleService.getAllArticles());
     return "Addarticle";
	}
	
	
	@PostMapping("/articles")
	public String saveArticle(@ModelAttribute("article") Articles article) {
		articleService.saveArticle(article);
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
