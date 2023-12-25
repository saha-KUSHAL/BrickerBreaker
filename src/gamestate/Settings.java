package gamestate;

import game.Game;
import utils.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Settings extends GameStateItems {
    private BufferedImage backButton,aboutText;
    private int backButtonX,backButtonY;
    public Settings(){
        backButton = Load.LoadImage(Load.BackButton1);
        aboutText = Load.LoadImage(Load.AboutText);
        backButtonX = 20;
        backButtonY = backButton.getHeight() - 30;
    }

    public void update(){
        if (isMouseClicked) {
            if (getRect(backButton, backButtonX, backButtonY).contains(mousePoint)) {
                GameState.state = GameState.MENU;
                isMouseClicked = false;
                //reset();
            }
        }
    }

    public void render(Graphics g){
        g.drawImage(backButton,backButtonX,backButtonY,backButton.getWidth(),backButton.getHeight(),null);
        g.drawImage(aboutText,(Game.Width - aboutText.getWidth() )/ 2,
                (Game.Hight - aboutText.getHeight())/2,aboutText.getWidth(), aboutText.getHeight(), null);
    }
}
