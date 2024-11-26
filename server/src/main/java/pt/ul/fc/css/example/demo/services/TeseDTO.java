package pt.ul.fc.css.example.demo.services;

import org.springframework.stereotype.Component;

@Component
public class TeseDTO {
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private boolean isProjeto;

  public boolean isProjeto() {
    return isProjeto;
  }

  public void setProjeto(boolean isProjeto) {
    this.isProjeto = isProjeto;
  }
}
