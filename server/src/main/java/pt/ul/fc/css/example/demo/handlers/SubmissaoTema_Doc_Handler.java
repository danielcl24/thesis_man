package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.entities.Docente;

@Component
public class SubmissaoTema_Doc_Handler {

  @Autowired private TemasCatalog temasCatalog;

  @Autowired private DocentesCatalog docentesCatalog;

  public boolean submeterTema(
      String titulo,
      String descricao,
      Double remuneracao,
      List<String> mestradosCompativeis,
      Long docenteId) {
    Docente docente = docentesCatalog.getDocente(docenteId);
    if (docente == null) {
      return false;
    }
    temasCatalog.addTema(docente, titulo, descricao, remuneracao, mestradosCompativeis);
    return true;
  }
}
