package com.Glab.LaboIntelligent.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Glab.LaboIntelligent.models.AppRole;





public interface AppRoleRepository extends JpaRepository<AppRole, String> {
	AppRole findByAppRoleName(String appRoleName);
}
