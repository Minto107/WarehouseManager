package mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectErrorController {
    @FXML
    public Button okBt;


    public void close() {
        Stage stage = (Stage) okBt.getScene().getWindow();
        stage.close();
    }
}
