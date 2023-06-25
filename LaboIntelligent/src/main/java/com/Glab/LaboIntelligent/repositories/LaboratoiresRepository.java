package com.Glab.LaboIntelligent.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Glab.LaboIntelligent.models.Laboratoires;

public interface LaboratoiresRepository extends JpaRepository<Laboratoires, Long>{
    List<Laboratoires> saveAll(List<Laboratoires> laboratories);

	
	  

}
