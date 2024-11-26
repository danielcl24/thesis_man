package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TemaDTO {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private UtilizadorDTO proponente;
  private String titulo;
  private String descricao;
  private Double remuneracao;
  private List<String> mestradosCompativeis;
  private Long aluno;

  public UtilizadorDTO getProponente() {
    return proponente;
  }

  public void setProponente(UtilizadorDTO proponente) {
    this.proponente = proponente;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getRemuneracao() {
    return remuneracao;
  }

  public void setRemuneracao(Double remuneracao) {
    this.remuneracao = remuneracao;
  }

  public List<String> getMestradosCompativeis() {
    return mestradosCompativeis;
  }

  public void setMestradosCompativeis(List<String> mestradosCompativeis) {
    this.mestradosCompativeis = mestradosCompativeis;
  }

  public Long getAluno() {
    return aluno;
  }

  public void setAluno(Long aluno) {
    this.aluno = aluno;
  }
}
