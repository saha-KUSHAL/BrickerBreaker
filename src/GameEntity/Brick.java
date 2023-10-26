package GameEntity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Brick extends Entity implements GameEntity {

	private Color color[], brickColor;
	private Random random;
	public Brick(int x, int y) {
		random = new Random();
		this.x = x;
		this.y = y;
		width = 50;
		hight = 30;
		isAlive = true;
		color = new Color[4];
		initColors();
		brickColor = color[random.nextInt(4)];
	}

	private void initColors() {
		color[0]= new Color(126,191,39);
		color[1]= new Color(198,148,99);
		color[2]= new Color(254,106,0);
		color[3]= new Color(252,254,251);
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		if (isAlive) {
			g.setColor(brickColor);
			g.fillRoundRect((int) x, (int) y, width, hight,15,15);
		}
	}

	@Override
	public Rectangle getHitbox() {
		return new Rectangle((int)x,(int)y,width,hight);
	}

}
