package softwareconstruction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

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
        
        view = new View();
        view.getFeedButton().addActionListener(this);
        view.getRaceButton().addActionListener(this);
        view.getPetPowerButton().addActionListener(this);
        view.getEndGameButton().addActionListener(this);
        view.getInstructionsButton().addActionListener(this);
        //this.getContentPane().add(view, BorderLayout.CENTER);    

        startScreen();
                 
    }
    
    //------------------------------------------------- Methods --------------------------------------------------
    
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
    
    public void startScreen()
    {
        JPanel startPanel = new JPanel();
        JLabel gameLabel = new JLabel("VIRTUAL PET GAME");
        JTextField usernameBox = new JTextField("Enter Your Username Here...");
        JButton startButton = new JButton("Start");
        JLabel errorLabel = new JLabel("");
        
        startPanel.setSize(853, 600);
        startPanel.setLocation(0, 0);
        startPanel.setLayout(null);
        startPanel.setBackground(Color.gray);
        
        Font f = new Font("Arial", Font.BOLD, 60);
        gameLabel.setFont(f);
        gameLabel.setSize(600, 60);
        gameLabel.setLocation(120, 100);
        
        usernameBox.setSize(250, 30);
        usernameBox.setLocation(280, 300);
        
        startButton.setSize(80, 30);
        startButton.setLocation(550, 300);
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameBox.getText();
                if(username.length() > 10)
                {
                    errorLabel.setText("Username can only be up to 10 characters! Try Again.");
                }
                else if(username.length() == 0)
                {
                    errorLabel.setText("Username must have at least 1 character! Try Again.");
                }
                else
                {
                    
                    if(Model.exists(username))
                    {
                        errorLabel.setText("Username Taken! Try Again.");
                        USERNAME_EXISTS = true;
                    }
                    else
                    {
                        USERNAME_EXISTS = false;
                    }
                }
            }
        });
        
        errorLabel.setSize(350, 30);
        errorLabel.setLocation(285, 350);
        
        startPanel.add(usernameBox);
        startPanel.add(startButton);
        startPanel.add(errorLabel);
        startPanel.add(gameLabel);
        this.getContentPane().add(startPanel);
        
    }
    
    public void choosePetPanel()
    {
        System.out.println("Choose Pet");
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
