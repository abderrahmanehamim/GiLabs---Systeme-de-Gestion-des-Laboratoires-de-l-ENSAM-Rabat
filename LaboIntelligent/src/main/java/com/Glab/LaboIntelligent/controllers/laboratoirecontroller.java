package com.Glab.LaboIntelligent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Glab.LaboIntelligent.models.Laboratoires;
import com.Glab.LaboIntelligent.services.LaboratoireService;

@Controller
public class laboratoirecontroller {
	
	
	private LaboratoireService laboratoireservice ;
	
	
	
	
	public String lisLaboratoires(Model model) {
		
		model.addAttribute("laboratoires", laboratoireservice.getAllLaboratoire());
		
		
		
		
		return "laboratoires";
		
		
	}
	
	@GetMapping("laboratoire/getbyID/{id}")
	public Laboratoires getById(@PathVariable long id) {
		
		
		return this.laboratoireservice.getLaboratoiresById(id);
	}
	
	

}
