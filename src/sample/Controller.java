package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller extends ControllerParent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;

    private final ObservableList<ReceiveUser> data = FXCollections.observableArrayList();

    @FXML
    private TableView<ReceiveUser> tableView;

    @FXML
    public TableColumn<ReceiveUser, String> usernameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> lastnameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> phoneColumn;

    @FXML
    private Button search_button;

    @FXML
    private Button createButton;

    @FXML
    void initialize() {
        createButton.setOnAction(event -> {
    openNewScene(createButton,"/sample/view/add.fxml");
    });

        search_button.setOnAction(event1 -> {
            try {
                searchUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void searchUser() throws SQLException {
        data.clear();
        String username = name_field.getText().trim();
        String lastname = surname_field.getText().trim();
        String phone = phone_field.getText().trim();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ReceiveUser receiveUser = new ReceiveUser(username, lastname, phone);
        int count = 0;

        ResultSet resultSet = dataBaseHandler.findUser(receiveUser);
        try {

            while(resultSet.next()){
                String firstColumn = resultSet.getString("lastname");
                String secondColumn = resultSet.getString("firstname");
                String thirdColumn = resultSet.getString("phone");
                data.add(new ReceiveUser(firstColumn,secondColumn,thirdColumn));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count<1){
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Такой клиент в базе отсутствует. Хотите добавить клиента?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK){
                openNewScene(createButton,"/sample/view/add.fxml");
            }

        }
        usernameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
        phoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
        tableView.setItems(data);

    }



}
