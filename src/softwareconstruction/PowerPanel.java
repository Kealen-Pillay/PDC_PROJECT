package softwareconstruction;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class PowerPanel extends JPanel
{
    //--------------------------------------------------- Instance Variables -------------------------------------
    private JLabel powerLabel;
    private JTextArea powerDescription;
    private JButton yesButton;
    private JButton goBackButton;
    
    //--------------------------------------------------- Instance Variables -------------------------------------
    public PowerPanel(Pet pet)
    {
        this.setLayout(null);
        this.setBackground(new Color(148, 129, 240));
        this.setSize(853, 600);
        
        powerLabel = new JLabel("PET POWER");
        Font f = new Font("Arial", Font.BOLD, 50);
        powerLabel.setFont(f);
        powerLabel.setSize(450, 100);
        powerLabel.setLocation(280, 40);
        
        powerDescription = new JTextArea();
        Font f1 = new Font("Arial", Font.PLAIN, 18);
        powerDescription.setFont(f1);
        powerDescription.setBackground(new Color(148, 129, 240));
        powerDescription.setSize(800, 200);
        powerDescription.setLocation(20, 200);
        powerDescription.setEditable(false);
        
        yesButton = new JButton("Use Pet Power");
        yesButton.setSize(150,50);
        yesButton.setLocation(250, 450);
        
        goBackButton = new JButton("Go Back");
        goBackButton.setSize(150, 50);
        goBackButton.setLocation(450, 450);
        
        this.add(powerLabel);
        this.add(powerDescription);
        this.add(yesButton);
        this.add(goBackButton);
        this.setVisible(false);
    }
    
    //--------------------------------------------------- Methods -------------------------------------
    
    public void setPowerDescription(Pet pet)
    {
        getPowerDescription().setText(pet.power());
    }

    /**
     * @return the powerLabel
     */
    public JLabel getPowerLabel() 
    {
        return powerLabel;
    }

    /**
     * @return the powerDescription
     */
    public JTextArea getPowerDescription() 
    {
        return powerDescription;
    }

    /**
     * @return the yesButton
     */
    public JButton getYesButton() 
    {
        return yesButton;
    }

    /**
     * @return the goBackButton
     */
    public JButton getGoBackButton() 
    {
        return goBackButton;
    }
}
