package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;

@Component
public class CandidaturaDTO {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String estado;
  private AlunoDTO aluno;
  private TemaDTO tema;

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public AlunoDTO getAluno() {
    return aluno;
  }

  public void setAluno(AlunoDTO aluno) {
    this.aluno = aluno;
  }

  public TemaDTO getTema() {
    return tema;
  }

  public void setTema(TemaDTO tema) {
    this.tema = tema;
  }
}
