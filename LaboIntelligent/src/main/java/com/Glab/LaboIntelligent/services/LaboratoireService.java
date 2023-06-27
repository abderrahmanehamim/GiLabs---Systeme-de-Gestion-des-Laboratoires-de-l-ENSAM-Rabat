package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Laboratoire;

public interface LaboratoireService {
	
	List<Laboratoire> getAllLaboratoire();
	
	Laboratoire getLaboratoiresById(Long idlabs);

	
    List<Laboratoire> saveAll(List<Laboratoire> laboratories);

	
}
