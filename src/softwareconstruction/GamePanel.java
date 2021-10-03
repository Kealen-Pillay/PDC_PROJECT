package softwareconstruction;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class GamePanel extends JPanel
{
    
    //--------------------------------------------------- Constructor --------------------------------------------------------
    public GamePanel()
    {
        this.setLayout(null);
        this.setSize(853, 600);
        this.setLocation(0, 0);
        this.setBackground(new Color(255, 134, 97));
        this.setVisible(true);
    }
    
    //----------------------------------------------------- Methods ----------------------------------------------------------------
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
