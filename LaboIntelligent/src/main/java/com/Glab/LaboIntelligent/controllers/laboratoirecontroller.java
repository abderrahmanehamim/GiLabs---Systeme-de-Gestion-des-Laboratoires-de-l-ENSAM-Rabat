package com.Glab.LaboIntelligent.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.ArticlesService;
import com.Glab.LaboIntelligent.services.DepartmentService;
import com.Glab.LaboIntelligent.services.LaboratoireService;


@Controller
public class laboratoirecontroller {
	
	private ArticlesService articleservice;
	private LaboratoireService laboratoireservice ;
	
	
	@Autowired
	private LaboratoiresRepository laboratoiresRepository;
	
	public String lisLaboratoires(Model model) {
		
		model.addAttribute("laboratoires", laboratoireservice.getAllLaboratoire());
		
		
		
		
		return "laboratoires";
		
		
	}
	
	@GetMapping("laboratoire/getbyID/{id}")
	public Laboratoire getById(@PathVariable long id) {
		
		
		return this.laboratoireservice.getLaboratoiresById(id);
	}
	
	 @PostMapping("/initialize")
	    public ResponseEntity<String> initializeLaboratoires() {
		// Create a list of laboratories for Department 1
		 Departement department1 = DepartmentService.getDepartmentById(1L);
		 Departement department2 = DepartmentService.getDepartmentById(2L);
		 Departement department3 = DepartmentService.getDepartmentById(3L);
		 Departement department4 = DepartmentService.getDepartmentById(4L);
		 Departement department5 = DepartmentService.getDepartmentById(5L);
		 Departement department6 = DepartmentService.getDepartmentById(6L);
		 Departement department7 = DepartmentService.getDepartmentById(7L);

		
		 return ResponseEntity.ok("Laboratoires initialized successfully.");
	 }
	 


}
