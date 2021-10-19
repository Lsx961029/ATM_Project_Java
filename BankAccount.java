public class BankAccount
{  
   private int balance; 

   public BankAccount()
   {  
      balance = 0;
   }

   public void deposit(int amount) 
   {  
      balance = balance + amount;
   }

   public void withdraw(int amount) 
   {  
      balance = balance - amount;
   }

   public int getBalance()
   {  
      return balance; 
   }
}
