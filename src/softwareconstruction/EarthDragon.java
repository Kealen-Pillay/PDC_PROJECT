package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class EarthDragon extends Pet
{
    //---------------------------------- Static Variable --------------------------------
    private int powerCounter;
    
    //------------------------------------ Constructor ----------------------------------
    
    /**
     * Multiple input parameter constructor for the earth dragon pet.
     * @param petName represents the earth dragon's name.
     * @param health represents the earth dragon's health.
     * @param energy represents the earth dragon's energy.
     * @param swimming represents the earth dragon's swimming skill.
     * @param speed represents the earth dragon's speed skill.
     * @param flight represents the earth dragon's flight skill.
     */
    public EarthDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //------------------------------------- Methods --------------------------------------
    
    /**
     * Returns the number of uses of the earth dragon's power.
     * @return the number of uses of the earth dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the numbers of uses of the earth dragon's power.
     * @param powerCounter represents the number of uses of the earth dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
    /**
     * Causes the earth dragon to roar. The type of roar varies depending on the earth dragon's health, giving the owner and indication of the earth dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a slow roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " is whimpering...");
        }
        else
        {
           System.out.println(this.getPetName() + " let out a proud roar!..."); 
        }  
    }
    
    /**
     * Provides the user with a description of the earth dragon pet.
     */
    @Override
    public String description()
    {
        return "Earth Dragon - The earth dragon has a relaxed nature but is very energetic! This dragon loves to RUN.";
    }
    
    /**
     * Allows the earth dragon pet to use it's special power. This will restore up to 20 health points to the earth dragon pet. This power has three uses.
     */
    @Override
    public String power()
    {  
        String powerDescription = "------------\nPower: Quake\n------------\nUses Remaining: " + (3 - this.getPowerCounter()) + "\nThe earth dragon channels the power of the Earth to instantly restore up to 20 health points.\nWould you like to use this power?";
        return powerDescription;
    }
}
