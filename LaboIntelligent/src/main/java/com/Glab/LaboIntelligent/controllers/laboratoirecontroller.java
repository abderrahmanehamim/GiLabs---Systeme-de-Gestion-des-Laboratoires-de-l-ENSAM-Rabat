package com.Glab.LaboIntelligent.controllers;

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
import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.models.Professeur;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.ArticlesRepository;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.repositories.ProfesseurRepository;

@Controller
public class laboratoirecontroller {
	@Autowired
	DepartmentRepository departmentRepository;
@Autowired
LaboratoiresRepository laboratoiresRepository;
@Autowired
ArticlesRepository articlesRepository;

@Autowired
EtudiantRepository etudiantRepository;
@Autowired
ProfesseurRepository professeurRepository;
@Autowired
private AppUserRepository appUserRepository;


	@GetMapping("laboratoire/{code}")
	public String Laboratoires(Model model, @PathVariable String code) {
		
		
		 List<Articles> articles = articlesRepository.findAll();
		    List<Articles> articleslab = new ArrayList<Articles>();
		    for(Articles article : articles) {
		    if(article.getLaboratoire().getCode().equals(code)) 	articleslab.add(article);
		    }
		    model.addAttribute("articles", articleslab);

		
		model.addAttribute("lab", laboratoiresRepository.getLabByCode(code));

		
		
		return "laboratoires.html";
	}


	 
	    @GetMapping("/addLabo")
	    public String addNew(Model model) {
	        List<Departement> deps = departmentRepository.findAll();
	        model.addAttribute("deps", deps);
	    	
	        
	        return "AddLabo.html";
	    }

	    @PostMapping("/addLabo")
	    public String articleForm( Model model,
	                              @RequestParam("labonom") String labonom,
	                              @RequestParam("code") String labCode,
	                              @RequestParam("activite") String activite,
	                              @RequestParam("dep") Long depid) {

	    Laboratoire lab = new Laboratoire();
lab.setCode(labCode);
lab.setLaboname(labonom);
lab.setActivite(activite);

	        // Set the lab attribute by fetching the Labo from the database based on labCode
	        Departement dep = departmentRepository.getById(depid);
//	        System.out.println(labo.getCode());
	        lab.setDep(dep);

	        // Save the article using the service
	        laboratoiresRepository.save(lab);

	        return "redirect:/laboratoire/"+labCode;
	    }
}
