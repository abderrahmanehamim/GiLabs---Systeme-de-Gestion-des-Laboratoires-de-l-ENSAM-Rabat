package com.Glab.LaboIntelligent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Glab.LaboIntelligent.models.Articles;

public interface ArticlesRepository extends JpaRepository<Articles, Long>{

}