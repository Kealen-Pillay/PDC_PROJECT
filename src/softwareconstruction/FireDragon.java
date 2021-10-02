package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class FireDragon extends Pet
{
    //------------------------------------ Static Variable -------------------------------
    private int powerCounter;
    
    //-------------------------------------- Constructor --------------------------------------
    
    /**
     * Multiple input parameter constructor for the fire dragon pet.
     * @param petName represents the fire dragon's name.
     * @param health represents the fire dragon's health.
     * @param energy represents the fire dragon's energy.
     * @param swimming represents the fire dragon's swimming skill.
     * @param speed represents the fire dragon's speed skill.
     * @param flight represents the fire dragon's flight skill.
     */
    public FireDragon(String petName, int health, int energy, int swimming, int speed, int flight)
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //------------------------------------ Methods ---------------------------------------------
    
    /**
     * Returns the number of uses of the fire dragon's power.
     * @return the number of uses of the fire dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the fire dragon's power.
     * @param powerCounter represents the number of uses of the fire dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
    /**
     * Causes the fire dragon to roar. The type of roar varies depending on the fire dragon's health, giving the owner and indication of the fire dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a whine...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out a cry...");
        }
        else
        {
            System.out.println(this.getPetName() + " let out a fiery roar!...");
        }
    }
    
    /**
     *  Provides the user with a description of the fire dragon pet.
     */
    @Override
    public String description()
    {
        return "Fire Dragon - The fire dragon has a fiery temper and is very mischievous! This dragon loves to FLY.";
    }
    
    /**
     * Allows the fire dragon pet to use it's special power. This will restore up to 5 energy points to the fire dragon pet. This power has three uses.
     */
    @Override
    public String power()
    {
        String powerDescription = "--------------\nPower: Inferno\n--------------\nUses Remaining: " + (3 - this.getPowerCounter()) + "\nThe fire dragon uses the power of the sun to instantly restore up to 5 energy points.Would you like to use this power?";
        return powerDescription;
    }
}
