import Controller.BankController;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.menu(new Scanner(System.in));

  }

}
