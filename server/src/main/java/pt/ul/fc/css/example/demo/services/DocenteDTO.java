package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;

@Component
public class DocenteDTO extends UtilizadorDTO {

  private boolean admin;

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
