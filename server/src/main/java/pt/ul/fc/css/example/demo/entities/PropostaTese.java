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
public class PropostaTese {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @Column(name = "filename")
  private File filename;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
  private Aluno aluno;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "orientador_id", referencedColumnName = "id")
  private Docente orientador;

  @ManyToOne
  @JoinColumn(name = "tema_id")
  private Tema tema;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "proposta_id", referencedColumnName = "id", nullable = true)
  private DefesaProposta defesa;

  protected PropostaTese() {}

  public PropostaTese(
      @NonNull File filename,
      @NonNull Aluno aluno,
      @NonNull Docente orientador,
      @NonNull Tema tema) {
    super();
    this.filename = filename;
    this.aluno = aluno;
    this.orientador = orientador;
    this.tema = tema;
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

  public Tema getTema() {
    return tema;
  }

  public void setTema(Tema tema) {
    this.tema = tema;
  }

  public DefesaProposta getDefesa() {
    return defesa;
  }

  public void setDefesa(DefesaProposta defesa) {
    this.defesa = defesa;
  }
}
