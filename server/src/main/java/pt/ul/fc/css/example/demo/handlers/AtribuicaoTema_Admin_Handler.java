package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.AlunosCatalog;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Tema;

@Component
public class AtribuicaoTema_Admin_Handler {
  @Autowired private AlunosCatalog alunosCatalog;

  @Autowired private TemasCatalog temasCatalog;

  public boolean atribuirTema(Long alunoId, Long temaId) {

    Aluno aluno = alunosCatalog.getAluno(alunoId);
    Tema tema = temasCatalog.getTema(temaId);
    if (aluno == null || tema == null) {
      return false;
    }
    if (aluno.getTema() != null) {
      return false;
    }
    if (tema.getAluno() != null) {
      return false;
    }
    aluno.setTema(tema);
    tema.setAluno(aluno);
    alunosCatalog.save(aluno);
    temasCatalog.save(tema);
    return true;
  }
}
