package com.Glab.LaboIntelligent.services.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.LaboratoireService;
@Service
public class LaboratoiresServiceImpl implements LaboratoireService{


	
	private LaboratoiresRepository laboratoiresRepository;
	private ArticlesRepository articlesRepository;
	
	@Override
	public List<Laboratoire> getAllLaboratoire() {
		
		
		return this.laboratoiresRepository.findAll();
	}

	@Override
	public Laboratoire getLaboratoiresById(Long idlabs) {
		
		return laboratoiresRepository.findById(idlabs).get() ;
	}

	@Override
	public List<Laboratoire> saveAll(List<Laboratoire> laboratories) {
		// TODO Auto-generated method stub
		return laboratoiresRepository.saveAll(laboratories);
	}
	
	public void deleteArticleById(Long idarticles) {
	    Articles article = articlesRepository.findById(idarticles).orElseThrow();

	    // Remove the article from the associated laboratoire
	    Laboratoire laboratoire = article.getLaboratoire();
	    if (laboratoire != null) {
	        laboratoire.getArticles().remove(article);
	    }

	    // Delete the article
	    articlesRepository.delete(article);
	}
	
}
