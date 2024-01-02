package gamestate;

import game.Game;
import gameentity.Ball;
import gameentity.Brick;
import gameentity.BrickLoader;
import gameentity.Paddle;
import level.LevelManager;
import sounds.SoundLoader;
import utils.Load;
import utils.Score;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Playing {
    private Ball ball;
    private Paddle paddle;
    private BrickLoader brickLoader;
    private Score score;
    private LevelFailed levelFailed;
    private LevelCompleted levelCompleted;
    private LevelPaused levelPaused;
    private BufferedImage pauseAndContinueButton[];
    private Point mousePoint;
    private int buttonX, buttonY;
    private Clip paddleHit;
    private SoundLoader soundLoader;
    public Playing() {
        paddle = new Paddle();
        brickLoader = new BrickLoader();
        ball = new Ball();
        score = new Score();
        levelFailed = new LevelFailed();
        levelCompleted = new LevelCompleted();
        levelPaused = new LevelPaused();
        pauseAndContinueButton = new BufferedImage[2];
        pauseAndContinueButton[1] = Load.LoadImage(Load.PauseIcon);
        pauseAndContinueButton[0] = Load.LoadImage(Load.ContinueIcon);
        buttonX = Game.Width - 40;
        buttonY = 32;
        soundLoader = new SoundLoader();
        paddleHit = soundLoader.getClip(SoundLoader.paddleHit);
        try {
            paddleHit.open(soundLoader.getAudioStream(SoundLoader.paddleHit));
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        switch (PlayState.state) {
            case Playing:
                paddle.update();
                checkCollision();
                ball.setPoition();
                ball.update();
                score.update();
                if (!Ball.getALive())
                    PlayState.state = PlayState.Failed;
                if (BrickLoader.BrickCount == 0) {
                    PlayState.state = PlayState.Completed;
                }
                break;
            case Failed:
                levelFailed.update();
                if(LevelManager.getTempLevel() > LevelManager.getLevel())
                    LevelManager.setLevel(LevelManager.getTempLevel());
                break;
            case Completed:
                levelCompleted.update();
                break;
            case Paused:
                levelPaused.update();
                break;
            default:
                break;
        }

    }

    public void render(Graphics2D g) {
        switch (PlayState.state) {
            case Playing:
                brickLoader.render(g);
                ball.render(g);
                paddle.render(g);
                score.render(g);
                g.drawImage(pauseAndContinueButton[0], buttonX, buttonY, pauseAndContinueButton[0].getTileWidth()
                        , pauseAndContinueButton[0].getHeight(), null);
                break;
            case Failed:
                levelFailed.render(g);
                break;
            case Completed:
                levelCompleted.render(g);
                break;
            case Paused:
                brickLoader.render(g);
                ball.render(g);
                paddle.render(g);
                score.render(g);
                g.drawImage(pauseAndContinueButton[1], buttonX, buttonY, pauseAndContinueButton[0].getTileWidth()
                        , pauseAndContinueButton[1].getHeight(), null);
                levelPaused.render(g);
                break;
            default:
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        mousePoint = e.getPoint();
        switch (PlayState.state) {
            case Playing: {
                ball.mouseClicked(e);
                if (getRect(pauseAndContinueButton[0], buttonX , buttonY,20).contains(mousePoint))
                    PlayState.state = PlayState.Paused;
                break;
            }
            case Failed:
                levelFailed.mouseClicked(e);
                break;
            case Completed:
                levelCompleted.mouseClicked(e);
                break;
            case Paused:
                levelPaused.mouseClicked(e);
                break;
            default:
                break;
        }
    }

    public void mouseMoved(MouseEvent e) {
        paddle.mouseMoved(e);
    }

    private Rectangle getRect(BufferedImage image, int x, int y) {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    private Rectangle getRect(BufferedImage image, int x, int y, int increaseHitBox) {
        return new Rectangle(x-increaseHitBox, y - increaseHitBox,
                image.getWidth() + (increaseHitBox*2), image.getHeight() + (increaseHitBox * 2));
    }

    private void checkCollision() {

       if( ball.checkCollision(paddle.getHitbox())){
           paddleHit.start();
           paddleHit.setMicrosecondPosition(0);
       }
        ArrayList<Brick> brickArrayList = BrickLoader.getBricks();

        for (Brick br : brickArrayList) {
            if(br.isAlive && (ball.checkCollision(br.getHitbox()))){
                // Check if the ball is collied with a brick
                    br.setHit();
                    // If the brick is fully destroyed
                    if (!br.isAlive) {
                        BrickLoader.BrickCount--;
                        BrickLoader.Score += br.getScore();
                    }
                }
            }
        }
    }