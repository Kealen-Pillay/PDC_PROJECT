package softwareconstruction;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class GUIDesign extends JFrame
{
    //---------------------------------------------- Instance Variables -------------------------------------
    private JButton feedButton;
    private JButton raceButton;
    private JButton powerButton;
    private JButton endButton;
    private JButton instructionsButton;
    private JPanel petPanel;
    private JPanel playerPanel;
    private JPanel earnFoodPanel;
    private JPanel evolutionPanel;
    
    //---------------------------------------------- Constructor -------------------------------------
    
    public GUIDesign(String title)
    {
        super(title);
        
        
        feedButton = new JButton("Feed Pet");
        feedButton.setSize(150, 20);
        feedButton.setLocation(50, 450);
        add(feedButton);
        
        raceButton = new JButton("Race Pet");
        raceButton.setSize(150, 20);
        raceButton.setLocation(300, 450);
        add(raceButton);
        
        powerButton = new JButton("Pet Power");
        powerButton.setSize(150, 20);
        powerButton.setLocation(50, 500);
        add(powerButton);
        
        endButton = new JButton("End Game");
        endButton.setSize(150, 20);
        endButton.setLocation(300, 500);
        add(endButton);
        
        petPanel = new JPanel();
        petPanel.setSize(150, 150);
        petPanel.setLocation(50, 30);
        petPanel.setBackground(Color.WHITE);
        add(petPanel);
        
        this.setSize(600, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    //---------------------------------------------- Main -------------------------------------
    
    public static void main(String[] args) 
    {
        JFrame frame = new GUIDesign("Pet Game");
        frame.setVisible(true);
        
    }
}
