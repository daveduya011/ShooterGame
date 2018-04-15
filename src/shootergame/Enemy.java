/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;

import java.awt.Graphics;
import java.awt.Rectangle;
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
public class Enemy {
    
    private int enemyLives;
    private int enemyHealth;
    private int enemySpeedX;
    private int enemySpeedY;
    
    private float enemyX, enemyY;
    private float lastEnemyX, lastEnemyY;
    public Rectangle enemyBox;
    
    private int speed = 7;
    
    private float interpolation;
    
    private BufferedImage imageEnemy;
    

    public Enemy() {
        loadImages();
        enemyBox = new Rectangle();
        
        enemyLives = 5;
        enemyX = lastEnemyX = 770;
        enemyY = lastEnemyY = 300;
        
        
        enemySpeedX = 0;
        enemySpeedY = speed;
        
    }
    
//    public void fire(){
//        bullet.fire(enemyX + 40, enemyY + 53);
//    }
    
    
    public void draw(Graphics g, GamePanel observer){
        int drawX = (int)((enemyX - lastEnemyX) * interpolation + lastEnemyX);
        int drawY = (int)((enemyY - lastEnemyY) * interpolation + lastEnemyY);
        
        g.drawImage(imageEnemy, drawX, drawY, observer);
    }
    public void update(){
        enemyBox.setBounds((int)enemyX, (int)enemyY, 119, 102);
        
        if (enemyY < 10){
            enemySpeedY = speed;
        }
        if (enemyY > 430){
            enemySpeedY = -speed;
        }
        
        
        lastEnemyX = enemyX;
        lastEnemyY = enemyY;
        
        enemyX += enemySpeedX;
        enemyY += enemySpeedY;
        
    }
    
    private void loadImages(){
        imageEnemy = loadImage("enemy");
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
    
    public void resetBox(){
        
        speed++;
        double rand = (Math.random() * (430 - 1));
        int r = (int) Math.round(rand);
        
        enemyY = r;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
