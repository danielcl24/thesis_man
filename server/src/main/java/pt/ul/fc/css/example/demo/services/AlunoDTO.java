package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;

@Component
public class AlunoDTO extends UtilizadorDTO {

  private Double media;
  private String mestrado;
  private Long tema;

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

  public Long getTema() {
    return tema;
  }

  public void setTema(Long tema) {
    this.tema = tema;
  }
}
