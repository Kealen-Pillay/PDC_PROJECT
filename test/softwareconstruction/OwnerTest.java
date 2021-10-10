/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author kealenpillay
 */
public class OwnerTest {
    
    public OwnerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of feed method, of class Owner.
     */
    @Test
    public void testFeedWhenHealthAndEnergyFull() {
        System.out.println("feed");
        Pet pet = new WaterDragon("Test", 100, 10, 6, 4, 2);
        Owner instance = new Owner("Test", 0, 1, 0, false);
        instance.feed(pet);
        int expectedHealth = 100;
        int expectedEnergy = 10;
        int expectedFood = 1;
        int actualHealth = pet.getHealth();
        int actualEnergy = pet.getEnergy();
        int actualFood = instance.getFood();
        Assert.assertEquals(expectedHealth, actualHealth);
        Assert.assertEquals(expectedEnergy, actualEnergy);
        System.out.println("Expected Health: " + expectedHealth + " Actual Health: " + actualHealth);
        System.out.println("Expected Energy: " + expectedEnergy + " Actual Energy: " + actualEnergy);
        System.out.println("Expected Food: " + expectedFood + " Actual Food: " + actualFood);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of feed method, of class Owner.
     */
    @Test
    public void testFeedWhenEnergyNotFullAndHealthNotFull() {
        System.out.println("feed");
        Pet pet = new WaterDragon("Test", 87, 8, 6, 4, 2);
        Owner instance = new Owner("Test", 0, 1, 0, false);
        instance.feed(pet);
        int expectedHealth = 87;
        int expectedEnergy = 9;
        int actualHealth = pet.getHealth();
        int actualEnergy = pet.getEnergy();
        Assert.assertEquals(expectedHealth, actualHealth);
        Assert.assertEquals(expectedEnergy, actualEnergy);
        System.out.println("Expected Health: " + expectedHealth + " Actual Health: " + actualHealth);
        System.out.println("Expected Energy: " + expectedEnergy + " Actual Energy: " + actualEnergy);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of feed method, of class Owner.
     */
    @Test
    public void testFeedWhenEnergyFullAndHealthNotFull() {
        System.out.println("feed");
        Pet pet = new WaterDragon("Test", 85, 10, 6, 4, 2);
        Owner instance = new Owner("Test", 0, 1, 0, false);
        instance.feed(pet);
        int expectedHealth = 90;
        int expectedEnergy = 10;
        int actualHealth = pet.getHealth();
        int actualEnergy = pet.getEnergy();
        Assert.assertEquals(expectedHealth, actualHealth);
        Assert.assertEquals(expectedEnergy, actualEnergy);
        System.out.println("Expected Health: " + expectedHealth + " Actual Health: " + actualHealth);
        System.out.println("Expected Energy: " + expectedEnergy + " Actual Energy: " + actualEnergy);
        // TODO review the generated test code and remove the default call to fail.
    }

    
}
