package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.Objects;
import org.springframework.lang.NonNull;

@Entity
public class Tema {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "proponente_id", nullable = false)
  private Utilizador proponente;

  @NonNull private String titulo;

  @NonNull private String descricao;

  @NonNull private Double remuneracao;

  @NonNull private List<String> mestradosCompativeis;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "aluno_id", referencedColumnName = "id")
  private Aluno aluno;

  protected Tema() {}

  public Tema(
      @NonNull Utilizador proponente,
      @NonNull String titulo,
      @NonNull String descricao,
      @NonNull Double remuneracao,
      @NonNull List<String> mestradosCompativeis) {
    super();
    this.proponente = proponente;
    this.titulo = titulo;
    this.descricao = descricao;
    this.remuneracao = remuneracao;
    this.mestradosCompativeis = mestradosCompativeis;
    this.aluno = null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getRemuneracao() {
    return remuneracao;
  }

  public void setRemuneracao(Double remuneracao) {
    this.remuneracao = remuneracao;
  }

  public Utilizador getProponente() {
    return proponente;
  }

  public void setProponente(Utilizador proponente) {
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

  public List<String> getMestradosCompativeis() {
    return mestradosCompativeis;
  }

  public void addMestradosCompativeis(List<String> mestradosCompativeis) {
    this.mestradosCompativeis = mestradosCompativeis;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  @Override
  public String toString() {
    return "Tema [id="
        + id
        + ", proponente="
        + proponente
        + ", titulo="
        + titulo
        + ", descricao="
        + descricao
        + ", remuneracao="
        + remuneracao
        + ", mestradosCompativeis="
        + mestradosCompativeis
        + ", alunoId="
        + (aluno != null ? aluno.getId() : "null")
        + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        aluno, descricao, id, mestradosCompativeis, proponente, remuneracao, titulo);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Tema other = (Tema) obj;
    return Objects.equals(aluno, other.aluno)
        && Objects.equals(descricao, other.descricao)
        && Objects.equals(id, other.id)
        && Objects.equals(mestradosCompativeis, other.mestradosCompativeis)
        && Objects.equals(proponente, other.proponente)
        && Objects.equals(remuneracao, other.remuneracao)
        && Objects.equals(titulo, other.titulo);
  }
}
