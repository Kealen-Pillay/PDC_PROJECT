package softwareconstruction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        view.getFoodPanel().getEnterButton().addActionListener(this);
        view.getPowerPanel().getGoBackButton().addActionListener(this);
        view.getPowerPanel().getYesButton().addActionListener(this);
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
                    if(selectedPet.equals("water"))
                    {
                        model.setPet(pets.get(0));
                        model.getPet().setPetName(petName);
                        view.setPetImage("water_dragon.png");
                    }
                    else if(selectedPet.equals("earth"))
                    {
                        model.setPet(pets.get(1));
                        model.getPet().setPetName(petName);
                        view.setPetImage("earth_dragon.png");
                    }
                    else
                    {
                        model.setPet(pets.get(2));
                        model.getPet().setPetName(petName);
                        view.setPetImage("fire_dragon.png");
                    }
                    view.getSelectionPanel().setVisible(false);
                    view.add(view.getGamePanel());
                    model.setup();
                    
                }
                
            }
        }
        else if(source == view.getFeedButton())
        {
            if(model.getOwner().getFood() != 0 && model.getPet().getHealth() != 100)
            {
                model.feedPet();
            }
            else if(model.getPet().getHealth() == 100)
            {
                JOptionPane.showMessageDialog(null, "Your pet's health and energy is full.", "Pet Health & Energy Full", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You have 0 food. You need to earn food pellets.\nThis can be done in the 'Earn Food Section'.", "No Food Remaining", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(source == view.getRaceButton())
        {
            if(model.getPet().getEnergy() >= 3)
            {
                model.racePet();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Your pet has no energy left. See one of the options below.\n1) Feed your pet to increase it's energy.\n2) Some pet's have a pet power that can increase their energy. Try your pet power!", "Pet Has No Energy To Race", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(source == view.getPetPowerButton())
        {
            if(model.getPet().getPowerCounter() == 3)
            {
                JOptionPane.showMessageDialog(null, "Your Pet has run out of power!", "No More Pet Powers", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                view.getGamePanel().setVisible(false);
                view.getPowerPanel().setVisible(true);
            }
        }
        else if(source == view.getInstructionsButton())
        {
            String instructions = model.instructions();
            JOptionPane.showMessageDialog(null, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(source == view.getFoodPanel().getEnterButton())
        {
            int userAnswer = Integer.parseInt(view.getFoodPanel().getAnswerBox().getText());
            if(userAnswer == view.getFoodPanel().getAnswer())
            {
                model.incrementFood();
                view.getFoodPanel().newQuestion();
                view.getFoodPanel().getAnswerBox().setText("");
            }
            else
            {
                view.getFoodPanel().newQuestion();
                view.getFoodPanel().getAnswerBox().setText("");
            }
        }
        else if(source == view.getPowerPanel().getGoBackButton())
        {
            view.getPowerPanel().setVisible(false);
            view.getGamePanel().setVisible(true);
        }
        else if(source == view.getPowerPanel().getYesButton())
        {
            model.usePower();
            view.getPowerPanel().setVisible(false);
            view.getGamePanel().setVisible(true);
        }
        
    }
}
