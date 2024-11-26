package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import thesisman.client.models.Candidatura;
import thesisman.client.models.Model;
import thesisman.client.views.CandidaturasCellFactory;

public class CandidaturasController implements Initializable {
  public ListView<Candidatura> list_view;
  public Button cancelar_button;
  public Label status_lbl;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cancelar_button.setOnAction(event -> onCancelar());

    Model.getInstance().fetchCandidaturas();

    list_view.setCellFactory(new CandidaturasCellFactory());
    list_view.setItems(Model.getInstance().getCandidaturas());
    list_view
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) ->
                Model.getInstance().setCurrentCandidatura(newSelection));
  }

  public void onCancelar() {
    boolean res = Model.getInstance().cancela();
    if (res) {
      updateList();
      status_lbl.setText("Cancellation successful!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> status_lbl.setText(""));
      visiblePause.play();
    } else {
      status_lbl.setText("Error in cancellation!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> status_lbl.setText(""));
      visiblePause.play();
    }
  }

  public void updateList() {
    Model.getInstance().fetchCandidaturas();
    list_view.setItems(Model.getInstance().getCandidaturas());
  }
}
