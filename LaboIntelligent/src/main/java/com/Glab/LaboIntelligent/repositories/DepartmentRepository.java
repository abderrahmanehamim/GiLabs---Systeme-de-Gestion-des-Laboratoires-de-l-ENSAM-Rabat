package com.Glab.LaboIntelligent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Glab.LaboIntelligent.models.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, Long> {

}
