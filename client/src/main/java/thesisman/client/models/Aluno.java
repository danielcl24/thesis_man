package thesisman.client.models;

import javafx.beans.property.*;

public class Aluno {
  private final IntegerProperty id = new SimpleIntegerProperty();

  public final IntegerProperty idProperty() {
    return this.id;
  }

  public final int getId() {
    return this.idProperty().get();
  }

  public final void setId(final int id) {
    this.idProperty().set(id);
  }

  private final StringProperty name = new SimpleStringProperty();

  public final StringProperty nameProperty() {
    return this.name;
  }

  public final String getName() {
    return this.nameProperty().get();
  }

  public final void setName(final String name) {
    this.nameProperty().set(name);
  }

  private final StringProperty surname = new SimpleStringProperty();

  public final StringProperty surnameProperty() {
    return this.surname;
  }

  public final String getSurname() {
    return this.surnameProperty().get();
  }

  public final void setSurname(final String surname) {
    this.surnameProperty().set(surname);
  }

  private final StringProperty email = new SimpleStringProperty();

  public final StringProperty emailProperty() {
    return this.email;
  }

  public final String getEmail() {
    return this.emailProperty().get();
  }

  public final void setEmail(final String email) {
    this.emailProperty().set(email);
  }

  private final DoubleProperty media = new SimpleDoubleProperty();

  public final DoubleProperty mediaProperty() {
    return this.media;
  }

  public final double getMedia() {
    return this.mediaProperty().get();
  }

  public final void setMedia(final double media) {
    this.mediaProperty().set(media);
  }

  private final StringProperty mestrado = new SimpleStringProperty();

  public final StringProperty mestradoProperty() {
    return this.mestrado;
  }

  public final String getMestrado() {
    return this.mestradoProperty().get();
  }

  public final void setMestrado(final String mestrado) {
    this.mestradoProperty().set(mestrado);
  }

  public Aluno(int id, String name, String surname, String email, double media, String mestrado) {
    setId(id);
    setName(name);
    setSurname(surname);
    setEmail(email);
    setMedia(media);
    setMestrado(mestrado);
  }

  @Override
  public String toString() {
    return "Aluno{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + '\''
        + ", surname='"
        + getSurname()
        + '\''
        + ", email='"
        + getEmail()
        + '\''
        + ", media="
        + getMedia()
        + ", mestrado='"
        + getMestrado()
        + '\''
        + '}';
  }
}
