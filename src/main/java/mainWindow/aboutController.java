package mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class aboutController {
    @FXML
    public Button okBt, blank;

    public int counter;


    public void close() {
        Stage stage = (Stage) okBt.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        counter = 1;
    }


    public void easterEgg() throws IOException {
        if (counter < 5) {
            counter++;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hidden.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Easter egg");
            stage.showAndWait();
        }
    }
}
