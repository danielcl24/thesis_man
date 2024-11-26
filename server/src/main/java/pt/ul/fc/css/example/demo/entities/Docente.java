package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

@Entity
public class Docente extends Utilizador {

  @NonNull private Boolean admin;

  protected Docente() {}

  public Docente(
      @NonNull String email,
      @NonNull String name,
      @NonNull String surname,
      @NonNull Boolean admin) {
    super(email, name, surname);
    this.admin = admin;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }
}
