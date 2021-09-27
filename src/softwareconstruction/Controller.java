package softwareconstruction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author kealenpillay
 */
public class Controller implements ActionListener
{
    //----------------------------------------------- Instance Variables ------------------------------------------
    private Model model;
    private View view;
    
    //----------------------------------------------- Constructor ------------------------------------------
    
    public Controller(Model m, View v)
    {
        model = m;
        view = v;
        view.getStartButton().addActionListener(this);
        view.getContinueButton().addActionListener(this);
        view.getEnterButton().addActionListener(this);
  
        view.getFeedButton().addActionListener(this);
        view.getRaceButton().addActionListener(this);
        view.getPetPowerButton().addActionListener(this);
        view.getEndGameButton().addActionListener(this);
        view.getInstructionsButton().addActionListener(this);
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
                if(model.exists(username))
                {
                    view.getErrorLabel().setText("Username Taken! Try Again.");
                }
                else
                {
                    model.setOwner(new Owner(username, 0, 10, 0, false));
                    view.getStartPanel().setVisible(false);
                    view.add(view.getInstructionPanel());
                }
            }
        }
        else if(source == view.getContinueButton())
        {
            view.getInstructionPanel().setVisible(false);
            view.add(view.getSelectionPanel());
        }
        else if(source == view.getEnterButton())
        {
            String petName = view.getPetNameField().getText();
            
            if(petName.length() > 10)
            {
                view.getErrorLabel2().setText("Pet name can only be up to 10 characters long! Try Again.");
            }
            else if(petName.length() == 0)
            {
                view.getErrorLabel2().setText("Pet name must have at least 1 character! Try Again.");
            }
            else
            {
                boolean acceptable = false;
                for(char c : petName.toCharArray())
                {
                    if(Character.isLetter(c) || c == ' ')
                    {
                        acceptable = true;
                    }
                    else
                    {
                        view.getErrorLabel2().setText("Pet name must contain characters only! Try Again.");
                        acceptable = false;
                        break;
                    }
                }
                if(acceptable)
                {
                    ArrayList<Pet> pets = model.petList();
                    String selectedPet = view.getOptionGroup().getSelection().getActionCommand();
                    if(selectedPet.equals("earth"))
                    {
                        model.setPet(pets.get(0));
                        model.getPet().setPetName(petName);
                    }
                    else if(selectedPet.equals("water"))
                    {
                        model.setPet(pets.get(1));
                        model.getPet().setPetName(petName);
                    }
                    else
                    {
                        model.setPet(pets.get(2));
                        model.getPet().setPetName(petName);
                    }
                    view.getSelectionPanel().setVisible(false);                   
                    view.add(view.getGamePanel());
                    view.update();
                }
                
            }
        }
        else if(source == view.getFeedButton())
        {
            model.getOwner().feed(model.getPet());
            view.update();
        }
        else if(source == view.getRaceButton())
        {
            model.getPet().race(model.getOwner());
            view.update();
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
