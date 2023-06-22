/*package com.Glab.LaboIntelligent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private EtudiantRepository etudiantRepository ;
	
	@Override
	public void run(String... args) throws Exception {
		
		 Etudiant etudiant1 =new Etudiant("abderrahmane", "Hamim", "abderahmane.hamim@gmail.com");
		   etudiantRepository.save(etudiant1);
		   
		   Etudiant etudiant2 =new Etudiant("Mohammed Lemine", "Telmoudy", "telmoudy.lemine@gmail.com");
		   etudiantRepository.save(etudiant2);
		   
		   Etudiant etudiant3 =new Etudiant("test", "test", "test");
		   etudiantRepository.save(etudiant3);
		
	
		
	}

}
*/