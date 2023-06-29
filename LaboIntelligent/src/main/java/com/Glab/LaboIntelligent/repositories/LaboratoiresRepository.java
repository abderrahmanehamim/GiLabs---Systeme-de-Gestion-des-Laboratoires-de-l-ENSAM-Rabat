package com.Glab.LaboIntelligent.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Laboratoire;

public interface LaboratoiresRepository extends JpaRepository<Laboratoire, Long>{
 //   List<Laboratoires> saveAll(List<Laboratoires> laboratories);

	@Query("select l from Laboratoire l where l.Code like :x")
	public Laboratoire getLabByCode(@Param("x") String code );
	  

}
