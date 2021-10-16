package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class LavaDragon extends FireDragon
{
    
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
     * Provides the user with a description of the lava dragon pet.
     */
    @Override
    public String description()
    {
        return "\nLava Dragon\n-----------\nThe lava dragon is erupting with power! This ferocious dragon loves resting in volcanoes.\n";
    }
    
    /**
     * Describes the lava dragon's pet power and its remaining uses.
     */
    @Override
    public String power()
    {
        String powerDescription =
                "----------------------------------------\n"
                + "Power: Solar Power\n"
                + "--------------------------------------\n\n"
                + "> Uses Remaining: " + (3 - this.getPowerCounter())
                + "\n\n> Description: Recharge up to 8 energy points and restore up to 5 health points.";
        return powerDescription;
    }
    
    /**
     * Uses the lava dragon's unique power.
     * @return returns true if the power is successfully used.
     */
    @Override
    public boolean usePower()
    {
        if(this.getPowerCounter() != 3)
        {
            if(this.getHealth() != 100 || this.getEnergy() != 10)
            {
                this.setHealth(this.getHealth() + 5);
                this.setEnergy(this.getEnergy() + 8);
                this.setPowerCounter(this.getPowerCounter() + 1);
                return true;
            }
            return false;
        }
        return false;
    }
}
