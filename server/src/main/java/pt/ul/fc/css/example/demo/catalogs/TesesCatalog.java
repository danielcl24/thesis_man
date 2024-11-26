package pt.ul.fc.css.example.demo.catalogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Aluno;
import pt.ul.fc.css.example.demo.entities.DefesaFinal;
import pt.ul.fc.css.example.demo.entities.Dissertacao;
import pt.ul.fc.css.example.demo.entities.Docente;
import pt.ul.fc.css.example.demo.entities.Juri;
import pt.ul.fc.css.example.demo.entities.JuriFinal;
import pt.ul.fc.css.example.demo.entities.Projeto;
import pt.ul.fc.css.example.demo.entities.PropostaTese;
import pt.ul.fc.css.example.demo.entities.Tese;
import pt.ul.fc.css.example.demo.entities.UtilizadorEmpresarial;
import pt.ul.fc.css.example.demo.repositories.DissertacaoRepository;
import pt.ul.fc.css.example.demo.repositories.ProjetoRepository;

@Component
public class TesesCatalog {

  @Autowired private DocentesCatalog docentesCatalog;

  @Autowired private ProjetoRepository projetoRepository;

  @Autowired private DissertacaoRepository dissertacaoRepository;

  public Projeto addSubTeseProjeto(
      File filename,
      Aluno aluno,
      Docente orientador,
      PropostaTese proposta,
      UtilizadorEmpresarial orientadorExterno,
      boolean isProjeto) {
    Projeto projeto = projetoRepository.findByAluno(aluno);
    projeto.setProposta(proposta);
    projetoRepository.save(projeto);
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    return projeto;
  }

  public Dissertacao addSubTeseDissertacao(
      File filename, Aluno aluno, Docente orientador, PropostaTese proposta, boolean isProjeto) {
    Dissertacao dissertacao = dissertacaoRepository.findByAluno(aluno);
    dissertacao.setProposta(proposta);
    dissertacaoRepository.save(dissertacao);
    return dissertacao;
  }

  public List<Projeto> getProjsByOrientador(Long orientadorId) {

    Docente orientador = docentesCatalog.getDocente(orientadorId);
    return projetoRepository.findByOrientador(orientador);
  }

  public List<Dissertacao> getDissertacoesByOrientador(Long orientadorId) {

    Docente orientador = docentesCatalog.getDocente(orientadorId);
    return dissertacaoRepository.findByOrientador(orientador);
  }

  public Projeto getProjById(Long id) {
    return projetoRepository.findById(id).orElse(null);
  }

  public Dissertacao getDissertacaoById(Long id) {
    return dissertacaoRepository.findById(id).orElse(null);
  }

  public void addDefesa(Tese tese, DefesaFinal defesa) {
    tese.setDefesa(defesa);
    if (tese instanceof Projeto) {
      projetoRepository.save((Projeto) tese);
    } else {
      dissertacaoRepository.save((Dissertacao) tese);
    }
  }

  public Tese getTese(Aluno a) {
    Projeto p = projetoRepository.findByAluno(a);
    Dissertacao d = dissertacaoRepository.findByAluno(a);

    if (p != null) return p;
    else if (d != null) return d;
    else return null;
  }

  public List<Projeto> getProjsByOrientadorPresi(Long orientadorId) {

    Docente orientador = docentesCatalog.getDocente(orientadorId);
    List<Projeto> all = projetoRepository.findAll();
    List<Projeto> ret = new ArrayList<>();
    for (Projeto p : all) {
      if (p.getDefesa() == null) {
        continue;
      }
      Juri juri = p.getDefesa().getJuri();
      if (juri instanceof JuriFinal) {
        JuriFinal juriFinal = (JuriFinal) juri;
        if (juriFinal.getPresidente().getId() == orientadorId) {
          ret.add(p);
        }
      }
    }
    return ret;
  }

  public List<Dissertacao> getDissertacoesByOrientadorPresi(Long orientadorId) {

    Docente orientador = docentesCatalog.getDocente(orientadorId);
    List<Dissertacao> all = dissertacaoRepository.findAll();
    List<Dissertacao> ret = new ArrayList<>();
    for (Dissertacao d : all) {
      if (d.getDefesa() == null) {
        continue;
      }
      Juri juri = d.getDefesa().getJuri();
      if (juri instanceof JuriFinal) {
        JuriFinal juriFinal = (JuriFinal) juri;
        if (juriFinal.getPresidente().getId() == orientadorId) {
          ret.add(d);
        }
      }
    }
    return ret;
  }
}
