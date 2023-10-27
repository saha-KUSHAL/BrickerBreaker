package GameEntity;

import Game.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ball extends Entity implements GameEntity {

	public static boolean isMouseClicked = false;
	private Paddle paddle;

	private boolean topLeft, topRight, bottomLeft, bottomRight;

	public Ball(Paddle paddle) {
		this.paddle = paddle;
		hight = 20;
		width = 20;
		dx = 3.5;
		dy = -1.2;
		x = (Game.Width - width) / 2;
		y = Game.Hight - 100;
		isAlive = true;
	}

	@Override
	public Rectangle getHitbox() {

		return new Rectangle((int) x, (int) y, width, hight);
	}
	public boolean getAlive() {
		return isAlive;
	}

	protected void checkCollision(Rectangle r) {
		// double tempX = x, tempY = y;
		checkCorners((int) (x + dx), (int) y, r);

		// If the ball is towards left
		if (dx < 0)
			if (bottomLeft || topLeft)
				dx = -dx;

		// If the ball is towards right
		if (dx > 0)
			if (topRight || bottomRight)
				dx = -dx;

		checkCorners((int) x, (int) (y + dy), r);

		if (dy < 0)
			if (topRight || topLeft)
				dy = -dy;

		if (dy > 0)
			if (bottomLeft || bottomRight)
				dy = -dy;

	}

	private void checkCorners(int x, int y, Rectangle r) {
		topLeft = topRight = bottomLeft = bottomRight = false;
		topLeft = r.contains(new Point(x, y));
		bottomLeft = r.contains(new Point(x, y + hight));
		topRight = r.contains(new Point(x + width, y));
		bottomRight = r.contains(new Point(x + width, y + hight));
	}

	protected boolean isCollided() {
		return (bottomLeft || bottomRight || topLeft || topRight);
	}

	@Override
	public void update() {
		if (isMouseClicked && isAlive) {
			x += dx;
			y += dy;
			if (x <= 0 || x >= Game.Width - width)
				dx = -dx;
			if (y <= 0)
				dy = -dy;
			if (y >= Game.Hight) {
				isAlive = false;
			}
		}
		if (isAlive)
			checkCollision(paddle.getHitbox());;
	}

	@Override
	public void render(Graphics2D g) {
		if (isAlive) {
			g.setColor(Color.orange);
			g.fillOval((int) x, (int) y, 20, 20);
			Toolkit.getDefaultToolkit().sync(); // Flush the Linux graphics buffer
		}

	}
}
