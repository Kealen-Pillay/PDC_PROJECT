/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareconstruction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kealenpillay
 */
public class ModelTest {
    
    public ModelTest() {
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
     * Test of usePower method, of class Model.
     */
    @Test
    public void testUsePowerWhenHealthAndEnergyFull() {
        System.out.println("usePower");
        Model instance = new Model();
        instance.setPet(new WaterDragon("Test", 100, 10, 6, 4, 2));
        instance.setOwner(new Owner("Test", 0, 0, 0, false));
        instance.usePower();
        int expectedHealth = 100;
        int expectedEnergy = 10;
        int expectedPowerCounter = 0;
        int actualHealth = instance.getPet().getHealth();
        int actualEnergy = instance.getPet().getEnergy();
        int actualPowerCounter = instance.getPet().getPowerCounter();
        Assert.assertEquals(expectedHealth, actualHealth);
        Assert.assertEquals(expectedEnergy, actualEnergy);
        Assert.assertEquals(expectedPowerCounter, actualPowerCounter);
        System.out.println("Expected Health: " + expectedHealth + " Actual Health: " + actualHealth + " Expected Energy: " + expectedEnergy + " Actual Energy: " + actualEnergy + " Expected Power Counter: " + expectedPowerCounter + " Actual Power Counter: " + actualPowerCounter);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of usePower method, of class Model.
     */
    @Test
    public void testUsePowerWhenAllPowerUsesAreUsed() {
        System.out.println("usePower");
        Model instance = new Model();
        instance.setPet(new WaterDragon("Test", 90, 8, 6, 4, 2));
        instance.getPet().setPowerCounter(3);
        instance.setOwner(new Owner("Test", 0, 0, 0, false));
        instance.usePower();
        int expectedHealth = 90;
        int expectedEnergy = 8;
        int actualHealth = instance.getPet().getHealth();
        int actualEnergy = instance.getPet().getEnergy();
        Assert.assertEquals(expectedHealth, actualHealth);
        Assert.assertEquals(expectedEnergy, actualEnergy);
        System.out.println("Expected Health: " + expectedHealth + " Actual Health: " + actualHealth + " Expected Energy: " + expectedEnergy + " Actual Energy: " + actualEnergy);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of evolvePet method, of class Model.
     */
    @Test
    public void testEvolvePetWhenOwnerDoesntMeetRequirements() {
        System.out.println("evolvePet");
        Model instance = new Model();
        instance.setPet(new IceDragon("Test", 100, 10, 8, 6, 4));
        instance.setOwner(new Owner("Test", 0, 0, 0, false));
        boolean actual = instance.evolvePet();
        assertFalse(actual);
        System.out.println("Expected Evolved Successfully?: false  Actual Evolved Successfully?: " +  actual);
        // TODO review the generated test code and remove the default call to fail.
    }

    
}
