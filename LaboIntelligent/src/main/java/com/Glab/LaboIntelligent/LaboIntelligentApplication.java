package com.Glab.LaboIntelligent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.repositories.AppRoleRepository;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;

@SpringBootApplication
public class LaboIntelligentApplication implements CommandLineRunner {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(LaboIntelligentApplication.class, args);
	}
	@Autowired
	private EtudiantRepository etudiantRepository ;
	
	@Override
	public void run(String... args) throws Exception {
		AppRole r1= new AppRole("Etudiant");
		AppRole r2= new AppRole("Admin");

		appRoleRepository.save(r1);
		appRoleRepository.save(r2);

		Collection<AppRole> listrole =new ArrayList<AppRole>();
		Collection<AppRole> listroleAdmi =new ArrayList<AppRole>();
		listroleAdmi.add(r2);
		listrole.add(r1);
		BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
		String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
		AppUser u6 =new AppUser("admin2@esp.mr", userPasswordEncrypted, listroleAdmi);
		appUserRepository.save(u6);
	}
}
