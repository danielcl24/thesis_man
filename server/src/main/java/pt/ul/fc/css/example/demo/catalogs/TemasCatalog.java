package pt.ul.fc.css.example.demo.catalogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.Utilizador;
import pt.ul.fc.css.example.demo.repositories.TemaRepository;

@Component
public class TemasCatalog {

  @Autowired private TemaRepository temaRepository;

  public Tema addTema(
      Utilizador proponente,
      String titulo,
      String descricao,
      Double remuneracao,
      List<String> mestradosCompativeis) {
    Tema tema = new Tema(proponente, titulo, descricao, remuneracao, mestradosCompativeis);
    temaRepository.save(tema);
    return tema;
  }

  public Tema getTema(Long id) {
    return temaRepository.findById(id).get();
  }

  public List<Tema> getTemas() {
    return temaRepository.findAll();
  }

  public List<Tema> getTemasSemAlunos() {
    List<Tema> temas = temaRepository.findAll();
    temas.removeIf(a -> a.getAluno() != null);
    return temas;
  }

  public void save(Tema tema) {
    temaRepository.save(tema);
  }

  public List<Tema> getTemasByProponente(Utilizador proponente) {
    return temaRepository.findByProponente(proponente);
  }
}
