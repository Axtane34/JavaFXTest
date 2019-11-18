package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonEditDialogController extends ControllerParent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button orderHistoryButton;

    @FXML
    private Button backButton;

    @FXML
    private Button createNewOrderButton;

    @FXML
    private TableView<?> orderTableView;

    @FXML
    private TableColumn<?, ?> orderLastnameColumn;

    @FXML
    private TableColumn<?, ?> orderFirstnameColumn;

    @FXML
    private TableColumn<?, ?> orderPhoneColumn;

    @FXML
    private TableColumn<?, ?> startOrderColumn;

    @FXML
    private TableColumn<?, ?> endOrderColumn;

    @FXML
    private TableColumn<?, ?> orderDetailsColumn;

    @FXML
    private TableColumn<?, ?> orderPriceColumn;

    @FXML
    private TableColumn<?, ?> orderCheckPaymentColumn;


    @FXML
    private TextArea currentClientField;

    @FXML
    void initialize() {
        currentClientField.setText(client);

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/sample.fxml");
        });

        createNewOrderButton.setOnAction(event -> {
            openNewScene(createNewOrderButton, "/sample/view/createOrderFrame.fxml");
        });
    }

}

