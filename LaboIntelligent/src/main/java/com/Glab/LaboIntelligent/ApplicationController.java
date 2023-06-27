package com.Glab.LaboIntelligent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ApplicationController {
	
	
	
 @GetMapping("/Admin")
 
 public String goHome() {
	 
	 
	return "index.html";
	 
	 
 }
 
 @GetMapping("/meca")
 
 public String goDepMec() {
	 
	 
	return "Meca.html";
	 
	 
 }
@GetMapping("/math")
 
 public String goDepMath() {
	 
	 
	return "Math.html";
	 
	 
 }
@GetMapping("/info")

public String goDepinfo() {
	 
	 
	return "Info.html";
	 
	 
}
@GetMapping("/elect")

public String goDepelect() {
	 
	 
	return "Elect.html";
	 
	 
}
@GetMapping("/langue")

public String goDeplangue() {
	 
	 
	return "Langues.html";
	 
	 
}
@GetMapping("/eco")

public String goDepeco() {
	 
	 
	return "Eco.html";
	 
	 
}
@GetMapping("/biom")

public String goDepbiom() {
	 
	 
	return "Biom.html";
	 
	 
}
@GetMapping("/laboratoires")

public String golaboratoires() {
	 
	 
	return "Laboratoires.html";
	 
}



 

}
