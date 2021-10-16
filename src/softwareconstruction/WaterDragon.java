package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class WaterDragon extends Pet
{
    
    //------------------------------------- Constructor ----------------------------------
    
    /**
     * Multiple input parameter constructor for the water dragon pet.
     * @param petName represents the water dragon's name.
     * @param health represents the water dragon's health.
     * @param energy represents the water dragon's energy.
     * @param swimming represents the water dragon's swimming skill.
     * @param speed represents the water dragon's speed skill.
     * @param flight represents the water dragon's flight skill.
     */
    public WaterDragon(String petName, int health, int energy, int swimming, int speed, int flight)
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //------------------------------------- Methods ----------------------------------
    
    /**
     * Provides the user with a description of the water dragon pet.
     */
    @Override
    public String description()
    {
        return "Water Dragon - The water dragon is cheerful and loyal! This dragon loves to SWIM.";
    }
    
    /**
     * Describes the water dragon's pet power and its remaining uses. Returns a string with the pet's power and remaining uses.
     * @return returns a string containing the pet's power and its remaining uses.
     */
    @Override
    public String power()
    {
        String powerDescription =
                "        ------------------------\n"
                + "        Power: Deluge\n"
                + "        ------------------------\n"
                + "        > Uses Remaining: " + (3 - this.getPowerCounter()) + "\n\n"
                + "        > Description: Instantly restores up to 10 health points and 2 energy points.";
        return powerDescription;
    }
    
    /**
     * Uses the water dragon's unique power.
     * @return returns true if the power was successfully used.
     */
    @Override
    public boolean usePower()
    {
        if(this.getPowerCounter() != 3)
        {
            if(this.getHealth() != 100 || this.getEnergy() != 10)
            {
                this.setHealth(this.getHealth() + 10);
                this.setEnergy(this.getEnergy() + 2);
                this.setPowerCounter(this.getPowerCounter() + 1);
                return true;
            }
            return false;
        }
        return false;
    }
}
