/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package softwareconstruction;
import java.awt.Color;
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
    
    public PetPanel()
    {
        this.setLayout(null);
        
        petPanelTitle = new JLabel("Pet Card");
        petPanelTitle.setSize(80, 20);
        petPanelTitle.setLocation(70, 10);
        
        healthLabel = new JLabel("HP: ");
        healthLabel.setSize(80, 20);
        healthLabel.setLocation(10, 45);
        
        energyLabel = new JLabel("Energy: ");
        energyLabel.setSize(80, 20);
        energyLabel.setLocation(10, 100);
        
        swimmingLabel = new JLabel("Swimming: ");
        swimmingLabel.setSize(80, 20);
        swimmingLabel.setLocation(10, 155);
        
        speedLabel = new JLabel("Speed: ");
        speedLabel.setSize(80, 20);
        speedLabel.setLocation(10, 210);
        
        flightLabel = new JLabel("Flight: ");
        flightLabel.setSize(80, 20);
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
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 190, 40);
    }

    /**
     * @return the petPanelTitle
     */
    public JLabel getPetPanelTitle() 
    {
        return petPanelTitle;
    }

    /**
     * @return the healthLabel
     */
    public JLabel getHealthLabel() 
    {
        return healthLabel;
    }

    /**
     * @return the energyLabel
     */
    public JLabel getEnergyLabel() 
    {
        return energyLabel;
    }

    /**
     * @return the swimmingLabel
     */
    public JLabel getSwimmingLabel() 
    {
        return swimmingLabel;
    }

    /**
     * @return the speedLabel
     */
    public JLabel getSpeedLabel() 
    {
        return speedLabel;
    }

    /**
     * @return the flightLabel
     */
    public JLabel getFlightLabel() 
    {
        return flightLabel;
    }
    
}
