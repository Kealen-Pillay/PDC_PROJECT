package softwareconstruction;

import java.util.Scanner;

/**
 *
 * @author kealenpillay
 */
public class EarthDragon extends Pet
{
    //---------------------------------- Static Variable --------------------------------
    private int powerCounter;
    
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
     * Returns the number of uses of the earth dragon's power.
     * @return the number of uses of the earth dragon's power.
     */
    public int getPowerCounter()
    {
        return this.powerCounter;
    }
    
    /**
     * Sets the numbers of uses of the earth dragon's power.
     * @param powerCounter represents the number of uses of the earth dragon's power.
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
    /**
     * Causes the earth dragon to roar. The type of roar varies depending on the earth dragon's health, giving the owner and indication of the earth dragon's health.
     */
    @Override
    public void roar()
    {
        if(this.getHealth() <= 50 && this.getHealth() > 25)
        {
            System.out.println(this.getPetName() + " let out a slow roar...");
        }
        else if(this.getHealth() <= 25)
        {
            System.out.println(this.getPetName() + " is whimpering...");
        }
        else
        {
           System.out.println(this.getPetName() + " let out a proud roar!..."); 
        }  
    }
    
    /**
     * Provides the user with a description of the earth dragon pet.
     */
    @Override
    public String description()
    {
        return "Earth Dragon - The earth dragon has a relaxed nature but is very energetic! This dragon loves to run.";
    }
    
    /**
     * Allows the earth dragon pet to use it's special power. This will restore up to 20 health points to the earth dragon pet. This power has three uses.
     */
    @Override
    public void power()
    {  
        if(this.getPowerCounter() != 3)
        {
            Scanner keyboard = new Scanner(System.in);
            String usePower = "";
            System.out.println("------------");
            System.out.println("Power: Quake");
            System.out.println("------------");
            System.out.println("Uses Remaining: " + (3 - this.getPowerCounter()));
            System.out.println("Description: The earth dragon channels the power of the Earth to instantly restore up to 20 health points.");
            System.out.println("Would you like to use this power (yes/no):");
            
            while(true)
            {
                usePower = keyboard.next();
                
                if(usePower.toLowerCase().equals("yes"))
                {
                    if(this.getHealth() == 100)
                    {
                        System.out.println("Power cannot be used as " + this.getPetName() + "'s health is full.");
                        break;
                    }
                    else
                    {
                        this.setHealth(this.getHealth() + 20);
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
            System.out.println("You are out of power.");
            System.out.println("Power Used: " + this.getPowerCounter() + "/3");
        }    
    }
}
