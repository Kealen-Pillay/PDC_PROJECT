package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class IceDragon extends WaterDragon
{
    //---------------------------------- Static Variable ----------------------------------------
    private int powerCounter;
    
    //---------------------------------------- Constructor ----------------------------------------------
    
    /**
     * Multiple input parameter constructor for Ice Dragon.
     * @param petName
     * @param health
     * @param energy
     * @param swimming
     * @param speed
     * @param flight 
     */
    public IceDragon(String petName, int health, int energy, int swimming, int speed, int flight) 
    {
        super(petName, health, energy, swimming, speed, flight);
        this.setPowerCounter(0);
    }
    
    //--------------------------------------- Methods ---------------------------------------------------
    
    /**
     * Returns the number of uses of the ice dragon's power.
     * @return the number of uses of the ice dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the number of uses of the ice dragon's power.
     * @param powerCounter represents the number of uses of the ice dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
     /**
     * Causes the ice dragon to roar. The type of roar varies depending on the ice dragon's health, giving the owner and indication of the ice dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a sneeze and a roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " let out a painful roar...");
        }
        else
        {
            System.out.println(this.getPetName() + " let out a frosty roar!...");
        }
    }
    
    /**
     * Provides the user with a description of the ice dragon pet.
     */
    @Override
    public String description()
    {
        return "\nIce Dragon\n------------\nThe ice dragon is an ice cold killer! This dragon loves to freeze it's prey.\n";
    }
    
    /**
     * Allows the ice dragon pet to use it's special power. This will restore up to 30 health points and up to 4 energy points to the ice dragon pet. This power has three uses.
     */
    @Override
    public void power()
    {
        if(this.getPowerCounter() != 3)
        {
            Scanner keyboard = new Scanner(System.in);
            String usePower = "";
            System.out.println("-----------------");            
            System.out.println("Power: Permafrost");
            System.out.println("-----------------");
            System.out.println("Uses Remaining: " + (3 - this.getPowerCounter()));
            System.out.println("Description: The ice dragon freezes itself over in order to heal up to 30 health points and restore up to 4 energy points.");
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
                        this.setEnergy(this.getEnergy() + 4);
                        this.setHealth(this.getHealth() + 30);
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
