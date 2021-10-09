
package softwareconstruction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class PetPanel extends JPanel
{
    //----------------------------------- Instance Variables ----------------------------------------
    
    private JLabel petPanelTitle;
    private JLabel healthLabel;
    private JLabel energyLabel;
    private JLabel swimmingLabel;
    private JLabel speedLabel;
    private JLabel flightLabel;
    
    //----------------------------------- Constructor ----------------------------------------
    
    /**
     * Zero input parameter constructor.
     */
    public PetPanel()
    {
        this.setLayout(null);
        
        petPanelTitle = new JLabel("Pet Card");
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        petPanelTitle.setFont(titleFont);
        petPanelTitle.setSize(180, 20);
        petPanelTitle.setLocation(10, 10);
        
        healthLabel = new JLabel("HP: ");
        healthLabel.setSize(120, 20);
        healthLabel.setLocation(10, 45);
        
        energyLabel = new JLabel("Energy: ");
        energyLabel.setSize(120, 20);
        energyLabel.setLocation(10, 100);
        
        swimmingLabel = new JLabel("Swimming: ");
        swimmingLabel.setSize(120, 20);
        swimmingLabel.setLocation(10, 155);
        
        speedLabel = new JLabel("Speed: ");
        speedLabel.setSize(120, 20);
        speedLabel.setLocation(10, 210);
        
        flightLabel = new JLabel("Flight: ");
        flightLabel.setSize(120, 20);
        flightLabel.setLocation(10, 265);
        
        this.add(petPanelTitle);
        this.add(healthLabel);
        this.add(energyLabel);
        this.add(swimmingLabel);
        this.add(speedLabel);
        this.add(flightLabel);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setSize(190, 300);
    }
    
    //----------------------------------- Methods ----------------------------------------
    
    /**
     * Paints the panel.
     * @param g represents the graphics object.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 190, 40);
    }

    /**
     * @return the petPanelTitle.
     */
    public JLabel getPetPanelTitle() 
    {
        return petPanelTitle;
    }

    /**
     * @return the healthLabel.
     */
    public JLabel getHealthLabel() 
    {
        return healthLabel;
    }

    /**
     * @return the energyLabel.
     */
    public JLabel getEnergyLabel() 
    {
        return energyLabel;
    }

    /**
     * @return the swimmingLabel.
     */
    public JLabel getSwimmingLabel() 
    {
        return swimmingLabel;
    }

    /**
     * @return the speedLabel.
     */
    public JLabel getSpeedLabel() 
    {
        return speedLabel;
    }

    /**
     * @return the flightLabel.
     */
    public JLabel getFlightLabel() 
    {
        return flightLabel;
    }
    
}
