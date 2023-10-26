package Game;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	private GamePanel gamePanel;
	
	public GameWindow(GamePanel gamePanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		add(gamePanel);
		pack();
		setVisible(true);
	}
}
