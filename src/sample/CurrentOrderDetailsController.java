package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentOrderDetailsController extends ControllerParent{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TitledPane currentOrderDetails;

    @FXML
    private TextArea startOrderDate;

    @FXML
    private TextArea endOrderDate;

    @FXML
    private TextArea orderPrice;

    @FXML
    private TextArea details;

    @FXML
    private TextArea clientInfo;

    @FXML
    private Button closeOrderButton;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox priceCheckbox;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/personEditDialog.fxml");
        });
    }
}
