package com.Glab.LaboIntelligent.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.FileInfo;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.models.Professeur;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.repositories.ProfesseurRepository;
import com.Glab.LaboIntelligent.services.FilesStorageService;


@Controller
public class FileController {

  @Autowired
  FilesStorageService storageService;
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
  @GetMapping("/")
  public String homepage() {
    return "redirect:/files";
  }

  @GetMapping("/files/new")
  public String newFile(Model model) {
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
    return "addpdf";
  }

  @PostMapping("/files/upload")
  public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(file);
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
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
    } catch (Exception e) {
    
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
    	message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
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
    return "addpdf";
  }

  @GetMapping("/files")
  public String getListFiles(Model model) {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    model.addAttribute("files", fileInfos);
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
    return "allpdf";
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);

    
    
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @GetMapping("/files/delete/{filename:.+}")
  public String deleteFile(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes) {
    try {
      boolean existed = storageService.delete(filename);

      if (existed) {
        redirectAttributes.addFlashAttribute("message", "Delete the file successfully: " + filename);
      } else {
        redirectAttributes.addFlashAttribute("message", "The file does not exist!");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message",
          "Could not delete the file: " + filename + ". Error: " + e.getMessage());
    }

    return "redirect:/files";
  }
}
