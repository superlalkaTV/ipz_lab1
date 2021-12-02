package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

  public static Connection getConnection() {
    try {

      return DriverManager.getConnection("jdbc:mysql://localhost:3306/ipz_lab?useSSL=false", "root", "admin");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
