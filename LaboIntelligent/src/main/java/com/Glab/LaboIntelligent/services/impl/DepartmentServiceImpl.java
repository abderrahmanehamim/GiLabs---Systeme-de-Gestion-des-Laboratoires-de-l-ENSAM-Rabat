package com.Glab.LaboIntelligent.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.models.Departments;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository ;

	
	
	
	
	
	@Override
	public List<Departments> getAllDepartment() {
		return departmentRepository.findAll();
	}

	
	
	
	
	@Override
	public Departments getDepartmentById(Long iddepart) {
		
		return departmentRepository.findById(iddepart).get();
	}

}
