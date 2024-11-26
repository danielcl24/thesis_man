package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Projeto;
import pt.ul.fc.css.example.demo.handlers.Listar_Teses_Docente_Handler;

@Service
public class TesesService {

  @Autowired private Listar_Teses_Docente_Handler listar_Teses_Docente_Handler;

  public TesesService() {}

  public List<Projeto> listar_Projs_Docente(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_Projs_Docente(docenteID);
  }

  public List<Dissertacao> listar_Dissertacoes_Docente(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_Dissertacoes_Docente(docenteID);
  }

  public List<Projeto> listar_Projs_Docente_Presi(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_Projs_Docente_Presi(docenteID);
  }

  public List<Dissertacao> listar_Dissertacoes_Docente_Presi(Long docenteID) {
    return listar_Teses_Docente_Handler.listar_Dissertacoes_Docente_Presi(docenteID);
  }
}
