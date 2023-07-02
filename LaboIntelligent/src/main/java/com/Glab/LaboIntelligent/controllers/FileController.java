package com.Glab.LaboIntelligent.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 import com.Glab.LaboIntelligent.models.FileInfo;

import com.Glab.LaboIntelligent.services.FilesStorageService;

@Controller
public class FileController {

  @Autowired
  FilesStorageService storageService;




  @GetMapping("/files/new")
  public String newFile(Model model) {
    return "addpdf";
  }

  @PostMapping("/files/upload")
  public String uploadFile(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    String message = "";

    try {
      storageService.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      redirectAttributes.addFlashAttribute("message", message);

      return "redirect:/files"; // Redirect to the list of files after successful upload
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);

      return "addpdf"; // Return to the addpdf.html view with the error message
    }
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