package com.Glab.LaboIntelligent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Glab.LaboIntelligent.models.AppRole;
import com.Glab.LaboIntelligent.models.AppUser;
import com.Glab.LaboIntelligent.models.Departement;
import com.Glab.LaboIntelligent.models.Laboratoire;
import com.Glab.LaboIntelligent.repositories.AppRoleRepository;
import com.Glab.LaboIntelligent.repositories.AppUserRepository;
import com.Glab.LaboIntelligent.repositories.DepartmentRepository;
import com.Glab.LaboIntelligent.repositories.EtudiantRepository;
import com.Glab.LaboIntelligent.repositories.LaboratoiresRepository;

@SpringBootApplication
public class LaboIntelligentApplication implements CommandLineRunner {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(LaboIntelligentApplication.class, args);
	}
	@Autowired
	private EtudiantRepository etudiantRepository ;
	@Autowired
	private DepartmentRepository departmentRepository ;
	@Autowired
	private  LaboratoiresRepository laboratoiresRepository ;
	
	@Override
	public void run(String... args) throws Exception {
		
		// initialise ROLES AND USERS //
		AppRole r1= new AppRole("Etudiant");
		AppRole r2= new AppRole("Admin");

		appRoleRepository.save(r1);
		appRoleRepository.save(r2);

		Collection<AppRole> listrole =new ArrayList<AppRole>();
		Collection<AppRole> listroleAdmi =new ArrayList<AppRole>();
		listroleAdmi.add(r2);
		listrole.add(r1);
		BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
		String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
		AppUser u6 =new AppUser("admin2@esp.mr", userPasswordEncrypted, listroleAdmi);
		appUserRepository.save(u6);
		
	

		  List<Departement> departmentsList = new ArrayList<>();

	       
	        Departement department1 = new Departement();
	        department1.setIddepart(1);
	        department1.setDepartmentName("Génie Mecanique");
	       
	        
	        departmentsList.add(department1);

	        Departement department2 = new Departement();
	        department2.setIddepart(2);
	        department2.setDepartmentName("Mathematiques Appliquees");
	       
	        departmentsList.add(department2);
	        
	        Departement department3 = new Departement();
	        department3.setIddepart(3);
	        department3.setDepartmentName("Génie Informatique");
	       
	        departmentsList.add(department3);

	        Departement department4 = new Departement();
	        department4.setIddepart(4);
	        department4.setDepartmentName("Génie Electrique");
	     
	        departmentsList.add(department4);
	        
	        Departement department5 = new Departement();
	        department5.setIddepart(5);
	        department5.setDepartmentName("Langues/Communication");
	       
	        departmentsList.add(department5);

	        Departement department6 = new Departement();
	        department6.setIddepart(6);
	        department6.setDepartmentName("Génie Economie et Management");
	        
	        departmentsList.add(department6);
	        
	        Departement department7 = new Departement();
	        department7.setIddepart(7);
	        
	        department7.setDepartmentName("Génie Biomédical");
	        departmentsList.add(department7);

	       for(Departement dep:departmentsList) {
	    	   departmentRepository.save(dep);
	       }

	    // initialise Labos //

	       List<Collection<Laboratoire>> listDepLabos = new ArrayList<>();

			 List<Laboratoire> departemenMecLab1 = new ArrayList<>();

			 
			 Laboratoire Meclab1 = new Laboratoire();
			 Meclab1.setLaboname("Lab Mecanique");
			 Meclab1.setCode("mecLab1");
			 Meclab1.setActivite("Activity 1");
			 Meclab1.setDep(department1);
			 // Set the department for the laboratory
			 Laboratoire Meclab2 = new Laboratoire();
			 Meclab2.setLaboname("Lab Mecanique");
			 Meclab2.setCode("mecLab2");
			 Meclab2.setActivite("Activity 1");
			 Meclab2.setDep(department1);
			 departemenMecLab1.add(Meclab1);
			 departemenMecLab1.add(Meclab2);

			 listDepLabos.add(departemenMecLab1);
			 List<Laboratoire> departmentMathLab1 = new ArrayList<>();

			 Laboratoire Mathlab1 = new Laboratoire();
			 Mathlab1.setLaboname("Lab Math ");
			 Mathlab1.setCode("mathlab1");
			 Mathlab1.setActivite("Activity 3");
			 Mathlab1.setDep(department2); // Set the department for the laboratory
			 departmentMathLab1.add(Mathlab1);
			 listDepLabos.add(departmentMathLab1);

			 List<Laboratoire> departmentInfoLab1 = new ArrayList<>();

			 Laboratoire Infolab1 = new Laboratoire();
			 Infolab1.setLaboname("Lab Informatique");
			 Infolab1.setCode("infolab1");
			 Infolab1.setActivite("Activity 3");
			 Infolab1.setDep(department3); // Set the department for the laboratory
			 departmentInfoLab1.add(Infolab1);
			 
			 
			 listDepLabos.add(departmentInfoLab1);
			 List<Laboratoire> departmentElecLab1 = new ArrayList<>();

			 Laboratoire Eleclab1 = new Laboratoire();
			 Eleclab1.setLaboname("Lab Electrique");
			 Eleclab1.setCode("eleclab1");
			 Eleclab1.setActivite("Activity 3");
			 Eleclab1.setDep(department4); // Set the department for the laboratory
			 departmentElecLab1.add(Eleclab1);
			 
			 listDepLabos.add(departmentElecLab1);
			 List<Laboratoire> departmentLanguelab1 = new ArrayList<>();

			 Laboratoire Languelab1 = new Laboratoire();
			 Languelab1.setLaboname("Lab Langue");
			 Languelab1.setCode("languelab1");
			 Languelab1.setActivite("Activity 3");
			 Languelab1.setDep(department5); // Set the department for the laboratory
			 departmentLanguelab1.add(Languelab1);
			
			 listDepLabos.add(departmentLanguelab1);

			 List<Laboratoire> departmenEcoLab1 = new ArrayList<>();

			 Laboratoire Ecolab1 = new Laboratoire();
			 Ecolab1.setLaboname("Lab Economie");
			 Ecolab1.setCode("ecolab1");
			 Ecolab1.setActivite("Activity 3");
			 Ecolab1.setDep(department6); // Set the department for the laboratory
			 departmenEcoLab1.add(Ecolab1);
			 
			 listDepLabos.add(departmenEcoLab1);
			 
			 List<Laboratoire> departmentBiomlab1 = new ArrayList<>();

			 Laboratoire Biomlab1 = new Laboratoire();
			 Biomlab1.setLaboname("Lab Biomédicale");
			 Biomlab1.setCode("biomlab1");
			 Biomlab1.setActivite("Activity 3");
			 Biomlab1.setDep(department7); // Set the department for the laboratory
			 departmentBiomlab1.add(Biomlab1);
			 
			 listDepLabos.add(departmentBiomlab1);


		
			 for(Collection<Laboratoire> labs : listDepLabos) {
				 for(Laboratoire lab:labs) {
					 laboratoiresRepository.save(lab);
				 }
			 }
	       
			 
			 
	}
}
