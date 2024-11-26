package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.AlunosCatalog;
import pt.ul.fc.css.example.demo.catalogs.CandidaturasCatalog;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Tema;

@Component
public class CandidatarTema_Aluno_Handler {

  @Autowired private TemasCatalog temasCatalog;

  @Autowired private CandidaturasCatalog candidaturasCatalog;

  @Autowired private AlunosCatalog alunosCatalog;

  public boolean candidatarTema(Long tema, Long aluno) {

    Aluno alunoCandidato = alunosCatalog.getAluno(aluno);

    Tema temaCandidato = temasCatalog.getTema(tema);

    if (alunoCandidato.getCandidaturasEfetuadas() < 5
        && temaCandidato.getMestradosCompativeis().contains(alunoCandidato.getMestrado())) {
      alunoCandidato.incCandidaturasEfetuadas();
      alunosCatalog.save(alunoCandidato);
      candidaturasCatalog.addCandidatura("Pendente", alunoCandidato, temaCandidato);
      return true;
    } else {
      System.out.println("Aluno não pode candidatar-se a este tema");
      return false;
    }
  }
}
