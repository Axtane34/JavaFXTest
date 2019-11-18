package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PersonEditDialogController extends ControllerParent {

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button orderHistoryButton;

    @FXML
    private Button backButton;

    @FXML
    private Button createNewOrderButton;

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

