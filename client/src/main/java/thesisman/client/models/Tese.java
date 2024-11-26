package thesisman.client.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tese {
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

  private final BooleanProperty isProjeto = new SimpleBooleanProperty();

  public final BooleanProperty projetoProperty() {
    return this.isProjeto;
  }

  public final boolean isProjeto() {
    return this.projetoProperty().get();
  }

  public final void setProjeto(boolean b) {
    this.projetoProperty().set(b);
  }

  public Tese(int id, boolean b) {
    setId(id);
    setProjeto(b);
  }

  @Override
  public String toString() {
    return "Tese{" + "id=" + id + ", isProjeto=" + isProjeto + '}';
  }
}
