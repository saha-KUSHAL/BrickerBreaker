package GameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BrickLoader implements GameEntity {

	private Brick[][] bricks;
	private final int col = 16;
	private final int row = 3;
	private final int grid = 60;
	private final int offset = 60;
	private Ball ball;
	public static int BrickCount = 48;

	public BrickLoader(Ball ball) {
		this.ball = ball;
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
					ball.checkCollision((int) (ball.x + ball.dx), (int) (ball.y + ball.dy), br.getHitbox());
					if (ball.isCollided()) {
						br.setAlive(false);
						BrickCount--;
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

}
