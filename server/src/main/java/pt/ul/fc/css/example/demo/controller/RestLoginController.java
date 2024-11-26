package pt.ul.fc.css.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.ul.fc.css.example.demo.services.AlunoDTO;
import pt.ul.fc.css.example.demo.services.AlunoService;

@RestController()
@RequestMapping("api")
public class RestLoginController {

  @Autowired private AlunoService alunoService;

  @GetMapping("/login")
  ResponseEntity<?> login(@RequestParam("email") String email) {
    AlunoDTO a = alunoService.login(email);
    if (a != null) {
      return ResponseEntity.ok().body(a);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
