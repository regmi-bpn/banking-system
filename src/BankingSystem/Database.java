package BankingSystem;

import java.sql.*;

public class Database {


    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Bipin7pr@bin");
        return connection;

    }
}




