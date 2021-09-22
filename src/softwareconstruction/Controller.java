package softwareconstruction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author kealenpillay
 */
public class Controller extends JFrame implements ActionListener
{
    private View view;
    
    public Controller(String title)
    {
        super(title);
        view = new View();
        view.getFeedButton().addActionListener(this);
        view.getRaceButton().addActionListener(this);
        view.getPetPowerButton().addActionListener(this);
        view.getEndGameButton().addActionListener(this);
        view.getInstructionsButton().addActionListener(this);
        this.getContentPane().add(view, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(853, 600);     
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new Controller("Virtual Pet Game");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
       
        if(source == view.getFeedButton())
        {
            System.out.println("Feed");
        }
        else if(source == view.getRaceButton())
        {
            System.out.println("Race");
        }
        else if(source == view.getPetPowerButton())
        {
            System.out.println("Pet Power");
        }
        else if(source == view.getInstructionsButton())
        {
            System.out.println("Instructions");
        }
    }
}
