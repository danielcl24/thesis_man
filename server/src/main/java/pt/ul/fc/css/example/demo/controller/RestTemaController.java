package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ul.fc.css.example.demo.services.TemaDTO;
import pt.ul.fc.css.example.demo.services.TemaService;

@RestController()
@RequestMapping("api")
public class RestTemaController {

  @Autowired private TemaService temaService;

  @GetMapping("/temas")
  List<TemaDTO> all() {
    return temaService.getTemasSemAlunos();
  }
}
