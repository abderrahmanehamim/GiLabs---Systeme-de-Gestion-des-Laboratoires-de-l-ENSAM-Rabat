package com.Glab.LaboIntelligent.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Professeur;



public interface ProfesseurRepository extends JpaRepository<Professeur ,Long>{
	
	@Query("select e from Professeur e where e.email like :email")
	public Professeur chercherProfesseurByEmail(@Param("email") String email);

	}
