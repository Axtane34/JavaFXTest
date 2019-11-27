package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller extends TableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button search_button;

    @FXML
    private Button createButton;

    @FXML
    void initialize() {
        createButton.setOnAction(event -> openNewScene(createButton, "/sample/view/add.fxml"));

        search_button.setOnAction(event1 -> {
            try {
                searchUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}





