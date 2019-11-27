package sample;

import javafx.beans.property.*;

public class ReceiveUser {
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty phone;
    private StringProperty currentOrder;
    private StringProperty startOrderDate;
    private StringProperty endOrderDate;
    private StringProperty orderDetails;
    private IntegerProperty price;
    private StringProperty checkPayment;
    private int id;

    public ReceiveUser(){

    }

    public ReceiveUser(String firstname, String lastname, String phone) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phone = new SimpleStringProperty(phone);

    }

    public ReceiveUser(String firstname, String lastname, String phone, String currentOrder) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phone = new SimpleStringProperty(phone);
        this.currentOrder = new SimpleStringProperty(currentOrder);
    }

    public ReceiveUser(String firstname, String lastname, String phone, String currentOrder, int id) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phone = new SimpleStringProperty(phone);
        this.currentOrder = new SimpleStringProperty(currentOrder);
        this.id = id;
    }

    public ReceiveUser(String firstname, String lastname, String phone, String startOrderDate, String endOrderDate, String orderDetails, int price, String checkPayment) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.phone = new SimpleStringProperty(phone);
        this.startOrderDate = new SimpleStringProperty(startOrderDate);
        this.endOrderDate = new SimpleStringProperty(endOrderDate);
        this.orderDetails = new SimpleStringProperty(orderDetails);
        this.price = new SimpleIntegerProperty(price);
        this.checkPayment = new SimpleStringProperty(checkPayment);
    }


    public String getStartOrderDate() {
        return startOrderDate.get();
    }

    public StringProperty startOrderDateProperty() {
        return startOrderDate;
    }

    public void setStartOrderDate(String startOrderDate) {
        this.startOrderDate.set(startOrderDate);
    }

    public String getEndOrderDate() {
        return endOrderDate.get();
    }

    public StringProperty endOrderDateProperty() {
        return endOrderDate;
    }

    public void setEndOrderDate(String endOrderDate) {
        this.endOrderDate.set(endOrderDate);
    }

    public String getOrderDetails() {
        return orderDetails.get();
    }

    public StringProperty orderDetailsProperty() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails.set(orderDetails);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getCheckPayment() {
        return checkPayment.get();
    }

    public StringProperty checkPaymentProperty() {
        return checkPayment;
    }

    public void setCheckPayment(String checkPayment) {
        this.checkPayment.set(checkPayment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentOrder() {
        return currentOrder.get();
    }

    public StringProperty currentOrderProperty() {
        return currentOrder;
    }

    public void setCurrentOrder(String currentOrder) {
        this.currentOrder.set(currentOrder);
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
