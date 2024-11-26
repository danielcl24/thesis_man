package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;

public interface PTDissertacaoRepository extends JpaRepository<PTDissertacao, Long> {
  PTDissertacao findByAluno(Aluno aluno);

  List<PTDissertacao> findByOrientador(Docente docente);
}
