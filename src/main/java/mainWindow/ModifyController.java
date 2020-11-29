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

public class ModifyController {

    public Manager manager;
    public boolean changed;

    @FXML
    public TextField nameField, quanField, catField;
    @FXML
    public Button button;

    public ModifyController(Manager manager) {
        this.manager = manager;
        changed = false;
    }

    public ModifyController() {
    }

    public void modify() throws IOException {
        if (nameField.getText().equals("") || quanField.getText().equals("")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/badValue.fxml"));
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
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error");
            stage.showAndWait();
        } else if (catField.getText().equals("")) {
            changed = true;
            manager.setName(nameField.getText());
            manager.setQuantity(Integer.parseInt(quanField.getText()));
            manager.setCategory("<brak>");
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
        else {
            changed = true;
            manager.setName(nameField.getText());
            manager.setQuantity(Integer.parseInt(quanField.getText()));
            manager.setCategory(catField.getText());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        }
    }

    public Manager getManager() {
        changed = false;
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        nameField.setText(this.manager.getName());
        quanField.setText(String.valueOf(this.manager.getQuantity()));
        catField.setText(this.manager.getCategory());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setTitle("Edit " + nameField.getText() + " category: " + catField.getText());
    }

    public boolean isChanged() {
        return changed;
    }
}
