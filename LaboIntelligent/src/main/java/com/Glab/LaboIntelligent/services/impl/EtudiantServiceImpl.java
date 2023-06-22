package com.Glab.LaboIntelligent.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.services.EtudiantService;
@Service
public class EtudiantServiceImpl implements EtudiantService {

	
	private EtudiantRepository etudiantRepository;
	
	
	public EtudiantServiceImpl(EtudiantRepository etudiantRepository) {
		super();
		this.etudiantRepository = etudiantRepository;
	}


	@Override
	public List<Etudiant> getAllEtudiant() {
		
		return etudiantRepository.findAll();
	}


	@Override
	public Etudiant saveStudent(Etudiant student) {
		
		return etudiantRepository.save(student);
	}


	@Override
	public Etudiant getStudentById(Long id) {
		
		return etudiantRepository.findById(id).get();
	}


	@Override
	public Etudiant updateStudent(Etudiant student) {
		
		return etudiantRepository.save(student);
	}


	@Override
	public void deleteStudentById(Long id) {
	  
		etudiantRepository.deleteById(id);
		
	}

	
}
