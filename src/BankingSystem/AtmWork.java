package BankingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AtmWork{
    Connection cn = new Database().getConnection();
    Statement statement = cn.createStatement();

    Scanner sc = new Scanner(System.in);

    public AtmWork() throws SQLException {
    }

    public void withdrawMoney() throws Exception {

        System.out.println("Enter your Account Number: ");
        int accountNumber = sc.nextInt();



        try {
            String query = "SELECT account_number, balance, atm_pin from banking_details where account_number =" + accountNumber ;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            double oldBalance = resultSet.getDouble(2);
            int aPin = resultSet.getInt(3);
            System.out.println("Enter your Atm Pin: ");
            int atmPin= sc.nextInt();
            sc.nextLine();
            if (atmPin==aPin) {
                System.out.println("Enter the amount you want to withdraw: ");
                double withDraw = sc.nextDouble();
                if(oldBalance>= withDraw) {
                    double newBalance = oldBalance - withDraw;

                    try {
                        String balance = "update banking_details set balance =" + newBalance + "where account_number = " + accountNumber;
                        statement.executeUpdate(balance);
                        System.out.println("Your balance has been withdrawn successfully");
                        System.out.println("Your remaining balance is: " + newBalance);
                    } catch (SQLException exception) {
                        System.out.println("Error!! Sorry for the inconvenience");
                    }
                }else{
                    System.out.println("Insufficient balance. Try again!!");
                }
            }else{
                System.out.println("Wrong Pin!!!");
            }

        }catch(SQLException exception){
            System.out.println("Check your credentials and enter again. Thank you for banking with us!!");

        }




    }

    public void balanceInquiry() {
        System.out.println("Enter you account number: ");
        int ac =sc.nextInt();
        try {
            String newQuery = "SELECT account_number, balance, atm_pin from banking_details where account_number=" + ac;
            ResultSet resultSet = statement.executeQuery(newQuery);
            resultSet.next();
            double balance = resultSet.getDouble(2);
            int aPin = resultSet.getInt(3);
            System.out.println("Enter you atm pin: ");
            int atmPin =sc.nextInt();
            sc.nextLine();
            if(atmPin==aPin) {
                System.out.println("Your current balance in your account is " + balance);
            }else{
                System.out.println("Your atm pin is wrong. Try again!!!");
            }
        }catch (SQLException exception){
            System.out.println("Enter a valid account number!!");
        }

    }
}
