package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Articles;

public interface ArticlesService {
	
	
List<Articles> getAllArticles();

Articles saveArticle(Articles article);

Articles getArticleById(Long idarticles);

Articles updateArticle(Articles article);

void deleteArticleById(Long idarticles) ;



}
