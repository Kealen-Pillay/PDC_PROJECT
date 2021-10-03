package softwareconstruction;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.InputMismatchException;
import java.util.Random;

/**
 *
 * @author kealenpillay
 */
public class OldMain
{
    /**
     * Presents the player with instructions of how the game and game mechanics work.
     */
    public static void instructions()
    {
        try
        {
            BufferedReader instructions = new BufferedReader(new FileReader("Instructions.txt"));
            String line = null;
            
            while((line = instructions.readLine()) != null)
            {
                System.out.println(line);
            }
            System.out.print("\n");
            instructions.close();
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch(IOException e)
        {
            System.out.println("IO Exception.");
        }
    }
    
    /**
     * Start screen which allows the user to pick the type of pet they want and the name of the pet.
     * @return returns either a WaterDragon, EarthDragon or FireDragon pet object.
     */
    public static Pet startScreen()
    {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Pet> pets = new ArrayList<Pet> ();
        String line = null;
        int petChoice = 0;
        String petName = null;
        
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("petstats.txt"));
            
            while((line = inputStream.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    Pet p1 = new WaterDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p1);
                    
                    Pet p2 = new EarthDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p2);
                    
                    Pet p3 = new FireDragon(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                    pets.add(p3);
                }
                
                //  User selects pet.
                System.out.println("---------------------------------------------------------------------");
                System.out.println("                        CHOOSE A PET");
                System.out.println("---------------------------------------------------------------------");
                
                for(Pet p : pets)
                {
                    p.description();
                }
                System.out.println("Select a pet (Enter 1, 2 or 3)");
                
                while(true)
                {
                    try
                    {
                        System.out.println("(1) Water Dragon   (2) Earth Dragon    (3) Fire Dragon");
                        petChoice = keyboard.nextInt();
                        if(petChoice > 0 && petChoice < 4)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid Input! Select a number between the given range.");
                        }
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Invalid Input! Try Again. Please pick between the options provided.");
                        keyboard.next();
                    }
                }
                
                while(true)
                {
                    System.out.println("\nEnter Pet Name (Characters Only): ");
                    petName = keyboard.next();
                    boolean acceptable = false;
                    
                    
                    for(char c : petName.toCharArray())
                    {
                        if(Character.isLetter(c) || c == ' ')
                        {
                            acceptable = true;
                        }
                        else
                        {
                            System.out.println("Invalid Input! CHARACTERS ONLY. Try Again.");
                            acceptable = false;
                            break;
                        }
                    }
                    if(acceptable)
                    {
                        pets.get(petChoice - 1).setPetName(petName);
                        break;
                    }
                }
            }
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        catch(IOException ex)
        {
            System.out.println("IOException");
        }
        
        return pets.get(petChoice -1);
    }
    
    /**
     * Allows the user to earn food for their pet by answering basic math questions correctly. Math questions are randomly generated. Each correct question will increase the player's food amount by 1.
     * @param owner represents the owner of the pet.
     */
    public static void earnFood(Owner owner)
    {
        try
        {
            Scanner scan = new Scanner(System.in);
            Random r = new Random();
            BufferedReader input = new BufferedReader(new FileReader("MathQuestions.txt"));
            String line = null;
            String answer = null;
            int foodGained = 0;
            HashMap<String, Integer> questions = new HashMap<String, Integer> ();
            
            while((line = input.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    questions.put(st.nextToken(), Integer.parseInt(st.nextToken()));
                }
            }
            
            System.out.println("\n---------------------------------------------------------------\n");
            System.out.println("INSTRUCTIONS");
            System.out.println("------------");
            System.out.println("-  Earn food pellets by answering basic math questions correctly!");
            System.out.println("\n---------------------------------------------------------------\n");
            
            
            int num = r.nextInt(questions.size());
            int counter = 0;
            String question = null;
            int correctAnswer = 0;
            
            while(true)
            {
                for(HashMap.Entry<String, Integer> o : questions.entrySet())
                {
                    if(counter == num)
                    {
                        num = r.nextInt(questions.size());
                        counter = 0;
                        question = o.getKey();
                        correctAnswer = o.getValue();
                        System.out.print("QUESTION (Type 'stop' to quit): " + question + " ");
                        break;
                    }
                    else
                    {
                        counter++;
                    }
                }
                
                answer = scan.next();
                while(true)
                {
                    try
                    {
                        if(answer.toLowerCase().equals("stop"))
                        {
                            break;
                        }
                        if(Integer.parseInt(answer) == correctAnswer)
                        {
                            owner.setFood(owner.getFood() + 1);
                            foodGained++;
                            System.out.println("CORRECT!\n");
                            break;
                        }
                        else
                        {
                            System.out.println("INCORRECT!\n");
                            break;
                        }
                        
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Invalid Input! Try Again. Enter integers only or type 'stop' to quit.");
                        System.out.print("\nQUESTION (Type stop to quit): " + question + " ");
                        answer = scan.next();
                    }
                }
                
                if(answer.toLowerCase().equals("stop"))
                {
                    break;
                }
            }
            
            System.out.println("\nFood Gained: " + foodGained);
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch (IOException ex)
        {
            System.out.println("IOException");
        }
    };
    
    /**
     * Checks if the username the player has entered already exists. True is returned if the username already exists, otherwise the new username is added to list of existing usernames in external file and false is returned.
     * @param username represents the username to check.
     * @return returns a Boolean value. True is returned if the username already exists, otherwise false is returned.
     */
    public static boolean exists(String username)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("Usernames.txt"));
            String line = null;
            
            while((line = inputStream.readLine()) != null)
            {
                if(line.toLowerCase().equals(username.toLowerCase()))
                {
                    inputStream.close();
                    return true;
                }
            }
            inputStream.close();
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("Usernames.txt", true));
            outputStream.println(username.toUpperCase());
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
        
        return false;
    }
    
    /**
     * Reads from an external file which keeps track of how many times each type of pet has been selected. This can be used to implement balance changes in the future, if a type of pet is being selected by users more than others. Allows the developer to track the pet usage for each type in the game.
     * @param p represents the current player's selected pet. This is used to check the type of pet in order to update usage statistics.
     */
    public static void usageStats(Pet p)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("PetUsageStatistics.txt"));
            String line = null;
            HashMap<String, Integer> usages = new HashMap<String, Integer>();
            int usage = 0;
            while((line = inputStream.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens())
                {
                    usages.put(st.nextToken(), Integer.parseInt(st.nextToken()));
                }
            }
            inputStream.close();
            
            if(p instanceof WaterDragon)
            {
                usage = usages.get("WaterDragon");
                usage++;
                usages.put("WaterDragon", usage);
            }
            else if(p instanceof FireDragon)
            {
                usage = usages.get("FireDragon");
                usage++;
                usages.put("FireDragon", usage);
            }
            else
            {
                usage = usages.get("EarthDragon");
                usage++;
                usages.put("EarthDragon", usage);
            }
            
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("PetUsageStatistics.txt"));
            for(HashMap.Entry<String, Integer> entry : usages.entrySet())
            {
                outputStream.println(entry.getKey() + "," + entry.getValue());
            }
            outputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }
        catch(IOException e)
        {
            System.out.println("IO Exception.");
        }
    }
    
    /**
     * Allows the player to write a review after playing the game. All reviews are stored in an external text file.
     */
    public static void reviews()
    {
        try
        {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("Reviews.txt", true));
            Scanner keyboard = new Scanner(System.in);
            String answer = "";
            String review = null;
            System.out.println("Would you like to leave a review?");
            
            while(true)
            {
                answer = keyboard.nextLine();
                if(answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no"))
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid Input! Try Again. (yes / no) only.");
                }
            }
            
            if(answer.toLowerCase().equals("yes"))
            {
                System.out.println("Enter your review below (Press Enter Key to Submit Review):");
                review = keyboard.nextLine();
                outputStream.print(review + "\n");
                System.out.println("Thank you for leaving a review :)");
            }
            System.out.println("******************************************************** THANKS FOR PLAYING ********************************************************");
            outputStream.close();
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found.");
        }  
    }
    
    /**
     * Main
     */
    public static void main(String[] args)
    {
        instructions();
        
        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        String endGame = "no";
        
        System.out.println("Enter your username: ");
        String username = keyboard.next();
        
        while(exists(username))
        {
            System.out.println("USERNAME ALREADY EXISTS! Enter a new username: ");
            username = keyboard.next();
        }
        
        Owner player = new Owner(username, 0, 10, 0, false);
        Pet pet = startScreen();
        
        usageStats(pet);
        
        while(!pet.isDead())
        {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println(pet);
            System.out.println(player);
            System.out.println("What would you like to do?");
            System.out.println("(1) Feed Pet    (2) Race Pet    (3) Earn Food   (4) Evolve  (5) Use Pet Power   (6) End Game");
            
            while(true)
            {
                try
                {
                    option = keyboard.nextInt();
                    
                    if(option > 0 && option < 7)
                    {
                        if(option == 1)
                        {
                            player.feed(pet);
                            pet.roar();
                            break;
                        }
                        else if(option == 2)
                        {
                            pet.race(player);
                            pet.roar();
                            break;
                        }
                        else if(option == 3)
                        {
                            earnFood(player);
                            break;
                        }
                        else if(option == 4)
                        {
                            if(!player.getMaxPet())
                            {
                                System.out.println("--------------------------------------------");
                                System.out.println("EVOLUTION TABLE");
                                System.out.println("--------------------------------------------");
                                System.out.println("Water Dragon --> Ice Dragon");
                                System.out.println("Earth Dragon --> Terra Dragon");
                                System.out.println("Fire Dragon  --> Lava Dragon");
                                System.out.println("\nTo evolve your pet you must have:");
                                System.out.println("(1) $500");
                                System.out.println("(2) A minimum of 5 races won");
                                
                                if(player.getMoney() >= 500 && player.getRacesWon() >= 5)
                                {
                                    System.out.println("Would you like to evolve " + pet.getPetName() + " (yes/no) - (This will deduct $500 from your money)");
                                    
                                    while(true)
                                    {
                                        String evolvePet = keyboard.next();
                                        
                                        if(evolvePet.toLowerCase().equals("yes"))
                                        {
                                            player.setMoney(player.getMoney() - 500);
                                            if(pet instanceof WaterDragon)
                                            {
                                                pet = new IceDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 3, pet.getSpeed() + 3, pet.getFlight() + 3);
                                                System.out.println("CONGRATULATION!!! " + pet.getPetName() + " has evolved into an Ice Dragon and has become more skilled.");
                                            }
                                            else if(pet instanceof EarthDragon)
                                            {
                                                pet = new TerraDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 3, pet.getSpeed() + 3, pet.getFlight() + 3);
                                                System.out.println("CONGRATULATION!!! " + pet.getPetName() + " has evolved into a Terra Dragon and has become more skilled.");
                                            }
                                            else
                                            {
                                                pet = new LavaDragon(pet.getPetName(), pet.getHealth(), pet.getEnergy(), pet.getSwimming() + 3, pet.getSpeed() + 3, pet.getFlight() + 3);
                                                System.out.println("CONGRATULATION!!! " + pet.getPetName() + " has evolved into a Lava Dragon and has become more skilled.");
                                            }
                                            pet.description();
                                            player.setMaxPet(true);
                                            break;
                                        }
                                        else if(evolvePet.toLowerCase().equals("no"))
                                        {
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
                                    System.out.println("YOU DO NOT MEET THE CONDITIONS TO EVOLVE YOUR PET.");
                                }
                            }
                            else
                            {
                                System.out.println("Your pet is already in it's final evolution state.");
                            }
                            break;
                        }
                        else if(option == 5)
                        {
                            pet.power();
                            pet.roar();
                        }
                        else
                        {
                            System.out.println("Are you sure you wish to end the game (yes / no)?\n(WARNING: This action cannot be undone and progress cannot be recovered)");
                            while(true)
                            {
                                endGame = keyboard.next();
                                if(endGame.toLowerCase().equals("yes"))
                                {
                                    System.out.println("Thank you for playing.");
                                    break;
                                }
                                else if(endGame.toLowerCase().equals("no"))
                                {
                                    break;
                                }
                                else
                                {
                                    System.out.println("Invalid Input! Try Again. Enter 'yes' or 'no' only.");
                                }
                            }                          
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Input! Enter a number within the given range.");
                    }
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Invalid Input! Try Again. Please select from the options provided.");
                    keyboard.next();
                }
                break;
            }
            if(endGame.toLowerCase().equals("yes"))
            {
                break;
            }
        }
        if(pet.isDead())
        {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println(pet.getPetName() + " HAS DIED..... GAME OVER!");
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
        Highscores hs = new Highscores(player.getName(), player.getRacesWon());
        hs.writeScores();
        
        
        System.out.print("\n");
        reviews();
    }
}