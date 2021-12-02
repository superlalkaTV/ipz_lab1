package Controller;

import DAO.UserDAO;
import Model.Account;
import View.BankView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankController {

  private UserDAO dao = new UserDAO();
  List<Account> accounts = new ArrayList<>();

  public void menu(Scanner sc) {
    int input;
    while (true) {
      System.out.println(BankView.MENU);
      System.out.println(BankView.MENU_INPUT);
      switch (input = sc.nextInt()) {
        case 1: //Accounts
          accounts = dao.getAllUsers();
          if (accounts.isEmpty()) {
            System.out.println("Empty!");
          }
          for (Account account : accounts) {
            System.out.println(
                "Account id: " + account.getId() + ", account name: " + account.getName()
                    + ", account cash: " + account.getCash());
          }
          break;
        case 2: //Add account
          System.out.println(BankView.ENTER_ACCOUNT_NAME);
          dao.addAccount(sc.next());
          break;
        case 3: //Remove account
          System.out.println(BankView.ENTER_ID);
//            bank.removeAccount(sc.nextInt());
          dao.removeAccount(sc.nextInt());
          break;
        case 4: //Send money
          System.out.println(BankView.ENTER_SENDER_ID);
          int sender = sc.nextInt();
          System.out.println(BankView.ENTER_RECIPIENT_ID);
          int recipient = sc.nextInt();
          System.out.println(BankView.ENTER_MONEY_TO_SEND);
          int cash = sc.nextInt();
//          bank.sendMoney(sender, recipient, cash);
          dao.sendMoney(sender,recipient,cash);
          break;
        case 5: //Deposite money
          System.out.println(BankView.ENTER_ID);
          int id = sc.nextInt();
          System.out.println(BankView.ENTER_MONEY_TO_SEND);
          int deposite = sc.nextInt();
          if (deposite > 0) {
            dao.depositeMoney(id, deposite);
          }
          break;
        case 0: //Exit
          return;
      }
    }
  }
}

