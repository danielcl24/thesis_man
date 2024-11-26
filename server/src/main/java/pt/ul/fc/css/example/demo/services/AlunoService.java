package pt.ul.fc.css.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.catalogs.AlunosCatalog;
import pt.ul.fc.css.example.demo.catalogs.CandidaturasCatalog;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.Candidatura;
import pt.ul.fc.css.example.demo.handlers.CancelarCandidatura_Handler;
import pt.ul.fc.css.example.demo.handlers.CandidatarTema_Aluno_Handler;

@Component
public class AlunoService {

  @Autowired private AlunosCatalog alunoCatalog;

  @Autowired private CandidaturasCatalog candidaturasCatalog;

  @Autowired private CandidatarTema_Aluno_Handler candidaturaHandler;

  @Autowired private CancelarCandidatura_Handler cancelarHandler;

  public List<CandidaturaDTO> getCandidaturas(Long id) {
    Aluno a = alunoCatalog.getAluno(id);
    ArrayList<CandidaturaDTO> arr = new ArrayList<>();
    for (Candidatura c : candidaturasCatalog.getCandidaturasByAluno(a)) {
      CandidaturaDTO dto = CandidaturaService.dtofy(c);
      arr.add(dto);
    }
    return arr;
  }

  public List<AlunoDTO> getAlunos() {
    ArrayList<AlunoDTO> arr = new ArrayList<>();
    for (Aluno a : alunoCatalog.getAlunosSemTemas()) {
      AlunoDTO dto = dtofy(a);
      arr.add(dto);
    }

    return arr;
  }

  public List<Aluno> getAlunosSemTema() {
    return alunoCatalog.getAlunosSemTemas();
  }

  public AlunoDTO candidataTema(Long idAluno, Long idTema) {
    boolean res = candidaturaHandler.candidatarTema(idTema, idAluno);
    AlunoDTO a = null;
    if (res) a = dtofy(alunoCatalog.getAluno(idAluno));
    return a;
  }

  public AlunoDTO login(String email) {
    Aluno a = alunoCatalog.getAluno(email);
    if (a != null) {
      AlunoDTO res = dtofy(a);
      return res;
    } else {
      return null;
    }
  }

  protected static AlunoDTO dtofy(Aluno a) {
    AlunoDTO a2 = new AlunoDTO();
    a2.setEmail(a.getEmail());
    a2.setId(a.getId());
    a2.setName(a.getName());
    a2.setSurname(a.getSurname());
    a2.setMedia(a.getMedia());
    a2.setMestrado(a.getMestrado());
    if (a.getTema() != null) {
      a2.setTema(a.getTema().getId());
    } else {
      a2.setTema(null);
    }
    return a2;
  }

  public AlunoDTO cancelaCandidatura(Long idAluno, Long idCandidatura) {
    boolean res = cancelarHandler.cancelarCandidatura(idCandidatura, idAluno);
    if (res) {
      AlunoDTO a = dtofy(alunoCatalog.getAluno(idAluno));
      return a;
    } else return null;
  }

  public Aluno getAluno(Long id) {
    return alunoCatalog.getAluno(id);
  }
}
