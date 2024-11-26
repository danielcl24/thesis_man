package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
public class DefesaFinal extends Defesa {

  protected DefesaFinal() {}

  public DefesaFinal(@NonNull Marcacao marcacao, @NonNull JuriFinal juriFinal) {
    super(marcacao, juriFinal);
  }

  public DefesaFinal(@NonNull Marcacao marcacao, @NonNull Juri juri, @NonNull Double avaliacao) {
    super(marcacao, juri, avaliacao);
  }

  public JuriFinal getJuriFinal() {
    return (JuriFinal) super.getJuri();
  }
}
