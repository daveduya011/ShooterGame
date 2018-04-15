/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author JUDELVCUNANAN
 */
public class Player {
    Bullet bullet;
    
    private int playerLives;
    private int playerHealth;
    private int playerSpeedX;
    private int playerSpeedY;
    private float playerX, playerY;
    private float lastPlayerX, lastPlayerY;
    
    private final int SPEED = 7;
    
    private float interpolation;
    
    private BufferedImage imagePlayer;
    

    public Player() {
        loadImages();
        bullet = new Bullet();
        
        playerLives = 5;
        playerX = lastPlayerX = 80;
        playerY = lastPlayerY = 300;
        
        
        playerSpeedX = 0;
        playerSpeedY = 0;
        
    }
    
    public void moveUp(){
        playerSpeedY = -SPEED;
    }
    public void moveDown(){
        playerSpeedY = SPEED;
    }
    public void stop(){
        playerSpeedX = 0;
        playerSpeedY = 0;
    }
    public void fire(){
        bullet.fire(playerX + 40, playerY + 38);
    }
    
    
    public void draw(Graphics g, GamePanel observer){
        
        int drawX = (int)((playerX - lastPlayerX) * interpolation + lastPlayerX);
        int drawY = (int)((playerY - lastPlayerY) * interpolation + lastPlayerY);
        bullet.draw(g, observer);
        
        g.drawImage(imagePlayer, drawX, drawY, observer);
    }
    public void update(){
        lastPlayerX = playerX;
        lastPlayerY = playerY;
        
        playerX += playerSpeedX;
        playerY += playerSpeedY;
        
        bullet.update();
    }
    
    private void loadImages(){
        imagePlayer = loadImage("player");
    }
    
    //loads images
    private BufferedImage loadImage(String fileName){
        BufferedImage img;
        
        try {
            img = ImageIO.read(getClass().getResource("/images/" + fileName + ".png"));
            return img;
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    
    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }
    
    
}
