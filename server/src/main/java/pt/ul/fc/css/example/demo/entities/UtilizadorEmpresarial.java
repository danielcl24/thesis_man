package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
public class UtilizadorEmpresarial extends Utilizador {

  protected UtilizadorEmpresarial() {}

  public UtilizadorEmpresarial(
      @NonNull String email, @NonNull String name, @NonNull String surname) {
    super(email, name, surname);
  }
}
