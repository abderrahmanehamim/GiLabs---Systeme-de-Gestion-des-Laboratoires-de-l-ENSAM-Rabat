package com.Glab.LaboIntelligent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Etudiant;

public interface DepartmentRepository extends JpaRepository<Departement, Long> {

	
}
