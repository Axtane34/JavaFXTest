package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class AddController extends ControllerParent{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;


    @FXML
    void initialize() {

        addButton.setOnAction(event -> {
            addNewUser(Const.USER_TABLE);
        });

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/sample.fxml");
        });
    }
}
