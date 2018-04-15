/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author JUDELVCUNANAN
 */
public class GamePanel extends JPanel{
    private Player player;
    private Enemy enemy;
    //images
    private BufferedImage imageBg_light;
    private BufferedImage imageBg_dark;
    private BufferedImage imageEnemy;
    private BufferedImage imageEnemyBullet;
    private BufferedImage backgroundImage;
    public boolean gameOver;
    
    public int score;
    public int highScore;
    public boolean newHighScore;
    
    private float interpolation;
    
    
    public GamePanel() {
        loadImages();
        backgroundImage = imageBg_light;
        this.setSize(960,560);
        
        player = new Player();
        enemy = new Enemy();
        
        score = 0;
        
        JFrame.getFrames()[0].addKeyListener(new TAdapter());
    }
    
    
    public void update(){
        
        player.update();
        enemy.update();
        
        //if strike!
        if (player.bullet.bulletBox.intersects(enemy.enemyBox)
                && player.bullet.isVisible()){
            
            player.bullet.setVisible(false);
            enemy.resetBox();
            
            score++;
        }
        
        //if fail! GAME OVER
        if (player.bullet.bulletBox.x > enemy.enemyBox.x + enemy.enemyBox.height
                && player.bullet.isVisible()){
            
            player.bullet.setVisible(false);
            enemy.setSpeed(7);
            
            if (score > highScore){
                highScore = score;
                newHighScore = true;
            } else {
                newHighScore = false;
            }
            
            gameOver = true;
            
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.add(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        
        g2.setRenderingHints(rh);

        
        //draws the background
        g2.drawImage(backgroundImage, 0, 0, this);
        
        player.setInterpolation(interpolation);
        player.draw(g2, this);
        
        enemy.setInterpolation(interpolation);
        enemy.draw(g2, this);
        
        
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Trench Thin", 0, 70));
        g2.drawString(String.valueOf(score), 960/2, 560/2+30);
    }
    
    //load all images
        private void loadImages() {
        imageBg_light = loadImage("GameBackground_light");
        imageBg_dark = loadImage("GameBackground_dark");
        imageEnemy = loadImage("enemy");
        imageEnemyBullet = loadImage("enemyBullet");
    }
    
    //loads images
    public BufferedImage loadImage(String fileName){
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
    
    //KEYBOARD
    class TAdapter extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (!gameOver){
            if (key == KeyEvent.VK_UP){
                System.out.println("UP");
                player.moveUp();
            }
            
            if (key == KeyEvent.VK_DOWN){
                player.moveDown();
            }
            
            if (key == KeyEvent.VK_SPACE){
                player.fire();
            }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            
            if (!gameOver){
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
                player.stop();
            }
            }
            
        }
        
        
        
    }
    
    public void resetGame(){
        score = 0;
    }
    
    
}
