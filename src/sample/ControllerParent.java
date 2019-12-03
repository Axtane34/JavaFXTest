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
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerParent {

    public static String client;
    public static String name;
    public static String userlastname;
    public static String userphone;
    public static int id;
    public Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public Button backButton;

    @FXML
    protected TextField surname_field;

    @FXML
    protected TextField name_field;

    @FXML
    protected TextField phone_field;

    @FXML
    private CheckBox checkPayment;

    @FXML
    private TextField orderDetail;

    @FXML
    protected TextField price;

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
                    dataBaseHandler.executeUser(user, Const.USER_TABLE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Клиент успешно добавлен!");

                    alert.showAndWait();
                    client = user.getLastname() + " "
                            + user.getFirstname() + " " + "\n"
                            + user.getPhone();
                    openNewScene(backButton, "/sample/view/personEditDialog.fxml");
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка :(");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }
        } else if (table.equals(Const.ORDERS_TABLE)) {
            String firstname = name_field.getText().trim();
            String lastname = surname_field.getText().trim();
            String phone = phone_field.getText().trim();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
            int money = 0;
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(price.getText().trim());
            try {
                timestamp1.setTime(System.currentTimeMillis() + (hours.getValue() * 1000 * 60 * 60) + (days.getValue() * 1000 * 60 * 60 * 24));
                if (matcher.matches()) {
                    money = Integer.parseInt(price.getText().trim());
                }else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Указывая цену, используйте целочисленные значения!");

                    alert.showAndWait();
                    price.setText("");
                }
            } catch (NullPointerException | NumberFormatException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка :(");
                alert.setHeaderText(null);
                alert.setContentText("Задайте ориентировочное время работы и цену!");

                alert.showAndWait();
            }

            String details = orderDetail.getText().trim();
            String check = "-";
            if (checkPayment.isSelected()) {
                check = "+";
            }
            pattern = Pattern.compile("^(8)\\d{10}");
            matcher = pattern.matcher(phone);

            if (!firstname.equals("") && !lastname.equals("") && !phone.equals("") && !timestamp.equals(timestamp1) && !details.equals("") && !price.getText().trim().equals("")) {

                if (!matcher.matches()) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите телефон в формате 89991112323!");

                    alert.showAndWait();
                } else {
                    User user = new User(firstname, lastname, phone, timestamp, timestamp1, details, money, check);
                    dataBaseHandler.executeUser(user, Const.ORDERS_TABLE);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Информация");
                    alert.setHeaderText(null);
                    alert.setContentText("Заказ успешно создан!");

                    alert.showAndWait();
                    openNewScene(backButton, "/sample/view/personEditDialog.fxml");
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




