package pt.ul.fc.css.example.demo.catalogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.repositories.UtilizadorEmpresarialRepository;

@Component
public class UtilizadoresEmpresarialCatalog {

  @Autowired private UtilizadorEmpresarialRepository ueRepository;

  public UtilizadorEmpresarial addUE(String email, String name, String surname) {
    UtilizadorEmpresarial ue = new UtilizadorEmpresarial(email, name, surname);
    ueRepository.save(ue);
    return ue;
  }

  public UtilizadorEmpresarial getUE(Long id) {
    return ueRepository.findById(id).orElse(null);
  }

  public UtilizadorEmpresarial findByEmail(String email) {
    List<UtilizadorEmpresarial> ues = ueRepository.findByEmail(email);
    if (ues.size() == 0) {
      return null;
    } else {
      return ues.get(0);
    }
  }

  public boolean existsByEmail(String email) {
    if (ueRepository.findByEmail(email).size() > 0) {
      return true;
    } else {
      return false;
    }
  }
}
