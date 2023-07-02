package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Laboratoire;

public interface LaboratoireService {
	
	List<Laboratoire> getAllLaboratoire();
	
	Laboratoire getLaboratoiresByCode(String code);

	
    List<Laboratoire> saveAll(List<Laboratoire> laboratories);


	
}
