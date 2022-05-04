package BankingSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class BankingMain {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int opt;
        AccountingWork aw =new AccountingWork();
        AtmWork atm = new AtmWork();
        boolean running = true;

        while(running){

            System.out.println("\n" +
                    "Menu\n" +
                    "What do you want to do?\n" +
                    "1. Create a new account \n" +
                    "2. Deposit money\n" +
                    "3. Withdraw money\n" +
                    "4. Balance Inquiry \n" +
                    "0. Cancel Process \n" +
                    " \n");

            opt = sc.nextInt();

            switch(opt){
                case 1:
                    aw.newAccount();
                    break;
                case 2:
                    aw.depositMoney();
                    break;
                case 3:
                    atm.withdrawMoney();
                    break;
                case 4:
                    atm.balanceInquiry();
                    break;
                case 0:
                    running=false;
                    break;
                default:
                    System.out.println("Enter a valid choice.");
                    break;
            }

        }


    }

}
