package pt.ul.fc.css.example.demo.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tese;
import pt.ul.fc.css.example.demo.services.TeseDTO;
import pt.ul.fc.css.example.demo.services.TeseService;

@RestController()
@RequestMapping("api")
public class RestTeseController {

  @Autowired private TeseService teseService;

  @GetMapping("/teses/{id}")
  TeseDTO getTese(@PathVariable Long id) {
    Tese t = teseService.getTeseByAluno(id);
    return teseService.dtofy(t);
  }

  @PostMapping("/teses/proposta/{id}")
  ResponseEntity<?> subemeteProposta(
      @PathVariable Long id, @RequestParam("file") MultipartFile file) {
    Tese t = teseService.getTeseByAluno(id);
    boolean isProj = t.isProjeto();
    try {
      File f = multipartToFile(file, file.getOriginalFilename());
      System.out.println(f.length());
      PropostaTese res = teseService.submeteProposta(id, f, isProj);
      if (res != null) return ResponseEntity.ok().body(teseService.dtofy(t));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(null);
  }

  @PostMapping("/teses/final/{id}")
  ResponseEntity<?> subemeteFinal(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    Tese t = teseService.getTeseByAluno(id);
    boolean isProj = t.isProjeto();
    try {
      File f = multipartToFile(file, file.getOriginalFilename());
      System.out.println(f.length());
      Tese res = teseService.submeteFinal(id, f, isProj);
      if (res != null) return ResponseEntity.ok().body(teseService.dtofy(t));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(null);
  }

  private static File multipartToFile(MultipartFile multipart, String fileName)
      throws IllegalStateException, IOException {
    File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
    multipart.transferTo(convFile);
    return convFile;
  }
}
