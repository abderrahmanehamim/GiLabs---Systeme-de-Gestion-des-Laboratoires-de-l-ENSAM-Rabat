package com.Glab.LaboIntelligent.services.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Glab.LaboIntelligent.repositories.AppRoleRepository;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Etudiant;

@Service

public class AppUserDetailsServiceImpl1 implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
    Etudiant Etconnect =new Etudiant();
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Etudiant etudiant = new Etudiant();
		etudiant= etudiantRepository.chercherEtudiantByEmail(email);
		Etconnect=etudiant;

		AppUser appUser = appUserRepository.findByEmail(email);
		if (appUser == null) {
			System.out.println("User not found!" + email);
			throw new UsernameNotFoundException("User" + email);
		}
		// System.out.println("Found User: " +appUser.getUserName());

		// ROle_USER, ROLE_ADMIN
		
		  Collection<AppRole> roleNames =appUser.getUserRoles();
		 // appRoleRepository.findByRoleName(appUser.getEmail());*/
		  List<GrantedAuthority>		 grantList = new ArrayList<GrantedAuthority>(); 
		  Iterator iterator =roleNames.iterator(); 
		  if(roleNames !=null) { while (iterator.hasNext()) {
		  AppRole appRole = (AppRole) iterator.next(); 
			
			  GrantedAuthority authority = new SimpleGrantedAuthority(
			  appRole.getAppRoleName()); grantList.add(authority);
		  }}
		  
		 UserDetails userDetails = (UserDetails)new User(appUser.getEmail(),
		  appUser.getUserPassword(),grantList );
		 
		// TODO Auto-generated method stub

		return  userDetails;
	

}}
