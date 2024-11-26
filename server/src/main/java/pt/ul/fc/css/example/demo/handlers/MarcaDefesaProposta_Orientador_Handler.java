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
import pt.ul.fc.css.example.demo.catalogs.PropostasTeseCatalog;
import pt.ul.fc.css.example.demo.entities.DefesaProposta;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Juri;
import pt.ul.fc.css.example.demo.entities.Marcacao;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;

@Component
public class MarcaDefesaProposta_Orientador_Handler {

  @Autowired private DocentesCatalog docCatalog;

  @Autowired private MarcacoesCatalog marcacaoCatalog;

  @Autowired private JurisCatalog juriCatalog;

  @Autowired private DefesasCatalog defesaPropostaCatalog;

  @Autowired private PropostasTeseCatalog propostasTeseCatalog;

  public boolean marcaDefesaProposta_Orientador(
      LocalDate data,
      LocalTime horaComeco,
      Duration duracao,
      String sala,
      String docenteArguente,
      String teseId) {

    Docente arguente = docCatalog.getDocente(Long.parseLong(docenteArguente));
    if (arguente == null) {
      return false;
    }

    PTProjeto tese = propostasTeseCatalog.getPTProjById(Long.parseLong(teseId));

    if (tese == null) {
      PTDissertacao dissertacao = propostasTeseCatalog.getPTDissertacaoById(Long.parseLong(teseId));
      if (dissertacao == null) {
        return false;
      }
      if (dissertacao.getDefesa() != null) {
        return false;
      }

      Juri juriFinal = juriCatalog.addJuri(dissertacao.getOrientador(), arguente);

      Marcacao marcacao =
          marcacaoCatalog.addMarcacao(sala, data, horaComeco, duracao, dissertacao.getOrientador());
      if (marcacao == null) {
        return false;
      }
      DefesaProposta defesaFinal = defesaPropostaCatalog.addDefesaProposta(marcacao, juriFinal);

      dissertacao.setDefesa(defesaFinal);

      propostasTeseCatalog.addDefesaProposta(dissertacao, defesaFinal);
      return true;
    }
    if (tese.getDefesa() != null) {
      return false;
    }

    Juri juriFinal = juriCatalog.addJuri(tese.getOrientador(), arguente);

    Marcacao marcacao =
        marcacaoCatalog.addMarcacao(sala, data, horaComeco, duracao, tese.getOrientador());
    if (marcacao == null) {
      return false;
    }
    DefesaProposta defesaFinal = defesaPropostaCatalog.addDefesaProposta(marcacao, juriFinal);

    propostasTeseCatalog.addDefesaProposta(tese, defesaFinal);

    return true;
  }
}
