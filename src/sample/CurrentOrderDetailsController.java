package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CurrentOrderDetailsController extends TableController {
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
    private Button sendSmsButton;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/personEditDialog.fxml");
        });
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String[] substr = client.split(" ");
        ReceiveUser receiveUser = new ReceiveUser(substr[1].trim(), substr[0].trim(), substr[2].trim(), id);
        ReceiveUser receiveUser1 = new ReceiveUser(substr[1].trim(), substr[0].trim(), substr[2].trim());
        ResultSet resultSet = dataBaseHandler.findUser(receiveUser, Const.ORDERS_TABLE);

        User user = new User();
        try {
            while (resultSet.next()) {
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPhone(resultSet.getString("phone"));
                user.setStartOrderDate(resultSet.getTimestamp("startOrderDate"));
                user.setEndOrderDate(resultSet.getTimestamp("endOrderDate"));
                user.setOrderDetails(resultSet.getString("orderDetails"));
                user.setPrice(resultSet.getInt("price"));
                if (resultSet.getString("CheckPayment").equals("+")) {
                    priceCheckbox.setSelected(true);
                }
                user.setCheckPayment("+");
            }
            clientInfo.setText(substr[0] + " " + substr[1] + "\n" + substr[2].trim());
            startOrderDate.setText(String.valueOf(user.getStartOrderDate()));
            endOrderDate.setText(String.valueOf(user.getEndOrderDate()));
            details.setText(user.getOrderDetails());
            orderPrice.setText(String.valueOf(user.getPrice()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeOrderButton.setOnAction(event -> {
            if (priceCheckbox.isSelected()) {
                dataBaseHandler.executeUser(user, Const.ORDERS_HISTORY_TABLE);
                dataBaseHandler.currentOrderDecrement(user);
                ResultSet resultSet1 = dataBaseHandler.findUser(receiveUser1, Const.USER_TABLE);
                int closeOrdersCount = 0;
                try {
                    while (resultSet1.next()) {
                        closeOrdersCount = resultSet1.getInt("closeOrders");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (closeOrdersCount % 5 == 0 && closeOrdersCount != 0) {
                    dataBaseHandler.discountIncrement(receiveUser1);
                }
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Заказ успешно завершен!");
                alert.showAndWait();
                openNewScene(closeOrderButton, "/sample/view/personEditDialog.fxml");

            } else {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Оплата внесена?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    priceCheckbox.setSelected(true);
                }
            }
        });
        sendSmsButton.setOnAction(event -> {
            SmsSender smsSender = new SmsSender();
            String text = receiveUser.getFirstname() + ", Ваш заказ готов!\nТел 677-800, Ателье";
            smsSender.sendSms(receiveUser.getPhone(), text, "TEST-SMS");
            if (SmsSender.resultString.contains("error")) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("На счете недостаточно средств!");
                alert.showAndWait();
            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Сообщение отправлено!");
                alert.showAndWait();
            }

        });


    }
}
