package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import thesisman.client.models.Model;
import thesisman.client.models.Tema;
import thesisman.client.views.TemasCellFactory;

public class TemasController implements Initializable {
  public Text user_name;
  public ListView<Tema> listView;
  public Button candidatar_button;
  public Label status_lbl;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    candidatar_button.setOnAction(event -> onCandidatar());

    Model.getInstance().fetchTemas();

    listView.setCellFactory(new TemasCellFactory());
    listView.setItems(Model.getInstance().getTemas());
    listView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (obs, oldSelection, newSelection) -> Model.getInstance().setCurrentTema(newSelection));
  }

  public void onCandidatar() {
    boolean res = Model.getInstance().candidata();
    if (res) {
      status_lbl.setText("Application successful!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> status_lbl.setText(""));
      visiblePause.play();
    } else {
      status_lbl.setText("Error in application!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> status_lbl.setText(""));
      visiblePause.play();
    }
  }

  public void updateList() {
    Model.getInstance().fetchTemas();
    listView.setItems(Model.getInstance().getTemas());
  }
}
