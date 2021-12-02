package DAO;

import Model.Account;
import Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {

  private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=(?)";
  private static final String GET_ALL_USERS = "SELECT * FROM users";
  private static final String INSERT_USER = "INSERT INTO users (name) VALUES (?)";
  private static final String DELETE_USER = "DELETE FROM users WHERE id=(?)";
  private static final String UPDATE_MONEY = "UPDATE users SET cash=(?) WHERE id=(?)";
  private static final String GET_CASH = "SELECT cash FROM users WHERE id=(?)";

  public List<Account> getAllUsers() {
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS);) {
      ResultSet rs = statement.executeQuery();
      List<Account> result = new ArrayList<>();
      while (rs.next()) {
        result.add(new Account(rs.getInt("id"), rs.getString("name"), rs.getInt("cash")));
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public Account getUserById(int id) {
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);) {
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (!rs.next()) {
        return null;
      }
      return new Account(id, rs.getString("name"), rs.getInt("cash"));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void addAccount(String name) {
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
      statement.setString(1, name);
      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void removeAccount(int id) {
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void depositeMoney(int id, int deposite) {
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_MONEY);
        PreparedStatement statement1 = connection.prepareStatement(GET_CASH)) {
      statement1.setInt(1, id);
      ResultSet rs = statement1.executeQuery();
      int num = rs.next() ? rs.getInt("cash") : 0;
      num += deposite;
      statement.setInt(1, num);
      statement.setInt(2, id);
      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendMoney(int senderId, int recipientId, int cash) {
    Account sender = getUserById(senderId);
    if (getUserById(recipientId) == null || getUserById(senderId) == null) {
      return;
    }
    if (sender.getCash() < cash) {
      return;
    }
    try (Connection connection = Util.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_MONEY);
        PreparedStatement statement1 = connection.prepareStatement(GET_CASH)) {
      statement1.setInt(1, senderId);
      ResultSet rs = statement1.executeQuery();
      int senderMoney = rs.next() ? rs.getInt("cash") : 0;
      statement1.setInt(1, recipientId);
      rs = statement1.executeQuery();
      int recipientMoney = rs.next() ? rs.getInt("cash") : 0;
      senderMoney -= cash;
      recipientMoney += cash;
      statement.setInt(1, senderMoney);
      statement.setInt(2, senderId);
      statement.executeUpdate();
      statement.setInt(1, recipientMoney);
      statement.setInt(2, recipientId);
      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}