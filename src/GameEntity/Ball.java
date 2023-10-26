package GameEntity;

import Game.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class Ball extends Entity implements GameEntity {

	public static boolean isMouseClicked = false;

	public Ball() {
		hight = 20;
		width = 20;
		dx = 0;
		dy = -1;
		x = (Game.Width - width) / 2;
		y = Game.Hight - 100;
	}

	@Override
	public void update() {
		if (isMouseClicked) {
			x += dx;
			y += dy;
			if (x <= 0 || x >= Game.Width - width)
				dx = -dx;
			if (y <= 0 || y >= Game.Hight - hight)
				dy = -dy;
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.orange);
		g.fillOval(x, y, 20, 20);
		Toolkit.getDefaultToolkit().sync(); // Flush the Linux graphics buffer
	}

}
