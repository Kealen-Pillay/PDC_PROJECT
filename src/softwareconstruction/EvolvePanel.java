package softwareconstruction;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class EvolvePanel extends JPanel
{
    //--------------------------------------------------- Instance Variables -------------------------------------
    private JLabel evolveLabel;
    private JTextArea evolveDescription;
    private JButton evolveButton;
    private JButton goBackButton;
    
    //--------------------------------------------------- Constructor -------------------------------------
    public EvolvePanel()
    {
        this.setLayout(null);
        this.setBackground(new Color(3, 252, 158));
        this.setSize(853, 600);
        
        evolveLabel = new JLabel("EVOLUTION");
        Font f = new Font("Arial", Font.BOLD, 50);
        evolveLabel.setFont(f);
        evolveLabel.setSize(450, 100);
        evolveLabel.setLocation(280, 40);
        
        evolveDescription = new JTextArea("TO EVOLVE YOUR PET YOU MUST HAVE:\n\n"
                + "1) $500\n\n2) 5 RACES WON\n\n"
                + "--------------------------------\n"
                + "EVOLUTION TABLE\n"
                + "--------------------------------\n"
                + "> Water Dragon -------------> Ice Dragon\n\n"
                + "> Earth Dragon -------------> Terra Dragon\n\n"
                + "> Fire Dragon  -------------> Lava Dragon");
        Font f1 = new Font("Arial", Font.PLAIN, 15);
        evolveDescription.setBackground(new Color(3, 252, 158));
        evolveDescription.setFont(f1);
        evolveDescription.setSize(600, 250);
        evolveDescription.setLocation(30, 150);
        
        
        evolveButton = new JButton("Evolve Pet");
        evolveButton.setSize(150,50);
        evolveButton.setLocation(250, 450);
        
        goBackButton = new JButton("Go Back");
        goBackButton.setSize(150, 50);
        goBackButton.setLocation(450, 450);
        
        this.add(evolveLabel);
        this.add(evolveDescription);
        this.add(evolveButton);
        this.add(goBackButton);
        this.setVisible(false);
    }
    
    //--------------------------------------------------- Methods -------------------------------------

    /**
     * @return the evolveLabel
     */
    public JLabel getEvolveLabel() {
        return evolveLabel;
    }

    /**
     * @return the evolveDescription
     */
    public JTextArea getEvolveDescription() {
        return evolveDescription;
    }

    /**
     * @return the evolveButton
     */
    public JButton getEvolveButton() {
        return evolveButton;
    }

    /**
     * @return the goBackButton
     */
    public JButton getGoBackButton() {
        return goBackButton;
    }
    
}
