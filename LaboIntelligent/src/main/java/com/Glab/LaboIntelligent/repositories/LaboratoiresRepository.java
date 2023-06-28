package com.Glab.LaboIntelligent.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Laboratoire;

public interface LaboratoiresRepository extends JpaRepository<Laboratoire, Long>{
 //   List<Laboratoires> saveAll(List<Laboratoires> laboratories);

	/*
	 List<Laboratoire> findByDepartments(Departement departments);
	  */

}
