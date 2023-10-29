package GameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Utils.Audio;
import Utils.Load;
import Game.Game;
import Level.LevelLoader;

public class BrickLoader implements GameEntity {

	private ArrayList<Brick> bricks;
	private LevelLoader levelLoader;
	private Ball ball;
	private Audio brickImpactAudio;
	public static int BrickCount = 48;
	public static int Score = 0;
	
	public BrickLoader(Ball ball) {
		this.ball = ball;
		levelLoader = new LevelLoader();
		bricks = new ArrayList<>();
		generateBricks();
		brickImpactAudio = new Audio("res/paddleHit.wav");
//		generateBricks(Score, BrickCount, row, col)
	}

	@Override
	public void update() {
			for (Brick br : bricks) {
				if (br.isAlive) {
					ball.checkCollision(br.getHitbox());
					if (ball.isCollided()) {
						boolean dead = br.setHit();
//						brickImpactAudio.pause(); 
//						brickImpactAudio.setTime(0);
//						brickImpactAudio.play();
						if(Game.debug)
							System.out.println("Brick got hit");
						if (dead) {
//							brickImpactAudio.pause(); 
//							brickImpactAudio.setTime(0);
//							brickImpactAudio.play();
							BrickCount--;
							Score += (int) br.getScore();
							if(Game.debug)
								System.out.println("Brick got destroyed | Score: " + Score);
						}
					}
				}
			}
	}

	private void generateBricks() {
		int level[][] = levelLoader.getBrickIndex(1);
		
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if(level[i][j] == 0)
					bricks.add(new Brick(j * Game.TileSize, i * Game.TileSize));
			} 
		}
	}
	@Override
	public void render(Graphics2D g) {
		for (Brick br: bricks) {
				br.render(g);
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
