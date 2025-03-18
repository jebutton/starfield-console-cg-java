package com.jebutton.starfield.console.cg.tests;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int countDLCs(Map<String, ItemType> itemMap, boolean flag) {
        int countOfFlag = 0;
        for (Entry<String, ItemType> item : itemMap.entrySet()) {
            if (item.getValue().getDLCFlag() == flag) {
                countOfFlag++;
            }
        }
        return countOfFlag;
    }
    
    /**
    * Counts the DLC flags based on desired value for the SpaceSuitSets
    * @param spaceSuitSetsMap a Map<String, SpaceSuitSet> with the items in it.
    * @param flag a boolean of whether you want it to check for DLC or not.
    * @return an integer of the number of items that match that value.
    */
    private int countDLCSpaceSuitSets(Map<String, SpaceSuitSet> spaceSuitSetsMap, boolean flag) {
        int countOfFlag = 0;
        for (Entry<String, SpaceSuitSet> item : spaceSuitSetsMap.entrySet()) {
            if (item.getValue().getDLCFlag() == flag) {
                countOfFlag++;
            }
        }
        return countOfFlag;
    }

    /**
     * Checks to see if any Ids are unprocessed.
     * @param itemMap The Map<String, ItemType> of the items.
     * @return boolean - Whether any of the IDs are unprocessed.
     */
    private boolean checkIDs(Map<String, ItemType> itemMap) {
	boolean isValid = true;
        for (Entry<String, ItemType> item : itemMap.entrySet()) {
            String id = item.getValue().getIdValue().toLowerCase();
            String regex = "[a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9][a-f0-9]";
    	    Pattern nonHexLetters = Pattern.compile(regex);
            Matcher matchingText = nonHexLetters.matcher(id); 
            isValid = matchingText.matches();
            if (isValid == false) {
        	StringBuilder errorMsg = new StringBuilder();
        	errorMsg.append("Item ");
        	errorMsg.append(item.getValue().getItemName());
        	errorMsg.append(" Has a bad ID: ");
        	errorMsg.append(id);
        	System.out.println(errorMsg.toString());
        	break;
            }
        }        
        return isValid;
    }
    /**
    * Sets up a Shared instance of the DataFileReader class
    * so that tests that involve checking the data don't
    * cause performance hits. This happens when constantly
    * reading the Excel file when we don't care about
    * the state of the DataFileReader's code execution.
    */
    @BeforeGroups(groups = {"Space Suits", "Helmets", "Packs", "Space Suit Sets"})
    private void setSharedTestReader() {
        this.sharedTestReader = new DataFileReader();
    }

    @Test(groups = { "Resources" })	
    public void verifyResourcesLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAResource("Argon".toLowerCase()).getItemName(), "Argon");
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
        
        Assert.assertEquals(resourceKeys.toArray()[0], "Adaptive Frame".toLowerCase());
        Assert.assertEquals(resourceKeys.toArray()[54], "Microsecond Regulator".toLowerCase());
        Assert.assertEquals(resourceKeys.toArray()[106], "Zero-G Gimbal".toLowerCase());
    }
    
    @Test(groups = { "Resources" })
    public void verifyResourceIDs() {       
        Map<String, ItemType> testResources = sharedTestReader.getResources();
                
        Assert.assertEquals(this.checkIDs(testResources), true);
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit".toLowerCase()).getItemName(),
        	"Bounty Hunter Spacesuit");
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
        
        Assert.assertEquals(suitKeys.toArray()[0], "Bounty Hunter Spacesuit".toLowerCase());
        Assert.assertEquals(suitKeys.toArray()[35], "Starborn Spacesuit Solis".toLowerCase());
        Assert.assertEquals(suitKeys.toArray()[64], "Zealot Spacesuit".toLowerCase());
    }

    @Test(groups = { "Space Suits" })
    public void verifySpaceSuitsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit".toLowerCase()).getDLCFlag(),
        	false);
    }

    @Test
    public void verifySpaceSuitsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Va'ruun Assault Spacesuit".toLowerCase()).getDLCFlag(),
        	true);
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
    public void verifySpaceSuitIDs() {
        Map<String, ItemType> testSpaceSuits = sharedTestReader.getSpaceSuits();
                
        Assert.assertEquals(this.checkIDs(testSpaceSuits), true);
    }
    @Test(groups = { "Helmets" })
    public void verifyHelmetsLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAHelmet("Bounty Hunter Space Helmet".toLowerCase()).getItemName(),
        	"Bounty Hunter Space Helmet");
    }

    @Test(groups = { "Helmets" })
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
        
        Assert.assertEquals(helmetKeys.toArray()[0], "Bounty Hunter Space Helmet".toLowerCase());
        Assert.assertEquals(helmetKeys.toArray()[24], "Pirate Sniper Space Helmet".toLowerCase());
        Assert.assertEquals(helmetKeys.toArray()[47], "Zealot Helmet".toLowerCase());
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAHelmet("Bounty Hunter Space Helmet".toLowerCase()).getDLCFlag(), false);
    }

    @Test(groups = { "Helmets" })
    public void verifyHelmetsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        Assert.assertEquals(testReader.getAHelmet("Valrak's Battle Helmet".toLowerCase()).getDLCFlag(), true);
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
    
    @Test(groups = { "Helmets" })
    public void verifyHelmetsIDs() {
        Map<String, ItemType> testHelmets = sharedTestReader.getHelmets();
        
        Assert.assertEquals(this.checkIDs(testHelmets), true);
    }
    
    @Test(groups = { "Packs" })
    public void verifyPacksLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAPack("Bounty Hunter Seek Pack".toLowerCase()).getItemName(), "Bounty Hunter Seek Pack");
    }

    @Test(groups = { "Packs" })
    public void verifyPacksCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testPacks = testReader.getPacks();

        Assert.assertEquals(testPacks.size(), 43);
    }
    
    @Test(groups = { "Packs" })
    public void verifyPacksSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testPacks = testReader.getPacks();
        Set<String> packKeys = testPacks.keySet();
        
        Assert.assertEquals(packKeys.toArray()[0], "Bounty Hunter Seek Pack".toLowerCase());
        Assert.assertEquals(packKeys.toArray()[21], "Old Earth Pack".toLowerCase());
        Assert.assertEquals(packKeys.toArray()[42], "Zealot Pack".toLowerCase());
    }

    @Test(groups = { "Packs" })
    public void verifyPacksDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAPack("Bounty Hunter Seek Pack".toLowerCase()).getDLCFlag(), false);
    }

    @Test(groups = { "Packs" })
    public void verifyPacksDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        Assert.assertEquals(testReader.getAPack("Va'ruun Mining Pack".toLowerCase()).getDLCFlag(), true);
    }

    @Test(groups = { "Packs" })
    public void verifyPacksDLCFlagFalseCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> packs = this.sharedTestReader.getPacks();
        int countOfFalse = this.countDLCs(packs, false);
        
        Assert.assertEquals(countOfFalse, 38);
    }

    @Test(groups = { "Packs" })
    public void verifyPacksDLCFlagTrueCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> packs = this.sharedTestReader.getPacks();
        int countOfTrue = this.countDLCs(packs, true);
        
        Assert.assertEquals(countOfTrue, 5);
    }
    
    @Test(groups = { "Packs" })
    public void verifyPackIDs() {
        Map<String, ItemType> testPacks = sharedTestReader.getPacks();
        
        Assert.assertEquals(this.checkIDs(testPacks), true);
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuitSet("Bounty Hunter 1".toLowerCase()).getItemName(),
        	"Bounty Hunter 1");
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, SpaceSuitSet> testSpaceSuitSets = testReader.getSpaceSuitSets();

        Assert.assertEquals(testSpaceSuitSets.size(), 85);
    }
    
    @Test(groups = { "SpaceSuitSets" })
    public void verifySpaceSuitSetsSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, SpaceSuitSet> testSpaceSuitSets = testReader.getSpaceSuitSets();
        Set<String> spaceSuitSetKeys = testSpaceSuitSets.keySet();
        
        Assert.assertEquals(spaceSuitSetKeys.toArray()[0], "Bounty Hunter 1".toLowerCase());
        Assert.assertEquals(spaceSuitSetKeys.toArray()[42], "Starborn Solis".toLowerCase());
        Assert.assertEquals(spaceSuitSetKeys.toArray()[84], "Zealot".toLowerCase());
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuitSet("Bounty Hunter 1".toLowerCase()).getDLCFlag(), false);
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        Assert.assertEquals(testReader.getASpaceSuitSet("Fang's".toLowerCase()).getDLCFlag(), true);
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsDLCFlagFalseCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, SpaceSuitSet> spaceSuitSets = this.sharedTestReader.getSpaceSuitSets();
        int countOfFalse = this.countDLCSpaceSuitSets(spaceSuitSets, false);
        
        Assert.assertEquals(countOfFalse, 78);
    }

    @Test(groups = { "Space Suit Sets" })
    public void verifySpaceSuitSetsDLCFlagTrueCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, SpaceSuitSet> spaceSuitSets = this.sharedTestReader.getSpaceSuitSets();
        int countOfTrue = this.countDLCSpaceSuitSets(spaceSuitSets, true);
        
        Assert.assertEquals(countOfTrue, 7);
    }
}