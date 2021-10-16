
package softwareconstruction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kealenpillay
 */
public class DBManager
{
    //----------------------------------------------------- Instance Variables ---------------------------------------------------------
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:VirtualPetGameDB; create=true";
    private Connection conn;
    private Statement statement;
   
    //----------------------------------------------------- Constructor ---------------------------------------------------------
    /**
     * Zero parameter constructor.
     */
    public DBManager()
    {
        this.establishConnection();
        try
        {
            statement = conn.createStatement();
            if(!this.checkExistedTable("INSTRUCTIONS"))
            {
                String instructionsTable = "CREATE TABLE INSTRUCTIONS (RULES VARCHAR(130))";
                String instructions = "INSERT INTO INSTRUCTIONS VALUES" +
                        "('- Welcome to the virtual pet game, We hope you take care of your little buddy.'),\n" +
                        "('- Once you have adopted your virtual pet, it will be your responsibility to take care of your new friend.'),\n" +
                        "('- Your new pet will have various skills such as swimming, speed, and flight.'),\n" +
                        "('- Depending on the pet you adopt, some pets have better skills or worse skills than others.'),\n" +
                        "('- You may choose to enter your pet into races such as swimming, speed or flight.'),\n" +
                        "('- If your pet wins the race, you will be rewarded with money which you may save to evolve your pet and make it more skilled.'),\n" +
                        "('- Entering your pet into a race will cause it too lose health and energy.'),\n" +
                        "('- It will be your responsibility to ensure your pet is full of energy and healthy by feeding them pellets.'),\n" +
                        "('- Feeding your pet a pellet will restore 1 energy bar.'),\n" +
                        "('- Once your pet is at full energy, its health will begin to increase when fed.'),\n" +
                        "('- You can earn pellets by answering basic math questions.'),\n" +
                        "('- Each pet has a unique power that can be used, but be conservative as each power only has 3 uses.')," +
                        "('- Be sure to keep your pet well fed to keep it from dying. (Death of pet will end game)'),\n" +
                        "('- Have Fun!')";
                statement.executeUpdate(instructionsTable);
                statement.executeUpdate(instructions);
            }
            if(!this.checkExistedTable("REVIEWS"))
            {
                String reviewsTable = "CREATE TABLE REVIEWS (REVIEW VARCHAR(255))";
                statement.executeUpdate(reviewsTable);
            }
            if(!this.checkExistedTable("USAGESTATS"))
            {
                String usageTable = "CREATE TABLE USAGESTATS (FIRE_DRAGON INT, WATER_DRAGON INT, EARTH_DRAGON INT)";
                String petStats = "INSERT INTO USAGESTATS VALUES (" + 0 + "," + 0 + "," + 0 + ")";
                statement.executeUpdate(usageTable);
                statement.executeUpdate(petStats);
            }
            if(!this.checkExistedTable("USERNAMES"))
            {
                String usernameTable = "CREATE TABLE USERNAMES (NAMES VARCHAR(10))";
                statement.executeUpdate(usernameTable);
            }            
            if(!this.checkExistedTable("FOOD"))
            {
                String foodTable = "CREATE TABLE FOOD (QUESTIONS VARCHAR(10),ANSWERS INT)";
                statement.executeUpdate(foodTable);
                BufferedReader input = new BufferedReader(new FileReader("MathQuestions.txt"));
                String line = null;
                while((line = input.readLine()) != null)
                {
                    StringTokenizer st = new StringTokenizer(line, ",");
                    while(st.hasMoreTokens())
                    {
                        String insertion = "INSERT INTO FOOD VALUES ('" + st.nextToken() + "'," + Integer.parseInt(st.nextToken()) + ")";
                        statement.executeUpdate(insertion);
                    }
                }
                input.close();
            }
            if(!this.checkExistedTable("PETS"))
            {
                String petTable = "CREATE TABLE PETS (NAME VARCHAR(1), HEALTH INT, ENERGY INT, SWIMMING INT, SPEED INT, FLIGHT INT)";
                String petInsertion = "INSERT INTO PETS VALUES"
                        + "('w'," + 100 + "," + 10 + "," + 6 + "," + 2 + "," + 4 + "),\n"
                        + "('e '," + 100 + "," + 10 + "," + 4 + "," + 6 + "," + 2 + "),\n"
                        + "('f'," + 100 + "," + 10 + "," + 2 + "," + 4 + "," + 6 + ")";
                statement.executeUpdate(petTable);
                statement.executeUpdate(petInsertion);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //----------------------------------------------------- Methods ---------------------------------------------------------
    
    /**
     * @return the conn.
     */
    public Connection getConn()
    {
        return conn;
    }
    
    /**
     * Establish a connection to the database.
     */
    public void establishConnection()
    {
        if(this.conn == null)
        {
            try
            {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println(URL + " Connected Successfully ....");
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Checks if a table already exists.
     * @param name represents the name of the table to be checked.
     * @return true is returned if the table exists.
     */
    public boolean checkExistedTable(String name)
    {
        try
        {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            
            while(rs.next())
            {
                String table_name = rs.getString("TABLE_NAME");
                if(table_name.equalsIgnoreCase(name))
                {
                    return true;
                }
            }
            rs.close();
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Closes the connection to the database.
     */
    public void closeConnections()
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Presents the player with instructions of how the game works. Instructions are read from the embedded database.
     */
    public String instructions()
    {
        String text = "";
        try
        {
            ResultSet rs = statement.executeQuery("SELECT RULES FROM INSTRUCTIONS");
            while(rs.next())
            {
                text += rs.getString("RULES") + "\n\n";
            }
            rs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
    }
    
    /**
     * Reads in each type of dragon's stats from the embedded database, and stores it in an ArrayList which is returned.
     * @return returns either a WaterDragon, EarthDragon or FireDragon pet object.
     */
    public ArrayList<Pet> petList()
    {
        ArrayList<Pet> pets = new ArrayList<Pet> ();
        try
        {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT NAME, HEALTH, ENERGY, SWIMMING, SPEED, FLIGHT FROM PETS");
            rs.next();
            Pet p1 = new WaterDragon(rs.getString("NAME"), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            rs.next();
            Pet p2 = new EarthDragon(rs.getString("NAME"), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            rs.next();
            Pet p3 = new FireDragon(rs.getString("NAME"), rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
            pets.add(p1);
            pets.add(p2);
            pets.add(p3);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pets;
    }
    
    /**
     * Reads math questions and corresponding answers from embedded database and stores them as a key-value pair in a HashMap<String, Integer> that is returned.
     */
    public HashMap<String, Integer> loadFood()
    {
        HashMap<String, Integer> questions = new HashMap<String, Integer> ();
        try
        {
            ResultSet rs = statement.executeQuery("SELECT QUESTIONS, ANSWERS FROM FOOD");
            while(rs.next())
            {
                String question = rs.getString("QUESTIONS");
                int answer = rs.getInt(2);
                questions.put(question, answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    };
    
    /**
     * Checks if the username the player username already exists within the embedded database. True is returned if the username already exists, otherwise the new username is added to list of existing usernames in the embedded database and false is returned.
     * @param username represents the username to check.
     * @return returns a Boolean value. True is returned if the username already exists, otherwise false is returned.
     */
    public boolean exists(String username)
    {
        try
        {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT NAMES FROM USERNAMES");
            while(rs.next())
            {
                String name = rs.getString("NAMES");
                if(name.equals(username))
                {
                    return true;
                }
            }
            String insertion = "INSERT INTO USERNAMES VALUES ('" + username + "')";
            statement.executeUpdate(insertion);
            rs.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Pet stats are read from the embedded database and updated, then stored in embedded database.This can be used to implement balance changes in the future, if a type of pet is being selected by users more than others. Allows the developer to track the pet usage for each type in the game.
     * @param p represents the current player's selected pet. This is used to check the type of pet in order to update usage statistics stored in the embedded database.
     */
    public void usageStats(Pet p)
    {
        try
        {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT FIRE_DRAGON, EARTH_DRAGON, WATER_DRAGON FROM USAGESTATS");
            int fireCount = 0;
            int earthCount = 0;
            int waterCount = 0;
            while(rs.next())
            {
                fireCount = rs.getInt(1);
                earthCount = rs.getInt(2);
                waterCount = rs.getInt(3);
            }
            rs.close();
            if(p instanceof WaterDragon)
            {
                waterCount++;
            }
            else if(p instanceof EarthDragon)
            {
                earthCount++;
            }
            else
            {
                fireCount++;
            }
            String petStats = "INSERT INTO USAGESTATS VALUES (" + fireCount + "," + waterCount + "," + earthCount + ")";
            statement.executeUpdate(petStats);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
