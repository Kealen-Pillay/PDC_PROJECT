package softwareconstruction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class FoodPanel extends JPanel
{
    //----------------------------------- Instance Variables ----------------------------------------
    private JTextField answerBox;
    private String question;
    private int answer;
    private HashMap<String, Integer> questions;
    private JButton enterButton;
    private JLabel earnFoodLabel;
    private JLabel questionLabel;
    
    
    //------------------------------------------- Constructor ----------------------------------------
    
    public FoodPanel(HashMap<String, Integer> questions)
    {
        this.setLayout(null);
        this.questions = questions;
        earnFoodLabel = new JLabel("Earn Food");
        earnFoodLabel.setSize(80, 20);
        earnFoodLabel.setLocation(60, 10);
        
        answerBox = new JTextField();
        answerBox.setSize(110, 20);
        answerBox.setLocation(10, 150);
        
        enterButton = new JButton("Enter");
        enterButton.setSize(40, 20);
        enterButton.setLocation(125, 150);
        
        questionLabel = new JLabel();
        this.newQuestion();
        Font f = new Font("Arial", Font.BOLD, 20);
        questionLabel.setFont(f);
        questionLabel.setSize(80, 40);
        questionLabel.setLocation(60, 70);
        
        this.add(earnFoodLabel);
        this.add(answerBox);
        this.add(enterButton);
        this.add(questionLabel);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setSize(190, 180);
    }
    
    //----------------------------------------------- Methods ----------------------------------------
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 190, 40);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 140, 190, 40);
    }
        
    public void newQuestion()
    {
        List<String> keys = new ArrayList<>(this.questions.keySet());
        Random r = new Random();
        this.question = keys.get(r.nextInt(keys.size()-1));
        this.questionLabel.setText(this.question);
        this.answer = this.questions.get(this.getQuestion());        
    }

    /**
     * @return the enterButton
     */
    public JButton getEnterButton() 
    {
        return enterButton;
    }

    /**
     * @return the answerBox
     */
    public JTextField getAnswerBox() {
        return answerBox;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }
}
