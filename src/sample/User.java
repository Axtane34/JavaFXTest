package sample;

import java.sql.Timestamp;

public class User {
    private String firstname;
    private String lastname;
    private String phone;
    private Timestamp startOrderDate;
    private Timestamp endOrderDate;
    private String orderDetails;
    private int price;
    private String checkPayment;

    public User() {
    }

    public User(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public User(String firstname, String lastname, String phone, Timestamp startOrderDate, Timestamp endOrderDate, String orderDetails, int price, String checkPayment) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.startOrderDate = startOrderDate;
        this.endOrderDate = endOrderDate;
        this.orderDetails = orderDetails;
        this.price = price;
        this.checkPayment = checkPayment;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(String checkPayment) {
        this.checkPayment = checkPayment;
    }

    public Timestamp getStartOrderDate() {
        return startOrderDate;
    }

    public void setStartOrderDate(Timestamp startOrderDate) {
        this.startOrderDate = startOrderDate;
    }

    public Timestamp getEndOrderDate() {
        return endOrderDate;
    }

    public void setEndOrderDate(Timestamp endOrderDate) {
        this.endOrderDate = endOrderDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
