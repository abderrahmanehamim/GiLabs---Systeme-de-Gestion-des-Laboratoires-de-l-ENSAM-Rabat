package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Etudiant;

public interface EtudiantService {
	
	
	List<Etudiant> getAllEtudiant();

	

	Etudiant saveStudent(Etudiant student);
	
	Etudiant getStudentById(Long id);
	
     Etudiant updateStudent(Etudiant student);
	
	void deleteStudentById(Long id);
}
