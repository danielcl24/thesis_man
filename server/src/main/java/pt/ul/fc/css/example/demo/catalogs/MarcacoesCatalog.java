package pt.ul.fc.css.example.demo.catalogs;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Marcacao;
import pt.ul.fc.css.example.demo.repositories.MarcacaoRepository;

@Component
public class MarcacoesCatalog {

  @Autowired private MarcacaoRepository marcacaoRepository;

  public Marcacao addMarcacao(
      String sala, LocalDate data, LocalTime horaComeco, Duration duracao, Docente responsavel) {
    if (!sala.equals("online")) {
      List<Marcacao> marcacoes = marcacaoRepository.findAll();
      Marcacao marcacao = new Marcacao(sala, data, horaComeco, duracao, responsavel);
      for (Marcacao m : marcacoes) {
        if (m.estaSobreposta(marcacao)) {

          return null;
        }
      }
      marcacaoRepository.save(marcacao);
      return marcacao;
    } else {
      Marcacao marcacao = new Marcacao(data, horaComeco, duracao, responsavel);
      marcacaoRepository.save(marcacao);
      return marcacao;
    }
  }

  public void save(Marcacao marcacao) {
    marcacaoRepository.save(marcacao);
  }

  public List<Marcacao> listar_Marcacoes() {
    return marcacaoRepository.findAll();
  }
}
