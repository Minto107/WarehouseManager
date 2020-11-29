package mainWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteDialogController {

    @FXML
    public Button noBt, yesBt;
    boolean delete;

    public void delete() {
        delete = true;
        Stage stage = (Stage) yesBt.getScene().getWindow();
        stage.close();
    }

    public boolean isDelete() {
        return delete;
    }

    public void close() {
        delete = false;
        Stage stage = (Stage) noBt.getScene().getWindow();
        stage.close();
    }
}
