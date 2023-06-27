package com.Glab.LaboIntelligent.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository ;

	
	
	
	
	
	@Override
	public List<Departement> getAllDepartment() {
		return departmentRepository.findAll();
	}

	
	
	
	
	public Departement getDepartmentById(Long iddepart) {
		
		return departmentRepository.findById(iddepart).get();
	}





	@Override
	public void saveDepartments(List<Departement> departmentsList) {
		// TODO Auto-generated method stub
		  for(Departement dep:departmentsList) {
	    	   departmentRepository.save(dep);
	       }
	}
	
	





	

}
