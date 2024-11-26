package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DefesasCatalog;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.entities.DefesaFinal;
import pt.ul.fc.css.example.demo.entities.Docente;

@Component
public class RegistaNotaDefTese_PresiJuri_Handler {

  @Autowired private DocentesCatalog docentesCatalog;

  @Autowired private DefesasCatalog defesasCatalog;

  public void registoNota(Long defesa, Long presidente, Double nota) {
    Docente presi = docentesCatalog.getDocente(presidente);

    DefesaFinal defesaFinal = defesasCatalog.getDefesaFinal(defesa);

    if (defesaFinal.getJuriFinal().getPresidente().equals(presi)) {
      defesasCatalog.addAvaliacaoDF(defesaFinal, nota);
    }
  }
}
