package pt.ul.fc.css.example.demo.catalogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Candidatura;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.repositories.CandidaturaRepository;

@Component
public class CandidaturasCatalog {

  @Autowired private CandidaturaRepository candidaturaRepository;

  public Candidatura addCandidatura(String estado, Aluno candidato, Tema tema) {
    Candidatura candidatura = new Candidatura(estado, candidato, tema);
    candidaturaRepository.save(candidatura);
    return candidatura;
  }

  public Candidatura deleteCandidatura(Candidatura candidatura) {
    candidaturaRepository.delete(candidatura);
    return candidatura;
  }

  public Candidatura getCandidatura(Aluno aluno, Tema tema) {
    return candidaturaRepository.findByTemaAndAluno(tema, aluno);
  }

  public Candidatura getCandidatura(Long id) {
    return candidaturaRepository.findById(id).orElse(null);
  }

  public List<Candidatura> getCandidaturasByAluno(Aluno aluno) {
    return candidaturaRepository.findByAluno(aluno);
  }
}
