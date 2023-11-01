package GameEntity;

import Game.Game;
import GameState.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

public class Ball extends Entity implements GameEntity {

	public static boolean isMouseClicked = false;
	private Paddle paddle;
	private static int hight,width;
	private static double dx,dy,x,y;
	private static boolean isAlive;
	private boolean topLeft, topRight, bottomLeft, bottomRight, isCollided;

	public Ball(Paddle paddle) {
		this.paddle = paddle;
		hight = 20;
		width = 20;
		dx = 2 * Game.scale;
		dy = -1 * Game.scale;
		x = (Game.Width - width) / 2;
		y = Game.Hight - 100;
		isAlive = true;
	}

	@Override
	public Rectangle getHitbox() {

		return new Rectangle((int) x, (int) y, width, hight);
	}

	protected  void checkCollision(Rectangle r) {
		int tempX = (int) (x + dx), tempY =(int)(y + dy);
		boolean xColid = false, yColid = false;
		checkCorners(tempX,(int) y,r);
		if(topLeft || bottomRight || topRight || bottomRight) {
			dx = - dx;
			xColid = true;
		}
		checkCorners((int)x, tempY, r);
		if(topLeft || bottomRight || topRight || bottomRight) {
			dy = - dy;
			yColid = true;
		}
		isCollided = xColid || yColid;
	}

	private void checkCorners(int x, int y, Rectangle r) {
		topLeft = topRight = bottomLeft = bottomRight = isCollided = false;
		topLeft = r.contains(new Point(x, y));
		bottomLeft = r.contains(new Point(x, y + hight));
		topRight = r.contains(new Point(x + width, y));
		bottomRight = r.contains(new Point(x + width, y + hight));
	}

	protected boolean isCollided() {
		return isCollided;
	}

	@Override
	public void update() {
		if (isMouseClicked && isAlive) {
			x += dx;
			y += dy;
			if (x < 0 || x >= Game.Width - width)
				dx = -dx;
			if (y <= 0)
				dy = -dy;
			if (y > Game.Hight)
				isAlive = false;
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
	
	public static void reset() {
		dx = 2 * Game.scale;
		dy = -1 * Game.scale;
		x = (Game.Width - width) / 2;
		y = Game.Hight - 100;
		isAlive = true;
		isMouseClicked = false;
	}
	
	public static boolean getALive() {
		return isAlive;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		isMouseClicked = true;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
