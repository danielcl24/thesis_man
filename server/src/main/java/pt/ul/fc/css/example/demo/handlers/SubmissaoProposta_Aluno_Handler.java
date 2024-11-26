package pt.ul.fc.css.example.demo.handlers;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.PropostasTeseCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class SubmissaoProposta_Aluno_Handler {

  @Autowired private PropostasTeseCatalog propostasTeseCatalog;

  public PropostaTese submissaoPropostaDissertacao(
      File filename, Aluno aluno, Docente orientador, Tema tema) {

    return propostasTeseCatalog.addSubPropostaTeseDissertacao(filename, aluno, orientador, tema);
  }

  public PropostaTese submissaoPropostaProjeto(
      File filename,
      Aluno aluno,
      Docente orientador,
      Tema tema,
      UtilizadorEmpresarial orientadorExterno) {

    return propostasTeseCatalog.addSubPropostaTeseProjeto(
        filename, aluno, orientador, tema, orientadorExterno);
  }
}
