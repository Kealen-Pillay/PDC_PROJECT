package softwareconstruction;

import java.util.Scanner;


/**
 *
 * @author kealenpillay
 */
public class TerraDragon extends EarthDragon
{
    //---------------------------------- Static Variable --------------------------------
    private int powerCounter;
    
    //-------------------------------------- Constructor ----------------------------------------------------
    
    /**
     * Multiple input parameter for Terra Dragon pet.
     * @param petName represents the terra dragon's name.
     * @param health represents the terra dragon's health.
     * @param energy represents the terra dragon's energy.
     * @param swimming represents the terra dragon's swimming skill.
     * @param speed represents the terra dragon's speed skill.
     * @param flight represents the terra dragon's flight skill.
     */
    public TerraDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //------------------------------------ Methods ----------------------------------------------------------
    
    /**
     * Returns the number of uses of the terra dragon's power.
     * @return the number of uses of the terra dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the terra dragon's power.
     * @param powerCounter represents the number of uses of the terra dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
          
     /**
     * Causes the terra dragon to roar. The type of roar varies depending on the terra dragon's health, giving the owner and indication of the terra dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a sighing roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out a sad grumble...");
        }
        else
        {
           System.out.println(this.getPetName() + " let out a deep roar!..."); 
        }  
    }
    
    /**
     * Provides the user with a description of the terra dragon pet.
     */
    @Override
    public String description()
    {
        return "\nTerra Dragon\n------------\nThe terra dragon is a tough beast of nature that has incredible speed! This dragon loves to hunt.\n";
    }
    
    /**
     * Allows the terra dragon pet to use it's special power. This will restore up to 50 health points to the terra dragon pet. This power has three uses.
     */
    @Override
    public String power()
    {  
        String powerDescription = "---------------------\nPower: Healing Nature\n---------------------\nUses Remaining: " + (3 - this.getPowerCounter()) + "\nThe terra dragon summons the healing power of nature and will instantly restore up to 50 health points.\nWould you like to use this power?";
        return powerDescription;  
    }
}
