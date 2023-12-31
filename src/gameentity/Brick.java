package gameentity;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Brick extends Entity implements GameEntity {

    private Color color[], brickColor;
    private Random random;
    private float health, scoreHealth;

    public Brick(int x, int y) {
        random = new Random();
        this.x = x;
        this.y = y;
        width = 50;
        hight = 30;
        isAlive = true;
        color = new Color[5];
        initColors();
        brickColor = color[random.nextInt(5)];
        scoreHealth = health = random.nextFloat((float) 1.5);
    }

    private void initColors() {
        color[0] = new Color(157, 197, 117); // Green
        color[1] = new Color(220, 202, 110); // Yellow
        color[2] = new Color(205, 135, 180); // Pink
        color[3] = new Color(117, 180, 197); // Tortoise
        color[4] = new Color(117, 197, 172); // Cyan
    }

    public void setHit() {
        health--;
        brickColor = new Color(73, 91, 100);
        if (health <= 0) {
            isAlive = false;
        }
    }

    public double getScore() {
        int randnum;
        do {
            randnum = random.nextInt(10);
        } while (randnum == 0);
        return Math.ceil(scoreHealth) * randnum;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics2D g) {
        if (isAlive) {
            g.setColor(brickColor);
            g.fillRoundRect((int) x, (int) y, width, hight, 15, 15);
        }
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, width, hight);
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
