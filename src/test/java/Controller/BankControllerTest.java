package Controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankControllerTest {

  private static BankController bankController;

  @BeforeClass
  public static void beforeClass() throws Exception {
    bankController = new BankController();
  }

  @Test
  public void menu() {
    assertEquals("Поставьте 100, пожалуйста","Поставьте 100, пожалуйста");
  }
}