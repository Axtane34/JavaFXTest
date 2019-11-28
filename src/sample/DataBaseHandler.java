package sample;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void currentOrderDecrement(User user){
        try {
            Statement statement = getConnection().createStatement();
            String update;
            update = "UPDATE users SET currentOrder = currentOrder-1" + " WHERE phone = " + user.getPhone();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void discountDecrement(ReceiveUser receiveUser){
        try {
            Statement statement = getConnection().createStatement();
            String update;
            update = "UPDATE users SET discountCount = discountCount-1" + " WHERE phone = " + receiveUser.getPhone();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void discountIncrement(ReceiveUser receiveUser){
        try {
            Statement statement = getConnection().createStatement();
            String update;
            update = "UPDATE users SET discountCount = discountCount+1" + " WHERE phone = " + receiveUser.getPhone();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUser(User user, String table) {
        if (table.equals(Const.USER_TABLE)) {
            String insert = "INSERT INTO " + table + "("
                    + Const.USERS_FIRSTNAME + ", "
                    + Const.USERS_LASTNAME + ", "
                    + Const.USERS_PHONE + ")" +
                    "VALUES(?,?,?)";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
                preparedStatement.setString(1, user.getFirstname());
                preparedStatement.setString(2, user.getLastname());
                preparedStatement.setString(3, user.getPhone());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            String insert = "INSERT INTO " + table + "("
                    + Const.USERS_FIRSTNAME + ", "
                    + Const.USERS_LASTNAME + ", "
                    + Const.USERS_PHONE + ", "
                    + Const.ORDERS_STARTORDERDATE + ", "
                    + Const.ORDERS_ENDORDERDATE + ", "
                    + Const.ORDERS_ORDERDETAILS + ", "
                    + Const.ORDERS_PRICE + ", "
                    + Const.ORDERS_CHECKPAYMENT + ")" +
                    "VALUES(?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
                preparedStatement.setString(1, user.getFirstname());
                preparedStatement.setString(2, user.getLastname());
                preparedStatement.setString(3, user.getPhone());
                preparedStatement.setTimestamp(4, user.getStartOrderDate());
                preparedStatement.setTimestamp(5, user.getEndOrderDate());
                preparedStatement.setString(6, user.getOrderDetails());
                preparedStatement.setInt(7, user.getPrice());
                preparedStatement.setString(8, user.getCheckPayment());

                preparedStatement.executeUpdate();
                Statement statement = getConnection().createStatement();
                String hex;
                String delete = "";
                if (table.equals(Const.ORDERS_TABLE)) {
                    hex = "UPDATE users SET currentOrder = currentOrder+1" + " WHERE phone = " + user.getPhone();
                }else {
                    hex = "UPDATE users SET closeOrders = closeOrders+1" + " WHERE phone = " + user.getPhone();
                    delete = "DELETE from currentorder WHERE id = '" + ControllerParent.id + "'";

                }
                statement.executeUpdate(hex);
                if (table.equals(Const.ORDERS_HISTORY_TABLE)) {
                    statement.executeUpdate(delete);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet findUser(ReceiveUser receiveUser, String table) {
        ResultSet resultSet = null;
        String select;
        if (table.equals(Const.USER_TABLE)) {
            select = "SELECT * FROM " + Const.USER_TABLE + " WHERE ";
        }else if(table.equals(Const.ORDERS_TABLE)) {
            select = "SELECT * FROM " + Const.ORDERS_TABLE + " WHERE ";
        }else {
            select = "SELECT * FROM " + Const.ORDERS_HISTORY_TABLE + " WHERE ";
        }
            if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("") && receiveUser.getOrderId()!=0) {
            select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                    Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'" + " AND " +
                    Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'" + " AND " +
                    Const.USERS_ID + " = '" + receiveUser.getOrderId() + "'";
            } else if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("")) {
                select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                        Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'" + " AND " +
                        Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
            } else if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("")) {
                select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                        Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'";
            } else if (!receiveUser.getFirstname().equals("") && !receiveUser.getPhone().equals("")) {
                select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                        Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
            } else if (!receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("")) {
                select += Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'" + " AND " +
                        Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
            } else if (!receiveUser.getFirstname().equals("")) {
                select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'";
            } else if (!receiveUser.getLastname().equals("")) {
                select += Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'";
            } else {
                select += Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
            }
        try {
            Statement statement = getConnection().createStatement();
            resultSet = statement.executeQuery(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }
}
