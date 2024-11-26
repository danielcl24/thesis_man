package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.AlunosCatalog;
import pt.ul.fc.css.example.demo.catalogs.CandidaturasCatalog;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Candidatura;

@Component
public class CancelarCandidatura_Handler {

  @Autowired private CandidaturasCatalog candidaturasCatalog;

  @Autowired private TemasCatalog temasCatalog;

  @Autowired private AlunosCatalog alunosCatalog;

  public boolean cancelarCandidatura(Long c, Long alunoID) {

    Aluno aluno = alunosCatalog.getAluno(alunoID);

    Candidatura candidatura = candidaturasCatalog.getCandidatura(c);

    if (candidatura != null && aluno.equals(candidatura.getCandidato())) {
      System.out.println(candidatura.getTema().getTitulo());
      aluno.decCandidaturasEfetuadas();
      alunosCatalog.save(aluno);
      candidaturasCatalog.deleteCandidatura(candidatura);
      return true;
    } else {
      System.out.println("Candidatura não encontrada");
      return false;
    }
  }
}
