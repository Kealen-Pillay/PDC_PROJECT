package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class FireDragon extends Pet
{
    
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
     *  Provides the user with a description of the fire dragon pet.
     */
    @Override
    public String description()
    {
        return "Fire Dragon - The fire dragon has a fiery temper and is very mischievous! This dragon loves to FLY.";
    }
    
    /**
     * Describes the fire dragon's unique pet power and its remaining uses.
     */
    @Override
    public String power()
    {
        String powerDescription =
                "--------------------\n"
                + "Power: Inferno\n"
                + "--------------------\n\n"
                + "> Uses Remaining: " + (3 - this.getPowerCounter())
                + "\n\n> Description: Instantly restore up to 5 energy points.";
        return powerDescription;
    }
    
    /**
     * Uses the fire dragon's unique power.
     * @return returns true if the power was successfully used.
     */
    @Override
    public boolean usePower()
    {
        if(this.getPowerCounter() != 3)
        {
            if(this.getEnergy() != 10)
            {
                this.setEnergy(this.getEnergy() + 5);
                this.setPowerCounter(this.getPowerCounter() + 1);
                return true;
            }
            return false;
        }
        return false;
    }
}
