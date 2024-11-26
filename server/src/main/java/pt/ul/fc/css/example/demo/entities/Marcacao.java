package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.lang.NonNull;

@Entity
public class Marcacao {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  private String sala;

  @NonNull private LocalDate data;

  @NonNull private LocalTime horaComeco;

  @NonNull private Duration duracao;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "responsavel_id", referencedColumnName = "id", nullable = false)
  private Docente responsavel;

  protected Marcacao() {}

  public Marcacao(
      String sala,
      @NonNull LocalDate data,
      @NonNull LocalTime horaComeco,
      @NonNull Duration duracao,
      @NonNull Docente responsavel) {
    super();
    this.sala = sala;
    this.data = data;
    this.horaComeco = horaComeco;
    this.duracao = duracao;
    this.responsavel = responsavel;
  }

  public Marcacao(
      @NonNull LocalDate data,
      @NonNull LocalTime horaComeco,
      @NonNull Duration duracao,
      @NonNull Docente responsavel) {
    super();
    this.sala = null;
    this.data = data;
    this.horaComeco = horaComeco;
    this.duracao = duracao;
    this.responsavel = responsavel;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSala() {
    return this.sala;
  }

  public void setLocal(String sala) {
    this.sala = sala;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public LocalTime getHoraComeco() {
    return horaComeco;
  }

  public void setHoraComeco(LocalTime horaComeco) {
    this.horaComeco = horaComeco;
  }

  public Duration getDuracao() {
    return duracao;
  }

  public void setDuracao(Duration duracao) {
    this.duracao = duracao;
  }

  public Docente getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(Docente responsavel) {
    this.responsavel = responsavel;
  }

  public boolean estaSobreposta(Marcacao outraMarcacao) {
    if (this.data.equals(outraMarcacao.getData())) {
      LocalTime fimDestaMarcacao = this.horaComeco.plus(this.duracao);
      LocalTime fimOutraMarcacao = outraMarcacao.getHoraComeco().plus(outraMarcacao.getDuracao());

      boolean horaSobreposta =
          !this.horaComeco.isAfter(fimOutraMarcacao)
              && !outraMarcacao.getHoraComeco().isAfter(fimDestaMarcacao);
      if (horaSobreposta) {
        return sala.equals(outraMarcacao.getSala());
      } else {
        return false;
      }
    }
    return false;
  }
}
