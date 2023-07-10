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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Articles;
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
@RequestMapping("/Etudiant")
public class EtudiantContoller {

	
	
	
	@Autowired
	private LaboratoiresRepository laboratoiresRepository;
	@Autowired
	private ArticlesRepository articlesRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	ProfesseurRepository professeurRepository;
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	
	private DepartmentRepository departmentRepository;
	
	
 @GetMapping("/Home")
  public String goHomeetd(Model model) {
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
	 model.addAttribute("email", email);

	 /*  get email and role and name  
		 * 
		 */
	return "index2.html";
	 
	 
 }
 
 @GetMapping("/meca")
 
 public String etdgoDepMec(Model model) {
	 
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
	 
	 
	 
	 
	 Long idDprt = 1L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	    model.addAttribute("deplabs", deplabs);
	return "Meca2.html";
	 
	 
 }
@GetMapping("/math")
 
 public String etdgoDepMath(Model model) {
	
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
	
	
	 Long idDprt = 2L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	    model.addAttribute("deplabs", deplabs);

	return "Math2.html";
	 
	 
 }
@GetMapping("/info")

public String etdgoDepinfo(Model model) {
	
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
	
	
	 Long idDprt = 3L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	    model.addAttribute("deplabs", deplabs);
	return "Info2.html";
	 
	 
}
@GetMapping("/elect")

public String etdgoDepelect(Model model) {
	 
	
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
	
	 Long idDprt = 4L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	    model.addAttribute("deplabs", deplabs);
	return "Elect2.html";
	 
	 
}
@GetMapping("/langue")

public String etdgoDeplangue(Model model) {
	
	
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
	
	
	Long idDprt = 5L;
    List<Articles> articles = articlesRepository.findAll();
    List<Articles> articleslab = new ArrayList<Articles>();
    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

    for(Articles article : articles) {
    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
    }
    model.addAttribute("articles", articleslab);
    model.addAttribute("deplabs", deplabs);
	 
	return "Langues2.html";
	 
	 
}
@GetMapping("/eco")

public String etdgoDepeco(Model model) {
	
	
	
	
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
	
	
	
	
	Long idDprt = 6L;
    List<Articles> articles = articlesRepository.findAll();
    List<Articles> articleslab = new ArrayList<Articles>();
    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

    for(Articles article : articles) {
    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
    }
    model.addAttribute("articles", articleslab);
    model.addAttribute("deplabs", deplabs);
	 
	return "Eco2.html";
	 
	 
}
@GetMapping("/biom")

public String etdgoDepbiom(Model model) {
	
	
	
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
	
	
	
	Long idDprt = 7L;
    List<Articles> articles = articlesRepository.findAll();
    List<Articles> articleslab = new ArrayList<Articles>();
    List<Laboratoire> deplabs = departmentRepository.getById(idDprt).getLaboratoires();

    for(Articles article : articles) {
    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
    }
    model.addAttribute("articles", articleslab);
    model.addAttribute("deplabs", deplabs);
	 
	return "Biom2.html";
	 
	 
}/*
@GetMapping("/laboratoires")

public String golaboratoires() {
	 
	 
	return "Laboratoires.html";
	 
}
*/

@GetMapping("/error")

public String error() {
	 
	 
	return "pages-error-404.html";
	 
	 
}
	
}
