package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public interface PetBehaviour 
{   
    /**
     * Method which outputs the pet's unique roar depending on how much health the pet has, giving the owner and indication of the pet's condition.
     */
    public void roar();
    
    /**
     * Method which enters the pet into a random race. The owner receives $100 if the pet wins the race. The type of race is randomly decided. After a race is decided, a random number is generated between 1-10, and if the pet's skill in the current race is equal/less than the random number the pet wins the race.
     * @param owner 
     */
    public String race(Owner owner);
    
    /**
     * Method which uses the pet's unique power. The power varies depending on the type of pet.
     */
    public String power();
    
}
