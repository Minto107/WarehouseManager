package mainWindow;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class hiddenController {

    @FXML
    public ImageView image;

    public void initialize() {
        Image image1 = new Image(getClass().getResource("/meme.jpg").toString());
        image.setImage(image1);
    }
}
