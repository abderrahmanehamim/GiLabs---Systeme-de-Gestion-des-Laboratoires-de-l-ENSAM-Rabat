package com.Glab.LaboIntelligent.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Etudiant;



public interface EtudiantRepository extends JpaRepository<Etudiant ,Long>{
	@Query("select e from Etudiant e where e.email like :x")
	public List<Etudiant> chercheEtudiants(@Param("x") String eml );
	@Query("select e from Etudiant e where e.matricule like :x")
	public List<Etudiant> chercheEtudiantsbymat(@Param("x") Long mat );
	@Query("select e from Etudiant e where e.matricule like :x")
	public Etudiant FindById(@Param("x") Long mat );
	@Query("select e from Etudiant e where e.email like :email")
	public Etudiant chercherEtudiantByEmail(@Param("email") String email);

	@Query("select et from   Etudiant et  where et.Dep like :dpt")
	public List<Etudiant> listEtudiantparDep(@Param("dpt") Departement dpt);
	@Query("select et from   Etudiant et  where et.semestre like :sem")
	public List<Etudiant> listEtudiantparSem(@Param("sem") String sem);
	@Query("select et from   Etudiant et  where et.semestre like :sem")
	public List<Etudiant> listEtudiantparDepSem(@Param("sem") String sem);
	
	
	}
