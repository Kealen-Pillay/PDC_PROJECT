package softwareconstruction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


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
    
    //--------------------------------------------- Constructor ----------------------------------------------------
    public Highscores(String playerName, int racesWon)
    {
        this.playerName = playerName;
        this.racesWon = racesWon;
        this.highscores = new HashMap<String, Integer> ();
    }
       
    //--------------------------------------------- Methods ----------------------------------------------------
    
    /**
     * Reads in scores stored in an external text file and stores the values in a HashMap. The HashMap contains players and their corresponding scores.
     */
    private void readScores()
    {
        try
        {
            //Sorting Highscores
            BufferedReader inputStream = new BufferedReader(new FileReader("Highscores.txt"));
            HashMap<String, Integer> scores = new HashMap<String, Integer> ();
            String line = null;
            String name = null;
            int score = 0;
            
            scores.put(this.playerName, this.racesWon);
            
            while((line = inputStream.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    name = st.nextToken();
                    score = Integer.parseInt(st.nextToken());
                    scores.put(name, score);   
                }
            }
            inputStream.close();
            
            this.highscores = scores;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        } 
        catch (IOException ex) 
        {
            System.out.println("IOException");
        }
        
    }
     
    /**
     * Sorts the scores stored in the HashMap containing players and their corresponding scores and sorts the scores in descending order.
     * @return returns an ArrayList<Map.Entry<String, Integer>> containing the sorted scores in descending order. 
     */
    public ArrayList<Map.Entry<String, Integer>> sortValues()
    {
        readScores();
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
     * Writes the sorted scores to the same external text file that the scores are read from.
     */
    public void writeScores()
    {
        try
        {
            ArrayList<Map.Entry<String, Integer>> list = sortValues();
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("Highscores.txt"));   
            for(Map.Entry<String, Integer> entry : list)
            {
                outputStream.println(entry.getKey().toUpperCase() + "," + entry.getValue());
            }
            outputStream.close();
        } 
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }     
    }
    

}
