package softwareconstruction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kealenpillay
 */
public class Model extends Observable
{
    //--------------------------- Instance Variables ----------------------------------------------
    private Pet pet;
    private Owner owner;
    private String raceResult;
    private DBManager dbManager;
    private Connection conn;
    private Statement statement;
    
    //---------------------------- Constructor -----------------------------------------------------
    public Model()
    {
        this.dbManager = new DBManager();
        this.conn = this.dbManager.getConn();
        
        try
        {
            this.checkExistedTable("INSTRUCTIONS");
            statement = conn.createStatement();
            String instructionsTable = "CREATE TABLE INSTRUCTIONS (RULES VARCHAR(130))";
            this.checkExistedTable("REVIEWS");
            String reviewsTable = "CREATE TABLE REVIEWS (REVIEW VARCHAR(255))"; 
            this.checkExistedTable("USAGESTATS");
            String usageTable = "CREATE TABLE USAGESTATS (FIRE_DRAGON INT, WATER_DRAGON INT, EARTH_DRAGON INT)";
            
            
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
            
            String petStats = "INSERT INTO USAGESTATS VALUES (" + 0 + "," + 0 + "," + 0 + ")";
            
            statement.executeUpdate(instructionsTable);
            statement.executeUpdate(instructions);
            statement.executeUpdate(reviewsTable);
            statement.executeUpdate(usageTable);
            statement.executeUpdate(petStats);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-------------------------- Methods ------------------------------------------------------------
    /**
     * Presents the player with instructions of how the game works.
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
     * Start screen which allows the user to pick the type of pet they want and the name of the pet.
     * @return returns either a WaterDragon, EarthDragon or FireDragon pet object.
     */
    public ArrayList<Pet> petList()
    {
        ArrayList<Pet> pets = new ArrayList<Pet> ();
        String line = null;
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("petstats.txt"));
            
            while((line = inputStream.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    Pet p1 = new WaterDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p1);
                    
                    Pet p2 = new EarthDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p2);
                    
                    Pet p3 = new FireDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p3);
                }
            }
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        return pets;
    }
    
    /**
     * Allows the user to earn food for their pet by answering basic math questions correctly.Math questions are randomly generated. Each correct question will increase the player's food amount by 1.
     */
    public HashMap<String, Integer> earnFood()
    {
        HashMap<String, Integer> questions = new HashMap<String, Integer> ();
        try
        {
            BufferedReader input = new BufferedReader(new FileReader("MathQuestions.txt"));
            String line = null;
            
            while((line = input.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    questions.put(st.nextToken(), Integer.parseInt(st.nextToken()));
                }
            }
            
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch (IOException ex)
        {
            System.out.println("IOException");
        }
        return questions;
    };
    
    /**
     * Checks if the username the player has entered already exists. True is returned if the username already exists, otherwise the new username is added to list of existing usernames in external file and false is returned.
     * @param username represents the username to check.
     * @return returns a Boolean value. True is returned if the username already exists, otherwise false is returned.
     */
    public boolean exists(String username)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("Usernames.txt"));
            String line = null;
            
            while((line = inputStream.readLine()) != null)
            {
                if(line.toLowerCase().equals(username.toLowerCase()))
                {
                    inputStream.close();
                    return true;
                }
            }
            inputStream.close();
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("Usernames.txt", true));
            outputStream.println(username.toUpperCase());
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
        
        return false;
    }
    
    /**
     * Reads from an external file which keeps track of how many times each type of pet has been selected. This can be used to implement balance changes in the future, if a type of pet is being selected by users more than others. Allows the developer to track the pet usage for each type in the game.
     * @param p represents the current player's selected pet. This is used to check the type of pet in order to update usage statistics.
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
    
    /**
     * Allows the player to write a review after playing the game. All reviews are stored in the embedded database.
     */
    public void reviews(String review)
    { 
        try
        {
            statement = conn.createStatement();         
            String insertion = "INSERT INTO REVIEWS VALUES ('"+ review + "')";
            statement.executeUpdate(insertion);
            setChanged();
            notifyObservers(9);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the pet
     */
    public Pet getPet()
    {
        return pet;
    }
    
    /**
     * @return the owner
     */
    public Owner getOwner()
    {
        return owner;
    }
    
    /**
     * @param pet the pet to set
     */
    public void setPet(Pet pet)
    {
        this.pet = pet;
    }
    
    /**
     * @param owner the owner to set
     */
    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }
    
    public void setup()
    {
        setChanged();
        notifyObservers(1);
    }
    
    public void feedPet()
    {
        this.owner.feed(this.pet);
        setChanged();
        notifyObservers(2);
    }
    
    public void racePet()
    {
        raceResult = this.pet.race(this.owner);
        setChanged();
        notifyObservers(3);
    }
    
    public void incrementFood()
    {
        setChanged();
        notifyObservers(4);
    }
    
    public void usePower()
    {
        if(owner.getMaxPet())
        {
            this.evolvedPower(pet);
        }
        else
        {
            if(this.pet.getPowerCounter() != 3)
            {
                if(this.pet instanceof WaterDragon)
                {
                    if(pet.getHealth() != 100 || pet.getEnergy() != 10)
                    {
                        this.pet.setHealth(this.pet.getHealth() + 10);
                        this.pet.setEnergy(this.pet.getEnergy() + 2);
                        this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                        setChanged();
                        notifyObservers(5);
                    }
                    else
                    {
                        setChanged();
                        notifyObservers(6);
                    }
                }
                else if(this.pet instanceof EarthDragon)
                {
                    if(pet.getHealth() != 100)
                    {
                        this.pet.setHealth(this.pet.getHealth() + 20);
                        this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                        setChanged();
                        notifyObservers(5);
                    }
                    else
                    {
                        setChanged();
                        notifyObservers(6);
                    }
                }
                else if(this.pet instanceof FireDragon)
                {
                    if(pet.getEnergy() != 10)
                    {
                        this.pet.setEnergy(this.pet.getEnergy() + 5);
                        this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                        setChanged();
                        notifyObservers(5);
                    }
                    else
                    {
                        setChanged();
                        notifyObservers(6);
                    }
                }
            }
        }
    }
    
    private void evolvedPower(Pet pet)
    {
        if(pet.getPowerCounter() != 3)
        {
            if(this.pet instanceof IceDragon)
            {
                if(pet.getHealth() != 100 || pet.getEnergy() != 10)
                {
                    this.pet.setHealth(this.pet.getHealth() + 30);
                    this.pet.setEnergy(this.pet.getEnergy() + 4);
                    this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                    setChanged();
                    notifyObservers(5);
                }
                else
                {
                    setChanged();
                    notifyObservers(6);
                }
            }
            else if(this.pet instanceof TerraDragon)
            {
                if(pet.getHealth() != 100)
                {
                    this.pet.setHealth(this.pet.getHealth() + 50);
                    this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                    setChanged();
                    notifyObservers(5);
                }
                else
                {
                    setChanged();
                    notifyObservers(6);
                }
            }
            else
            {
                if(pet.getHealth() != 100 || pet.getEnergy() != 10)
                {
                    this.pet.setHealth(this.pet.getHealth() + 5);
                    this.pet.setEnergy(this.pet.getEnergy() + 8);
                    this.pet.setPowerCounter(this.pet.getPowerCounter() + 1);
                    setChanged();
                    notifyObservers(5);
                }
                else
                {
                    setChanged();
                    notifyObservers(6);
                }
            }
        }
        
    }
    
    public void evolvePet()
    {
        if(owner.getRacesWon() >= 1 && owner.getMoney() >= 100)
        {
            if(pet instanceof WaterDragon)
            {
                pet = new IceDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 2, pet.getSpeed() + 2, pet.getFlight() + 2);
            }
            else if(pet instanceof EarthDragon)
            {
                pet = new TerraDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 2, pet.getSpeed() + 2, pet.getFlight() + 2);
            }
            else
            {
                pet = new LavaDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 2, pet.getSpeed() + 2, pet.getFlight() + 2);
            }
            setChanged();
            notifyObservers(7);
        }
        else
        {
            setChanged();
            notifyObservers(8);
        }
        
    }
    
    /**
     * @return the raceResult
     */
    public String getRaceResult() {
        return raceResult;
    }
    
    /**
     * @return the dbManager
     */
    public DBManager getDbManager() {
        return dbManager;
    }
    
    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }
    
    public void checkExistedTable(String name)
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
                System.out.println(table_name);
                if(table_name.equalsIgnoreCase(name))
                {
                    statement.executeUpdate("Drop table " + name);
                    System.out.println("Table " + name + " has been deleted");
                    break;
                }
            }
            rs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
