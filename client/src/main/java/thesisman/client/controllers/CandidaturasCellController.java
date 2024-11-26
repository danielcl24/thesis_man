package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import thesisman.client.models.Candidatura;

public class CandidaturasCellController implements Initializable {
  public Label titulo_lbl;
  public Label remun_lbl;
  public Label estado_lbl;
  private final Candidatura candidatura;

  public CandidaturasCellController(Candidatura candidatura) {
    this.candidatura = candidatura;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    if (candidatura != null) {
      titulo_lbl.setText(candidatura.getTitulo());
      remun_lbl.setText(candidatura.getRemuneracao() + "€/month");
      estado_lbl.setText(candidatura.getEstado());
    }
  }
}
