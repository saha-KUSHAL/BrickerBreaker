package GameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import Utils.Audio;
import Utils.Load;
import Game.Game;

public class BrickLoader implements GameEntity {

	private Brick[][] bricks;
	private final int col = 16;
	private final int row = 3;
	private final int grid = 60;
	private final int offset = 60;
	private Ball ball;
	private Audio brickImpactAudio;
	public static int BrickCount = 48;
	public static int Score = 0;
	
	public BrickLoader(Ball ball) {
		this.ball = ball;
		brickImpactAudio = new Audio("res/paddleHit.wav");
		bricks = new Brick[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				bricks[i][j] = new Brick(j * grid + offset, i * grid + offset);
			}
		}
	}

	@Override
	public void update() {
		for (Brick[] row : bricks) {
			for (Brick br : row) {
				if (br.isAlive) {
					ball.checkCollision(br.getHitbox());
					if (ball.isCollided()) {
						boolean dead = br.setHit();
						brickImpactAudio.pause();
						brickImpactAudio.setTime(0);
						brickImpactAudio.play();
						if(Game.debug)
							System.out.println("Brick got hit");
						if (dead) {
							brickImpactAudio.pause(); 
							brickImpactAudio.setTime(0);
							brickImpactAudio.play();
							BrickCount--;
							Score += (int) br.getScore();
							if(Game.debug)
								System.out.println("Brick got destroyed | Score: " + Score);
						}
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		for (Brick[] row : bricks) {
			for (Brick br : row) {
				br.render(g);
			}
		}

	}

	@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
