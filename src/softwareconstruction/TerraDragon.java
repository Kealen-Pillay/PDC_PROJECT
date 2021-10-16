package softwareconstruction;


/**
 *
 * @author kealenpillay
 */
public class TerraDragon extends EarthDragon
{
    
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
     * Provides the user with a description of the terra dragon pet.
     */
    @Override
    public String description()
    {
        return "\nTerra Dragon\n------------\nThe terra dragon is a tough beast of nature that has incredible speed! This dragon loves to hunt.\n";
    }
    
    /**
     * Describes the terra dragon's pet power and the remaining uses. A string is returned with the description.
     * @return returns a string containing the pet's power.
     */
    @Override
    public String power()
    {
        String powerDescription =
                "-----------------------------------\n"
                + "Power: Healing Nature\n"
                + "---------------------------------\n\n"
                + "> Uses Remaining: " + (3 - this.getPowerCounter())
                + "\n\n> Description: Instantly restore up to 50 health points.";
        return powerDescription;
    }
    
    /**
     * Uses the terra dragon's unique power.
     * @return returns true if the power is successfully used.
     */
    @Override
    public boolean usePower()
    {
        if(this.getPowerCounter() != 3)
        {
            if(this.getHealth() != 100)
            {
                this.setHealth(this.getHealth() + 50);
                this.setPowerCounter(this.getPowerCounter() + 1);
                return true;
            }
            return false;
        }
        return false;
    }
}
