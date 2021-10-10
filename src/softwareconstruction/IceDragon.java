package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class IceDragon extends WaterDragon
{
    
    //---------------------------------------- Constructor ----------------------------------------------
    
    /**
     * Multiple input parameter constructor for Ice Dragon.
     * @param petName represents the pet's name.
     * @param health represents the pet's health.
     * @param energy represents the pet's energy.
     * @param swimming represents the pet's swimming skill.
     * @param speed represents the pet's speed skill.
     * @param flight represents the pet's flight skill.
     */
    public IceDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //--------------------------------------- Methods ---------------------------------------------------
    
    /**
     * Provides the user with a description of the ice dragon pet.
     */
    @Override
    public String description()
    {
        return "\nIce Dragon\n------------\nThe ice dragon is an ice cold killer! This dragon loves to freeze it's prey.\n";
    }
    
    /**
     * Describes the ice dragon's pet power and its remaining uses.
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
