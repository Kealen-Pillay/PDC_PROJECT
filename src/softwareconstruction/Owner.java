package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public class Owner implements OwnerActions
{
    //---------------------------------------- Instance Variables -----------------------------------------
    private String name;
    private int money;
    private int food;
    private int racesWon;
    private boolean maxPet;
    
    //----------------------------------------- Constructor ----------------------------------------------
    
    /**
     * Single input parameter constructor.
     * @param name represents the player's username.
     * @param money represents the player's money.
     * @param food represents the amount of food the play has.
     * @param racesWon represents the number of races won by the player.
     * @param maxed represents whether the player owns a fully evolved pet.
     */
    public Owner(String name, int money, int food, int racesWon, boolean maxed) 
    {
        this.name = name;
        this.setMoney(money);
        this.setFood(food);
        this.setRacesWon(racesWon);
        this.setMaxPet(maxed);
    }
    
   
    //----------------------------------------- Getters and Setters ----------------------------------------
    
    /**
     * Returns the owner's name.
     * @return the name
     */
    public String getName() 
    {
        return this.name;
    }

    /**
     * Sets the owner's name.
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Returns the owner's money amount.
     * @return the money
     */
    public int getMoney() 
    {
        return this.money;
    }

    /**
     * Sets the owners money amount.
     * @param money the money to set
     */
    public void setMoney(int money) 
    {
        this.money = money;
    }

    /**
     * Returns the amount of food the owner has.
     * @return the food
     */
    public int getFood() 
    {
        return this.food;
    }

    /**
     * Sets the amount of food the owner has.
     * @param food the food to set
     */
    public void setFood(int food) 
    {
        this.food = food;
    }

    /**
     * Returns the number of races won by the owner's pet.
     * @return the racesWon
     */
    public int getRacesWon() 
    {
        return this.racesWon;
    }

    /**
     * Sets the number of races won by the owner's pet.
     * @param racesWon the racesWon to set
     */
    public void setRacesWon(int racesWon) 
    {
        this.racesWon = racesWon;
    }
    
    /**
     * Returns true if the owner's pet is fully evolved. True indicates the pet is fully evolved.
     * @return returns a Boolean value representing the pet's evolution state. True is returned if the owner's pet is fully evolved, otherwise false is returned.
     */
    public boolean getMaxPet()
    {
        return this.maxPet;
    }
    
    /**
     * Sets the evolution status of the owner's pet.
     * @param maxPet represents whether the owner's pet is fully evolved.
     */
    public void setMaxPet(boolean maxPet)
    {
        this.maxPet = maxPet;
    }
    
    //-------------------------------------------- Methods --------------------------------
    
    /**
     * Allows the owner to feed their pet.
     * @param pet represents the owner's pet.
     */
    public void feed(Pet pet)
    {          
        if(this.getFood() > 0)
        {
            if(pet.getEnergy() < 10 && pet.getHealth() == 100)
            {
                pet.setEnergy(pet.getEnergy() + 1);
                this.setFood(this.getFood() - 1);
            }
            else if(pet.getHealth() < 100 && pet.getEnergy() == 10)
            {
                pet.setHealth(pet.getHealth() + 5);
                this.setFood(this.getFood() - 1);
            }
            else if((pet.getEnergy() < 10) && (pet.getHealth() < 100))
            {
                pet.setEnergy(pet.getEnergy() + 1);                
                this.setFood(this.getFood() - 1);
            }                    
        }   
    }
    
    /**
     * Returns a string representation of the owner.
     * @return a string representation of an owner object.
     */
    public String toString()
    {
        return "Player: " + this.getName() + " | Money: $" + this.getMoney() + " | Food: " + this.getFood() +  " | Races Won: " + this.getRacesWon() + "\n";
    }
}
