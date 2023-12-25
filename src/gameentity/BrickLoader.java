package gameentity;

import game.Game;
import level.LevelLoader;
import level.LevelManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BrickLoader implements GameEntity {

    public static int BrickCount = 0;
    public static int Score = 0;
    private static ArrayList<Brick> bricks;
    private static LevelLoader levelLoader;
    private Ball ball;

    public BrickLoader(Ball ball) {
        this.ball = ball;
        levelLoader = new LevelLoader();
        bricks = new ArrayList<>();
        generateBricks();
    }

    private static void generateBricks() {
        int level[][] = levelLoader.getBrickIndex(LevelManager.getLevel());

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                if (level[i][j] == 0)
                    bricks.add(new Brick(j * Game.TileSize, i * Game.TileSize));
            }
        }
        BrickCount = bricks.size();
    }

    public static void reset() {
        bricks.clear();
        BrickCount = 0;
        Score = 0;
        generateBricks();
    }

    @Override
    public void update() {
        if (BrickCount != 0) {
            for (Brick br : bricks) {
                if (br.isAlive) {
                    ball.checkCollision(br.getHitbox());
                    if (ball.isCollided()) {
                        boolean dead = br.setHit();
                        if (Game.debug)
                            System.out.println("Brick got hit");
                        if (dead) {
                            BrickCount--;
                            Score += (int) br.getScore();
                            if (Game.debug)
                                System.out.println("Brick got destroyed | Score: " + Score);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void render(Graphics2D g) {
        if (Ball.getALive()) {
            for (Brick br : bricks) {
                br.render(g);
            }
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
