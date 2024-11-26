package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.File;
import org.springframework.lang.NonNull;

@Entity
public class PTProjeto extends PropostaTese {

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "orientador_ext_id", referencedColumnName = "id")
  private UtilizadorEmpresarial orientadorExterno;

  protected PTProjeto() {}

  public PTProjeto(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull Tema tema,
      @NonNull UtilizadorEmpresarial orientadorExterno) {
    super(filename, aluno, orientador, tema);
    this.orientadorExterno = orientadorExterno;
  }

  public UtilizadorEmpresarial getOrientadorExterno() {
    return orientadorExterno;
  }

  public void setOrientadorExterno(UtilizadorEmpresarial orientadorExterno) {
    this.orientadorExterno = orientadorExterno;
  }
}
