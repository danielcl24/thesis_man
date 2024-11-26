package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Candidatura;

@Component
public class CandidaturaService {

  protected static CandidaturaDTO dtofy(Candidatura c) {
    CandidaturaDTO c2 = new CandidaturaDTO();
    c2.setId(c.getId());
    c2.setEstado(c.getEstado());

    if (c.getCandidato() != null) c2.setAluno(AlunoService.dtofy(c.getCandidato()));
    else c2.setAluno(null);

    if (c.getTema() != null) c2.setTema(TemaService.dtofy(c.getTema()));
    else c2.setTema(null);

    return c2;
  }
}
