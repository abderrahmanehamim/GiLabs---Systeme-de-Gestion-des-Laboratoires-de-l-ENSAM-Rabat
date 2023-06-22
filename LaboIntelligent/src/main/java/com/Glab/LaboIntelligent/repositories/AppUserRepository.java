package com.Glab.LaboIntelligent.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Glab.LaboIntelligent.models.AppUser;




public interface AppUserRepository extends JpaRepository<AppUser, String>{
	AppUser findByEmail(String email);
	@Transactional
	@Modifying
	@Query("UPDATE  AppUser u SET u.userPassword = :password WHERE u.email = :email")
	public void changePasswordEtudiant(@Param("email") String email,@Param("password") String password );
	
	@Transactional
	@Modifying
	@Query("UPDATE  AppUser u SET u.userPasswordId = :passwordId WHERE u.email = :email")
	public void generatePasswordUserId(@Param("email") String email,@Param("passwordId") String passwordId );
}
