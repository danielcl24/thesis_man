package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import java.io.File;
import org.springframework.lang.NonNull;

@Entity
public class Dissertacao extends Tese {

  protected Dissertacao() {}

  public Dissertacao(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull PropostaTese proposta,
      @NonNull boolean isProjeto) {
    super(filename, aluno, orientador, proposta, isProjeto);
  }
}
