package Game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import GameEntity.Ball;
import GameEntity.BrickLoader;
import GameEntity.Paddle;
import Utils.Score;

public class Playing {
	private Ball ball;
	private Paddle paddle;
	private BrickLoader brickLoader;
	private Score score;

	public Playing() {
		paddle = new Paddle();
		ball = new Ball(paddle);
		brickLoader = new BrickLoader(ball);
		score = new Score();
	}

	public void update() {
		if (ball.getAlive() && BrickLoader.BrickCount != 0) {
			ball.update();
			paddle.update();
			brickLoader.update();
			score.update();
		}
	}
	
	public void render(Graphics2D g) {
		ball.render(g);
		paddle.render(g);
		brickLoader.render(g);
		score.render(g);
	}
	
	public void mouseClicked(MouseEvent e) {
		paddle.mouseClicked(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		paddle.mouseMoved(e);
	}
}
