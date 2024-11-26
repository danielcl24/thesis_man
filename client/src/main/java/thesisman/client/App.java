package thesisman.client;

import javafx.application.Application;
import javafx.stage.Stage;
import thesisman.client.models.Model;

public class App extends Application {
  @Override
  public void start(Stage stage) {
    Model.getInstance().getViewFactory().showLoginWindow();
  }
}
