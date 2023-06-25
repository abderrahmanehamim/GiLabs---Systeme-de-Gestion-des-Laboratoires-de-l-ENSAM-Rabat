package com.Glab.LaboIntelligent.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Glab.LaboIntelligent.models.Departments;
import com.Glab.LaboIntelligent.models.Laboratoires;
import com.Glab.LaboIntelligent.services.DepartmentService;
import com.Glab.LaboIntelligent.services.LaboratoireService;

@Controller
@RequestMapping("/departments")
public class departmentcontoller {
	
	
	
	private DepartmentService departmentService;
	
	
	
	public String listDepartment(Model model) {
		
		model.addAttribute("department", departmentService.getAllDepartment());
		
		return "department";
	}
	
	@GetMapping("departement/getbyID/{id}")
	public Departments getbyID(@PathVariable long id) {
		return DepartmentService.getDepartmentById(id);
	}
	
	
	  @PostMapping("/initialize")
	    public ResponseEntity<String> initializeDepartments() {
		  List<Departments> departmentsList = new ArrayList<>();

	        // Create Department objects and set the department names
	        Departments department1 = new Departments();
	        department1.setIddepart(1);
	        department1.setDepartmentName("Génie Mecanique");
	        //department1.setLaboratoire(departemenMecLab1);
	        
	        departmentsList.add(department1);

	        Departments department2 = new Departments();
	        department2.setIddepart(2);
	        department2.setDepartmentName("Mathematiques Appliquees");
	        //department2.setLaboratoire(departmentMathLab1);
	        departmentsList.add(department2);
	        
	        Departments department3 = new Departments();
	        department3.setIddepart(3);
	        department3.setDepartmentName("Génie Informatique");
	        //department3.setLaboratoire(departmentInfoLab1);
	        departmentsList.add(department3);

	        Departments department4 = new Departments();
	        department4.setIddepart(4);
	        department4.setDepartmentName("Génie Electrique");
	        //department4.setLaboratoire(departmentElecLab1);
	        departmentsList.add(department4);
	        
	        Departments department5 = new Departments();
	        department5.setIddepart(5);
	        department5.setDepartmentName("Langues/Communication");
	        //department5.setLaboratoire(departmentLanguelab1);
	        departmentsList.add(department5);

	        Departments department6 = new Departments();
	        department6.setIddepart(6);
	        department6.setDepartmentName("Génie Economie et Management");
	        //department6.setLaboratoire(departmenEcoLab1);
	        departmentsList.add(department6);
	        
	        Departments department7 = new Departments();
	        department7.setIddepart(7);
	        //department7.setLaboratoire(departmentBiomlab1);
	        department7.setDepartmentName("Génie Biomédical");
	        departmentsList.add(department7);

	       


	        departmentService.saveDepartments(departmentsList);

	        return ResponseEntity.ok("Departments initialized successfully.");
	    }
}
