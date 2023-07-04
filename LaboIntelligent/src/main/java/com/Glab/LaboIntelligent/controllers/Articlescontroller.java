package com.Glab.LaboIntelligent.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.models.Articles;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;
import com.Glab.LaboIntelligent.services.impl.ArticlesServiceImpl;


@RequestMapping(value = "/")
@Controller
public class Articlescontroller {
    @Autowired
    private ArticlesServiceImpl articleService;

    @Autowired
    LaboratoiresRepository laboratoiresRepository;

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

        return "redirect:/articles";
    }


    @PostMapping("/addarticles/uploadimg")
    public String uploadimg(@ModelAttribute("article") Articles article,
                            @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = "gilab/LaboIntelligent/src/main/resources/static/articleimg/" + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            article.setVisuel(fileName);
        }

        articleService.saveArticle(article);
        return "redirect:/addarticles";
    }

    @GetMapping("/articles/edit/{id}")
    public String editArticlesForm(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "edit_article";
    }

    @PostMapping("/articles/edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute("article") Articles article, Model model) {
        Articles existingArticle = articleService.getArticleById(id);
        existingArticle.setId(id);
        existingArticle.setArticlenom(article.getArticlenom());
        existingArticle.setCategorie(article.getCategorie());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setDocumentation(article.getDocumentation());
        existingArticle.setDomaine(article.getDomaine());
        existingArticle.setLaboratoire(article.getLaboratoire());
        existingArticle.setQuantite(article.getQuantite());
        existingArticle.setReference(article.getReference());
        existingArticle.setVisuel(article.getVisuel());

        articleService.updateArticle(existingArticle);
        return "redirect:/articles";
    }

    @GetMapping("articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return "redirect:/articles";
    }
}