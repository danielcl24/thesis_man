package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.lang.NonNull;

@Entity
public class JuriFinal extends Juri {

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "presidente_id", referencedColumnName = "id", nullable = false)
  private Docente presidente;

  protected JuriFinal() {}

  public JuriFinal(
      @NonNull Docente orientador, @NonNull Docente arguente, @NonNull Docente presidente) {
    super(orientador, arguente);
    this.presidente = presidente;
  }

  public Docente getPresidente() {
    return presidente;
  }

  public void setPresidente(Docente presidente) {
    this.presidente = presidente;
  }
}
