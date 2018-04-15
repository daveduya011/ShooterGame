/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author JUDELVCUNANAN
 */
public class Bullet {
    
    private BufferedImage imageBullet;
    private int bulletSpeedX;
    private int bulletSpeedY;
    private float bulletX, bulletY;
    private float lastBulletX, lastBulletY;
    private boolean visible;
    
    private final int SPEED = 5;
    private int velocity = 1;
    
    private float interpolation;
    
    public Rectangle bulletBox;

    public Bullet() {
        imageBullet = loadImage("playerBullet");
        bulletBox = new Rectangle();
        
        this.bulletX = lastBulletX = 0;
        this.bulletY = lastBulletY = 0;
        
        bulletSpeedX = 0;
        bulletSpeedY = 0;
    }
    
    public void draw(Graphics g, GamePanel observer){
        if (visible == true){
            
            g.drawImage(imageBullet, (int)bulletX, (int)bulletY, observer);
        }
    }
    public void update(){
        bulletBox.setBounds((int)bulletX, (int)bulletY, 10, 27);
        if (visible == true){
            velocity++;
        }
        
            lastBulletX = bulletX;
            lastBulletY = bulletY;

            bulletX += bulletSpeedX * velocity;
            bulletY += bulletSpeedY;
        
        if (bulletX > 1000) {
            velocity = 1;
            visible = false;
        }
    }
    
    public void fire(float bulletX, float bulletY){
        
        if (!visible){
            visible = true;
            this.bulletX = bulletX;
            this.bulletY = bulletY;
            bulletSpeedX = SPEED;
        }
        
        
        
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setBulletX(float bulletX) {
        this.bulletX = bulletX;
    }

    public boolean isVisible() {
        return visible;
    }
    
    
    
    
}
