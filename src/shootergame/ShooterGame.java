/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootergame;


/**
 *
 * @author JUDELVCUNANAN
 */
public class ShooterGame extends GameThread{

    MainMenu mainMenu;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ShooterGame();
    }

    public ShooterGame() {
        mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        
        setRunning(true);
        runGameLoop();
    }
    
     
    @Override
    public void updateGame() {
        super.updateGame();
        if (mainMenu.gamePanel.gameOver == false){
            mainMenu.gamePanel.update();
        }
        else {
            mainMenu.gamePanel.setVisible(false);
        }
    }

    @Override
    public void drawGame(float interpolation) {
        super.drawGame(interpolation);
        if (mainMenu.gamePanel.gameOver == false){
            mainMenu.gamePanel.setInterpolation(interpolation);
            mainMenu.gamePanel.repaint();
        }
    }
    
    
    
    
    
    
    
}
