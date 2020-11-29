package mainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
public class addController {

    @FXML
    public Button button;
    @FXML
    public TextField nameField, quanField, catField;

    public boolean add;

    public Manager manager;

    public boolean isAdd() {
        return add;
    }

    public void confirm() throws IOException {
        if (nameField.getText().equals("") || quanField.getText().equals("")) {
            // TODO new stage that will tell the end user to pass all required information
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/badValue.fxml"));
            add = false;
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error");
            stage.showAndWait();
        } else if (!quanField.getText().matches("^[0-9]+$")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notInteger.fxml"));
            Parent root = loader.load();
            add = false;
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error");
            stage.showAndWait();
        } else if (catField.getText().equals("")) {
            add = true;
            manager = new Manager(nameField.getText(), Integer.parseInt(quanField.getText()), "<brak>");
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
        else {
            add = true;
            manager = new Manager(nameField.getText(), Integer.parseInt(quanField.getText()), catField.getText());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
    }

    public Manager getManager() {
        add = false;
        return manager;
    }
}
