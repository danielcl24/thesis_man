package thesisman.client.models;

import java.util.List;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Tema {
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

  private final ListProperty<String> mestrados = new SimpleListProperty<>();

  public final ListProperty<String> mestradosProperty() {
    return this.mestrados;
  }

  public final ObservableList<String> getMestrados() {
    return this.mestradosProperty().get();
  }

  public final void setMestrados(List<String> mestrados) {
    this.mestradosProperty().set((ObservableList<String>) mestrados);
  }

  private final StringProperty descricao = new SimpleStringProperty();

  public final StringProperty descricaoProperty() {
    return this.descricao;
  }

  public final String getDescricao() {
    return this.descricaoProperty().get();
  }

  public final void setDescricao(String descricao) {
    this.descricaoProperty().set(descricao);
  }

  public Tema(int id, String titulo, Double remuneracao, List<String> mestrados, String descricao) {
    setId(id);
    setTitulo(titulo);
    setRemuneracao(remuneracao);
    setMestrados(mestrados);
    setDescricao(descricao);
  }

  @Override
  public String toString() {
    return "Tema{"
        + "id="
        + getId()
        + ", titulo='"
        + getTitulo()
        + '\''
        + ", remuneracao="
        + getRemuneracao()
        + ", mestrados="
        + getMestrados()
        + ", descricao='"
        + getDescricao()
        + '\''
        + '}';
  }
}
