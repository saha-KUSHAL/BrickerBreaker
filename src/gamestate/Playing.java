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
    private Clip paddleHit, levelBg1, brickBreak,hardHit;
    private SoundLoader soundLoader;
    public static boolean isPlaying ;
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
        levelBg1 = soundLoader.getClip(SoundLoader.levelBg1);
        brickBreak = soundLoader.getClip(SoundLoader.brickBreak);
        hardHit = soundLoader.getClip(SoundLoader.hardHit);
        try {
            paddleHit.open(soundLoader.getAudioStream(SoundLoader.paddleHit));
            levelBg1.open(soundLoader.getAudioStream(SoundLoader.levelBg1));
            brickBreak.open(soundLoader.getAudioStream(SoundLoader.brickBreak));
            hardHit.open(soundLoader.getAudioStream(SoundLoader.hardHit));
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isPlaying = true;
    }

    public void update() {
        switch (PlayState.state) {
            case Playing:
                paddle.update();
                checkCollision();
                ball.setPoition();
                ball.update();
                score.update();
                if(isPlaying) {
                    if(!levelBg1.isOpen()){
                        try{
                            levelBg1.open(soundLoader.getAudioStream(SoundLoader.levelBg1));
                        }catch (Exception e){}
                    }
                    levelBg1.setMicrosecondPosition(0);
                    levelBg1.loop(1);
                    levelBg1.start();
                    isPlaying = false;
                }
                if (!Ball.getALive())
                    PlayState.state = PlayState.Failed;
                if (BrickLoader.BrickCount == 0) {
                    PlayState.state = PlayState.Completed;
                }
                break;
            case Failed:
                levelBg1.close();
                levelFailed.update();
                if(LevelManager.getTempLevel() > LevelManager.getLevel())
                    LevelManager.setLevel(LevelManager.getTempLevel());
                break;
            case Completed:
                levelBg1.close();
                levelCompleted.update();
                break;
            case Paused:
                levelBg1.stop();
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
           ball.increaseVelocity();
       }
        ArrayList<Brick> brickArrayList = BrickLoader.getBricks();

        for (Brick br : brickArrayList) {
            if(br.isAlive && (ball.checkCollision(br.getHitbox()))){
                // Check if the ball is collied with a brick
                    br.setHit();
                    ball.decreaseVelocity();
                    // If the brick is fully destroyed
                    if (!br.isAlive) {
                        brickBreak.start();
                        brickBreak.setMicrosecondPosition(0);
                        BrickLoader.BrickCount--;
                        BrickLoader.Score += br.getScore();
                    }
                    else
                    {
                        hardHit.start();
                        hardHit.setMicrosecondPosition(0);
                    }
                }
            }
        }
    }