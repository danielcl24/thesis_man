package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.File;
import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Tese {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @NonNull private File filename;

  @ManyToOne
  @JoinColumn(name = "tema_id")
  private Tema tema;

  @NonNull private boolean isProjeto;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
  private Aluno aluno;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "orientador_id", referencedColumnName = "id", nullable = false)
  private Docente orientador;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "proposta_id", referencedColumnName = "id", nullable = false)
  private PropostaTese proposta;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "defesa_id", referencedColumnName = "id", nullable = true)
  private DefesaFinal defesa;

  protected Tese() {}

  public Tese(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull PropostaTese proposta,
      @NonNull boolean isProjeto) {
    super();
    this.filename = filename;
    this.aluno = aluno;
    this.orientador = orientador;
    this.proposta = proposta;
    this.isProjeto = isProjeto;
  }

  public Tese(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull boolean isProjeto) {
    this.filename = filename;
    this.aluno = aluno;
    this.orientador = orientador;
    this.tema = aluno.getTema();
    this.proposta = null;
    this.isProjeto = isProjeto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public File getFilename() {
    return filename;
  }

  public void setFilename(File filename) {
    this.filename = filename;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Docente getOrientador() {
    return orientador;
  }

  public void setOrientador(Docente orientador) {
    this.orientador = orientador;
  }

  public PropostaTese getProposta() {
    return proposta;
  }

  public void setProposta(PropostaTese proposta) {
    this.proposta = proposta;
  }

  public Tema getTema() {
    return tema;
  }

  public void setTema(Tema tema) {
    this.tema = tema;
  }

  public DefesaFinal getDefesa() {
    return defesa;
  }

  public void setDefesa(DefesaFinal defesa) {
    this.defesa = defesa;
  }

  public boolean isProjeto() {
    return isProjeto;
  }

  public void setProjeto(boolean isProjeto) {
    this.isProjeto = isProjeto;
  }
}
