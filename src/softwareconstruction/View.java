package softwareconstruction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class View extends JPanel
{
    //----------------------------------- Instance Variables ----------------------------------------
    private Image petImage;
    private Image mod;
    private JLabel petIconLabel;

    private JButton feedButton;
    private JButton raceButton;
    private JButton petPowerButton;
    private JButton endGameButton;
    private JButton instructionsButton;

    private PetPanel petPanel;
    private PlayerPanel playerPanel;
    private FoodPanel foodPanel;
    //------------------------------------ Constructor ------------------------------------------------
    
    public View()
    {
        this.setLayout(null);     
        
        petPanel = new PetPanel();       
        petPanel.setLocation(30, 40);
        
        playerPanel = new PlayerPanel();
        playerPanel.setLocation(630, 40);
        
        foodPanel = new FoodPanel();
        foodPanel.setLocation(630, 380);
        
        petImage = new ImageIcon("ice_dragon.png").getImage();
        mod = petImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon petIcon = new ImageIcon(mod);
        petIconLabel = new JLabel();
        petIconLabel.setIcon(petIcon);
        petIconLabel.setSize(300, 300);
        petIconLabel.setLocation(275, 40);
              
        feedButton = new JButton("Feed Pet");
        feedButton.setSize(260, 60);
        feedButton.setLocation(40, 390);
        
        raceButton = new JButton("Race Pet");
        raceButton.setSize(260, 60);
        raceButton.setLocation(305, 390);
        
        petPowerButton = new JButton("Pet Power");
        petPowerButton.setSize(260, 60);
        petPowerButton.setLocation(40, 455);
        
        endGameButton = new JButton("End Game");
        endGameButton.setSize(260, 60);
        endGameButton.setLocation(305, 455);
        
        instructionsButton = new JButton("Instructions");
        instructionsButton.setSize(535, 30);
        instructionsButton.setLocation(35, 522);
        
        this.add(petIconLabel);   
        this.add(petPanel);
        this.add(playerPanel);
        this.add(foodPanel);
        this.add(feedButton);
        this.add(raceButton);
        this.add(petPowerButton);
        this.add(endGameButton);
        this.add(instructionsButton);
        this.setBackground(Color.GRAY);
        this.setVisible(true);
    }
    
    //----------------------------------- Methods -----------------------------------------------
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(9));
        g2.drawRoundRect(273, 37, 305, 305, 10, 10);
        g2.drawRoundRect(28, 37, 195, 305, 10, 10);
        g2.drawRoundRect(628, 38, 195, 305, 10, 10);
        g2.drawRoundRect(30, 60, 650, 200, 10, 10);
        g2.drawRoundRect(30, 380, 548, 180, 10, 10);
        g2.drawRoundRect(628, 378, 195, 185, 10, 10);
    }
}
