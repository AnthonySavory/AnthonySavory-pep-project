package DAO;

import Model.Account;

import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

    public Account UserRegister(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            // Inserting into the account database
            // Username, password

            String sql = "Insert into account ( username, password) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // write preparedStatement's setString method here.

            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.username, account.password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account UserLogin(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            // Write SQL logic here
            String sql = "select * from account where username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // write preparedStatement's setInt method here.
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account replacment = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));

                return replacment;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
