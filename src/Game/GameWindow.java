package Game;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public GameWindow(GamePanel gamePanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Brick Breaker");
		requestFocus();
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
