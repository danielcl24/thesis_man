package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
public class DefesaProposta extends Defesa {

  protected DefesaProposta() {}

  public DefesaProposta(@NonNull Marcacao marcacao, @NonNull Juri juri) {
    super(marcacao, juri);
  }

  public DefesaProposta(@NonNull Marcacao marcacao, @NonNull Juri juri, @NonNull Double avaliacao) {
    super(marcacao, juri, avaliacao);
  }
}
