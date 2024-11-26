package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class UtilizadorEmpresarialService {

  protected static UtilizadorEmpresarialDTO dtofy(UtilizadorEmpresarial ue) {
    UtilizadorEmpresarialDTO ue2 = new UtilizadorEmpresarialDTO();
    ue2.setEmail(ue2.getEmail());
    ue2.setId(ue.getId());
    ue2.setName(ue.getName());
    ue2.setSurname(ue.getSurname());
    return ue2;
  }
}
