package softwareconstruction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class PlayerPanel extends JPanel
{
    //----------------------------------- Instance Variables ----------------------------------------
    private JLabel playerPanelTitle;
    private JLabel moneyLabel;
    private JLabel foodLabel;
    private JLabel racesLabel;
    private JTextArea eventLog;
    private JScrollPane scrollpane;
    
    //----------------------------------- Constructor ----------------------------------------
    
    /**
     * Zero input parameter constructor.
     */
    public PlayerPanel()
    {
        this.setLayout(null);
        
        playerPanelTitle = new JLabel("Player Card");
        Font titleFont = new Font("Arial", Font.BOLD, 15);
        playerPanelTitle.setFont(titleFont);
        playerPanelTitle.setSize(180, 20);
        playerPanelTitle.setLocation(10, 10);
        
        moneyLabel = new JLabel("Money: ");
        moneyLabel.setSize(120, 20);
        moneyLabel.setLocation(10, 45);
        
        foodLabel = new JLabel("Food: ");
        foodLabel.setSize(120, 20);
        foodLabel.setLocation(10, 100);
        
        racesLabel = new JLabel("Races Won:");
        racesLabel.setSize(120, 20);
        racesLabel.setLocation(10, 155);
        
        eventLog = new JTextArea();
        eventLog.setEditable(false);
        eventLog.setSize(170, 100);
        
        scrollpane = new JScrollPane(getEventLog());
        scrollpane.setSize(170, 100);
        scrollpane.setLocation(10, 185);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        

        this.add(playerPanelTitle);
        this.add(moneyLabel);
        this.add(foodLabel);
        this.add(racesLabel);
        this.add(scrollpane);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setSize(190, 300);
    }
    
    
    //----------------------------------- Methods ----------------------------------------
    
    /**
     * Paints the panel
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
     * @return the playerPanelTitle.
     */
    public JLabel getPlayerPanelTitle() 
    {
        return playerPanelTitle;
    }

    /**
     * @return the moneyLabel.
     */
    public JLabel getMoneyLabel() 
    {
        return moneyLabel;
    }

    /**
     * @return the foodLabel.
     */
    public JLabel getFoodLabel() 
    {
        return foodLabel;
    }

    /**
     * @return the racesLabel.
     */
    public JLabel getRacesLabel() 
    {
        return racesLabel;
    }

    /**
     * @return the eventLog.
     */
    public JTextArea getEventLog() 
    {
        return eventLog;
    }

    /**
     * @return the scroll pane.
     */
    public JScrollPane getScrollpane() 
    {
        return scrollpane;
    }
}
