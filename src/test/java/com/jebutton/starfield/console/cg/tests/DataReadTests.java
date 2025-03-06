package com.jebutton.starfield.console.cg.tests;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;

import com.jebutton.starfield.console.cg.datatypes.*;
import com.jebutton.starfield.console.cg.datahandlers.DataFileReader;

public class DataReadTests {

    DataFileReader sharedTestReader;
    /**
    * Counts the DLC flags based on desired value.
    * @param itemMap a Map<String, ItemType> with the items in it.
    * @param flag a boolean of whether you want it to check for DLC or not.
    * @return an integer of the number of items that match that value.
    */
    private int countDLCs (Map<String, ItemType> itemMap, boolean flag) {
        int countOfFlag = 0;
        for (Entry<String, ItemType> helmet : itemMap.entrySet()) {
            if (helmet.getValue().getDLCFlag() == flag) {
                countOfFlag++;
            }
        }
        return countOfFlag;
    }

    /**
    * Sets up a Shared instance of the DataFileReader class
    * so that tests that involve checking the data don't
    * cause performance hits. This happens when constantly
    * reading the Excel file when we don't care about
    * the state of the DataFileReader's code execution.
    */
    @BeforeGroups(groups = {"Space Suits", "Helmets"})
    private void setSharedTestReader() {
        this.sharedTestReader = new DataFileReader();
    }

    @Test(groups = { "Resources" })	
    public void verifyResourcesLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAResource("Argon").getItemName(), "Argon");
      }

    @Test(groups = { "Resources" })
    public void verifyResourcesCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testResources = testReader.getResources();
        
        Assert.assertEquals(testResources.size(), 107);
    }

    @Test(groups = { "Resources" })
    public void verifyResourcesSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testResources = testReader.getResources();
        Set<String> resourceKeys = testResources.keySet();
        
        Assert.assertEquals(resourceKeys.toArray()[0], "Adaptive Frame");
        Assert.assertEquals(resourceKeys.toArray()[54], "Microsecond Regulator");
        Assert.assertEquals(resourceKeys.toArray()[106], "Zero-G Gimbal");
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getItemName(), "Bounty Hunter Spacesuit");
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
        
        Assert.assertEquals(testSpaceSuits.size(), 65);
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
        Set<String> suitKeys = testSpaceSuits.keySet();
        
        Assert.assertEquals(suitKeys.toArray()[0], "Bounty Hunter Spacesuit");
        Assert.assertEquals(suitKeys.toArray()[35], "Starborn Spacesuit Solis");
        Assert.assertEquals(suitKeys.toArray()[64], "Zealot Spacesuit");
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getDLCFlag(), false);
    }

    @Test
    public void verifySpaceSuitsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Va'ruun Assault Spacesuit").getDLCFlag(), true);
    }
    
    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsDLCFlagFalseCount() {
	//Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> spaceSuits = this.sharedTestReader.getSpaceSuits();
        int countOfFalse = this.countDLCs(spaceSuits, false);
        
        Assert.assertEquals(countOfFalse, 58);
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsDLCFlagTrueCount() {
	//Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> spaceSuits = this.sharedTestReader.getSpaceSuits();
        int countOfTrue = this.countDLCs(spaceSuits, true);
        
        Assert.assertEquals(countOfTrue, 7);
    }

    @Test(groups = { "Space Suits" })
    public void verifyHelmetsCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testHelmets = testReader.getHelmets();

        Assert.assertEquals(testHelmets.size(), 48);
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testHelmets = testReader.getHelmets();
        Set<String> helmetKeys = testHelmets.keySet();
        
        Assert.assertEquals(helmetKeys.toArray()[0], "Bounty Hunter Space Helmet");
        Assert.assertEquals(helmetKeys.toArray()[24], "Pirate Sniper Space Helmet");
        Assert.assertEquals(helmetKeys.toArray()[47], "Zealot Helmet");
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAHelmet("Bounty Hunter Space Helmet").getDLCFlag(), false);
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        Assert.assertEquals(testReader.getAHelmet("Valrak's Battle Helmet").getDLCFlag(), true);
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagFalseCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> helmets = this.sharedTestReader.getHelmets();
        int countOfFalse = this.countDLCs(helmets, false);
        
        Assert.assertEquals(countOfFalse, 41);
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagTrueCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> helmets = this.sharedTestReader.getHelmets();
        int countOfTrue = this.countDLCs(helmets, true);
        
        Assert.assertEquals(countOfTrue, 7);
    }
}