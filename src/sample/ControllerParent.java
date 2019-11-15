package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerParent {

    public static String client;
    public static String currentOrder;

    public void openNewScene(Node node, String window) {
        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Учет клиентов");
        stage.getIcons().add(new Image("/image/stackoverflow.png"));
        stage.setScene(new Scene(root, 800, 500));
        stage.show();

    }
}
