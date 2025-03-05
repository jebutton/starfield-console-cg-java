
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;

import datahandlers.DataFileReader;
import datatypes.ItemType;

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
    * cause performance hits from constantly reading the
    * Excel file when we don't care about the state of 
    * the DataFileReader's code execution.
    */
    @BeforeSuite
    private void setSharedTestReader() {
        sharedTestReader = new DataFileReader();
    }

    @Test
    public void verifyResourcesLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAResource("Argon").getItemName(), "Argon");
      }

    @Test
    public void verifyResourcesCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testResources = testReader.getResources();
        
        Assert.assertEquals(testResources.size(), 107);
    }

    @Test
    public void verifyResourcesSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testResources = testReader.getResources();
        Set<String> resourceKeys = testResources.keySet();
        
        Assert.assertEquals(resourceKeys.toArray()[0], "Adaptive Frame");
        Assert.assertEquals(resourceKeys.toArray()[54], "Microsecond Regulator");
        Assert.assertEquals(resourceKeys.toArray()[106], "Zero-G Gimbal");
    }

    @Test
    public void verifySpaceSuitsLoad() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getItemName(), "Bounty Hunter Spacesuit");
    }

    @Test
    public void verifySpaceSuitsCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
        Set<String> suitKeys = testSpaceSuits.keySet();
        for (String suitKey : suitKeys) {
            System.out.println(suitKey);
        }
        
        Assert.assertEquals(testSpaceSuits.size(), 65);
    }

    @Test
    public void verifySpaceSuitsSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testSpaceSuits = testReader.getSpaceSuits();
        Set<String> suitKeys = testSpaceSuits.keySet();
        
        Assert.assertEquals(suitKeys.toArray()[0], "Bounty Hunter Spacesuit");
        Assert.assertEquals(suitKeys.toArray()[35], "Starborn Spacesuit Solis");
        Assert.assertEquals(suitKeys.toArray()[64], "Zealot Spacesuit");
    }

    @Test
    public void verifySpaceSuitsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Bounty Hunter Spacesuit").getDLCFlag(), false);
    }

    @Test
    public void verifySpaceSuitsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getASpaceSuit("Va'ruun Assault Spacesuit").getDLCFlag(), true);
    }
    
    @Test
    public void verifySpaceSuitsDLCFlagFalseCount() {
        /*
        * Use a shared DataFileReader instance because this isn't testing
        * whether the DataFileReader class is working as much as it is
        * testing whether the data is correct. 
        */
        Map<String, ItemType> spaceSuits = sharedTestReader.getSpaceSuits();
        int countOfFalse = this.countDLCs(spaceSuits, false);
        
        Assert.assertEquals(countOfFalse, 58);
    }

    @Test
    public void verifySpaceSuitsDLCFlagTrueCount() {
        /*
        * Use a shared DataFileReader instance because this isn't testing
        * whether the DataFileReader class is working as much as it is
        * testing whether the data is correct. 
        */
        Map<String, ItemType> spaceSuits = sharedTestReader.getSpaceSuits();
        int countOfTrue = this.countDLCs(spaceSuits, true);
        
        Assert.assertEquals(countOfTrue, 7);
    }

    @Test
    public void verifyHelmetsCount() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testHelmets = testReader.getHelmets();
        Set<String> helmetKeys = testHelmets.keySet();
        for (String helmetKey : helmetKeys) {
            System.out.println(helmetKey);
        }
        
        Assert.assertEquals(testHelmets.size(), 48);
    }

    @Test
    public void verifyHelmetsSort() {
        DataFileReader testReader = new DataFileReader();
        Map<String, ItemType> testHelmets = testReader.getHelmets();
        Set<String> helmetKeys = testHelmets.keySet();
        
        Assert.assertEquals(helmetKeys.toArray()[0], "Bounty Hunter Space Helmet");
        Assert.assertEquals(helmetKeys.toArray()[24], "Pirate Sniper Space Helmet");
        Assert.assertEquals(helmetKeys.toArray()[47], "Zealot Helmet");
    }

    @Test
    public void verifyHelmetsDLCFlagFalse() {
        DataFileReader testReader = new DataFileReader();
        
        Assert.assertEquals(testReader.getAHelmet("Bounty Hunter Space Helmet").getDLCFlag(), false);
    }

    @Test
    public void verifyHelmetsDLCFlagTrue() {
        DataFileReader testReader = new DataFileReader();
        Assert.assertEquals(testReader.getAHelmet("Valrak's Battle Helmet").getDLCFlag(), true);
    }

    @Test
    public void verifyHelmetsDLCFlagFalseCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> helmets = this.sharedTestReader.getHelmets();
        int countOfFalse = this.countDLCs(helmets, false);
        
        Assert.assertEquals(countOfFalse, 41);
    }

    @Test
    public void verifyHelmetsDLCFlagTrueCount() {
        //Use a shared DataFileReader instance to save performance.
        Map<String, ItemType> helmets = this.sharedTestReader.getHelmets();
        int countOfTrue = this.countDLCs(helmets, true);
        
        Assert.assertEquals(countOfTrue, 7);
    }
}