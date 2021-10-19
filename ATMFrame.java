import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ATMFrame extends JFrame {
  private KeyPad pad;
  private JTextArea display;
  private ATM theATM;
  private static final int FRAME_WIDTH = 540;
  private static final int FRAME_HEIGHT = 400;

  public ATMFrame(ATM atm) {
    theATM = atm;

    display = new JTextArea(10, 40);
    display.setEditable(false);

    pad = new KeyPad(display, theATM);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setPreferredSize(new Dimension(540, 200));

    panel.setBackground(new Color(135, 206, 250));

    panel.add(display);

    setLayout(new GridLayout(2, 1));
    add(panel);
    add(pad);

    pad.showState();
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }

  public void drawRectangles(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.LIGHT_GRAY);
    g.draw3DRect(30, 25, 480, 180, true);
    g.draw3DRect(40, 35, 460, 160, false);
  }

}