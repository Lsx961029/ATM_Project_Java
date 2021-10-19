public class Customer
{  
  private int accountNumber;
  private int pin;
  private BankAccount account;

  public Customer(int accountNumber, int pin)
  {  
    this.accountNumber = accountNumber;
    this.pin = pin;
    account = new BankAccount();
  }
   
  public boolean match(int accountNumber, int pin)
  {  
    return this.accountNumber == accountNumber && this.pin == pin;
  }

  public BankAccount getAccount()
  {  
    return account;
  }
}