package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CurrentOrderDetailsController extends TableController{
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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String [] substr = client.split(" ");
        ReceiveUser receiveUser = new ReceiveUser(substr[1].trim(), substr[0].trim(), substr[2].trim(), id);
        ResultSet resultSet = dataBaseHandler.findUser(receiveUser, Const.ORDERS_TABLE);
        try {
            while (resultSet.next()) {
                clientInfo.setText(substr[0] + " " + substr[1] + "\n" + substr[2].trim());
                startOrderDate.setText(resultSet.getString("startOrderDate"));
                endOrderDate.setText(resultSet.getString("endOrderDate"));
                details.setText(resultSet.getString("orderDetails"));
                orderPrice.setText(String.valueOf(resultSet.getInt("price")));
                if (resultSet.getString("CheckPayment").equals("+")) {
                    priceCheckbox.setSelected(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
