package pt.ul.fc.css.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.catalogs.UtilizadoresEmpresarialCatalog;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;

@Component
public class TemaService {

  @Autowired private TemasCatalog temaCatalog;

  @Autowired private UtilizadoresEmpresarialCatalog utilizadoresEmpresarialCatalog;

  @Autowired private DocentesCatalog docentesCatalog;

  public List<TemaDTO> getTemasSemAlunos() {
    ArrayList<TemaDTO> arr = new ArrayList<>();
    for (Tema t : temaCatalog.getTemasSemAlunos()) {
      TemaDTO dto = dtofy(t);
      arr.add(dto);
    }

    return arr;
  }

  public List<Tema> getTemasSemAlunos2() {
    return temaCatalog.getTemasSemAlunos();
  }

  protected static TemaDTO dtofy(Tema t) {
    TemaDTO t2 = new TemaDTO();
    t2.setId(t.getId());
    t2.setProponente(UtilizadorService.dtofy(t.getProponente()));
    t2.setTitulo(t.getTitulo());
    t2.setDescricao(t.getDescricao());
    t2.setRemuneracao(t.getRemuneracao());
    t2.setMestradosCompativeis(t.getMestradosCompativeis());
    if (t.getAluno() != null) {
      t2.setAluno(t.getAluno().getId());
    }
    return t2;
  }

  public List<Tema> getTemasByProponenteId(Long id) {
    UtilizadorEmpresarial ue = utilizadoresEmpresarialCatalog.getUE(id);
    if (ue == null) {
      Docente docente = docentesCatalog.getDocente(id);
      if (docente == null) {
        return null;
      }
      List<Tema> temas = temaCatalog.getTemasByProponente(docente);
      return temas;
    }
    List<Tema> temas = temaCatalog.getTemasByProponente(ue);
    return temas;
  }

  public List<Tema> getAllThemes() {
    return temaCatalog.getTemas();
  }

  public Tema getTema(Long id) {
    return temaCatalog.getTema(id);
  }
}
