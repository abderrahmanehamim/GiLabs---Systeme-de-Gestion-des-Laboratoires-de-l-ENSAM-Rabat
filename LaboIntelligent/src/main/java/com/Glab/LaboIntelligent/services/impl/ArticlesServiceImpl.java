package com.Glab.LaboIntelligent.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.services.ArticlesService;

import javassist.NotFoundException;
@Service
public class ArticlesServiceImpl implements ArticlesService {
	
	private ArticlesRepository articlesRepository;

	public ArticlesServiceImpl(ArticlesRepository articlesRepository) {
		super();
		this.articlesRepository = articlesRepository;
	}
	@Override
	public List<Articles> getAllArticles() {
		
		return articlesRepository.findAll();
	}
	@Override
	public Articles saveArticle(Articles article) {
		return articlesRepository.save(article);
	}
	@Override
	public Articles getArticleById(Long idarticles) {

		return articlesRepository.findById(idarticles).get();
	}
	@Override
	public Articles updateArticle(Articles article) {

		return articlesRepository.save(article);
	}

	@Override
	public void deleteArticleById(Long idarticles) {
	 /*   Articles article = articlesRepository.findById(idarticles)
	            .orElseThrow();
	    // Remove the article from the associated laboratoire
	    Laboratoire laboratoire = article.getLaboratoire();
	    if (laboratoire != null) {
	        laboratoire.getArticles().remove(article);
	    }

	    // Delete the article
	    articlesRepository.delete(article);*/
	}
		
	}


