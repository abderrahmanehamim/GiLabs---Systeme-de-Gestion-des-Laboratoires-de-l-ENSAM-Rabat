package com.Glab.LaboIntelligent.services;

import java.util.List;

import com.Glab.LaboIntelligent.models.Departement;

public interface DepartmentService {
	
	List<Departement> getAllDepartment();
	
	
	static Departement getDepartmentById(Long iddepart) {
		// TODO Auto-generated method stub
		return null;
	}


	void saveDepartments(List<Departement> departmentsList);

}
