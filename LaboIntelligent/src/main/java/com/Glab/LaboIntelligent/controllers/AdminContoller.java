package com.Glab.LaboIntelligent.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.models.Professeur;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.repositories.ProfesseurRepository;
import com.Glab.LaboIntelligent.services.EtudiantService;
import com.Glab.LaboIntelligent.services.impl.ExcelService;
import com.Glab.LaboIntelligent.helper.ExcelUploader;
import com.Glab.LaboIntelligent.helper.ExcelUploader2Profs;


@Controller
public class AdminContoller {
	private String fileLocation;
	// Excel

		@Autowired
		ExcelService fileService;
	
		@Autowired
		private EtudiantRepository etudiantRepository;
		@Autowired
		private LaboratoiresRepository laboratoiresRepository;
		@Autowired
		private ArticlesRepository articlesRepository;
	
		@Autowired
		ProfesseurRepository professeurRepository;
		@Autowired
		private AppUserRepository appUserRepository;

		@Autowired
		
		private DepartmentRepository departmentRepository;
				
	private EtudiantService etudiantService ;
	
	public AdminContoller (EtudiantService etudiantService ) {
		super();
		this.etudiantService = etudiantService;
		
		
		
	}


	@PostMapping("/Admin/uploadFileEtudiant")
	public String uploadFileEtudiant (Model model, @RequestParam("file") MultipartFile file)throws IOException {
		String message = "";

		if (com.Glab.LaboIntelligent.helper.ExcelUploader.hasExcelFormat(file)) {
			
				fileService.saveExcelEtudiant(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				model.addAttribute("Message", message);
				
				
				/*
		    	 *  get email and role and name  
		    	 * 
		    	 */
		    	 
		    	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    	    String email = authentication.getName(); // This will give you the email of the authenticated user

		    	 String username="";
		    	 String role="";
		    	//System.out.println("####################################\n" +email +"####################################\n");	
		    	
		    	    AppUser user = appUserRepository.findByEmail(email);
		    	 
		    	    List<AppRole> Role = (List<AppRole>) user.getUserRoles();    
		    	System.out.println(Role.get(0).getAppRoleName());
		    	    if(Role.get(0).getAppRoleName().equals("Admin")) {
		    		username = email;
		    		role = "ADMIN";}
		    		else if (Role.get(0).getAppRoleName() == "Etudiant") {
		    			Etudiant etd = etudiantRepository.chercherEtudiantByEmail(email);
		    			username = etd.getNom().toUpperCase()  + " " + etd.getPrenom();
		    			role = "Etudiant";}	
		    		else if (Role.get(0).getAppRoleName() == "Professeur") {
		    			Professeur  prof = professeurRepository.chercherProfesseurByEmail(email);
		    			username = prof.getNom().toUpperCase()  + " " + prof.getPrenom();
		    			role = "Profeseur";}	
		    ArrayList<Laboratoire> labs = (ArrayList<Laboratoire>) laboratoiresRepository.findAll();
		    	 model.addAttribute("labs",labs);
		    	 model.addAttribute("role", role);
		    	 model.addAttribute("username", username);

		    	 /*  get email and role and name  
		    		 * 
		    		 */	
				
				return "excel";
		}
				
		/*
    	 *  get email and role and name  
    	 * 
    	 */
    	 
    	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	    String email = authentication.getName(); // This will give you the email of the authenticated user

    	 String username="";
    	 String role="";
    	//System.out.println("####################################\n" +email +"####################################\n");	
    	
    	    AppUser user = appUserRepository.findByEmail(email);
    	 
    	    List<AppRole> Role = (List<AppRole>) user.getUserRoles();    
    	System.out.println(Role.get(0).getAppRoleName());
    	    if(Role.get(0).getAppRoleName().equals("Admin")) {
    		username = email;
    		role = "ADMIN";}
    		else if (Role.get(0).getAppRoleName() == "Etudiant") {
    			Etudiant etd = etudiantRepository.chercherEtudiantByEmail(email);
    			username = etd.getNom().toUpperCase()  + " " + etd.getPrenom();
    			role = "Etudiant";}	
    		else if (Role.get(0).getAppRoleName() == "Professeur") {
    			Professeur  prof = professeurRepository.chercherProfesseurByEmail(email);
    			username = prof.getNom().toUpperCase()  + " " + prof.getPrenom();
    			role = "Profeseur";}	
    ArrayList<Laboratoire> labs = (ArrayList<Laboratoire>) laboratoiresRepository.findAll();
    	 model.addAttribute("labs",labs);
    	 model.addAttribute("role", role);
    	 model.addAttribute("username", username);

    	 /*  get email and role and name  
    		 * 
    		 */	
		message = "Please upload an excel file!";
		model.addAttribute("Message", message);
		return "excel";
	}
	
	@PostMapping("/Admin/uploadFileProf")
	public String uploadFileProf (Model model, @RequestParam("file") MultipartFile file)throws IOException {
		String message = "";

		if (ExcelUploader2Profs.hasExcelFormat(file)) {
			
				fileService.saveExcelProf(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				model.addAttribute("Message", message);
				
				/*
		    	 *  get email and role and name  
		    	 * 
		    	 */
		    	 
		    	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    	    String email = authentication.getName(); // This will give you the email of the authenticated user

		    	 String username="";
		    	 String role="";
		    	//System.out.println("####################################\n" +email +"####################################\n");	
		    	
		    	    AppUser user = appUserRepository.findByEmail(email);
		    	 
		    	    List<AppRole> Role = (List<AppRole>) user.getUserRoles();    
		    	System.out.println(Role.get(0).getAppRoleName());
		    	    if(Role.get(0).getAppRoleName().equals("Admin")) {
		    		username = email;
		    		role = "ADMIN";}
		    		else if (Role.get(0).getAppRoleName() == "Etudiant") {
		    			Etudiant etd = etudiantRepository.chercherEtudiantByEmail(email);
		    			username = etd.getNom().toUpperCase()  + " " + etd.getPrenom();
		    			role = "Etudiant";}	
		    		else if (Role.get(0).getAppRoleName() == "Professeur") {
		    			Professeur  prof = professeurRepository.chercherProfesseurByEmail(email);
		    			username = prof.getNom().toUpperCase()  + " " + prof.getPrenom();
		    			role = "Profeseur";}	
		    ArrayList<Laboratoire> labs = (ArrayList<Laboratoire>) laboratoiresRepository.findAll();
		    	 model.addAttribute("labs",labs);
		    	 model.addAttribute("role", role);
		    	 model.addAttribute("username", username);

		    	 /*  get email and role and name  
		    		 * 
		    		 */	
				
				return "excel";
		}
		/*
    	 *  get email and role and name  
    	 * 
    	 */
    	 
    	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	    String email = authentication.getName(); // This will give you the email of the authenticated user

    	 String username="";
    	 String role="";
    	//System.out.println("####################################\n" +email +"####################################\n");	
    	
    	    AppUser user = appUserRepository.findByEmail(email);
    	 
    	    List<AppRole> Role = (List<AppRole>) user.getUserRoles();    
    	System.out.println(Role.get(0).getAppRoleName());
    	    if(Role.get(0).getAppRoleName().equals("Admin")) {
    		username = email;
    		role = "ADMIN";}
    		else if (Role.get(0).getAppRoleName() == "Etudiant") {
    			Etudiant etd = etudiantRepository.chercherEtudiantByEmail(email);
    			username = etd.getNom().toUpperCase()  + " " + etd.getPrenom();
    			role = "Etudiant";}	
    		else if (Role.get(0).getAppRoleName() == "Professeur") {
    			Professeur  prof = professeurRepository.chercherProfesseurByEmail(email);
    			username = prof.getNom().toUpperCase()  + " " + prof.getPrenom();
    			role = "Profeseur";}	
    ArrayList<Laboratoire> labs = (ArrayList<Laboratoire>) laboratoiresRepository.findAll();
    	 model.addAttribute("labs",labs);
    	 model.addAttribute("role", role);
    	 model.addAttribute("username", username);

    	 /*  get email and role and name  
    		 * 
    		 */		

		message = "Please upload an excel file!";
		model.addAttribute("Message", message);
		return "excel";
	}

	
}
