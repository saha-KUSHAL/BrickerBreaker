package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import GameEntity.Ball;
import GameEntity.Brick;
import GameEntity.BrickLoader;
import GameEntity.Paddle;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Ball ball;
	private Paddle paddle;
	private BrickLoader brickLoader;

	public GamePanel() {
		setPreferredSize(new Dimension(Game.Width, Game.Hight));
		setDoubleBuffered(true);
		setBackground(new Color(43, 60, 78));
		paddle = new Paddle();
		ball = new Ball(paddle);
		addMouseMotionListener(paddle);
		addMouseListener(paddle);
		brickLoader = new BrickLoader(ball);
	}

	protected void update() {
		if (ball.getAlive() && brickLoader.BrickCount != 0) {
			ball.update();
			paddle.update();
			brickLoader.update();
		} else {
			System.exit(0);
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ((Graphics2D) g);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.render(g2);
		paddle.render(g2);
		brickLoader.render(g2);
		g.dispose();
	}
}
