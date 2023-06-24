package com.Glab.LaboIntelligent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Glab.LaboIntelligent.models.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, Long> {

	Departments save(List<Departments> departmentsList);

}
