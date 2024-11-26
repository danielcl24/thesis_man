package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import thesisman.client.models.Model;
import thesisman.client.views.ClientMenuOptions;

public class MenuController implements Initializable {
  public Button temas_button;
  public Button candidaturas_button;
  public Button tese_button;
  public Button logout_button;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    addListeners();
  }

  private void addListeners() {
    temas_button.setOnAction(event -> onTemas());
    candidaturas_button.setOnAction(event -> onCandidaturas());
    tese_button.setOnAction(event -> onTese());
    logout_button.setOnAction(event -> onLogout());
  }

  private void onTemas() {
    Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TEMAS);
  }

  private void onCandidaturas() {
    Model.getInstance()
        .getViewFactory()
        .getClientSelectedMenuItem()
        .set(ClientMenuOptions.CANDIDATURAS);
  }

  private void onTese() {
    Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TESE);
  }

  private void onLogout() {
    Stage stage =
        (Stage) logout_button.getScene().getWindow(); // gambiarra para pegar o stage atual
    Model.getInstance().logout();
    Model.getInstance().getViewFactory().closeStage(stage);
    Model.getInstance().getViewFactory().showLoginWindow();
  }
}
