package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTProjeto;

public interface PTProjetoRepository extends JpaRepository<PTProjeto, Long> {
  PTProjeto findByAluno(Aluno aluno);

  List<PTProjeto> findByOrientador(Docente docente);
}
