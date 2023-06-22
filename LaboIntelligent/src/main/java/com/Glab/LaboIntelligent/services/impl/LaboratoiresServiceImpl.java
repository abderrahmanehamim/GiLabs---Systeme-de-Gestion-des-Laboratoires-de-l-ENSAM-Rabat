package com.Glab.LaboIntelligent.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Laboratoires;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.LaboratoireService;
@Service
public class LaboratoiresServiceImpl implements LaboratoireService{


	
	private LaboratoiresRepository laboratoiresRepository;
	
	@Override
	public List<Laboratoires> getAllLaboratoire() {
		
		
		return this.laboratoiresRepository.findAll();
	}

	@Override
	public Laboratoires getLaboratoiresById(Long idlabs) {
		
		return laboratoiresRepository.findById(idlabs).get() ;
	}

}
