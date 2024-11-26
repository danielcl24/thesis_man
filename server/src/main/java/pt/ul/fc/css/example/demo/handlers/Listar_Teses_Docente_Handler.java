package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.catalogs.PropostasTeseCatalog;
import pt.ul.fc.css.example.demo.catalogs.TesesCatalog;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;
import pt.ul.fc.css.example.demo.entities.Projeto;

@Component
public class Listar_Teses_Docente_Handler {

  @Autowired private DocentesCatalog docentesCatalog;

  @Autowired private TesesCatalog teseCatalog;

  @Autowired private PropostasTeseCatalog propostasTeseCatalog;

  public List<Projeto> listar_Projs_Docente(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return teseCatalog.getProjsByOrientador(docenteID);
    } else {
      return null;
    }
  }

  public List<Projeto> listar_Projs_Docente_Presi(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return teseCatalog.getProjsByOrientadorPresi(docenteID);
    } else {
      return null;
    }
  }

  public List<Dissertacao> listar_Dissertacoes_Docente_Presi(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return teseCatalog.getDissertacoesByOrientadorPresi(docenteID);
    } else {
      return null;
    }
  }

  public List<Dissertacao> listar_Dissertacoes_Docente(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return teseCatalog.getDissertacoesByOrientador(docenteID);
    } else {
      return null;
    }
  }

  public List<PTProjeto> listar_PTProjs_Docente(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return propostasTeseCatalog.getPTProjetosByDocente(docente);
    } else {
      return null;
    }
  }

  public List<PTDissertacao> listar_PTDissertacoes_Docente(Long docenteID) {

    Docente docente = docentesCatalog.getDocente(docenteID);

    if (docente != null) {
      return propostasTeseCatalog.getPTDissertacoesByDocente(docente);
    } else {
      return null;
    }
  }
}
