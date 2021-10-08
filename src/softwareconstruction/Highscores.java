package softwareconstruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kealenpillay
 */
public class Highscores
{
    //--------------------------------------------- Instance Variables ---------------------------------------------------
    
    private String playerName;
    private int racesWon;
    private HashMap<String, Integer> highscores;
    private Connection conn;
    private Statement statement;
    private Model m;
    
    //--------------------------------------------- Constructor ----------------------------------------------------
    public Highscores(String playerName, int racesWon, Connection conn, Model m)
    {
        this.playerName = playerName;
        this.racesWon = racesWon;
        this.highscores = new HashMap<String, Integer> ();
        this.conn = conn;
        this.m = m;
        
        try
        {
            statement = conn.createStatement();
            if(!m.checkExistedTable("HIGHSCORES"))
            {
                String highscoresTable = "CREATE TABLE HIGHSCORES (PLAYER VARCHAR(10), SCORE INT)";
                statement.executeUpdate(highscoresTable);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Highscores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //--------------------------------------------- Methods ----------------------------------------------------
    
    /**
     * Reads in scores stored in an embedded database and stores the values in a HashMap. The HashMap contains players and their corresponding scores.
     */
    private void readScores()
    {
        try
        {
            statement = conn.createStatement();
            HashMap<String, Integer> scores = new HashMap<String, Integer> ();
            scores.put(this.playerName, this.racesWon);
            ResultSet rs = statement.executeQuery("SELECT PLAYER, SCORE FROM HIGHSCORES");
            while(rs.next())
            {
                String player = rs.getString("PLAYER");
                int score = rs.getInt(2);
                scores.put(player, score);
            }
            rs.close();
            this.highscores = scores;
        } catch (SQLException ex) {
            Logger.getLogger(Highscores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sorts the scores stored in the HashMap containing players and their corresponding scores and sorts the scores in descending order.
     * @return returns an ArrayList<Map.Entry<String, Integer>> containing the sorted scores in descending order.
     */
    public ArrayList<Map.Entry<String, Integer>> sortValues()
    {
        this.readScores();
        Set<Map.Entry<String, Integer>> set = this.highscores.entrySet();
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>> (set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        return list;
    }
    
    /**
     * Writes the sorted scores to the embedded database.
     */
    public void writeScore()
    {
        try
        {
            statement = conn.createStatement();
            String insertion = "INSERT INTO HIGHSCORES VALUES('" + this.playerName + "'," + this.racesWon + ")";
            statement.executeUpdate(insertion);
            
        } catch (SQLException ex) {
            Logger.getLogger(Highscores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
