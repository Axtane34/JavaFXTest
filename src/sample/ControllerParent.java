package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerParent {

    public static String client;
    public static String currentOrder;
    public Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public Button backButton;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private CheckBox checkPayment;

    @FXML
    private TextField orderDetail;

    @FXML
    private TextField price;

    @FXML
    public ComboBox<Integer> hours;

    @FXML
    public ComboBox<Integer> days;

    public void openNewScene(Node node, String window) {
        node.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Учет клиентов");
        stage.getIcons().add(new Image("/image/stackoverflow.png"));
        stage.setScene(new Scene(root, 800, 500));
        stage.show();

    }

    public void addNewUser(String table) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        if (table.equals(Const.USER_TABLE)) {
            String name = name_field.getText().trim();
            String lastname = surname_field.getText().trim();
            String phone = phone_field.getText().trim();
            Pattern pattern = Pattern.compile("^(8)\\d{10}");
            Matcher matcher = pattern.matcher(phone);
            User user = new User(name, lastname, phone);

            if (!name.equals("") && !lastname.equals("") && !phone.equals("")) {
                if (!matcher.matches()) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите телефон в формате 89991112323!");

                    alert.showAndWait();
                } else {
                    dataBaseHandler.executeUser(user);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Клиент успешно добавлен!");

                    alert.showAndWait();
                    openNewScene(backButton, "/sample/view/sample.fxml");
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка :(");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }
        }else {
            String firstname = name_field.getText().trim();
            String lastname = surname_field.getText().trim();
            String phone = phone_field.getText().trim();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis() + (hours.getValue()*1000*60*60) + (days.getValue()*1000*60*60*24));
            String details = orderDetail.getText().trim();
            int money = Integer.parseInt(price.getText().trim());
            String check = "-";
            if (checkPayment.isSelected()){
                check = "+";
            }
            Pattern pattern = Pattern.compile("^(8)\\d{10}");
            Matcher matcher = pattern.matcher(phone);

            User user = new User(firstname,lastname,phone,timestamp,timestamp1,details,money,check);

            if (!firstname.equals("") && !lastname.equals("") && !phone.equals("") && !timestamp.equals(null) && !timestamp1.equals(null) && !details.equals("") && money!=0) {
                if (!matcher.matches()) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите телефон в формате 89991112323!");

                    alert.showAndWait();
                } else {
                    dataBaseHandler.executeUser(user);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Заказ успешно создан!");

                    alert.showAndWait();
                    openNewScene(backButton, "/sample/view/sample.fxml");
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка :(");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }

        }
    }

}
