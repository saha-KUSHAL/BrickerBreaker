package game;

import utils.Load;

import javax.swing.*;

public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public GameWindow(GamePanel gamePanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Brick Breaker");
        setIconImage(Load.LoadImage(Load.LogoIcon));
        requestFocus();
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
