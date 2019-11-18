package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TableController extends ControllerParent {
    private final ObservableList<ReceiveUser> data = FXCollections.observableArrayList();

    @FXML
    public TableView<ReceiveUser> tableView;

    @FXML
    private TableColumn<ReceiveUser, String> usernameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> lastnameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> phoneColumn;

    @FXML
    private TableColumn<ReceiveUser, String> currentOrderColumn;

    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;


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

            while (resultSet.next()) {
                String firstColumn = resultSet.getString("lastname");
                String secondColumn = resultSet.getString("firstname");
                String thirdColumn = resultSet.getString("phone");
                String fourthColumn = resultSet.getString("currentOrder");
                data.add(new ReceiveUser(firstColumn, secondColumn, thirdColumn, fourthColumn));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count < 1) {
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Такой клиент в базе отсутствует. Хотите добавить клиента?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                openNewScene(tableView, "/sample/view/add.fxml");
            }

        }
        usernameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
        phoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
        currentOrderColumn.setCellValueFactory(cell -> cell.getValue().currentOrderProperty());
        tableView.setItems(data);
        dataBaseHandler.dbConnection.close();

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    tableView.getSelectionModel().getSelectedItem();
                    if (tableView.getSelectionModel().getSelectedItem().getCurrentOrder().equals("+")) {
                        currentOrder = "Текущий заказ существует";
                    } else
                        currentOrder = "Текущий заказ отсутствует";
                    client = tableView.getSelectionModel().getSelectedItem().getFirstname() + " "
                            + tableView.getSelectionModel().getSelectedItem().getLastname() + " " + "\n"
                            + tableView.getSelectionModel().getSelectedItem().getPhone() + " " + "\n"
                            + currentOrder;
                        openNewScene(tableView, "/sample/view/personEditDialog.fxml");
                }
            }
        });
    }
}