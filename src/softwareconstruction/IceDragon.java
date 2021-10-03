package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class IceDragon extends WaterDragon
{
    
    //---------------------------------------- Constructor ----------------------------------------------
    
    /**
     * Multiple input parameter constructor for Ice Dragon.
     * @param petName
     * @param health
     * @param energy
     * @param swimming
     * @param speed
     * @param flight 
     */
    public IceDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //--------------------------------------- Methods ---------------------------------------------------
    
     /**
     * Causes the ice dragon to roar. The type of roar varies depending on the ice dragon's health, giving the owner and indication of the ice dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a sneeze and a roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out a painful roar...");
        }
        else
        {
            System.out.println(this.getPetName() + " let out a frosty roar!...");
        }
    }
    
    /**
     * Provides the user with a description of the ice dragon pet.
     */
    @Override
    public String description()
    {
        return "\nIce Dragon\n------------\nThe ice dragon is an ice cold killer! This dragon loves to freeze it's prey.\n";
    }
    
    /**
     * Allows the ice dragon pet to use it's special power. This will restore up to 30 health points and up to 4 energy points to the ice dragon pet. This power has three uses.
     */
    @Override
    public String power()
    {
        String powerDescription = 
                  "-------------------------------\n"
                + "Power: Permafrost\n"
                + "-------------------------------\n\n"
                + "> Uses Remaining: " + (3 - this.getPowerCounter())
                + "\n\n> Description: Heal up to 30 health points and restore up to 4 energy points.";
        return powerDescription;
    }
}
