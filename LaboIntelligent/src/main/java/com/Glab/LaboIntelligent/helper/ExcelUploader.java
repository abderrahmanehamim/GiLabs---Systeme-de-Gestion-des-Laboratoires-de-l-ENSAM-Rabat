package com.Glab.LaboIntelligent.helper;



import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.Glab.LaboIntelligent.*;
import com.Glab.LaboIntelligent.models.*;
import com.Glab.LaboIntelligent.repositories.*;

public class ExcelUploader {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Matricule", "Prénom" ,"Nom", "Semestre", "Departement", "Email" };
	static String SHEET = "Feuille 1";
	@Autowired
	EtudiantRepository etudiantRepository;
	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Etudiant> Etudiants(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Etudiant> students = new ArrayList<Etudiant>();
			List<AppUser> users =new ArrayList<AppUser>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
                
			
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				Cell currentCell = cellsInRow.next();
if(currentCell.getNumericCellValue() <1) break ;
				AppUser user =new AppUser();
				AppRole r1= new AppRole("Etudiant");
				Collection<AppRole> listrole =new ArrayList<AppRole>();
				listrole.add(r1);
				Etudiant student = new Etudiant();
				Departement departemet = new Departement();
				BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {

					switch (cellIdx) {
					case 0:
						System.out.println("student.setMatricule((long) currentCell.getNumericCellValue()");
						student.setMatricule((long) currentCell.getNumericCellValue());
						
						break;

					case 1:
						System.out.println("prenom");
						student.setPrenom(currentCell.getStringCellValue());
						
						break;

					case 2:
						System.out.println("nom");
						student.setNom(currentCell.getStringCellValue());
						
						break;

		
					case 4:
						System.out.println("id");
						departemet.setIddepart((long) currentCell.getNumericCellValue());
						
						break;

					case 5:
						student.setEmail(currentCell.getStringCellValue());
						user.setEmail(currentCell.getStringCellValue());
						break;
					case 6:
						student.setSemestre(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
					
					String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
					user.setUserPassword(userPasswordEncrypted);
					user.setUserRoles(listrole);
					 currentCell = cellsInRow.next();
					cellIdx++;
				}
				
           if(true) {
                student.setDep(departemet);
           }
				students.add(student);
				users.add(user);
			}

			workbook.close();

			return students;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	public static List<AppUser> users(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			
			List<AppUser> users =new ArrayList<AppUser>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				AppUser user =new AppUser();
				AppRole r1= new AppRole("Etudiant");
				Collection<AppRole> listrole =new ArrayList<AppRole>();
				listrole.add(r1);
				
				BCryptPasswordEncoder bcryptPaswwordEncoder=new BCryptPasswordEncoder(10, new SecureRandom());
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:

						break;

					case 1:
						
						break;

					case 2:
						
						break;

					case 3:
						
						break;

					case 4:
						
						break;

					case 5:
if(currentCell.getStringCellValue().equals(""))  break ;
						user.setEmail(currentCell.getStringCellValue());
						break;

					default:
						break;
					}
					
					String userPasswordEncrypted = bcryptPaswwordEncoder.encode("1234");
					user.setUserPassword(userPasswordEncrypted);
					user.setUserRoles(listrole);
					cellIdx++;
				}
				if(user.getEmail() == null) break;
           System.out.println(user);
				users.add(user);
			}

			workbook.close();

			return users;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
	

}
