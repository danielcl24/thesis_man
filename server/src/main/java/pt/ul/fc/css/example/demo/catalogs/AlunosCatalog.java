package pt.ul.fc.css.example.demo.catalogs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.repositories.AlunoRepository;

@Component
public class AlunosCatalog {

  @Autowired private AlunoRepository alunoRepository;

  public Aluno addAluno(String email, String name, String surname, Double media, String mestrado) {
    Aluno aluno = new Aluno(email, name, surname, media, mestrado);
    alunoRepository.save(aluno);
    return aluno;
  }

  public Aluno getAluno(String email) {
    return alunoRepository.findByEmail(email);
  }

  public Aluno getAluno(Long id) {
    return alunoRepository.findById(id).orElse(null);
  }

  public List<Aluno> getAlunosSemTemas() {
    List<Aluno> alunos = alunoRepository.findAll();
    alunos.removeIf(a -> a.getTema() != null);
    return alunos;
  }

  public void save(Aluno aluno) {
    alunoRepository.save(aluno);
  }
}
