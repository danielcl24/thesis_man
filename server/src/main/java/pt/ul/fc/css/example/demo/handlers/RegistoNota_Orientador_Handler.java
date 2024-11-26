package pt.ul.fc.css.example.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DefesasCatalog;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.entities.DefesaProposta;
import pt.ul.fc.css.example.demo.entities.Docente;

@Component
public class RegistoNota_Orientador_Handler {

  @Autowired private DocentesCatalog docentesCatalog;

  @Autowired private DefesasCatalog defesasCatalog;

  public void registoNota(Long defesa, Long docente, Double nota) {
    Docente orientador = docentesCatalog.getDocente(docente);

    DefesaProposta defesaProposta = defesasCatalog.getDefesaProposta(defesa);

    if (defesaProposta.getJuri().getOrientador().equals(orientador)) {
      defesasCatalog.addAvaliacaoDP(defesaProposta, nota);
    }
  }
}
