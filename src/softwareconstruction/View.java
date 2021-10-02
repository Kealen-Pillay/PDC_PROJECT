package softwareconstruction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author kealenpillay
 */
public class View extends JFrame implements Observer
{
    //----------------------------------- Instance Variables ----------------------------------------
    private Model model;
    
    private GamePanel gamePanel;
    
    private Image petImage;
    private Image mod;
    private JLabel petIconLabel;
    
    private JButton feedButton;
    private JButton raceButton;
    private JButton petPowerButton;
    private JButton endGameButton;
    private JButton instructionsButton;
    
    private PetPanel petPanel;
    private PlayerPanel playerPanel;
    private FoodPanel foodPanel;
    
    private JPanel startPanel;
    private JLabel gameLabel;
    private JTextField usernameBox;
    private JButton startButton;
    private JLabel errorLabel;
    
    private JPanel instructionPanel;
    private JLabel instructionTitleLabel;
    private JTextArea instructionText;
    private JButton continueButton;
    private JButton evolveButton;
    
    private JPanel selectionPanel;
    private JLabel selectionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private ButtonGroup optionGroup;
    private JTextField petNameField;
    private JLabel errorLabel2;
    private JButton enterButton;
    
    private PowerPanel powerPanel;
    private EvolvePanel evolvePanel;
    
    //------------------------------------ Constructor ------------------------------------------------
    
    public View(String title, Model m)
    {
        super(title);
        this.getContentPane().setLayout(new BorderLayout());
        model = m;
        
        //----- Start Screen -----
        startPanel = new JPanel();
        gameLabel = new JLabel("VIRTUAL PET GAME");
        usernameBox = new JTextField("Enter Your Username Here...");
        startButton = new JButton("Start");
        errorLabel = new JLabel("");
        startPanel.setSize(853, 600);
        startPanel.setLocation(0, 0);
        startPanel.setLayout(null);
        startPanel.setBackground(new Color(255, 134, 97));
        
        Font f = new Font("Arial", Font.BOLD, 60);
        gameLabel.setFont(f);
        gameLabel.setSize(600, 60);
        gameLabel.setLocation(120, 100);
        
        usernameBox.setSize(250, 30);
        usernameBox.setLocation(280, 300);
        
        startButton.setSize(80, 30);
        startButton.setLocation(550, 300);
        
        errorLabel.setSize(350, 30);
        errorLabel.setLocation(285, 350);
        
        startPanel.add(usernameBox);
        startPanel.add(startButton);
        startPanel.add(errorLabel);
        startPanel.add(gameLabel);
        
        //----- Instruction Panel -----
        instructionPanel = new JPanel();
        instructionPanel.setLayout(null);
        instructionPanel.setBackground(new Color(255, 134, 97));
        
        instructionTitleLabel = new JLabel("INSTRUCTIONS");
        Font instructionTitleFont = new Font("Arial", Font.BOLD, 40);
        instructionTitleLabel.setFont(instructionTitleFont);
        instructionTitleLabel.setSize(600, 60);
        instructionTitleLabel.setLocation(275, 5);
        
        instructionText = new JTextArea(model.instructions());
        instructionText.setBackground(new Color(255, 134, 97));
        instructionText.setEditable(false);
        instructionText.setSize(800, 480);
        instructionText.setLocation(40, 65);
        
        continueButton = new JButton("Continue");
        continueButton.setSize(100, 30);
        continueButton.setLocation(375, 530);
        
        instructionPanel.add(instructionTitleLabel);
        instructionPanel.add(continueButton);
        instructionPanel.add(instructionText);
        
        
        //----- Pet Selection Panel -----
        selectionPanel = new JPanel();
        selectionPanel.setLayout(null);
        selectionPanel.setBackground(new Color(255, 134, 97));
        selectionPanel.setSize(853, 600);
        selectionPanel.setLocation(0, 0);
        
        selectionLabel = new JLabel("Choose A Pet");
        Font f1 = new Font("Arial", Font.BOLD, 60);
        selectionLabel.setFont(f1);
        selectionLabel.setSize(600, 60);
        selectionLabel.setLocation(230, 50);
        
        ArrayList<Pet> pets = model.petList();
        
        option1 = new JRadioButton();
        option1.setText(pets.get(0).description());
        option1.setSize(600, 50);
        option1.setActionCommand("water");
        option1.setLocation(120, 150);
        
        option2 = new JRadioButton();
        option2.setText(pets.get(1).description());
        option2.setSize(700, 50);
        option2.setActionCommand("earth");
        option2.setLocation(120, 250);
        
        option3 = new JRadioButton();
        option3.setText(pets.get(2).description());
        option3.setSize(700, 50);
        option3.setActionCommand("fire");
        option3.setLocation(120, 350);
        
        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        
        petNameField = new JTextField("Enter Pet Name");
        petNameField.setSize(250, 30);
        petNameField.setLocation(250, 450);
        
        errorLabel2 = new JLabel("");
        errorLabel2.setSize(380, 30);
        errorLabel2.setLocation(255, 500);
        
        enterButton = new JButton("Confirm");
        enterButton.setSize(100, 30);
        enterButton.setLocation(510, 450);
        
        selectionPanel.add(selectionLabel);
        selectionPanel.add(option1);
        selectionPanel.add(option2);
        selectionPanel.add(option3);
        selectionPanel.add(petNameField);
        selectionPanel.add(enterButton);
        selectionPanel.add(errorLabel2);
        
        //----- Pet Panel ------
        petPanel = new PetPanel();
        petPanel.setLocation(30, 40);
        
        //----- Player Panel -----
        playerPanel = new PlayerPanel();
        playerPanel.setLocation(630, 40);
        
        
        //----- Food Panel -----
        foodPanel = new FoodPanel(model.earnFood());
        foodPanel.setLocation(630, 380);
        
        feedButton = new JButton("Feed Pet");
        feedButton.setSize(260, 40);
        feedButton.setLocation(40, 395);
        
        raceButton = new JButton("Race Pet");
        raceButton.setSize(260, 40);
        raceButton.setLocation(305, 395);
        
        petPowerButton = new JButton("Pet Power");
        petPowerButton.setSize(260, 40);
        petPowerButton.setLocation(40, 450);
        
        evolveButton = new JButton("Evolve Pet");
        evolveButton.setSize(260, 40);
        evolveButton.setLocation(305, 450);
        
        instructionsButton = new JButton("Instructions");
        instructionsButton.setSize(260, 40);
        instructionsButton.setLocation(40, 505);
        
        endGameButton = new JButton("End Game");
        endGameButton.setSize(260, 40);
        endGameButton.setLocation(305, 505);
        
        //----- Game Panel -----
        gamePanel = new GamePanel();
        
        gamePanel.add(petPanel);
        gamePanel.add(playerPanel);
        gamePanel.add(foodPanel);
        gamePanel.add(feedButton);
        gamePanel.add(raceButton);
        gamePanel.add(evolveButton);
        gamePanel.add(petPowerButton);
        gamePanel.add(endGameButton);
        gamePanel.add(instructionsButton);
        
        //----- Pet Power Panel -----
        powerPanel = new PowerPanel(model.getPet());
        
        //----- Evolve Panel -----
        evolvePanel = new EvolvePanel();
        
        this.getContentPane().add(this.getStartPanel(), BorderLayout.CENTER);
        this.add(powerPanel);
        this.add(evolvePanel);
        this.getContentPane().setBackground(Color.gray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(853, 600);
        this.setBackground(new Color(255, 134, 97));
        this.setVisible(true);
    }
    
    //----------------------------------- Methods -----------------------------------------------
    
    @Override
    public void update(Observable o, Object arg)
    {
        if((Integer) arg == 1)
        {
            petPanel.getPetPanelTitle().setText(model.getPet().getPetName() + " -  Stats");
            petPanel.getHealthLabel().setText("Health: " + String.valueOf(model.getPet().getHealth()) + " / 100");
            petPanel.getEnergyLabel().setText("Energy: " + String.valueOf(model.getPet().getEnergy()) + " / 10");
            petPanel.getSwimmingLabel().setText("Swimming: " + String.valueOf(model.getPet().getSwimming()) + " / 10");
            petPanel.getSpeedLabel().setText("Speed: " + String.valueOf(model.getPet().getSpeed()) + " / 10");
            petPanel.getFlightLabel().setText("Flight: " + String.valueOf(model.getPet().getFlight()) + " / 10");
            playerPanel.getPlayerPanelTitle().setText(model.getOwner().getName() + " - Info");
            playerPanel.getFoodLabel().setText("Food: " + String.valueOf(model.getOwner().getFood()));
            playerPanel.getMoneyLabel().setText("Money: $" + String.valueOf(model.getOwner().getMoney()));
            playerPanel.getRacesLabel().setText("Races Won: " + String.valueOf(model.getOwner().getRacesWon()));
            powerPanel.setPowerDescription(model.getPet());
        }
        else if((Integer) arg == 2)
        {
            petPanel.getHealthLabel().setText("Health: " + String.valueOf(model.getPet().getHealth()) + " / 100");
            petPanel.getEnergyLabel().setText("Energy: " + String.valueOf(model.getPet().getEnergy()) + " / 10");
            playerPanel.getFoodLabel().setText("Food: " + String.valueOf(model.getOwner().getFood()));
            playerPanel.getEventLog().append("-1 food pellet.\n");
            
        }
        else if((Integer) arg == 3)
        {
            petPanel.getHealthLabel().setText("Health: " + String.valueOf(model.getPet().getHealth()) + " / 100");
            petPanel.getEnergyLabel().setText("Energy: " + String.valueOf(model.getPet().getEnergy()) + " / 10");
            playerPanel.getMoneyLabel().setText("Money: $" + String.valueOf(model.getOwner().getMoney()));
            playerPanel.getRacesLabel().setText("Races Won: " + String.valueOf(model.getOwner().getRacesWon()));
        }
        else if((Integer) arg == 4)
        {
            model.getOwner().setFood(model.getOwner().getFood() + 1);
            playerPanel.getFoodLabel().setText("Food: " + String.valueOf(model.getOwner().getFood()));
            playerPanel.getEventLog().append("+1 food pellet.\n");
            foodPanel.newQuestion();
            foodPanel.getAnswerBox().setText("");
        }
        else if((Integer) arg == 5)
        {
            petPanel.getHealthLabel().setText("Health: " + String.valueOf(model.getPet().getHealth()) + " / 100");
            petPanel.getEnergyLabel().setText("Energy: " + String.valueOf(model.getPet().getEnergy()) + " / 10");
            powerPanel.setPowerDescription(model.getPet());
            playerPanel.getEventLog().append("Pet power used.\n");
        }
        else if((Integer) arg == 6)
        {
            JOptionPane.showMessageDialog(null, "Your pet cannot use it's power as it is healthy!", "No Use For Pet Power", JOptionPane.INFORMATION_MESSAGE);
        }
        else if((Integer) arg == 7)
        {
            petPanel.getHealthLabel().setText("Health: " + String.valueOf(model.getPet().getHealth()) + " / 100");
            petPanel.getEnergyLabel().setText("Energy: " + String.valueOf(model.getPet().getEnergy()) + " / 10");
            petPanel.getSwimmingLabel().setText("Swimming: " + String.valueOf(model.getPet().getSwimming()) + " / 10");
            petPanel.getSpeedLabel().setText("Speed: " + String.valueOf(model.getPet().getSpeed()) + " / 10");
            petPanel.getFlightLabel().setText("Flight: " + String.valueOf(model.getPet().getFlight()) + " / 10");
            playerPanel.getMoneyLabel().setText("Money: $" + String.valueOf(model.getOwner().getMoney()));
            model.getOwner().setMaxPet(true);
            String evolutionImage;
            if(model.getPet() instanceof IceDragon)
            {
                evolutionImage = "ice_dragon.png";
            }
            else if(model.getPet() instanceof TerraDragon)
            {
                evolutionImage = "terra_dragon.png";
            }
            else
            {
                evolutionImage = "lava_dragon.png";
            }
            
            Image newImage = new ImageIcon(evolutionImage).getImage();
            mod = newImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon petIcon = new ImageIcon(mod);
            petIconLabel.setIcon(petIcon);
        }
        else if((Integer) arg == 8)
        {
            JOptionPane.showMessageDialog(null, "You do not meet the conditions to evolve!", "Unable to Evolve Pet", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(model.getOwner().getMaxPet())
        {
            evolveButton.setEnabled(false);
        }
        
    }
    
    /**
     * @return the feedButton
     */
    public JButton getFeedButton()
    {
        return feedButton;
    }
    
    /**
     * @return the raceButton
     */
    public JButton getRaceButton()
    {
        return raceButton;
    }
    
    /**
     * @return the petPowerButton
     */
    public JButton getPetPowerButton()
    {
        return petPowerButton;
    }
    
    /**
     * @return the endGameButton
     */
    public JButton getEndGameButton()
    {
        return endGameButton;
    }
    
    /**
     * @return the instructionsButton
     */
    public JButton getInstructionsButton()
    {
        return instructionsButton;
    }
    
    /**
     * @return the usernameBox
     */
    public JTextField getUsernameBox() {
        return usernameBox;
    }
    
    /**
     * @return the startButton
     */
    public JButton getStartButton() {
        return startButton;
    }
    
    /**
     * @return the errorLabel
     */
    public JLabel getErrorLabel() {
        return errorLabel;
    }
    
    /**
     * @return the startPanel
     */
    public JPanel getStartPanel() {
        return startPanel;
    }
    
    /**
     * @return the gameLabel
     */
    public JLabel getGameLabel() {
        return gameLabel;
    }
    
    /**
     * @return the selectionPanel
     */
    public JPanel getSelectionPanel() {
        return selectionPanel;
    }
    
    /**
     * @return the selectionLabel
     */
    public JLabel getSelectionLabel() {
        return selectionLabel;
    }
    
    /**
     * @return the option1
     */
    public JRadioButton getOption1() {
        return option1;
    }
    
    /**
     * @return the option2
     */
    public JRadioButton getOption2() {
        return option2;
    }
    
    /**
     * @return the option3
     */
    public JRadioButton getOption3() {
        return option3;
    }
    
    /**
     * @return the petNameField
     */
    public JTextField getPetNameField() {
        return petNameField;
    }
    
    /**
     * @return the errorLabel2
     */
    public JLabel getErrorLabel2() {
        return errorLabel2;
    }
    
    /**
     * @return the enterButton
     */
    public JButton getEnterButton() {
        return enterButton;
    }
    
    /**
     * @return the petPanel
     */
    public PetPanel getPetPanel() {
        return petPanel;
    }
    
    /**
     * @return the playerPanel
     */
    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }
    
    /**
     * @return the foodPanel
     */
    public FoodPanel getFoodPanel() {
        return foodPanel;
    }
    
    /**
     * @return the instructionPanel
     */
    public JPanel getInstructionPanel() {
        return instructionPanel;
    }
    
    /**
     * @return the instructionLabel
     */
    public JTextArea getInstructionText() {
        return instructionText;
    }
    
    /**
     * @return the instructionTitleLabel
     */
    public JLabel getInstructionTitleLabel() {
        return instructionTitleLabel;
    }
    
    /**
     * @return the continueButton
     */
    public JButton getContinueButton() {
        return continueButton;
    }
    
    /**
     * @return the optionGroup
     */
    public ButtonGroup getOptionGroup() {
        return optionGroup;
    }
    
    /**
     * @return the gamePanel
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }
    
    /**
     * @param petImage the petImage to set
     */
    public void setPetImage(String image)
    {
        this.petImage = new ImageIcon(image).getImage();
        mod = petImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon petIcon = new ImageIcon(mod);
        petIconLabel = new JLabel();
        petIconLabel.setIcon(petIcon);
        petIconLabel.setSize(300, 300);
        petIconLabel.setLocation(275, 40);
        gamePanel.add(petIconLabel);
    }
    
    /**
     * @return the powerPanel
     */
    public PowerPanel getPowerPanel() {
        return powerPanel;
    }
    
    /**
     * @return the evolvePanel
     */
    public EvolvePanel getEvolvePanel() {
        return evolvePanel;
    }
    
    /**
     * @return the evolveButton
     */
    public JButton getEvolveButton() {
        return evolveButton;
    }
    
    
}
