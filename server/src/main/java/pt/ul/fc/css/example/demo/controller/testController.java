package pt.ul.fc.css.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ul.fc.css.example.demo.repositories.AlunoRepository;
import pt.ul.fc.css.example.demo.repositories.DissertacaoRepository;
import pt.ul.fc.css.example.demo.repositories.ProjetoRepository;
import pt.ul.fc.css.example.demo.repositories.TemaRepository;

@RestController
@RequestMapping("/test")
public class testController {

  @Autowired private AlunoRepository alunoRepository;

  @Autowired private TemaRepository temaRepository;

  @Autowired private DissertacaoRepository dissertacaoRepository;

  @Autowired private ProjetoRepository projetoRepository;

  public testController() {}

  @GetMapping("/temas")
  public String getTemas() {
    return temaRepository.findAll().toString();
  }

  @GetMapping("/alunos")
  public String getAlunos() {
    return alunoRepository.findAll().toString();
  }

  @GetMapping("/dissertacoes")
  public String getDissertacoes() {
    return dissertacaoRepository.findAll().toString();
  }

  @GetMapping("/projetos")
  public String getProjetos() {
    return projetoRepository.findAll().toString();
  }
}
