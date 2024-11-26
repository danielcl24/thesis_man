package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;
import pt.ul.fc.css.example.demo.handlers.Listar_Teses_Docente_Handler;

@Service
public class PropostasTeseService {

  @Autowired private Listar_Teses_Docente_Handler listar_Teses_Docente_Handler;

  public PropostasTeseService() {}

  public List<PTProjeto> listar_PTProjs_Docente(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_PTProjs_Docente(docenteID);
  }

  public List<PTDissertacao> listar_PTDissertacoes_Docente(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_PTDissertacoes_Docente(docenteID);
  }
}
