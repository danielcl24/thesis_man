package thesisman.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import thesisman.client.models.Model;
import thesisman.client.views.ClientMenuOptions;

public class ClientController implements Initializable {

  public BorderPane client_parent;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Model.getInstance()
        .getViewFactory()
        .getClientSelectedMenuItem()
        .addListener(
            (observableVal, oldVal, newVal) -> {
              switch (newVal) {
                case ClientMenuOptions.CANDIDATURAS:
                  client_parent.setCenter(
                      Model.getInstance().getViewFactory().getCandidaturasView());
                  break;
                case ClientMenuOptions.TESE:
                  if (Model.getInstance().getTese() != null)
                    client_parent.setCenter(Model.getInstance().getViewFactory().getTeseView());
                  else
                    client_parent.setCenter(Model.getInstance().getViewFactory().getTeseErroView());
                  break;
                default:
                  client_parent.setCenter(Model.getInstance().getViewFactory().getTemasView());
                  break;
              }
            });
  }
}
