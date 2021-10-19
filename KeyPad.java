import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class KeyPad extends JPanel {
  private JPanel buttonPanel;
  private JButton enterButton;
  private JTextArea display;
  private ATM theATM;

  public KeyPad(JTextArea display, ATM atm) {
    theATM = atm;

    this.display = display;

    buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(135, 206, 250));
    buttonPanel.setLayout(new GridLayout(4, 3, 10, 10));

    addButton("1");
    addButton("2");
    addButton("3");
    addButton("4");
    addButton("5");
    addButton("6");
    addButton("7");
    addButton("8");
    addButton("9");
    addButton("0");

    enterButton = new JButton("Enter");
    enterButton.addActionListener(new EnterButtonListener());
    enterButton.setPreferredSize(new Dimension(10, 10));
    buttonPanel.add(enterButton);
    setBackground(new Color(135, 206, 250));

    JPanel a = new JPanel();
    a.setLayout(new GridBagLayout());
    a.setBackground(new Color(135, 206, 250));
    a.setLayout(new GridLayout(2, 1, 0, 40));
    a.add(new JButton("Take cash here"));
    a.add(new JButton("Insert deposit envelope here"));

    add(buttonPanel);
    add(a);
    setLayout(new GridLayout(1, 2, 30, 0));

  }

  private void addButton(final String label) {
    class DigitButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {

        display.setText(display.getText() + label);

      }
    }

    JButton button = new JButton(label);
    button.setPreferredSize(new Dimension(10, 10));
    buttonPanel.add(button);
    ActionListener listener = new DigitButtonListener();
    button.addActionListener(listener);
  }

  public int getValue() {
    int index = display.getText().lastIndexOf(":");
    return Integer.parseInt(display.getText().substring(index + 1).trim());
  }

  public void clear() {
    display.setText("");
  }

  private class EnterButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      int state = theATM.getState();

      if (state == ATM.START) {
        theATM.setAccountNumber(getValue());
        theATM.setState(ATM.START2);
      }

      else if (state == ATM.START2) {
        theATM.selectCustomer(getValue());
      }

      else if (state == ATM.INCORRECT) {
        theATM.setState(ATM.START);
      }

      else if (state == ATM.MAIN) {
        switch (getValue()) {
        case 1:
          theATM.setState(ATM.VIEW);
          break;
        case 2:
          theATM.setState(ATM.WITHDRAW);
          break;
        case 3:
          theATM.setState(ATM.DEPOSIT);
          break;
        case 4:
          theATM.reset();
        }
      }

      else if (state == ATM.VIEW) {
        theATM.setState(ATM.MAIN);
      }

      else if (state == ATM.WITHDRAW) {
        switch (getValue()) {
        case 1:
          theATM.withdraw(20);
          break;
        case 2:
          theATM.withdraw(40);
          break;
        case 3:
          theATM.withdraw(60);
          break;
        case 4:
          theATM.withdraw(100);
          break;
        case 5:
          theATM.withdraw(200);
          break;
        case 6:
        }
        theATM.setState(ATM.MAIN);
      }

      else if (state == ATM.DEPOSIT) {
        theATM.deposit(getValue());
        theATM.setState(ATM.MAIN);
      }

      showState();
    }
  }

  public void showState() {
    int state = theATM.getState();
    if (state == ATM.START2)
      display.append("\n\nEnter your PIN: ");
    else if (state == ATM.INCORRECT)
      display.append("\n\nYour login failed due to an unrecognized username or password. \nPress Enter to retry.");

    else {
      clear();

      if (state == ATM.START)
        display.setText("Welcome!         Shuoxin Liu\n\nPlease enter your account number:");

      else if (state == ATM.MAIN) {
        display.setText(String.format("Main menu%n%s%n%-10s%n%-10s%n%-10s%n", "        1 - View my balance",
            "        2 - Withdraw cash", "        3 - Deposit funds", "        4 - Exit"));
        display.append("\n\n\n\nEMPLID: 23737767       Enter a choice:");
      }

      else if (state == ATM.VIEW)
        display.setText(
            "Your balance is: " + theATM.getBalance() + "\n\n\nReturn to the Main Menu: press Enter button.");

      else if (state == ATM.WITHDRAW)
        display.setText(String.format("Withdraw menu%n%s%s%n%s%s%n%s%s%nChoose a withdraw amount: ", "        1 - $20",
            "        4 - $100", "        2 - $40", "        5 - $200", "        3 - $60",
            "        6 - Cancel transaction"));

      else if (state == ATM.DEPOSIT)
        display.setText(
            "\nEnter the amount that you want to deposit(Press enter to continue):\n                                                    ");
    }
  }

}
