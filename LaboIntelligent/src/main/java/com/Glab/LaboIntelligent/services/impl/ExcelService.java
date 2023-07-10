package com.Glab.LaboIntelligent.services.impl;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.helper.ExcelUploader;
import com.Glab.LaboIntelligent.helper.ExcelUploader2Profs;
import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Professeur;
import com.Glab.LaboIntelligent.repositories.AppRoleRepository;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.ProfesseurRepository;

@Service
public class ExcelService {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	  EtudiantRepository etudiantrepository;
	@Autowired
	  ProfesseurRepository professeurRepository ;
	@Autowired
	AppRoleRepository appRoleRepository;
	@Autowired
	AppUserRepository appUserRepository;
	  public void saveExcelEtudiant(MultipartFile file) throws IOException {
	 
		  List<Etudiant> etudiants = ExcelUploader.Etudiants(file.getInputStream());
		  for (Etudiant etd : etudiants) {
		      if ((etd.getDep().getIddepart() > 0) && (etd.getDep().getIddepart() < 8)) {
		          etudiantrepository.save(etd);
		          System.out.println("Etudiant: " + etd.getNom().toUpperCase() + " " + etd.getPrenom() + " a été ajouté avec succès");
		      } else {
		          System.out.println("Etudiant: " + etd.getNom().toUpperCase() + " " + etd.getPrenom() + " n'appartient à aucun département");
		      }
		  }

	    List<AppUser> users = ExcelUploader.users(file.getInputStream());
	 // initialise ROLES AND USERS //
	 		AppRole r2= new AppRole("Etudiant");
//	 		appRoleRepository.save(r1);
	 		appRoleRepository.save(r2);
	 	//	appRoleRepository.save(r3);

	 		Collection<AppRole> listroleEtu =new ArrayList<AppRole>();
	 		listroleEtu.add(r2);
	 		//listrole.add(r1);
	 	//	listrole.add(r3);
	 		BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
	 		String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
	 		
	 		for(AppUser etd : users) {
	 				etd.setUserPassword(userPasswordEncrypted);
	 				etd.setUserRoles(listroleEtu);	 
appUserRepository.save(etd);

	 		}
	 		
	 		
	    
	    
	   // appUserRepository.saveAll(Users);
 	  }

	  public List<Professeur> getAllTutorials() {
	    return professeurRepository.findAll();
	  }

	public void saveExcelProf(MultipartFile file)throws IOException {
		// TODO Auto-generated method stub

		  List<Professeur> profs = ExcelUploader2Profs.Professeurs(file.getInputStream());
		  for (Professeur prof : profs) {
		      if ((prof.getDep().getIddepart() > 0) && (prof.getDep().getIddepart() < 8)) {
		       Departement dep =  departmentRepository.findById(prof.getDep().getIddepart()).get();
		       prof.setDep(dep);
		    	  professeurRepository.save(prof);
		          System.out.println("Professur: " + prof.getNom().toUpperCase() + " " + prof.getPrenom() + " a été ajouté avec succès");
		      } else {
		          System.out.println("Professur: " + prof.getNom().toUpperCase() + " " + prof.getPrenom() + " n'appartient à aucun département");
		      }
		  }

	    List<AppUser> users = ExcelUploader2Profs.users(file.getInputStream());
		AppRole r2= new AppRole("Professeur");
// 		appRoleRepository.save(r1);
 		appRoleRepository.save(r2);
 	//	appRoleRepository.save(r3);

 		Collection<AppRole> listroleEtu =new ArrayList<AppRole>();
 		listroleEtu.add(r2);
 		//listrole.add(r1);
 	//	listrole.add(r3);
 		BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
 		String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
 		
 		for(AppUser prof : users) {
 			prof.setUserPassword(userPasswordEncrypted);
 			prof.setUserRoles(listroleEtu);	 
appUserRepository.save(prof);

 		}	}
	
	  

}
