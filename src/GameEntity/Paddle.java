package GameEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import Game.Game;
public class Paddle extends Entity implements GameEntity {

	public Paddle() {
		hight = 15;
		width = 100;
		dx = 10;
		dy = 0;
		x = (Game.Width - width)/2;
		y = Game.Hight - 80;
	}
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(x,y,width,hight,15,15);
		Toolkit.getDefaultToolkit().sync();
	}

}
