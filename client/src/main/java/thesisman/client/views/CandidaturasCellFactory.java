package thesisman.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import thesisman.client.controllers.CandidaturasCellController;
import thesisman.client.models.Candidatura;

public class CandidaturasCellFactory
    implements Callback<ListView<Candidatura>, ListCell<Candidatura>> {
  @Override
  public ListCell<Candidatura> call(ListView<Candidatura> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(Candidatura candidatura, boolean empty) {
        super.updateItem(candidatura, empty);

        if (empty || candidatura == null) {
          setText(null);
          setGraphic(null);
        } else {
          FXMLLoader loader =
              new FXMLLoader(getClass().getResource("/fxml/candidaturas_cell.fxml"));
          CandidaturasCellController controller = new CandidaturasCellController(candidatura);
          loader.setController(controller);

          setText(null);
          try {
            setGraphic(loader.load());
          } catch (Exception e) {
            e.printStackTrace();
            setGraphic(null);
          }
        }
      }
    };
  }
}
