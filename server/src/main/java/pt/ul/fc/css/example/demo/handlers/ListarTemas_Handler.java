package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.TemasCatalog;
import pt.ul.fc.css.example.demo.entities.Tema;

@Component
public class ListarTemas_Handler {

  @Autowired private TemasCatalog temasCatalog;

  public List<Tema> listarTemas() {
    return temasCatalog.getTemas();
  }
}
