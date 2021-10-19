import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) {
    ATM myATM;

    try {
      Bank myBank = new Bank();
      myBank.readCustomers("customers.txt");
      myATM = new ATM(myBank);
    }

    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error opening accounts file.");
      return;
    }

    JFrame frame = new ATMFrame(myATM);

    frame.setTitle("Automated teller machine user interface");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}