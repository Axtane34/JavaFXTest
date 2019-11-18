package sample;

import java.sql.*;
import java.util.Calendar;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void executeUser(User user) {
        if (user.getStartOrderDate()==null) {
            String insert = "INSERT INTO " + Const.USER_TABLE + "("
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
            String insert = "INSERT INTO " + Const.ORDERS_TABLE + "("
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
                String hex = "UPDATE users SET currentOrder = " + "'+'" + " WHERE phone = " + user.getPhone();
                System.out.println(hex);
                statement.executeUpdate(hex);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet findUser(ReceiveUser receiveUser) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE ";
        if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("")) {
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
