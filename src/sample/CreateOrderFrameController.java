package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrderFrameController extends ControllerParent {

    private final ObservableList<Integer> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button addNewOrderButton;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField name_field;

    @FXML
    void initialize() {
        String [] substr = client.split(" ");
        surname_field.setText(substr[0]);
        name_field.setText(substr[1]);
        phone_field.setText(substr[2]);
        for (int i = 0; i < 13; i++) {
            data.add(i);
        }
        hours.setItems(data);
        days.setItems(data);

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/personEditDialog.fxml");
        });

        addNewOrderButton.setOnAction(event -> {
        addNewUser(Const.ORDERS_TABLE);
        });
    }
}
