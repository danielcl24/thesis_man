package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import java.io.File;
import org.springframework.lang.NonNull;

@Entity
public class PTDissertacao extends PropostaTese {

  protected PTDissertacao() {}

  public PTDissertacao(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull Tema tema) {
    super(filename, aluno, orientador, tema);
  }
}
