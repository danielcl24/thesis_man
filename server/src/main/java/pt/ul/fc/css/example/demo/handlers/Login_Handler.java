package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.catalogs.UtilizadoresEmpresarialCatalog;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class Login_Handler {

  @Autowired private UtilizadoresEmpresarialCatalog ueCatalog;

  @Autowired private DocentesCatalog docentesCatalog;

  public UtilizadorEmpresarial login_web(String email) {
    UtilizadorEmpresarial ue = ueCatalog.findByEmail(email);
    if (ue == null) {
      return null;
    }
    return ue;
  }

  public Docente login(String email) {
    Docente docente = docentesCatalog.getDocente(email);
    if (docente == null) {
      return null;
    }
    return docente;
  }
}
