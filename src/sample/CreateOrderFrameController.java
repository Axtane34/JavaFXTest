package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateOrderFrameController extends ControllerParent {

    private final ObservableList<Integer> data = FXCollections.observableArrayList();
    private final ObservableList<Integer> data1 = FXCollections.observableArrayList();

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
    private CheckBox discountCheckbox;

    @FXML
    private TextArea discountCounter;


    @FXML
    void initialize() {
        String [] substr = client.split(" ");
        surname_field.setText(substr[0]);
        name_field.setText(substr[1]);
        phone_field.setText(substr[2]);
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ReceiveUser receiveUser = new ReceiveUser(substr[1].trim(), substr[0].trim(), substr[2].trim());
        ResultSet resultSet = dataBaseHandler.findUser(receiveUser, Const.USER_TABLE);
        try {
            while (resultSet.next()) {
                discountCounter.setText(String.valueOf(resultSet.getInt("discountCount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(discountCounter.getText())!=0){
            discountCheckbox.setDisable(false);
        }

        discountCheckbox.setOnAction(event -> {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(price.getText().trim());
            if (!price.getText().equals("") && matcher.matches()) {

                if (discountCheckbox.isSelected()) {
                    double calc = Integer.parseInt(price.getText())*0.8;
                    int actualPrice = (int) calc;
                    price.setText(String.valueOf(actualPrice));
                } else {
                    double calc = Integer.parseInt(price.getText())/0.8;
                    int actualPrice = (int) calc;
                    price.setText(String.valueOf(actualPrice));
                }
            }else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Перед списанием акции необходимо указать цену!");

                alert.showAndWait();
                price.setText("");
                discountCheckbox.setSelected(false);
            }
        });

        for (int i = 0; i < 13; i++) {
            data.add(i);
        }
        for (int i = 0; i < 366; i++){
            data1.add(i);
        }
        hours.setItems(data);
        days.setItems(data1);

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/personEditDialog.fxml");
        });

        addNewOrderButton.setOnAction(event -> {
        addNewUser(Const.ORDERS_TABLE);
        if (discountCheckbox.isSelected()){
        dataBaseHandler.discountDecrement(receiveUser);
        }
        });
    }
}
