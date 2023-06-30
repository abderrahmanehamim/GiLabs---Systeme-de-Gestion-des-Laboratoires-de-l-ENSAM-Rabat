package com.Glab.LaboIntelligent.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.models.Etudiant;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.services.EtudiantService;
import com.Glab.LaboIntelligent.services.impl.ExcelService;
import com.Glab.LaboIntelligent.helper.ExcelUploader;


@Controller
public class EtudiantContoller {
	private String fileLocation;
	// Excel

		@Autowired
		ExcelService fileService;
	
		@Autowired
		private EtudiantRepository etudiantRepository;
		
				
	private EtudiantService etudiantService ;
	
	public EtudiantContoller (EtudiantService etudiantService ) {
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
				return "excel";
		}
				

		message = "Please upload an excel file!";
		model.addAttribute("Message", message);
		return "excel";
	}
	
	@PostMapping("/Admin/uploadFileProf")
	public String uploadFileProf (Model model, @RequestParam("file") MultipartFile file)throws IOException {
		String message = "";

		if (com.Glab.LaboIntelligent.helper.ExcelUploader.hasExcelFormat(file)) {
			
				fileService.saveExcelProf(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				model.addAttribute("Message", message);
				return "excel";
		}
				

		message = "Please upload an excel file!";
		model.addAttribute("Message", message);
		return "excel";
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
