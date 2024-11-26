package pt.ul.fc.css.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.Objects;
import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilizador {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Long id;

  @NonNull private String email;

  @NonNull private String name;

  @NonNull private String surname;

  public Utilizador(@NonNull String email, @NonNull String name, @NonNull String surname) {
    this.email = email;
    this.surname = surname;
    this.name = name;
  }

  protected Utilizador() {}

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Utilizador) obj;
    return Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Utilizador[" + "id=" + id + ", " + "name=" + name + ']';
  }
}
