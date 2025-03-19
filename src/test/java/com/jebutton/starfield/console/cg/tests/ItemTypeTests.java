package com.jebutton.starfield.console.cg.tests;


import org.testng.annotations.Test;

import java.util.ArrayList;

import org.testng.Assert;

import com.jebutton.starfield.console.cg.datatypes.*;


public class ItemTypeTests {
    
    @Test(groups = { "ID Validation" })
    public void verifyItemIDIfGreater() {        
        
        ItemType testNineChars = new ItemType("A00000000", "Test Item 9 Chars");
        testNineChars.setDLCFlag(false);
        ItemType testTenChars = new ItemType( "AA00000000", "Test Item 10 Chars");
        testNineChars.setDLCFlag(false);
        
        Assert.assertEquals(testNineChars.getIdValue().length(), 8);
        Assert.assertEquals(testTenChars.getIdValue().length(), 8);
        Assert.assertEquals(testNineChars.getIdValue(), "00000000");
        Assert.assertEquals(testTenChars.getIdValue(), "00000000");
    }

    @Test(groups = { "ID Validation" })
    public void verifyItemIDIfLesser() {        
        
        ItemType testSevenChars = new ItemType("000000A", "Test Item 7 Chars");
        ItemType testSixChars = new ItemType("00000A", "Test Item 6 Chars");
        
        Assert.assertEquals(testSevenChars.getIdValue().length(), 8);
        Assert.assertEquals(testSixChars.getIdValue().length(), 8);
        Assert.assertEquals(testSevenChars.getIdValue(), "0000000A");
        Assert.assertEquals(testSixChars.getIdValue(), "0000000A");
    }

    @Test(groups = { "ID Validation" })
    public void verifyItemIDIfEqual() {        
        
        ItemType testSameOne = new ItemType("00ABCEF1", "Test Item Same Chars");
                
        Assert.assertEquals(testSameOne.getIdValue().length(), 8);
        Assert.assertEquals(testSameOne.getIdValue(), "00ABCEF1");
    }

    @Test(groups = { "ID Validation" })
    public void verifyItemIDCase() {        
        
        ItemType testCaps = new ItemType("00ABCEF1", "Test Item Capitalized");
        ItemType testLower = new ItemType("00abcef1", "Test Item Lowercase");
        
        Assert.assertEquals(testCaps.getIdValue(), "00ABCEF1");
        Assert.assertEquals(testLower.getIdValue(), "00ABCEF1");
    }
    
    @Test(groups = { "ID Validation" })
    public void verifyItemIDDLC() {        
        
        ItemType testDLCTrue = new ItemType("XXABCEF1", "Test Item DLC");
        testDLCTrue.setDLCFlag(true);
        ItemType testDLCFalse = new ItemType("xxabcef1", "Test Item Not DLC");
        
        Assert.assertEquals(testDLCTrue.getIdValue(), "00ABCEF1");
        Assert.assertEquals(testDLCFalse.getIdValue(), "XXABCEF1");
    }
    
    @Test(groups = { "Console Commands" })
    public void verifyConsoleCommandDLC() {        
        
        ItemType testDLCTrue = new ItemType("XXABCEF1", "Test Item DLC");
        testDLCTrue.setDLCFlag(true);
        ItemType testDLCFalse = new ItemType("xxabcef1", "Test Item Not DLC");
        
        StringBuilder firstResult = new StringBuilder();
        StringBuilder secondResult = new StringBuilder();
        int amount = 5;
        String prefix = "player.additem ";
        firstResult.append(prefix);
        firstResult.append("00ABCEF1 ");
        firstResult.append(amount);
        secondResult.append(prefix);        
        secondResult.append("XXABCEF1 ");
        secondResult.append(amount);
        
        Assert.assertEquals(testDLCTrue.getCommand(amount), firstResult.toString());
        Assert.assertEquals(testDLCFalse.getCommand(amount), secondResult.toString());
    }
    
    @Test(groups = { "Comparable" })
    public void verifyCompareToValidId() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category One");
	ItemType testItemTwo = new ItemType("AA000002", "Test Item", "Category One");
	Assert.assertEquals(testItemOne.compareTo(testItemTwo), "AA000001".compareTo("AA000002") );
	Assert.assertEquals(testItemTwo.compareTo(testItemOne), "AA000002".compareTo("AA000001") );
    }
    
    @Test(groups = { "Comparable" })
    public void verifyCompareToValidName() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item1", "Category One");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item2", "Category One");
	Assert.assertEquals(testItemOne.compareTo(testItemTwo), "Test Item1".compareTo("Test Item2") );
	Assert.assertEquals(testItemTwo.compareTo(testItemOne), "Test Item2".compareTo("Test Item1") );
    }
    
    @Test(groups = { "Comparable" })
    public void verifyCompareToValidCategory() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 2");
	Assert.assertEquals(testItemOne.compareTo(testItemTwo), "Category 1".compareTo("Category 2") );
	Assert.assertEquals(testItemTwo.compareTo(testItemOne), "Category 2".compareTo("Category 1") );
    }
    
    @Test(groups = { "Comparable" })
    public void verifyCompareToValidDLC() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 1");
	testItemOne.setDLCFlag(true);
	testItemTwo.setDLCFlag(false);
	Assert.assertEquals(testItemOne.compareTo(testItemTwo), -1);
	Assert.assertEquals(testItemTwo.compareTo(testItemOne), 1 );
    }
    
    @Test(groups = { "Comparable" })
    public void verifyEqualsEquals() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 1");
	
	Assert.assertEquals(testItemOne.equals(testItemTwo), true);
    }
    
    @Test(groups = { "Comparable" })
    public void verifyNotEqualsId() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000002", "Test Item", "Category 1");
	
	Assert.assertEquals(testItemOne.equals(testItemTwo), false);
    }
    
    @Test(groups = { "Comparable" })
    public void verifyNotEqualsName() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item 2", "Category 1");
	
	Assert.assertEquals(testItemOne.equals(testItemTwo), false);
    }

    @Test(groups = { "Comparable" })
    public void verifyNotEqualsCategory() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 2");	

	Assert.assertEquals(testItemOne.equals(testItemTwo), false);
    }

    @Test(groups = { "Comparable" })
    public void verifyNotEqualsDLC() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 1");
	testItemOne.setDLCFlag(true);

	Assert.assertEquals(testItemOne.equals(testItemTwo), false);
    }
    
    @Test(groups = { "Comparable" })
    public void verifyHashCodeEquals() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 1");
	
	Assert.assertEquals(testItemOne.hashCode(), testItemTwo.hashCode());
    }
    
    @Test(groups = { "Comparable" })
    public void verifyHashCodeEqualsDLC() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item", "Category 1");
	testItemOne.setDLCFlag(true);
	testItemTwo.setDLCFlag(true);
	Assert.assertEquals(testItemOne.hashCode(), testItemTwo.hashCode());
    }
    
    @Test(groups = { "Comparable" })
    public void verifySortID() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemTwo = new ItemType("AA000002", "Test Item 1", "Category 1");
	ItemType testItemThree = new ItemType("AA000003", "Test Item 1", "Category 1");
	ItemType testItemFour = new ItemType("AA000004", "Test Item 1", "Category 1");
	
	
	ArrayList<ItemType> testArrayList = new ArrayList<>();
	testArrayList.add(testItemFour);
	testArrayList.add(testItemThree);
	testArrayList.add(testItemTwo);
	testArrayList.add(testItemOne);
	
	testArrayList.sort(null);
	
	
	Assert.assertEquals(testArrayList.get(0), testItemOne);
	Assert.assertEquals(testArrayList.get(1), testItemTwo);
	Assert.assertEquals(testArrayList.get(2), testItemThree);
	Assert.assertEquals(testArrayList.get(3), testItemFour);
    }
    
    @Test(groups = { "Comparable" })
    public void verifySortName() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item 2", "Category 1");
	ItemType testItemThree = new ItemType("AA000001", "Test Item 3", "Category 1");
	ItemType testItemFour = new ItemType("AA000001", "Test Item 4", "Category 1");
	
	
	ArrayList<ItemType> testArrayList = new ArrayList<>();
	testArrayList.add(testItemFour);
	testArrayList.add(testItemThree);
	testArrayList.add(testItemTwo);
	testArrayList.add(testItemOne);
	
	testArrayList.sort(null);
	
	
	Assert.assertEquals(testArrayList.get(0), testItemOne);
	Assert.assertEquals(testArrayList.get(1), testItemTwo);
	Assert.assertEquals(testArrayList.get(2), testItemThree);
	Assert.assertEquals(testArrayList.get(3), testItemFour);
    }
    
    @Test(groups = { "Comparable" })
    public void verifySortCategory() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item 1", "Category 2");
	ItemType testItemThree = new ItemType("AA000001", "Test Item 1", "Category 3");
	ItemType testItemFour = new ItemType("AA000001", "Test Item 1", "Category 4");
	
	
	ArrayList<ItemType> testArrayList = new ArrayList<>();
	testArrayList.add(testItemFour);
	testArrayList.add(testItemThree);
	testArrayList.add(testItemTwo);
	testArrayList.add(testItemOne);
	
	testArrayList.sort(null);
	
	
	Assert.assertEquals(testArrayList.get(0), testItemOne);
	Assert.assertEquals(testArrayList.get(1), testItemTwo);
	Assert.assertEquals(testArrayList.get(2), testItemThree);
	Assert.assertEquals(testArrayList.get(3), testItemFour);
    }
    
    @Test(groups = { "Comparable" })
    public void verifySortDLC() {
	ItemType testItemOne = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemTwo = new ItemType("AA000001", "Test Item 1", "Category 1");
	ItemType testItemThree = new ItemType("AA000002", "Test Item 2", "Category 1");
	ItemType testItemFour = new ItemType("AA000002", "Test Item 2", "Category 1");
	
	testItemTwo.setDLCFlag(true);
	testItemFour.setDLCFlag(true);
	
	ArrayList<ItemType> testArrayList = new ArrayList<>();
	testArrayList.add(testItemFour);
	testArrayList.add(testItemThree);
	testArrayList.add(testItemTwo);
	testArrayList.add(testItemOne);
	
	testArrayList.sort(null);
	
	Assert.assertEquals(testArrayList.get(0), testItemTwo);
	Assert.assertEquals(testArrayList.get(1), testItemOne);
	Assert.assertEquals(testArrayList.get(2), testItemFour);
	Assert.assertEquals(testArrayList.get(3), testItemThree);
    }
}
