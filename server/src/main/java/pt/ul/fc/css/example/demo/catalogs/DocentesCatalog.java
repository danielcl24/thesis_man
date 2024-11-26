package pt.ul.fc.css.example.demo.catalogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.repositories.DocenteRepository;

@Component
public class DocentesCatalog {

  @Autowired private DocenteRepository docenteRepository;

  public Docente addDocente(String email, String name, String surname, Boolean admin) {
    Docente docente = new Docente(email, name, surname, admin);
    docenteRepository.save(docente);
    return docente;
  }

  public Docente getDocente(String email) {
    List<Docente> docentes = docenteRepository.findByEmail(email);
    if (docentes.size() == 0) {
      return null;
    } else {
      return docentes.get(0);
    }
  }

  public Docente getDocente(Long id) {
    return docenteRepository.findById(id).orElse(null);
  }

  public List<Docente> listar_Docentes() {
    return docenteRepository.findAll();
  }
}
