package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.lang.System.Logger.Level;

import GameEntity.Ball;
import GameEntity.BrickLoader;
import GameEntity.Paddle;
import Level.LevelManager;
import Utils.Score;

public class Playing {
	private Ball ball;
	private Paddle paddle;
	private BrickLoader brickLoader;
	private Score score;
	private LevelFailed levelFailed;
	private LevelCompleted levelCompleted;
	public Playing() {
		paddle = new Paddle();
		ball = new Ball(paddle);
		brickLoader = new BrickLoader(ball);
		score = new Score();
		levelFailed = new LevelFailed();
		levelCompleted = new LevelCompleted();
	}

	public void update() {
		switch (PlayState.state) {
		case Playing:
			brickLoader.update();
			ball.update();
			paddle.update();
			score.update();
			if(!Ball.getALive())
				PlayState.state = PlayState.Failed;
			if(brickLoader.BrickCount == 0) {
				PlayState.state= PlayState.Completed;
			}
			break;
		case Failed:
			levelFailed.update();
			break;
		case Retry:
		case Completed:
			levelCompleted.update();
			break;
		default:
			break;
		}

	}

	public void render(Graphics2D g) {
		switch (PlayState.state) {
		case Playing:
			brickLoader.render(g);
			ball.render(g);
			paddle.render(g);
			score.render(g);
			break;
		case Failed:
			levelFailed.render(g);
			break;
		case Retry:
		case Completed:
			levelCompleted.render(g);
			break;
		default:
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
		switch (PlayState.state) {
		case Playing:
			ball.mouseClicked(e);
			break;
		case Failed:
			levelFailed.mouseClicked(e);
			break;
		case Retry:
			break;
		case Completed:
			levelCompleted.mouseClicked(e);
		default:
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		paddle.mouseMoved(e);
	}
}
