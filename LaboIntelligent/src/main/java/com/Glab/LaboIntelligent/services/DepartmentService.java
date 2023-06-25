package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Departments;

public interface DepartmentService {
	
	List<Departments> getAllDepartment();
	
	
	static Departments getDepartmentById(Long iddepart) {
		// TODO Auto-generated method stub
		return null;
	}


	Departments saveDepartments(List<Departments> departmentsList);

}
