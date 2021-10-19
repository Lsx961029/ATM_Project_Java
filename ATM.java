public class ATM {
  private int state;
  private int accountNumber;
  private Customer currentCustomer;
  private BankAccount currentAccount;
  private Bank theBank;

  public static final int START = 1;
  public static final int START2 = 2;
  public static final int MAIN = 3;
  public static final int VIEW = 4;
  public static final int WITHDRAW = 5;
  public static final int DEPOSIT = 6;
  public static final int INCORRECT = 7;

  public ATM(Bank aBank) {
    theBank = aBank;
    reset();
  }

  public void reset() {
    accountNumber = -1;
    currentAccount = null;
    state = START;
  }

  public void setAccountNumber(int Number) {
    assert state == START;
    accountNumber = Number;
  }

  public void selectCustomer(int pin) {
    assert state == START2;
    currentCustomer = theBank.findCustomer(accountNumber, pin);
    if (currentCustomer == null)
      state = INCORRECT;
    else {
      currentAccount = currentCustomer.getAccount();
      state = MAIN;
    }
  }

  public void withdraw(int value) {
    assert state == WITHDRAW;
    currentAccount.withdraw(value);
  }

  public void deposit(int value) {
    assert state == DEPOSIT;
    currentAccount.deposit(value);
  }

  public int getBalance() {
    return currentAccount.getBalance();
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

}