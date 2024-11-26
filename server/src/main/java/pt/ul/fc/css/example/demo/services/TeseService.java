package pt.ul.fc.css.example.demo.services;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.AlunosCatalog;
import pt.ul.fc.css.example.demo.catalogs.TesesCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.Tese;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.handlers.SubmissaoDocFinal_Aluno_Handler;
import pt.ul.fc.css.example.demo.handlers.SubmissaoProposta_Aluno_Handler;

@Component
public class TeseService {

  @Autowired private TesesCatalog teseCatalog;

  @Autowired private AlunosCatalog alunoCatalog;

  @Autowired private SubmissaoProposta_Aluno_Handler propHandler;

  @Autowired private SubmissaoDocFinal_Aluno_Handler finalHandler;

  public Tese getTeseByAluno(Long id) {
    Aluno a = alunoCatalog.getAluno(id);
    Tese t = teseCatalog.getTese(a);
    return t;
  }

  public PropostaTese submeteProposta(Long id, File f, boolean isProj) {
    Aluno aluno = alunoCatalog.getAluno(id);
    Tese tese = teseCatalog.getTese(aluno);
    Tema tema = tese.getTema();
    Docente orientador = tese.getOrientador();
    if (isProj) {
      UtilizadorEmpresarial orientadorExterno = (UtilizadorEmpresarial) tema.getProponente();
      return propHandler.submissaoPropostaProjeto(f, aluno, orientador, tema, orientadorExterno);
    } else {
      return propHandler.submissaoPropostaDissertacao(f, aluno, orientador, tema);
    }
  }

  public Tese submeteFinal(Long id, File f, boolean isProj) {
    Aluno aluno = alunoCatalog.getAluno(id);
    Tese tese = teseCatalog.getTese(aluno);
    Tema tema = tese.getTema();
    Docente orientador = tese.getOrientador();
    if (isProj) {
      UtilizadorEmpresarial orientadorExterno = (UtilizadorEmpresarial) tema.getProponente();
      return finalHandler.submissaoFinalProj(f, aluno, orientador, orientadorExterno, tema);
    } else {
      return finalHandler.submissaoFinalDiss(f, aluno, orientador, tema);
    }
  }

  public TeseDTO dtofy(Tese t) {
    TeseDTO t2 = new TeseDTO();
    t2.setId(t.getId());
    t2.setProjeto(t.isProjeto());
    return t2;
  }
}
