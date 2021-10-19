import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
  private ArrayList<Customer> customers;

  public Bank() {
    customers = new ArrayList<Customer>();
  }

  public void readCustomers(String filename) throws IOException {
    Scanner in = new Scanner(new FileReader(filename));
    while (in.hasNext()) {
      int accountNumber = in.nextInt();
      int pin = in.nextInt();
      Customer customer = new Customer(accountNumber, pin);
      addCustomer(customer);
    }
    in.close();
  }

  public void addCustomer(Customer c) {
    customers.add(c);
  }

  public Customer findCustomer(int number, int pi) {
    for (Customer customer : customers) {
      if (customer.match(number, pi))
        return customer;
    }

    return null;
  }
}
