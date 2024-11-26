package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ul.fc.css.example.demo.services.AlunoDTO;
import pt.ul.fc.css.example.demo.services.AlunoService;
import pt.ul.fc.css.example.demo.services.CandidaturaDTO;

@RestController()
@RequestMapping("api")
public class RestAlunoController {

  @Autowired private AlunoService alunoService;

  @GetMapping("/alunos")
  List<AlunoDTO> all() {
    return alunoService.getAlunos();
  }

  @GetMapping("/alunos/{id}/candidaturas")
  List<CandidaturaDTO> candidaturas(@PathVariable Long id) {
    return alunoService.getCandidaturas(id);
  }

  @PatchMapping("/alunos/{idAluno}/{idTema}")
  ResponseEntity<?> candidataTema(@PathVariable Long idAluno, @PathVariable Long idTema) {
    AlunoDTO a = alunoService.candidataTema(idAluno, idTema);
    if (a != null) return ResponseEntity.ok().body(a);
    else return ResponseEntity.ok(null);
  }

  @DeleteMapping("/alunos/{idAluno}/{idCandidatura}")
  ResponseEntity<?> cancelaCandidatura(
      @PathVariable Long idAluno, @PathVariable Long idCandidatura) {
    AlunoDTO a = alunoService.cancelaCandidatura(idAluno, idCandidatura);
    if (a != null) return ResponseEntity.ok().body(a);
    else return ResponseEntity.ok(null);
  }
}
