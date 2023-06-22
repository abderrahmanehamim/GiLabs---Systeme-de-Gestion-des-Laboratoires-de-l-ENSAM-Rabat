package com.Glab.LaboIntelligent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Glab.LaboIntelligent.models.Departments;
import com.Glab.LaboIntelligent.services.DepartmentService;

@Controller
public class departmentcontoller {
	
	
	
	private DepartmentService departmentService;
	
	
	
	public String listDepartment(Model model) {
		
		model.addAttribute("department", departmentService.getAllDepartment());
		
		return "department";
	}
	
	@GetMapping("departement/getbyID/{id}")
	public Departments getbyID(@PathVariable long id) {
		return this.departmentService.getDepartmentById(id);
	}
}
