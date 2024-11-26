package pt.ul.fc.css.example.demo.handlers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.DefesasCatalog;
import pt.ul.fc.css.example.demo.catalogs.DocentesCatalog;
import pt.ul.fc.css.example.demo.catalogs.JurisCatalog;
import pt.ul.fc.css.example.demo.catalogs.MarcacoesCatalog;
import pt.ul.fc.css.example.demo.catalogs.TesesCatalog;
import pt.ul.fc.css.example.demo.entities.DefesaFinal;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.JuriFinal;
import pt.ul.fc.css.example.demo.entities.Marcacao;
import pt.ul.fc.css.example.demo.entities.Projeto;

@Component
public class MarcaDefesa_Orientador_Handler {

  @Autowired private DocentesCatalog docCatalog;

  @Autowired private MarcacoesCatalog marcacaoCatalog;

  @Autowired private JurisCatalog juriCatalog;

  @Autowired private DefesasCatalog defesaFinalCatalog;

  @Autowired private TesesCatalog teseCatalog;

  public boolean marcaDefesa_Orientador(
      LocalDate data,
      LocalTime horaComeco,
      Duration duracao,
      String sala,
      String docenteArguente,
      String president,
      String teseId) {

    Docente arguente = docCatalog.getDocente(Long.parseLong(docenteArguente));
    if (arguente == null) {
      return false;
    }
    Docente presidente = docCatalog.getDocente(Long.parseLong(president));
    if (presidente == null) {
      return false;
    }
    Projeto tese = teseCatalog.getProjById(Long.parseLong(teseId));

    if (tese == null) {
      Dissertacao dissertacao = teseCatalog.getDissertacaoById(Long.parseLong(teseId));
      if (dissertacao == null) {
        return false;
      }
      if (dissertacao.getDefesa() != null) {
        return false;
      }

      JuriFinal juriFinal =
          juriCatalog.addJuriFinal(dissertacao.getOrientador(), arguente, presidente);

      Marcacao marcacao =
          marcacaoCatalog.addMarcacao(sala, data, horaComeco, duracao, dissertacao.getOrientador());
      if (marcacao == null) {
        System.out.println("Marcacao is null");
        return false;
      }
      DefesaFinal defesaFinal = defesaFinalCatalog.addDefesaFinal(marcacao, juriFinal);

      dissertacao.setDefesa(defesaFinal);

      teseCatalog.addDefesa(dissertacao, defesaFinal);
      return true;
    }
    if (tese.getDefesa() != null) {
      return false;
    }

    JuriFinal juriFinal = juriCatalog.addJuriFinal(tese.getOrientador(), arguente, presidente);

    Marcacao marcacao =
        marcacaoCatalog.addMarcacao(sala, data, horaComeco, duracao, tese.getOrientador());
    if (marcacao == null) {
      return false;
    }
    DefesaFinal defesaFinal = defesaFinalCatalog.addDefesaFinal(marcacao, juriFinal);

    teseCatalog.addDefesa(tese, defesaFinal);

    return true;
  }
}
