package com.Glab.LaboIntelligent.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.helper.ExcelUploader;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;

@Service
public class ExcelService {
	@Autowired
	  EtudiantRepository etudiantrepository;
	
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

	    List<AppUser> Users = ExcelUploader.users(file.getInputStream());
	     appUserRepository.saveAll(Users);
 	  }

	  public List<Etudiant> getAllTutorials() {
	    return etudiantrepository.findAll();
	  }
	
	  

}
