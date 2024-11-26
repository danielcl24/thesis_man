package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

  List<Docente> findByEmail(String email);
}
