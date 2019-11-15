package sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddController extends ControllerParent{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;


    @FXML
    void initialize() {

        addButton.setOnAction(event -> {
            addNewUser();
        });

        backButton.setOnAction(event -> {
            openNewScene(backButton, "/sample/view/sample.fxml");
        });
    }

    private void addNewUser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

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
            }else {
                dataBaseHandler.executeUser(user);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Клиент успешно добавлен!");

                alert.showAndWait();
                openNewScene(addButton, "/sample/view/sample.fxml");
            }
        }else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка :(");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля!");

            alert.showAndWait();
        }
    }
}
