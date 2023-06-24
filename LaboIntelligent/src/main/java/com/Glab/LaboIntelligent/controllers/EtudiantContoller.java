package com.Glab.LaboIntelligent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.services.EtudiantService;


@Controller
public class EtudiantContoller {
	
	private EtudiantService etudiantService ;
	
	public EtudiantContoller (EtudiantService etudiantService ) {
		super();
		this.etudiantService = etudiantService;
		
		
		
	}

		
		@GetMapping("/students")
		 public String listEtudiant(Model model) {
			model.addAttribute("etudiants",etudiantService.getAllEtudiant());
			
			return "etudiants";
			 
		 }
		
		
		@GetMapping("/students/new")
		public String createStudentForm(Model model) {
			
			// create student object to hold student form data
			Etudiant student = new Etudiant();
			model.addAttribute("student", student);
			return "create_student";
			
		}
		
		@PostMapping("/students")
		public String saveStudent(@ModelAttribute("student") Etudiant student) {
			etudiantService.saveStudent(student);
			return "redirect:/students";
		}
		
		@GetMapping("/students/edit/{id}")
		public String editStudentForm(@PathVariable Long id, Model model) {
			model.addAttribute("student", etudiantService.getStudentById(id));
			return "edit_student";
		}

		@PostMapping("/students/{id}")
		public String updateStudent(@PathVariable Long id,
				@ModelAttribute("student") Etudiant student,
				Model model) {
			
			// get student from database by id
			
			Etudiant existingStudent = etudiantService.getStudentById(id);
		/*changed by minu
		 * 	existingStudent.setId(id);
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setEmail(student.getEmail());
			
			// save updated student object
			etudiantService.updateStudent(existingStudent); */
			return "redirect:/students";		
		}
		
		// handler method to handle delete student request
		
		@GetMapping("/students/{id}")
		public String deleteStudent(@PathVariable Long id) {
			etudiantService.deleteStudentById(id);
			return "redirect:/students";
		}
	
}
