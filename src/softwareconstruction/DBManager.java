
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
    
}
