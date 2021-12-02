package Model;

public class Account {

  private static int SUM = 1;
  private int id;
  private String name;
  private int cash;

  public Account(int id, String name, int cash){
    this.id = id;
    this.name = name;
    this.cash = cash;
  }

  public Account(String name) {
    this.name = name;
    id = SUM;
    cash = 0;
    SUM++;
  }

  public int getId() {
    return id;
  }

  public boolean send(Account anotherAccount, int cashToSend) {
    if (cashToSend > 0 && cashToSend <= cash) {
      anotherAccount.cash += cashToSend;
      cash -= cashToSend;
      return true;
    }

    return false;
  }

  public void deposite(int cashToDeposite) {
    if (cashToDeposite > 0) {
      cash += cashToDeposite;
    }
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCash() {
    return cash;
  }

  public void setCash(int cash) {
    this.cash = cash;
  }
}
