package softwareconstruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
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
    
    /**
     * Zero input parameter constructor.
     */
    public Model()
    {
        this.dbManager = new DBManager();
        this.conn = this.dbManager.getConn();
        try
        {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-------------------------- Methods ------------------------------------------------------------
    
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
    
    /**
     * Allows the player to write a review after playing the game. All reviews are written to and stored in the embedded database.
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
     * @return the pet.
     */
    public Pet getPet()
    {
        return pet;
    }
    
    /**
     * @return the owner.
     */
    public Owner getOwner()
    {
        return owner;
    }
    
    /**
     * @param pet the pet to set.
     */
    public void setPet(Pet pet)
    {
        this.pet = pet;
    }
    
    /**
     * @param owner the owner to set.
     */
    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }
    
    /**
     * Notifies the observer to setup the game screen.
     */
    public void setup()
    {
        setChanged();
        notifyObservers(1);
    }
    
    /**
     * Feeds the pet and notifies the observer to update.
     */
    public void feedPet()
    {
        this.owner.feed(this.pet);
        setChanged();
        notifyObservers(2);
    }
    
    /**
     * Enters the pet into a race and notifies the observer to update.
     */
    public void racePet()
    {
        raceResult = this.pet.race(this.owner);
        setChanged();
        notifyObservers(3);
    }
    
    /**
     * Increments the owner's food and notifies the observer to update.
     */
    public void incrementFood()
    {
        owner.setFood(owner.getFood() + 1);
        setChanged();
        notifyObservers(4);
    }
    
    /**
     * Uses the pet's unique power and notifies the observer to update. If the pet is fully evolved, a private method performing the same functionality on the evolved pet is performed.
     */
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
    
    /**
     * Uses the evolved pet's pet power if possible and notifies the observer to update.
     * @param pet 
     */
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
    
    /**
     * Checks if the owner is eligible to evolve their pet. If possible, the pet will become the evolved pet and the observer is notified.
     */
    public boolean evolvePet()
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
            return true;
        }
        else
        {
            setChanged();
            notifyObservers(8);
            return false;
        }
        
    }
    
    /**
     * @return the raceResult.
     */
    public String getRaceResult() {
        return raceResult;
    }
    
    /**
     * @return the dbManager.
     */
    public DBManager getDbManager() {
        return dbManager;
    }
    
    /**
     * @return the conn.
     */
    public Connection getConn() {
        return conn;
    }
}
