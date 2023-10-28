package GameEntity;
import Game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.time.LocalTime;

public class Paddle extends Entity implements GameEntity{
	private double paddleX, mouseX;
	public Paddle() {
		hight = 15;
		width = 100;
		dx = 10;
		dy = 0;
		paddleX = (Game.Width - width)/2;
		mouseX= paddleX;
		y = Game.Hight - 80;
		isAlive = true;
	}
	
	
	
	@Override
	public void update() {
		paddleX += ( mouseX - paddleX - width/2) * .05;
		if( paddleX >= Game.Width - width)
			paddleX = Game.Width -width;
		if(paddleX <= 0)
			paddleX = 0;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect((int)paddleX,(int)y,width,hight,15,15);
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Ball.isMouseClicked = true;
		if(Game.debug)
			System.out.println("Mouse clicked: Paddle|" + LocalTime.now());
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle((int)paddleX,(int) y,width,hight);
	}

}
