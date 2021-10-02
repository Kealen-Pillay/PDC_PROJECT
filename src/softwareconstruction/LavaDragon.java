package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class LavaDragon extends FireDragon
{
    //------------------------------------ Static Variable -----------------------------------
    private int powerCounter;
    
    //------------------------------------ Constructor ----------------------------------------
    
    /**
     * Multiple input parameter constructor for Lava Dragon pet.
     * @param petName represents the lava dragon's name.
     * @param health represents the lava dragon's health.
     * @param energy represents the lava dragon's energy.
     * @param swimming represents the lava dragon's swimming skill.
     * @param speed represents the lava dragon's speed skill. 
     * @param flight represents the lava dragon's flight skill.
     */
    public LavaDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //------------------------------------ Methods ----------------------------------------
    
    /**
     * Returns the number of uses of the Lava dragon's power.
     * @return the number of uses of the lava dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the lava dragon's power.
     * @param powerCounter represents the number of uses of the lava dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
    /**
     * Causes the lava dragon to roar. The type of roar varies depending on the lava dragon's health, giving the owner and indication of the lava dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a puff of smoke...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out an ashy cough...");
        }
        else
        {
            System.out.println(this.getPetName() + " let out a bellowing roar!...");
        }
    }
    
    /**
     * Provides the user with a description of the lava dragon pet.
     */
    @Override
    public String description()
    {
        return "\nLava Dragon\n-----------\nThe lava dragon is erupting with power! This ferocious dragon loves resting in volcanoes.\n";
    }
    
    /**
     * Allows the lava dragon pet to use it's special power. This will restore up to 8 energy points and 5 health points to the lava dragon pet. This power has three uses.
     */
    @Override
    public String power()
    {
        String powerDescription = "------------------\nPower: Solar Power\n------------------\nUses Remaining: " + (3 - this.getPowerCounter()) + "\nThe lava dragon spreads its wings to absorb solar energy and is able to recharge up to 8 energy points and restore up to 5 health points.\nWould you like to use this power?";
        return powerDescription;
            
    }
}
