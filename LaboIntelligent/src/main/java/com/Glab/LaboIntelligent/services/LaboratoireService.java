package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Laboratoires;

public interface LaboratoireService {
	
	List<Laboratoires> getAllLaboratoire();
	
	Laboratoires getLaboratoiresById(Long idlabs);

	
    List<Laboratoires> saveAll(List<Laboratoires> laboratories);

	
}
