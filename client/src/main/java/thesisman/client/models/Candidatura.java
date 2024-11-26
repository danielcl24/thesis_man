package thesisman.client.models;

import javafx.beans.property.*;

public class Candidatura {

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

  private final StringProperty titulo = new SimpleStringProperty();

  public final StringProperty tituloProperty() {
    return this.titulo;
  }

  public final String getTitulo() {
    return this.tituloProperty().get();
  }

  public final void setTitulo(final String titulo) {
    this.tituloProperty().set(titulo);
  }

  private final DoubleProperty remuneracao = new SimpleDoubleProperty();

  public final DoubleProperty remuneracaoProperty() {
    return this.remuneracao;
  }

  public final Double getRemuneracao() {
    return this.remuneracaoProperty().get();
  }

  public final void setRemuneracao(final Double remuneracao) {
    this.remuneracaoProperty().set(remuneracao);
  }

  private final StringProperty estado = new SimpleStringProperty();

  public final StringProperty estadoProperty() {
    return this.estado;
  }

  public final String getEstado() {
    return this.estadoProperty().get();
  }

  public final void setEstado(String estado) {
    this.estadoProperty().set(estado);
  }

  public Candidatura(int id, String titulo, Double remuneracao, String estado) {
    setId(id);
    setTitulo(titulo);
    setRemuneracao(remuneracao);
    setEstado(estado);
  }

  @Override
  public String toString() {
    return "Candidatura{"
        + "id="
        + getId()
        + ", titulo='"
        + getTitulo()
        + '\''
        + ", remuneracao="
        + getRemuneracao()
        + ", estado='"
        + getEstado()
        + '\''
        + '}';
  }
}
