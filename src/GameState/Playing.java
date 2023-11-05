package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameEntity.Ball;
import GameEntity.BrickLoader;
import GameEntity.Paddle;
import Utils.Load;
import Utils.Score;
import Game.Game;

public class Playing {
	private Ball ball;
	private Paddle paddle;
	private BrickLoader brickLoader;
	private Score score;
	private LevelFailed levelFailed;
	private LevelCompleted levelCompleted;
	private LevelPaused levelPaused;
	private BufferedImage pauseAndContinueButton[];
	private Point mousePoint;
	private int buttonX,buttonY;

	public Playing() {
		paddle = new Paddle();
		ball = new Ball(paddle);
		brickLoader = new BrickLoader(ball);
		score = new Score();
		levelFailed = new LevelFailed();
		levelCompleted = new LevelCompleted();
		levelPaused = new LevelPaused();
		pauseAndContinueButton = new BufferedImage[2];
		pauseAndContinueButton[1] = Load.LoadImage(Load.PauseIcon);
		pauseAndContinueButton[0] = Load.LoadImage(Load.ContinueIcon);
		buttonX =  Game.Width - 40;
		buttonY = 32;
	}

	public void update() {
		switch (PlayState.state) {
		case Playing:
			brickLoader.update();
			ball.update();
			paddle.update();
			score.update();
			if (!Ball.getALive())
				PlayState.state = PlayState.Failed;
			if (BrickLoader.BrickCount == 0) {
				PlayState.state = PlayState.Completed;
			}
			break;
		case Failed:
			levelFailed.update();
			break;
		case Completed:
			levelCompleted.update();
			break;
		case Paused:
			levelPaused.update();
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
			g.drawImage(pauseAndContinueButton[0],buttonX,buttonY,pauseAndContinueButton[0].getTileWidth()
					,pauseAndContinueButton[0].getHeight(), null);
			break;
		case Failed:
			levelFailed.render(g);
			break;
		case Completed:
			levelCompleted.render(g);
			break;
		case Paused:
			brickLoader.render(g);
			ball.render(g);
			paddle.render(g);
			score.render(g);
			g.drawImage(pauseAndContinueButton[1],buttonX,buttonY,pauseAndContinueButton[0].getTileWidth()
					,pauseAndContinueButton[1].getHeight(), null);
			levelPaused.render(g);
			break;
		default:
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
		mousePoint = e.getPoint();
		if (getRect(pauseAndContinueButton[0],buttonX,buttonY).contains(mousePoint))
			PlayState.state = PlayState.Paused;
		switch (PlayState.state) {
		case Playing: {
			ball.mouseClicked(e);
			break;
		}
		case Failed:
			levelFailed.mouseClicked(e);
			break;
		case Completed:
			levelCompleted.mouseClicked(e);
			break;
		case Paused:
			levelPaused.mouseClicked(e);
			break;
		default:
			break;
		}
	}

	public void mouseMoved(MouseEvent e) {
		paddle.mouseMoved(e);
	}

	private Rectangle getRect(BufferedImage image, int x, int y) {
		return new Rectangle(x, y, image.getWidth(), image.getHeight());
	}
}
