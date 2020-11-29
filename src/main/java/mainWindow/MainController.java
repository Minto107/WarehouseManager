package mainWindow;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    public TableView<Manager> tableView = new TableView<>();

    public Manager add;

    public Stage stage;
    @FXML
    public Button addBt, delBt, abBt, editBt;
    @FXML
    public Label itemsCount;

    ObservableList<Manager> list;

    public void addDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        addController controller = loader.getController();
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dodaj przedmiot");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        if (controller.isAdd()) {
            add = controller.getManager();
            list.add(add);
        }
    }

    public void deleteDialog() throws IOException {
        if (tableView.getSelectionModel().getSelectedIndex() >= 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deleteDialog.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DeleteDialogController controller = loader.getController();
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Usunąć?");
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            if (controller.isDelete()) {
                list.remove(tableView.getSelectionModel().getSelectedIndex());
            }
        } else {
            showError();
        }
    }

    private void showError() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SelectError.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }


    public void aboutWindowOpen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/aboutDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("About...");
        stage.showAndWait();
    }

    public void initialize() {

        System.out.println("Welcome to Manager 1.0 \nPlease wait while loading...");

        long startTime = System.currentTimeMillis();

        TableColumn<Manager, String> productCol = new TableColumn<>("Product");
        TableColumn<Manager, Integer> quanCol = new TableColumn<Manager, Integer>("Quantity");
        TableColumn<Manager, String> catCol = new TableColumn<Manager, String>("Category");

        tableView.setEditable(true);
        tableView.getColumns().addAll(productCol, quanCol, catCol);

        list = read();

        productCol.setCellValueFactory(
                new PropertyValueFactory<Manager, String>("name")
        );
        quanCol.setCellValueFactory(
                new PropertyValueFactory<Manager, Integer>("quantity")
        );
        catCol.setCellValueFactory(
                new PropertyValueFactory<Manager, String>("category")
        );

        tableView.setItems(list);
        list.addListener((ListChangeListener.Change<? extends Manager> manager) -> {
            while (manager.next()) {
                if (manager.wasUpdated()) {
                    write();
                    itemsCount.setText("Items currently in the list " + list.size());
                }
                if (manager.wasAdded()) {
                    write();
                    itemsCount.setText("Items currently in the list " + list.size());
                }
                if (manager.wasRemoved()) {
                    write();
                    itemsCount.setText("Items currently in the list " + list.size());
                }
            }
        });

        long finishTime = System.currentTimeMillis();
        long time = finishTime - startTime;
        System.out.println("Load successful. \nLoading took " + time + "ms\nItems currently in the list " + list.size());
        itemsCount.setText("Items currently in the list " + list.size());

    }

    private ObservableList<Manager> read() {
        try {
            InputStream is = Files.newInputStream(Path.of("WarehouseDatabase.obj"));
            ObjectInputStream ois = new ObjectInputStream(is);
            List<Manager> list = (List<Manager>) ois.readObject();

            return FXCollections.observableArrayList(list);
        } catch (IOException | ClassNotFoundException e) {
            try {
                FileWriter fw = new FileWriter("WarehouseDatabase.obj");
                fw.close();
                writeClean();
                InputStream is = Files.newInputStream(Path.of("WarehouseDatabase.obj"));
                ObjectInputStream ois = new ObjectInputStream(is);
                List<Manager> list = (List<Manager>) ois.readObject();
                return FXCollections.observableArrayList(list);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
        return null;
    }

    private void write() {
        try {
            FileOutputStream fos = new FileOutputStream("WarehouseDatabase.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Manager>(list));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeClean() {
        try {
            FileOutputStream fos = new FileOutputStream("WarehouseDatabase.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ArrayList<Manager> al = new ArrayList<>();
            oos.writeObject(al);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editDialog() throws IOException {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modify.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ModifyController controller = loader.getController();
            stage = new Stage();
            stage.setScene(scene);
            //stage.setTitle("Edit");
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            controller.setManager(list.get(index));
            stage.showAndWait();
            if (controller.isChanged())
                list.set(index, controller.getManager());
        } else {
            showError();
        }
    }
}
