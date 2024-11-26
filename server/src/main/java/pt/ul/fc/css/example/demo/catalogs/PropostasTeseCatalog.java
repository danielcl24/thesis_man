package pt.ul.fc.css.example.demo.catalogs;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.DefesaProposta;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.PTDissertacao;
import pt.ul.fc.css.example.demo.entities.PTProjeto;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tema;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.repositories.PTDissertacaoRepository;
import pt.ul.fc.css.example.demo.repositories.PTProjetoRepository;

@Component
public class PropostasTeseCatalog {

  @Autowired private PTProjetoRepository ptprojetoRepository;

  @Autowired private PTDissertacaoRepository ptdissertacaoRepository;

  @Autowired AlunosCatalog alunosCatalog;

  public PTProjeto addSubPropostaTeseProjeto(
      File filename,
      Aluno aluno,
      Docente orientador,
      Tema tema,
      UtilizadorEmpresarial orientadorExterno) {
    PTProjeto ptprojeto = new PTProjeto(filename, aluno, orientador, tema, orientadorExterno);
    ptprojetoRepository.save(ptprojeto);
    return ptprojeto;
  }

  public PTDissertacao addSubPropostaTeseDissertacao(
      File filename, Aluno aluno, Docente orientador, Tema tema) {
    PTDissertacao ptdissertacao = new PTDissertacao(filename, aluno, orientador, tema);
    ptdissertacaoRepository.save(ptdissertacao);
    return ptdissertacao;
  }

  public PTProjeto getPropostaTeseProjeto(Aluno aluno) {
    return ptprojetoRepository.findByAluno(aluno);
  }

  public PTDissertacao getPropostaTeseDissertacao(Aluno aluno) {
    return ptdissertacaoRepository.findByAluno(aluno);
  }

  public List<PTProjeto> getPTProjetosByDocente(Docente docente) {
    return ptprojetoRepository.findByOrientador(docente);
  }

  public List<PTDissertacao> getPTDissertacoesByDocente(Docente docente) {
    return ptdissertacaoRepository.findByOrientador(docente);
  }

  public PTProjeto getPTProjById(Long id) {
    return ptprojetoRepository.findById(id).orElse(null);
  }

  public PTDissertacao getPTDissertacaoById(Long id) {
    return ptdissertacaoRepository.findById(id).orElse(null);
  }

  public void addDefesaProposta(PropostaTese ptprojeto, DefesaProposta defesa) {
    ptprojeto.setDefesa(defesa);
    if (ptprojeto instanceof PTProjeto) {
      ptprojetoRepository.save((PTProjeto) ptprojeto);
    } else if (ptprojeto instanceof PTDissertacao) {
      ptdissertacaoRepository.save((PTDissertacao) ptprojeto);
    }
  }
}
