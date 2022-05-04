package BankingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.UUID;

public class AccountingWork {
    Connection cn = new Database().getConnection();
    Statement statement = cn.createStatement();

    Scanner sc = new Scanner(System.in);

    public AccountingWork() throws Exception {
    }

    public void newAccount() throws Exception {
        //Creating Database Connection
        String acNo= UUID.randomUUID().toString().split("-")[0];

        System.out.println("Enter your first name: ");
        String fn = sc.nextLine();
        System.out.println("Enter your last name: ");
        String ln = sc.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter your address name: ");
        String address = sc.nextLine();
        System.out.println("Enter your ATM pin : ");
        int atmPin = sc.nextInt();
        try {
            String sql = "INSERT INTO banking_details(account_number,first_name,last_name,phone_number,address,atm_pin,balance) VALUES ('"+acNo+"','" + fn + "','" + ln + "','" + phoneNumber + "','" + address + "','" + atmPin + "','0.0')";
            statement.executeUpdate(sql);
            System.out.println("Account successfully created. Thank you for banking with us!");
            String query = "select account_number from banking_details where phone_number = '"+phoneNumber+"'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            String accountNumber = resultSet.getString(1);
            System.out.println("Your account number is "+accountNumber);

        } catch (SQLException ex) {
            System.out.println("Error!!!");
            System.out.println(ex.getMessage());
        }

    }

    public void depositMoney() throws Exception {

        System.out.println("Enter your account number: ");
        int accountNumber = sc.nextInt();
        try {
            String query = "select balance,first_name,last_name from banking_details where account_number=" + accountNumber + "";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            double oldBalance = resultSet.getDouble(1);
            System.out.println("Welcome "+resultSet.getString(2)+"!!");
            System.out.println("How much money do you wish to deposit: ");
            double deposit = sc.nextDouble();
            oldBalance = deposit + oldBalance;
            try {
                String sql = "update banking_details set balance =" + oldBalance + " where account_number=" + accountNumber + "";
                statement.executeUpdate(sql);
                System.out.println("Successfully deposited the amount: " + deposit);
                System.out.println("Your new balance is " + oldBalance);
            } catch (SQLException exception) {
                System.out.println("Some error occurred! We will get back to you shortly after the error is fixed. Thank you for banking with us!");
            }

        } catch (
                SQLException ex) {
            System.out.println("Invalid Account Number!!!");
        }

    }
}
