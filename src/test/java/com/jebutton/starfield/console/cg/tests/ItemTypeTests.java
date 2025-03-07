package com.jebutton.starfield.console.cg.tests;


import org.testng.annotations.Test;

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
}
