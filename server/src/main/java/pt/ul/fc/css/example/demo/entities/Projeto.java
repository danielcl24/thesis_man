package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.File;
import org.springframework.lang.NonNull;

@Entity
public class Projeto extends Tese {

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "orientador_ext_id", referencedColumnName = "id")
  private UtilizadorEmpresarial orientadorExterno;

  protected Projeto() {}

  public Projeto(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull UtilizadorEmpresarial orientadorExterno,
      @NonNull PropostaTese proposta,
      @NonNull boolean isProjeto) {
    super(filename, aluno, orientador, proposta, isProjeto);
    this.orientadorExterno = orientadorExterno;
  }

  public UtilizadorEmpresarial getOrientadorExterno() {
    return orientadorExterno;
  }

  public void setOrientadorExterno(UtilizadorEmpresarial orientadorExterno) {
    this.orientadorExterno = orientadorExterno;
  }
}
