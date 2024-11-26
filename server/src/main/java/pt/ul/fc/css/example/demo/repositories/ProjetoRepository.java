package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

  public List<Projeto> findByOrientador(Docente orientador);

  public Projeto findByAluno(Aluno a);
}
