package sample;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void executeUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "("
                + Const.USERS_FIRSTNAME + ", "
                + Const.USERS_LASTNAME + ", "
                + Const.USERS_PHONE + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet findUser(ReceiveUser receiveUser){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE ";
        if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("")) {
            select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                    Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'" + " AND " +
                    Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
        }else if (!receiveUser.getFirstname().equals("") && !receiveUser.getLastname().equals("")) {
            select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                    Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'";
        }else if (!receiveUser.getFirstname().equals("") && !receiveUser.getPhone().equals("")) {
            select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'" + " AND " +
                    Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
        }else if (!receiveUser.getLastname().equals("") && !receiveUser.getPhone().equals("")) {
            select += Const.USERS_LASTNAME + " = '" + receiveUser.getLastname() + "'" + " AND " +
                    Const.USERS_PHONE + " = '" + receiveUser.getPhone() + "'";
        }else if (!receiveUser.getFirstname().equals("")){
            select += Const.USERS_FIRSTNAME + " = '" + receiveUser.getFirstname() + "'";
        }else if (!receiveUser.getLastname().equals("")){
            select += Const.USERS_LASTNAME + " = '" + receiveUser.getLastname()+ "'";
        }else {
            select += Const.USERS_PHONE + " = '" + receiveUser.getPhone()+ "'";
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
