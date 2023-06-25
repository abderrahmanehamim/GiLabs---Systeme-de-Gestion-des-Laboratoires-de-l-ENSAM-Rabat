package com.Glab.LaboIntelligent.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Glab.LaboIntelligent.models.Departments;
import com.Glab.LaboIntelligent.models.Laboratoires;
import com.Glab.LaboIntelligent.services.DepartmentService;
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
	
	 @PostMapping("/initialize")
	    public ResponseEntity<String> initializeLaboratoires() {
		// Create a list of laboratories for Department 1
		 Departments department1 = DepartmentService.getDepartmentById(1L);
		 Departments department2 = DepartmentService.getDepartmentById(2L);
		 Departments department3 = DepartmentService.getDepartmentById(3L);
		 Departments department4 = DepartmentService.getDepartmentById(4L);
		 Departments department5 = DepartmentService.getDepartmentById(5L);
		 Departments department6 = DepartmentService.getDepartmentById(6L);
		 Departments department7 = DepartmentService.getDepartmentById(7L);
		 List<Laboratoires> departemenMecLab1 = new ArrayList<>();

		 Laboratoires Meclab1 = new Laboratoires();
		 Meclab1.setLaboname("Lab Mecanique");
		 Meclab1.setCode("Code 1");
		 Meclab1.setActivite("Activity 1");
		 Meclab1.setDepartments(department1);
		 // Set the department for the laboratory
		 Laboratoires Meclab2 = new Laboratoires();
		 Meclab2.setLaboname("Lab Mecanique");
		 Meclab2.setCode("Code 1");
		 Meclab2.setActivite("Activity 1");
		 Meclab2.setDepartments(department1);
		 departemenMecLab1.add(Meclab1);
		 departemenMecLab1.add(Meclab2);

		 List<Laboratoires> departmentMathLab1 = new ArrayList<>();

		 Laboratoires Mathlab1 = new Laboratoires();
		 Mathlab1.setLaboname("Lab Math ");
		 Mathlab1.setCode("Code 3");
		 Mathlab1.setActivite("Activity 3");
		 Mathlab1.setDepartments(department2); // Set the department for the laboratory
		 departmentMathLab1.add(Mathlab1);

		 List<Laboratoires> departmentInfoLab1 = new ArrayList<>();

		 Laboratoires Infolab1 = new Laboratoires();
		 Infolab1.setLaboname("Lab Informatique");
		 Infolab1.setCode("Code 3");
		 Infolab1.setActivite("Activity 3");
		 Infolab1.setDepartments(department3); // Set the department for the laboratory
		 departmentInfoLab1.add(Infolab1);
		 
		 List<Laboratoires> departmentElecLab1 = new ArrayList<>();

		 Laboratoires Eleclab1 = new Laboratoires();
		 Eleclab1.setLaboname("Lab Electrique");
		 Eleclab1.setCode("Code 3");
		 Eleclab1.setActivite("Activity 3");
		 Eleclab1.setDepartments(department4); // Set the department for the laboratory
		 departmentElecLab1.add(Eleclab1);
		 
		 List<Laboratoires> departmentLanguelab1 = new ArrayList<>();

		 Laboratoires Languelab1 = new Laboratoires();
		 Languelab1.setLaboname("Lab Langue");
		 Languelab1.setCode("Code 3");
		 Languelab1.setActivite("Activity 3");
		 Languelab1.setDepartments(department5); // Set the department for the laboratory
		 departmentLanguelab1.add(Languelab1);
		 
		 List<Laboratoires> departmenEcoLab1 = new ArrayList<>();

		 Laboratoires Ecolab1 = new Laboratoires();
		 Ecolab1.setLaboname("Lab Economie");
		 Ecolab1.setCode("Code 3");
		 Ecolab1.setActivite("Activity 3");
		 Ecolab1.setDepartments(department6); // Set the department for the laboratory
		 departmenEcoLab1.add(Ecolab1);
		 
		 
		 List<Laboratoires> departmentBiomlab1 = new ArrayList<>();

		 Laboratoires Biomlab1 = new Laboratoires();
		 Biomlab1.setLaboname("Lab Biomédicale");
		 Biomlab1.setCode("Code 3");
		 Biomlab1.setActivite("Activity 3");
		 Biomlab1.setDepartments(department7); // Set the department for the laboratory
		 departmentBiomlab1.add(Biomlab1);
		 
	

		 // Save the lists of laboratories to the database using the LaboratoryRepository
		 laboratoireservice.saveAll(departemenMecLab1);
		 laboratoireservice.saveAll(departmentMathLab1);
		 laboratoireservice.saveAll(departmentInfoLab1);
		 laboratoireservice.saveAll(departmentElecLab1);
		 laboratoireservice.saveAll(departmentLanguelab1);
		 laboratoireservice.saveAll(departmentBiomlab1);
		 
		 laboratoireservice.saveAll(departmenEcoLab1);

		 // Repeat this process for other departments and their respective laboratories

		 return ResponseEntity.ok("Laboratoires initialized successfully.");
	 }

}
