package softwareconstruction;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author kealenpillay
 */
public class EndPanel extends JPanel
{
    //--------------------------------------------------- Instance Variables -------------------------------------
    private JLabel highscoresLabel;
    private JTextArea highscores;
    private JTextField review;
    private JButton playAgainButton;
    private JButton submitButton;
    
    //--------------------------------------------------- Constructor -------------------------------------
    
    public EndPanel()
    {
        this.setLayout(null);
        this.setBackground(new Color(255, 134, 97));
        this.setSize(853, 600);
        
        highscoresLabel = new JLabel("HIGHSCORES");
        Font f = new Font("Arial", Font.BOLD, 50);
        highscoresLabel.setFont(f);
        highscoresLabel.setSize(450, 100);
        highscoresLabel.setLocation(250, 30);
        
        highscores = new JTextArea();
        Font f1 = new Font("Arial", Font.BOLD, 25);
        highscores.setFont(f1);
        highscores.setEditable(false);
        highscores.setBackground(new Color(255, 134, 97));
        highscores.setSize(500, 200);
        highscores.setLocation(200, 200);
        
        review = new JTextField("Enter Review Here...");
        review.setSize(300, 40);
        review.setLocation(200, 420);
        
        playAgainButton = new JButton("New Game");
        playAgainButton.setSize(150, 50);
        playAgainButton.setLocation(350, 500);
        
        submitButton = new JButton("Submit Review");
        submitButton.setSize(150, 40);
        submitButton.setLocation(520, 420);
        
        this.add(highscoresLabel);
        this.add(highscores);
        this.add(review);
        this.add(playAgainButton);
        this.add(submitButton);
        this.setVisible(false);
    }
    
    //--------------------------------------------------- Methods -------------------------------------
    
    public void setScores(List<Map.Entry<String, Integer>> scores)
    {
        String scoreList = "";
        int counter = 1;
        for(Map.Entry<String, Integer> entry : scores)
        {
            if(counter == 6)
            {
                break;
            }
            scoreList += counter + ") " + entry.getKey() + " - SCORE: " + entry.getValue() + " RACES WON\n";
            counter++;
        }
        highscores.setText(scoreList);
    }

    /**
     * @return the highscoresLabel
     */
    public JLabel getHighscoresLabel() {
        return highscoresLabel;
    }

    /**
     * @return the high scores
     */
    public JTextArea getHighscores() {
        return highscores;
    }

    /**
     * @return the review
     */
    public JTextField getReview() {
        return review;
    }

    /**
     * @return the playAgainButton
     */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    /**
     * @return the submitButton
     */
    public JButton getSubmitButton() {
        return submitButton;
    }
    
}
