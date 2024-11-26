package pt.ul.fc.css.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Utilizador;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class UtilizadorService {

  @Autowired private DocentesCatalog docentesCatalog;

  protected static UtilizadorDTO dtofy(Utilizador u) {
    if (u instanceof Docente) {
      return DocenteService.dtofy((Docente) u);
    } else if (u instanceof UtilizadorEmpresarial) {
      return UtilizadorEmpresarialService.dtofy((UtilizadorEmpresarial) u);
    } else {
      UtilizadorDTO u2 = new UtilizadorDTO();
      u2.setEmail(u.getEmail());
      u2.setId(u.getId());
      u2.setName(u.getName());
      u2.setSurname(u.getSurname());
      return u2;
    }
  }
}
