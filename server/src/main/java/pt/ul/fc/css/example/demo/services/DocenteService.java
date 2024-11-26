package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.entities.Docente;

@Component
public class DocenteService {

  @Autowired private DocentesCatalog docentesCatalog;

  protected static DocenteDTO dtofy(Docente d) {
    DocenteDTO d2 = new DocenteDTO();
    d2.setEmail(d.getEmail());
    d2.setId(d.getId());
    d2.setName(d.getName());
    d2.setSurname(d.getSurname());
    d2.setAdmin(d.getAdmin());
    return d2;
  }

  public boolean isAdmin(Long id) {
    Docente d = docentesCatalog.getDocente(id);
    if (d == null) {
      return false;
    }
    return d.getAdmin();
  }

  public Docente getDocente(Long id) {
    return docentesCatalog.getDocente(id);
  }

  public List<Docente> listar_Docentes() {
    return docentesCatalog.listar_Docentes();
  }
}
