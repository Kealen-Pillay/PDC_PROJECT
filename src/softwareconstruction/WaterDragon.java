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
     * Causes the water dragon to roar. The type of roar varies depending on the water dragon's health, giving the owner and indication of the water dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a stubborn roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out a tearful roar...");
        }
        else
        {
            System.out.println(this.getPetName() + " let out a chirp!...");
        }
    }
    
    /**
     * Provides the user with a description of the water dragon pet.
     */
    @Override
    public String description()
    {
        return "Water Dragon - The water dragon is cheerful and loyal! This dragon loves to SWIM.";
    }
    
    /**
     * Allows the water dragon pet to use it's special power. This will restore up to 10 health points and up to 2 energy points to the water dragon pet. This power has three uses.
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
}
