package softwareconstruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void usePetPower()
    {
        if(this.pet.usePower())
        {
            setChanged();
            notifyObservers(5);
        }
        else
        {
            setChanged();
            notifyObservers(6);
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
