package zelda.java2d;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Main
{
  public static void main(String ... args)
  {
    Runnable r = () -> {
        JFrame gameView = new JFrame("2D Zelda Top-Down Game");
        gameView.setSize(486, 474);
        gameView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameView.setResizable(false);
        ControlPad a = new ControlPad();
        gameView.getContentPane().add(new Game(a));
        gameView.setIconImage(ImageUtil.get("winIcon.png"));
        gameView.setVisible(true);
        gameView.setFocusable(true);
        gameView.requestFocus();
        gameView.addKeyListener(a);
        gameView.setLocationRelativeTo(null);
    };
    EventQueue.invokeLater(r);
  }
}
