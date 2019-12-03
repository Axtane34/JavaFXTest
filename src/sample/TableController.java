package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TableController extends ControllerParent {
    private final ObservableList<ReceiveUser> data = FXCollections.observableArrayList();
    protected final ObservableList<ReceiveUser> data1 = FXCollections.observableArrayList();

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

    @FXML
    private TableColumn<ReceiveUser, String> discountCountColumn;

    protected Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    protected TableView<ReceiveUser> orderTableView;

    @FXML
    private TableColumn<ReceiveUser, String> orderLastnameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> orderFirstnameColumn;

    @FXML
    private TableColumn<ReceiveUser, String> orderPhoneColumn;

    @FXML
    private TableColumn<ReceiveUser, String> startOrderColumn;

    @FXML
    private TableColumn<ReceiveUser, String> endOrderColumn;

    @FXML
    private TableColumn<ReceiveUser, String> orderDetailsColumn;

    @FXML
    private TableColumn<ReceiveUser, Integer> orderPriceColumn;

    @FXML
    private TableColumn<ReceiveUser, String> orderCheckPaymentColumn;

    @FXML
    private TableColumn<ReceiveUser, Integer> idColumn;

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
            ResultSet resultSet = dataBaseHandler.findUser(receiveUser, Const.USER_TABLE);
            try {
                while (resultSet.next()) {
                    String firstColumn = resultSet.getString("lastname");
                    String secondColumn = resultSet.getString("firstname");
                    String thirdColumn = resultSet.getString("phone");
                    String fourthColumn = resultSet.getString("currentOrder");
                    String fifthColumn = resultSet.getString("discountCount");
                    data.add(new ReceiveUser(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn));
                    count++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (count < 1) {
                alert.setTitle("Информация");
                alert.setHeaderText(null);
                alert.setContentText("Такой клиент в базе отсутствует. Хотите добавить клиента?");
                name = receiveUser.getFirstname();
                userlastname = receiveUser.getLastname();
                userphone = receiveUser.getPhone();
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    openNewScene(tableView, "/sample/view/add.fxml");
                }

            }
            usernameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
            lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
            phoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
            currentOrderColumn.setCellValueFactory(cell -> cell.getValue().currentOrderProperty());
            discountCountColumn.setCellValueFactory(cell -> cell.getValue().discountCountProperty());
            tableView.setItems(data);

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    tableView.getSelectionModel().getSelectedItem();
                    client = tableView.getSelectionModel().getSelectedItem().getFirstname() + " "
                            + tableView.getSelectionModel().getSelectedItem().getLastname() + " " + "\n"
                            + tableView.getSelectionModel().getSelectedItem().getPhone();
                        openNewScene(tableView, "/sample/view/personEditDialog.fxml");
                }
            }
        });
    }

    public void searchOrder(String table) {
        data1.clear();
        String[] substr = client.split(" ");
        String lastname = substr[0];
        String name = substr[1];
        String phone = substr[2].trim();
        ReceiveUser receiveUser = new ReceiveUser(name, lastname, phone);
        int count = 0;
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.findUser(receiveUser, table);
        try {
            while (resultSet.next()) {
                String firstColumn = resultSet.getString("lastname");
                String secondColumn = resultSet.getString("firstname");
                String thirdColumn = resultSet.getString("phone");
                String fourthColumn = resultSet.getString("startOrderDate");
                String fifthColumn = resultSet.getString("endOrderDate");
                String sixthColumn = resultSet.getString("orderDetails");
                int seventhColumn = resultSet.getInt("price");
                String eighthColumn = resultSet.getString("CheckPayment");
                int orderId = resultSet.getInt("id");
                data1.add(new ReceiveUser(firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn, sixthColumn, seventhColumn, eighthColumn, orderId));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count < 1) {
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            if (table.equals(Const.ORDERS_TABLE)) {
                alert.setContentText("Незавершенных заказов нет. Хотите создать новый?");
            }else{
                alert.setContentText("У клиента нет завершенных заказов. Хотите создать новый?");
            }
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                openNewScene(orderTableView, "/sample/view/createOrderFrame.fxml");
            }

        }
        orderFirstnameColumn.setCellValueFactory(cell -> cell.getValue().lastnameProperty());
        orderLastnameColumn.setCellValueFactory(cell -> cell.getValue().firstnameProperty());
        orderPhoneColumn.setCellValueFactory(cell -> cell.getValue().phoneProperty());
        startOrderColumn.setCellValueFactory(cell -> cell.getValue().startOrderDateProperty());
        endOrderColumn.setCellValueFactory(cell -> cell.getValue().endOrderDateProperty());
        orderDetailsColumn.setCellValueFactory(cell -> cell.getValue().orderDetailsProperty());
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderCheckPaymentColumn.setCellValueFactory(cell -> cell.getValue().checkPaymentProperty());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderTableView.setItems(data1);
    }
}