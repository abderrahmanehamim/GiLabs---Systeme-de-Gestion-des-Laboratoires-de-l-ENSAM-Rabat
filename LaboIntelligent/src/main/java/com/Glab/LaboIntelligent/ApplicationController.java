package com.Glab.LaboIntelligent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.models.Professeur;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.repositories.ProfesseurRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Controller

public class ApplicationController {
	
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

	
	
	@GetMapping("laboratoire/{code}")
	public String Laboratoires( @PathVariable String code) {
		System.out.println(code);
		return "laboratoires.html";
	}
	
 @GetMapping("/Admin")
 
 public String goHome(Model model) {
	
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

	return "index.html";
	 
	 
 }
 
 @GetMapping("/meca")
 
 public String goDepMec(Model model) {
	 
	 Long idDprt = 1L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab); 
	return "Meca.html";
	 
	 
 }
@GetMapping("/math")
 
 public String goDepMath(Model model) {
	 Long idDprt = 2L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	 
	return "Math.html";
	 
	 
 }
@GetMapping("/info")

public String goDepinfo(Model model) {
	 Long idDprt = 3L;
    List<Articles> articles = articlesRepository.findAll();
    List<Articles> articleslab = new ArrayList<Articles>();
    for(Articles article : articles) {
    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
    }
    model.addAttribute("articles", articleslab);
	return "Info.html";
	 
	 
}
@GetMapping("/elect")

public String goDepelect(Model model) {
	 
	 Long idDprt = 4L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	return "Elect.html";
	 
	 
}
@GetMapping("/langue")

public String goDeplangue(Model model) {
	 Long idDprt = 5L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	 
	return "Langues.html";
	 
	 
}
@GetMapping("/eco")

public String goDepeco(Model model) {
	 Long idDprt = 6L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab = new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	 
	return "Eco.html";
	 
	 
}
@GetMapping("/biom")

public String goDepbiom(Model model) {
	 Long idDprt = 7L;
	    List<Articles> articles = articlesRepository.findAll();
	    List<Articles> articleslab =  new ArrayList<Articles>();
	    for(Articles article : articles) {
	    if(article.getLaboratoire().getDep().getIddepart() == idDprt) 	articleslab.add(article);
	    }
	    model.addAttribute("articles", articleslab);
	 
	return "Biom.html";
	 
	 
}/*
@GetMapping("/laboratoires")

public String golaboratoires() {
	 
	 
	return "Laboratoires.html";
	 
}
*/

@GetMapping("/adduser")

public String goadduser() {
	 
	 
	return "adduser.html";
	 
}
@GetMapping("/error")

public String error() {
	 
	 
	return "pages-error-404.html";
	 
	 
}

}
