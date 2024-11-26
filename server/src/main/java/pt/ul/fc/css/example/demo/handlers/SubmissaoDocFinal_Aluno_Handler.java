package pt.ul.fc.css.example.demo.handlers;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.PropostasTeseCatalog;
import pt.ul.fc.css.example.demo.catalogs.TesesCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.Tese;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class SubmissaoDocFinal_Aluno_Handler {

  @Autowired private TesesCatalog teseCatalog;

  @Autowired private PropostasTeseCatalog propostaCatalog;

  public Tese submissaoFinalDiss(File filename, Aluno aluno, Docente orientador, Tema tema) {
    PropostaTese proposta = new PropostaTese(filename, aluno, orientador, tema);
    return teseCatalog.addSubTeseDissertacao(filename, aluno, orientador, proposta, false);
  }

  public Tese submissaoFinalProj(
      File filename,
      Aluno aluno,
      Docente orientador,
      UtilizadorEmpresarial orientadorExterno,
      Tema tema) {
    PropostaTese proposta = new PropostaTese(filename, aluno, orientador, tema);
    return teseCatalog.addSubTeseProjeto(
        filename, aluno, orientador, proposta, orientadorExterno, true);
  }
}
