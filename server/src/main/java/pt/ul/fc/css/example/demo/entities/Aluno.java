package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Aluno extends Utilizador {

  @Column(name = "media", nullable = false)
  private Double media;

  @Column(name = "mestrado", nullable = false)
  private String mestrado;

  @Column(name = "candidaturas_efetuadas")
  private int candidaturasEfetuadas;

  @OneToOne
  @JoinColumn(name = "temaAtribuido_id", referencedColumnName = "id")
  private Tema tema;

  protected Aluno() {}

  public Aluno(String email, String name, String surname, Double media, String mestrado) {
    super(email, name, surname);
    this.media = media;
    this.mestrado = mestrado;
    this.candidaturasEfetuadas = 0;
  }

  public int getCandidaturasEfetuadas() {
    return candidaturasEfetuadas;
  }

  public void incCandidaturasEfetuadas() {
    this.candidaturasEfetuadas++;
  }

  public void decCandidaturasEfetuadas() {
    this.candidaturasEfetuadas--;
  }

  public Tema getTema() {
    return tema;
  }

  public void setTema(Tema tema) {
    this.tema = tema;
  }

  public Double getMedia() {
    return media;
  }

  public void setMedia(Double media) {
    this.media = media;
  }

  public String getMestrado() {
    return mestrado;
  }

  public void setMestrado(String mestrado) {
    this.mestrado = mestrado;
  }

  @Override
  public String toString() {
    return "Aluno [media="
        + media
        + ", mestrado="
        + mestrado
        + ", candidaturasEfetuadas="
        + candidaturasEfetuadas
        + ", temaId="
        + (tema != null ? tema.getId() : "null")
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(candidaturasEfetuadas, media, mestrado, tema);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    Aluno other = (Aluno) obj;
    return candidaturasEfetuadas == other.candidaturasEfetuadas
        && Objects.equals(media, other.media)
        && Objects.equals(mestrado, other.mestrado)
        && Objects.equals(tema, other.tema);
  }
}
