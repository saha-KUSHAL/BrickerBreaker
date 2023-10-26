package GameEntity;
import Game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Game.Game;
public class Paddle extends Entity implements GameEntity, MouseMotionListener, MouseListener{
	private double paddleX, mouseX;
	public Paddle() {
		hight = 15;
		width = 100;
		dx = 10;
		dy = 0;
		paddleX = (Game.Width - width)/2;
		mouseX= paddleX;
		y = Game.Hight - 80;
	}
	
	
	
	@Override
	public void update() {
		paddleX += ( mouseX - paddleX - width/2) * .05;
		if( paddleX >= Game.Width - width)
			paddleX = Game.Width -width;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect((int)paddleX,y,width,hight,15,15);
		Toolkit.getDefaultToolkit().sync();
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		Ball.isMouseClicked = true;
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
