package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Departments;

public interface DepartmentService {
	
	List<Departments> getAllDepartment();
	
	
	Departments getDepartmentById(Long iddepart);


	Departments saveDepartments(List<Departments> departmentsList);

}
