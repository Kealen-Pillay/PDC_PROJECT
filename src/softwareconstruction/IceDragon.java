package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class IceDragon extends WaterDragon
{
    //---------------------------------- Static Variable ----------------------------------------
    private int powerCounter;
    
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
     * Returns the number of uses of the ice dragon's power.
     * @return the number of uses of the ice dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the ice dragon's power.
     * @param powerCounter represents the number of uses of the ice dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
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
        String powerDescription = "-----------------\nPower: Permafrost\n-----------------\nUses Remaining: " + (3 - this.getPowerCounter()) + "\n The ice dragon freezes itself over in order to heal up to 30 health points and restore up to 4 energy points.\nWould you like to use this power?";
        return powerDescription;
    }
}
