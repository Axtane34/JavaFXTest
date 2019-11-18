package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReceiveUser {
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty phone;
    private StringProperty currentOrder;
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
