package thesisman.client.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import thesisman.client.models.Model;

public class TeseController implements Initializable {
  public Button proposta_button;
  public Text proposta_lbl;
  public Button final_button;
  public Text final_lbl;
  public Button proposta_button1;
  public Button final_button1;
  private File propostaFile;
  private File finalFile;
  private FileChooser fileChooser = new FileChooser();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    proposta_button1.setOnAction(event -> propostaSelect());
    proposta_button.setOnAction(event -> propostaSend());

    final_button1.setOnAction(event -> finalSelect());
    final_button.setOnAction(event -> finalSend());
  }

  public void finalSend() {
    boolean res = Model.getInstance().submeteFinal(this.finalFile);
    if (res) {
      final_lbl.setText("File submitted successfully!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> final_lbl.setText(""));
      visiblePause.play();
    } else {
      final_lbl.setText("Error in submission!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> final_lbl.setText(""));
      visiblePause.play();
    }
  }

  public void finalSelect() {
    Stage stage = (Stage) proposta_button1.getScene().getWindow();
    try {
      this.finalFile = fileChooser.showOpenDialog(stage);
      final_lbl.setText(this.finalFile.getCanonicalPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void propostaSelect() {
    Stage stage = (Stage) proposta_button1.getScene().getWindow();
    try {
      this.propostaFile = fileChooser.showOpenDialog(stage);
      proposta_lbl.setText(this.propostaFile.getCanonicalPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void propostaSend() {
    boolean res = Model.getInstance().submeteProposta(this.propostaFile);
    if (res) {
      proposta_lbl.setText("File submitted successfully!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> proposta_lbl.setText(""));
      visiblePause.play();
    } else {
      proposta_lbl.setText("Error in submission!");
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> proposta_lbl.setText(""));
      visiblePause.play();
    }
  }
}
