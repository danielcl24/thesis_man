package thesisman.client.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thesisman.client.controllers.ClientController;

public class ViewFactory {

  // Property que serve de flag para dizer ao ClientController qual dos botoes do menu foi cliclado
  private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;

  private AnchorPane temasView;
  private AnchorPane candidaturasView;
  private AnchorPane teseView;
  private AnchorPane teseErroView;

  public ViewFactory() {
    this.clientSelectedMenuItem = new SimpleObjectProperty<>();
  }

  public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
    return clientSelectedMenuItem;
  }

  public AnchorPane getTeseView() {
    try {
      teseView = new FXMLLoader(getClass().getResource("/fxml/tese.fxml")).load();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return teseView;
  }

  public AnchorPane getTeseErroView() {
    try {
      teseErroView = new FXMLLoader(getClass().getResource("/fxml/tese_erro.fxml")).load();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return teseErroView;
  }

  public AnchorPane getCandidaturasView() {
    try {
      candidaturasView = new FXMLLoader(getClass().getResource("/fxml/candidaturas.fxml")).load();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return candidaturasView;
  }

  public AnchorPane getTemasView() {
    try {
      temasView = new FXMLLoader(getClass().getResource("/fxml/temas.fxml")).load();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return temasView;
  }

  public void showLoginWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
    createStage(loader);
  }

  public void showClientWindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/client.fxml"));
    ClientController clientController = new ClientController();
    loader.setController(clientController);
    createStage(loader);
  }

  private static void createStage(FXMLLoader loader) {
    Scene scene = null;
    try {
      scene = new Scene(loader.load());
    } catch (Exception e) {
      e.printStackTrace();
    }
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("ThesisMan");
    stage.show();
  }

  public void closeStage(Stage stage) {
    stage.close();
  }
}
