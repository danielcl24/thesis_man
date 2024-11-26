package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import thesisman.client.models.Model;

public class LoginController implements Initializable {
  public TextField email_field;
  public PasswordField password_field;
  public Button login_button;
  public Label error_lbl;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    login_button.setOnAction(event -> onLogin());
  }

  private void onLogin() {
    Stage stage = (Stage) error_lbl.getScene().getWindow(); // gambiarra para pegar o stage atual

    // avalia credenciais do aluno
    Model.getInstance().evaluateCreds(email_field.getText(), password_field.getText());
    if (Model.getInstance().isLoginSuccessFlag()) {
      Model.getInstance().getViewFactory().showClientWindow();
      Model.getInstance().initTese();
      // close stage
      Model.getInstance().getViewFactory().closeStage(stage);
    } else {
      email_field.setText("");
      password_field.setText("");
      error_lbl.setText("Invalid credentials.");
    }
  }
}
