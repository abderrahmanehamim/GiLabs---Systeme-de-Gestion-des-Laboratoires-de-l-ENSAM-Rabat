package com.Glab.LaboIntelligent.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.services.DepartmentService;
import com.Glab.LaboIntelligent.services.LaboratoireService;

@Controller
@RequestMapping("/departments")
public class departmentcontoller {
	
	
	
	//private DepartmentService departmentService;
	@Autowired
	DepartmentRepository departmentRepository;

	
	
	public String listDepartment(Model model) {
		
		model.addAttribute("department", departmentRepository.findAll());
		
		return "department";
	}
	
	@GetMapping("departement/getbyID/{id}")
	public Departement getbyID(@PathVariable long id) {
		Departement dep = departmentRepository.getById(id); 
		
		
		return dep;
	}

}
