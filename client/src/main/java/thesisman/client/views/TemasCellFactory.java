package thesisman.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import thesisman.client.controllers.TemasCellController;
import thesisman.client.models.Tema;

public class TemasCellFactory implements Callback<ListView<Tema>, ListCell<Tema>> {
  @Override
  public ListCell<Tema> call(ListView<Tema> param) {
    return new ListCell<>() {
      @Override
      protected void updateItem(Tema tema, boolean empty) {
        super.updateItem(tema, empty);

        if (empty || tema == null) {
          setText(null);
          setGraphic(null);
        } else {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/temas_cell.fxml"));
          TemasCellController controller = new TemasCellController(tema);
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
