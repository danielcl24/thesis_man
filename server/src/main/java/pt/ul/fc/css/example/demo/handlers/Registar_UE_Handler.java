package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.UtilizadoresEmpresarialCatalog;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class Registar_UE_Handler {

  @Autowired private UtilizadoresEmpresarialCatalog ueCatalog;

  public UtilizadorEmpresarial registarUE(String email, String name, String surname) {
    if (ueCatalog.existsByEmail(email)) {
      return null;
    } else {
      return ueCatalog.addUE(email, name, surname);
    }
  }
}
