package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class EarthDragon extends Pet
{
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
     * Provides the user with a description of the earth dragon pet.
     */
    @Override
    public String description()
    {
        return "Earth Dragon - The earth dragon has a relaxed nature but is very energetic! This dragon loves to RUN.";
    }
    
    /**
     * Describes the earth dragon's pet power and the remaining power uses.
     */
    @Override
    public String power()
    {
        String powerDescription =
                "------------------\n"
                + "Power: Quake\n"
                + "------------------\n\n"
                + "> Uses Remaining: " + (3 - this.getPowerCounter())
                + "\n\n> Description: Instantly restore up to 20 health points.";
        return powerDescription;
    }
    
    /**
     * Uses the earth dragon's unique power.
     * @return returns true if the power was successfully used.
     */
    @Override
    public boolean usePower()
    {
        if(this.getPowerCounter() != 3)
        {
            if(this.getHealth() != 100)
            {
                this.setHealth(this.getHealth() + 20);
                this.setPowerCounter(this.getPowerCounter() + 1);
                return true;
            }
            return false;
        }
        return false;
    }
}
