package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;

public interface DissertacaoRepository extends JpaRepository<Dissertacao, Long> {

  public List<Dissertacao> findByOrientador(Docente orientador);

  public Dissertacao findByAluno(Aluno a);
}
