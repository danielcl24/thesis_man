package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.MarcacoesCatalog;
import pt.ul.fc.css.example.demo.entities.Marcacao;

@Component
public class Listar_Marcacoes_Handler {

  @Autowired private MarcacoesCatalog marcacoesCatalog;

  public Listar_Marcacoes_Handler() {}

  public List<Marcacao> listar_Marcacoes() {
    return marcacoesCatalog.listar_Marcacoes();
  }
}
