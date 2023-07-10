package com.Glab.LaboIntelligent.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.Glab.LaboIntelligent.services.impl.ArticlesServiceImpl;


@RequestMapping(value = "/")
@Controller
public class Articlescontroller {
    @Autowired
    private ArticlesServiceImpl articleService;

    @Autowired
    LaboratoiresRepository laboratoiresRepository;

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
    public Articlescontroller() {
        super();
    }

    @GetMapping("/articles")
    public String listArticle(Model model) {
     

    	
    	
    	
    	model.addAttribute("articles", articleService.getAllArticles());
        return "AllArticles.html";
    }

    @GetMapping("/addarticles")
    public String addNew(Model model) {
      

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
    	 model.addAttribute("role", role);
    	 model.addAttribute("username", username);
    	
    	
    	model.addAttribute("article", new Articles());
        List<Laboratoire> labs = laboratoiresRepository.findAll();
        model.addAttribute("labs", labs);
        return "Addarticle.html";
    }
    @Autowired
    private Environment env;
    @PostMapping("/addarticles")
    public String articleForm(@ModelAttribute Articles article, Model model,
                              @RequestParam("articlenom") String articlenom,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("documentation") String documentation,
                              @RequestParam("quantite") int quantite,
                              @RequestParam("domaine") String domaine,
                              @RequestParam("categorie") String categorie,
                              @RequestParam("lab") String labCode,
                              @RequestParam("reference") String reference,
                              @RequestParam("description") String description) {

    	
        // Create a new Articles object and set its attributes using the form data
        Articles newArticle = new Articles();
        
        
        
        
        // Handle article's image upload 
        if(!file.isEmpty()) {
        	String fileName = file.getOriginalFilename();
        
        
           // String uploadDirectory = "C:\\Users\\telmoudy\\pfa\\gilab\\LaboIntelligent\\src\\main\\resources\\static\\articleimg";
            String uploadDirectory = "C:\\Users\\H-R\\Documents\\workspace_pfa\\gilab\\LaboIntelligent\\src\\main\\resources\\static\\articleimg";
            System.out.println("#######################################");
            System.out.println(uploadDirectory);
            System.out.println("#######################################");

        	File dest = new File(uploadDirectory,fileName);
        	try {
				file.transferTo(dest);
				newArticle.setVisuel(fileName);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        
        newArticle.setArticlenom(articlenom);
        newArticle.setDocumentation(documentation);
        newArticle.setQuantite(quantite);
        newArticle.setDomaine(domaine);
        newArticle.setCategorie(categorie);
        newArticle.setReference(reference);
        newArticle.setDescription(description);

        // Set the lab attribute by fetching the Labo from the database based on labCode
        Laboratoire labo = laboratoiresRepository.getLabByCode(labCode);
//        System.out.println(labo.getCode());
        newArticle.setLaboratoire(labo);

        // Save the article using the service
        articleService.saveArticle(newArticle);

        
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
        
        
        return "redirect:/articles";
    }



    @GetMapping("/articles/edit/{id}")
    public String editArticlesForm(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
       
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
        
        
        return "edit_article";
    }

    @PostMapping("/articles/edit/{id}")
    public String updateArticle(@PathVariable Long id,@ModelAttribute Articles article, Model model,
            @RequestParam("articlenom") String articlenom,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentation") String documentation,
            @RequestParam("quantite") int quantite,
            @RequestParam("domaine") String domaine,
            @RequestParam("categorie") String categorie,
            @RequestParam("lab") String labCode,
            @RequestParam("reference") String reference,
            @RequestParam("description") String description) {


    	// Create a new Articles object and set its attributes using the form data
    	Articles newArticle = articleService.getArticleById(id);


    	// Handle article's image upload 
    	if(!file.isEmpty()) {
    		String fileName = file.getOriginalFilename();


    	//	String uploadDirectory = "C:\\Users\\telmoudy\\pfa\\gilab\\LaboIntelligent\\src\\main\\resources\\static\\articleimg";
    		String uploadDirectory = "C:\\Users\\H-R\\Documents\\workspace_pfa\\gilab\\LaboIntelligent\\src\\main\\resources\\static\\articleimg";
    		System.out.println("#######################################");
    		System.out.println(uploadDirectory);
    		System.out.println("#######################################");

    		File dest = new File(uploadDirectory,fileName);
    		try {
    			file.transferTo(dest);
    			newArticle.setVisuel(fileName);
}				 catch (IllegalStateException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}
System.out.println(articlenom);
if(!articlenom.equals(""))
newArticle.setArticlenom(articlenom);
if(!documentation.equals(""))
newArticle.setDocumentation(documentation);
if(quantite != 0)
newArticle.setQuantite(quantite);
if(domaine != null)
newArticle.setDomaine(domaine);
if(!categorie.equals(""))
newArticle.setCategorie(categorie);
if(!reference.equals(""))
newArticle.setReference(reference);
if(!description.equals(""))
newArticle.setDescription(description);

// Set the lab attribute by fetching the Labo from the database based on labCode
Laboratoire labo = laboratoiresRepository.getLabByCode(labCode);
//System.out.println(labo.getCode());
if(labo != null)
newArticle.setLaboratoire(labo);

// Save the article using the service
			articleService.saveArticle(newArticle);

				return "redirect:/articles";
}

	
    @GetMapping("articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
      System.out.println("@!#################################");
 
  
  Articles article = articleService.getArticleById(id);
  Laboratoire laboratoire = article.getLaboratoire();
  laboratoire.getArticles().remove(article); // Remove the article from the laboratoire's collection
  laboratoiresRepository.save(laboratoire); // Save the updated laboratoire
  //articleService.deleteArticleById(id);
  article.setLaboratoire(null);
  articlesRepository.deleteById(id);
  return "redirect:/articles";
    }
}