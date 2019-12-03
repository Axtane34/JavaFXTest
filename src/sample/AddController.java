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
        name_field.setText(name);
        surname_field.setText(userlastname);
        phone_field.setText(userphone);

        addButton.setOnAction(event -> {
            addNewUser(Const.USER_TABLE);
            name = "";
            userlastname = "";
            userphone = "";
        });

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/sample.fxml");
            name = "";
            userlastname = "";
            userphone = "";
        });
    }
}
