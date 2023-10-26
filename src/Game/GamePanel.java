package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import GameEntity.Ball;

public class GamePanel extends JPanel {

	
	private Ball ball;
	public GamePanel() {
		setPreferredSize(new Dimension(Game.Width,Game.Hight));
		setDoubleBuffered(true);
		ball = new Ball();
	}
	
	protected void update() {
		ball.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ((Graphics2D) g);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.render(g2);
		g.dispose();
	}
}
