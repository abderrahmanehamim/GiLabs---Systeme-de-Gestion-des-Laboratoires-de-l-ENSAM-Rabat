package com.Glab.LaboIntelligent.security;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;



@Component
public class EmployeeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	public  String email ;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private AppUserRepository appUserRepository;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
			Authentication authentication) throws IOException, ServletException {
		boolean hasEtudiantRole = false;
		boolean hasAdminRole = false;
		boolean hasProfRole = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("Etudiant")) {
				
				hasEtudiantRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("Admin")) {
				hasAdminRole = true;
				break;
			}
			else if (grantedAuthority.getAuthority().equals("Professeur")) {
				hasProfRole = true;
				break;
			}
		}
		
	
		String email = authentication.getName();
		AppUser user = new AppUser();
		user=appUserRepository.findByEmail(email);
		if (hasEtudiantRole) {
			System.out.println("ETUDIANT Authentication");
			redirectStrategy.sendRedirect(arg0, arg1, "/Etudiant/Home");
		} 
		 else if (hasAdminRole && user.isStatus()==false) {
			 
				System.out.println("ADMIN Authentication ");
System.out.println(user.getEmail());
				redirectStrategy.sendRedirect(arg0, arg1, "/Admin/Home");

		 } 
		else if (hasAdminRole ) {
			System.out.println("ADMIN Authentication");
			System.out.println(user.getEmail());

			redirectStrategy.sendRedirect(arg0, arg1, "/Admin/Home");
		} else if (hasProfRole ) {
			System.out.println("Prof Authentication");
			System.out.println(user.getEmail());

			redirectStrategy.sendRedirect(arg0, arg1, "/Prof/Home");
		} 
	 
		else {
			throw new IllegalStateException();
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}