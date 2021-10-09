package softwareconstruction;

/**
 *
 * @author kealenpillay
 */
public interface PetBehaviour 
{       
    /**
     * Method which enters the pet into a random race. The owner receives $100 if the pet wins the race. The type of race is randomly decided. After a race is decided, a random number is generated between 1-10, and if the pet's skill in the current race is equal/less than the random number the pet wins the race.A string is returned containing the result of the race.
     * @param owner represents the owner of the pet.
     * @return returns a string containing the result of the race.
     */
    public String race(Owner owner);
    
    /**
     * Describes the pet's unique power and its remaining uses. The power varies depending on the type of pet.
     */
    public String power();
    
}
