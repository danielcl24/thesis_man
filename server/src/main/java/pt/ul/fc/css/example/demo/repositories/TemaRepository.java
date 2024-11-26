package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.Utilizador;

public interface TemaRepository extends JpaRepository<Tema, Long> {

  public List<Tema> findByProponente(Utilizador proponente);
}
