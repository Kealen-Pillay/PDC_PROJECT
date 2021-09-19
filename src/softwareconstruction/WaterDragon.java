package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class WaterDragon extends Pet
{
    //------------------------------------ Static Variable --------------------------------
    private int powerCounter;
    
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
     * Returns the number of uses of the water dragon's power.
     * @return the number of uses of the water dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the water dragon's power.
     * @param powerCounter represents the number of uses of the water dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
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
    public void description()
    {
        System.out.println("Water Dragon\n------------\nThe water dragon is cheerful and loyal! This dragon loves to swim.\n");
    }
    
    /**
     * Allows the water dragon pet to use it's special power. This will restore up to 10 health points and up to 2 energy points to the water dragon pet. This power has three uses.
     */
    @Override
    public void power()
    {
        if(this.getPowerCounter() != 3)
        {
            Scanner keyboard = new Scanner(System.in);
            String usePower = "";
            System.out.println("-------------");            
            System.out.println("Power: Deluge");
            System.out.println("-------------");
            System.out.println("Uses Remaining: " + (3 - this.getPowerCounter()));
            System.out.println("Description: The water dragon showers itself with healing rapids that will instantly restore up to 10 health points and 2 energy points.");
            System.out.println("Would you like to use this power (yes/no):");
            
            while(true)
            {
                usePower = keyboard.next();
                
                if(usePower.toLowerCase().equals("yes"))
                {
                    if(this.getEnergy() == 10 && this.getHealth() == 100)
                    {
                        System.out.println("Power cannot be used as " + this.getPetName() + "'s health and energy is full.");
                        break;
                    }
                    else
                    {
                        this.setEnergy(this.getEnergy() + 2);
                        this.setHealth(this.getHealth() + 10);
                        this.setPowerCounter(this.getPowerCounter() + 1);
                        System.out.println("Power Used: " + this.getPowerCounter() + "/3");
                        break;
                    }
                }
                else if(usePower.toLowerCase().equals("no"))
                {
                    System.out.println("Power Used: " + this.getPowerCounter() + "/3");
                    break;
                }
                else
                {
                    System.out.println("Invalid Input! Try Again. Enter 'yes' or 'no' only.");
                }
            }
        }
        else
        {
            System.out.println("You have no more power.");
            System.out.println("Power Used: " + this.getPowerCounter() + "/3");
        }
    }
}
