package pt.ul.fc.css.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Candidatura;
import pt.ul.fc.css.example.demo.entities.Tema;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
  public Candidatura findByTemaAndAluno(Tema tema, Aluno aluno);

  public List<Candidatura> findByAluno(Aluno aluno);
}
