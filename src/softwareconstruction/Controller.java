package softwareconstruction;

import java.awt.BorderLayout;
import java.awt.Color;
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
    //----------------------------------------------- Instance Variables ------------------------------------------
    private static boolean USERNAME_EXISTS;
    private Model model;
    private View view;
    
    //----------------------------------------------- Constructor ------------------------------------------
    
    public Controller(String title)
    {
        super(title);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.gray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(853, 600);
        USERNAME_EXISTS = true;
        
        model = new Model();
        
        view = new View(model);
        view.getStartButton().addActionListener(this);
        view.getEnterButton().addActionListener(this);
        view.getFeedButton().addActionListener(this);
        view.getRaceButton().addActionListener(this);
        view.getPetPowerButton().addActionListener(this);
        view.getEndGameButton().addActionListener(this);
        view.getInstructionsButton().addActionListener(this);
        //this.getContentPane().add(view, BorderLayout.CENTER);
        
        this.getContentPane().add(view.getStartPanel());
        
    }
    
    //------------------------------------------------- Methods --------------------------------------------------
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
        if(source == view.getStartButton())
        {
            String username = view.getUsernameBox().getText();
            if(username.length() > 10)
            {
                view.getErrorLabel().setText("Username can only be up to 10 characters! Try Again.");
            }
            else if(username.length() == 0)
            {
                view.getErrorLabel().setText("Username must have at least 1 character! Try Again.");
            }
            else
            {
                if(Model.exists(username))
                {
                    view.getErrorLabel().setText("Username Taken! Try Again.");
                }
                else
                {
                    view.getStartPanel().setVisible(false);
                    this.getContentPane().add(view.getSelectionPanel());
                }
            }
        }
        else if(source == view.getEnterButton())
        {
            String petName = view.getPetNameField().getText();
            char[] arr = petName.toCharArray();
            for(char c : arr)
            {
                if(!Character.isLetter(c))
                {
                    view.getErrorLabel2().setText("Pet name must contain characters only!");
                    break;
                }
            }
        }
        else if(source == view.getFeedButton())
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
    
    public static void main(String[] args)
    {
        JFrame frame = new Controller("Virtual Pet Game");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2, (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
    }
}
