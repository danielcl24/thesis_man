package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import thesisman.client.models.Tema;

public class TemasCellController implements Initializable {
  public Label titulo_lbl;
  public Label remun_lbl;
  public Label mestrados_lbl;
  public TextArea desc_box;
  private final Tema tema;

  public TemasCellController(Tema tema) {
    this.tema = tema;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    if (tema != null) {
      titulo_lbl.setText(tema.getTitulo());
      remun_lbl.setText(tema.getRemuneracao() + "€/month");
      mestrados_lbl.setText(tema.getMestrados().toString());
      desc_box.setText(tema.getDescricao());
      desc_box.setEditable(false);
      desc_box.setStyle("-fx-focus-color: transparent; -fx-text-box-border: transparent;");
    }
  }
}
