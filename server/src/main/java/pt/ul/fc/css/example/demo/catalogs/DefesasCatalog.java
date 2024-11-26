package pt.ul.fc.css.example.demo.catalogs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.DefesaFinal;
import pt.ul.fc.css.example.demo.entities.DefesaProposta;
import pt.ul.fc.css.example.demo.entities.Juri;
import pt.ul.fc.css.example.demo.entities.JuriFinal;
import pt.ul.fc.css.example.demo.entities.Marcacao;
import pt.ul.fc.css.example.demo.repositories.DefesaFinalRepository;
import pt.ul.fc.css.example.demo.repositories.DefesaPropostaRepository;

@Component
public class DefesasCatalog {

  @Autowired private DefesaFinalRepository defesaFinalRepository;

  @Autowired private DefesaPropostaRepository defesaPropostaRepository;

  public DefesaFinal addDefesaFinal(Marcacao marcacao, JuriFinal juri) {
    DefesaFinal defesaFinal = new DefesaFinal(marcacao, juri);
    defesaFinalRepository.save(defesaFinal);
    return defesaFinal;
  }

  public List<Long> getDefesasFinal() {
    ArrayList<Long> defesasFinaisIds = new ArrayList<Long>();
    List<DefesaFinal> defesasFinais = defesaFinalRepository.findAll();
    // for each defesa, add the id to the list
    for (DefesaFinal defesa : defesasFinais) {
      defesasFinaisIds.add(defesa.getId());
    }
    return defesasFinaisIds;
  }

  public DefesaProposta addDefesaProposta(Marcacao marcacao, Juri juri) {
    DefesaProposta defesa = new DefesaProposta(marcacao, juri);
    defesaPropostaRepository.save(defesa);
    return defesa;
  }

  public void addAvaliacaoDF(DefesaFinal defesa, Double avaliacao) {
    defesa.setAvaliacao(avaliacao);
    defesaFinalRepository.save(defesa);
  }

  public void addAvaliacaoDP(DefesaProposta defesa, Double avaliacao) {
    defesa.setAvaliacao(avaliacao);
    defesaPropostaRepository.save(defesa);
  }

  public DefesaFinal getDefesaFinal(Long id) {
    return defesaFinalRepository.findById(id).get();
  }

  public DefesaProposta getDefesaProposta(Long id) {
    return defesaPropostaRepository.findById(id).get();
  }
}
