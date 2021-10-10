package softwareconstruction;

import java.util.Random;

/**
 *
 * @author kealenpillay
 */
public abstract class Pet implements PetBehaviour
{
    //--------------------------------- Instance Variables -----------------------------
    private String petName;
    private int health;
    private int energy;
    private int swimming;
    private int speed;
    private int flight;
    private boolean alive;
    private int powerCounter;
    
    //---------------------------------- Constructor --------------------------------------
    
    /**
     * Multiple input parameter constructor for a pet.
     * @param petName represents the name of the pet.
     * @param health represents the pet's health.
     * @param energy represents the pet's energy.
     * @param swimming represents the pet's swimming skill.
     * @param speed represents the pet's speed skill.
     * @param flight represents the pet's flight skill.
     */
    public Pet(String petName, int health, int energy, int swimming, int speed, int flight)
    {
        this.setPetName(petName);
        this.setHealth(health);
        this.setEnergy(energy);
        this.setSwimming(swimming);
        this.setSpeed(speed);
        this.setFlight(flight);
        this.setPowerCounter(0);
        this.setAlive(true);
    }
    
    //---------------------------------- Getters and Setters --------------------------------
    
    /**
     * Returns the pet's name.
     * @return the pet's name.
     */
    public String getPetName()
    {
        return this.petName;
    }
    
    /**
     * Sets the pet's name.
     * @param petName represents the pet's name to set.
     */
    public void setPetName(String petName)
    {
        this.petName = petName;
    }
    
    /**
     * Returns the pet's health.
     * @return the pet's health.
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Sets the pet's health.
     * @param health represents the pet's health to set.
     */
    public void setHealth(int health)
    {
        if(health > 100)
        {
            this.health = 100;
        }
        else if(health < 0)
        {
            this.health = 0;
            this.setAlive(false);
        }
        else
        {
            this.health = health;
        }
    }
    
    /**
     * Returns the pet's energy.
     * @return the pet's energy.
     */
    public int getEnergy()
    {
        return this.energy;
    }
    
    /**
     * Sets the pet's energy.
     * @param energy represents the pet's energy to set.
     */
    public void setEnergy(int energy)
    {
        if(energy > 10)
        {
            this.energy = 10;
        }
        else if(energy < 0)
        {
            this.energy = 0;
        }
        else
        {
            this.energy = energy;
        }
    }
    
    /**
     * Returns the pet's swimming skill.
     * @return the pet's swimming skill.
     */
    public int getSwimming()
    {
        return this.swimming;
    }
    
    /**
     * Sets the pet's swimming skill.
     * @param swimming represents the pet's swimming skill to set.
     */
    public void setSwimming(int swimming)
    {
        this.swimming = swimming;
    }
    
    /**
     * Returns the pet's speed skill.
     * @return the pet's speed skill.
     */
    public int getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Sets the pet's speed skill.
     * @param speed represents the pet's speed skill to set.
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    /**
     * Returns the pet's flight skill.
     * @return the pet's flight skill.
     */
    public int getFlight()
    {
        return this.flight;
    }
    
    /**
     * Sets the pet's flight skill.
     * @param flight represents the pet's flight skill to set.
     */
    public void setFlight(int flight)
    {
        this.flight = flight;
    }
    
    /**
     * Returns the state of the pet.
     * @return returns the state of the pet. true is returned if the pet is alive.
     */
    private boolean getAlive()
    {
        return this.alive;
    }
    
    /**
     * Sets the state of the pet.
     * @param alive represents the state of the pet to be set (state is set to true if the pet is alive).
     */
    private void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    
    /**
     * @return the powerCounter
     */
    public int getPowerCounter()
    {
        return powerCounter;
    }
    
    /**
     * @param powerCounter the powerCounter to set
     */
    public void setPowerCounter(int powerCounter)
    {
        this.powerCounter = powerCounter;
    }
    
    //----------------------------------------------- Methods --------------------------------------------------------
    
    /**
     * Abstract method which is implemented by subclasses of Pet Class. Method which provides the user with a description of the pet.
     */
    public abstract String description();
    
    /**
     * Method which describes the pet's unique power and its remaining uses. The power varies depending on the type of pet.
     */
    public abstract String power();
    
    /**
     * Checks if the pet is alive and returns a Boolean value.
     * @return returns the state of whether the pet is dead or alive. (true is returned if the the pet is dead.)
     */
    public boolean isDead()
    {
        if(this.getAlive())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Enters the pet into a race. A random race is chosen. Winning a race results in the owner receiving $100. If the random number generated between 1-10 is less than/equal to the pet's skill, the pet wins. A string is returned with the result.
     * @param owner represents the owner of the pet.
     * @return returns a string of the result of the race.
     */
    public String race(Owner owner)
    {
        String[] races = new String[] {"SWIMMING", "SPEED", "FLIGHT"};
        Random randomizer = new Random();
        int raceOption = randomizer.nextInt(3);
        String result = "";
        
        
        if(raceOption == 0)
        {
            if((randomizer.nextInt(9) + 1) <= this.getSwimming())
            {
                result = "WON THE " + races[raceOption] + " RACE!";
                owner.setMoney(owner.getMoney() + 100);
                owner.setRacesWon(owner.getRacesWon() + 1);
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
            else
            {
                result = "LOST THE " + races[raceOption] + " RACE!";
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
        }
        else if(raceOption == 1)
        {
            if((randomizer.nextInt(9) + 1) <= this.getSpeed())
            {
                result = "WON THE " + races[raceOption] + " RACE!";
                owner.setMoney(owner.getMoney() + 100);
                owner.setRacesWon(owner.getRacesWon() + 1);
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
            else
            {
                result = "LOST THE " + races[raceOption] + " RACE!";
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
        }
        else
        {
            if((randomizer.nextInt(9) + 1) <= this.getFlight())
            {
                result = "WON THE " + races[raceOption] + " RACE!";
                owner.setMoney(owner.getMoney() + 100);
                owner.setRacesWon(owner.getRacesWon() + 1);
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
            else
            {
                result = "LOST THE " + races[raceOption] + " RACE!";
                this.setHealth(this.getHealth() - (randomizer.nextInt(20) + 1));
                this.setEnergy(this.getEnergy() - 3);
            }
        }
        
        return result;
    }
    
    /**
     * Returns a string representation of the pet.
     * @return a string representation of the pet.
     */
    public String toString()
    {
        return "Pet Name:" + this.getPetName() + " | Health:" + this.getHealth() + " | Energy:" + this.getEnergy() + " | Swimming:" + this.getSwimming() + "/10  | Speed:" + this.getSpeed() + "/10  | Flight:" + this.getFlight() + "/10";
    }
    
    
}
