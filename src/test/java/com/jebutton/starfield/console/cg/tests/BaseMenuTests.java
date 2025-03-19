package com.jebutton.starfield.console.cg.tests;


import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;

import com.jebutton.starfield.console.cg.menusystem.*;
import com.jebutton.starfield.console.cg.datatypes.*;

public class BaseMenuTests {

    private int chunkSize = 12; // Actual Chunk Size.
    private BaseMenu sharedMenu;
    
    private LinkedHashMap<String, ItemType> generateMapByLength(int length){
	LinkedHashMap<String, ItemType> testMap = new LinkedHashMap<String, ItemType>();
	
	// Populate the map.
	for (int i=0; i<length; i++) {
	    String itemID = "" + i;
	    String itemName = i + " of " + length;
	    String itemCategory = "Test Item";
	    ItemType tempItem = new ItemType(itemID, itemName, itemCategory);
	    testMap.put(itemName.toLowerCase(), tempItem);
	}
	
	return testMap;
    }
    
    @BeforeGroups(groups = {"Border", "Boxed Line", "Prompt", "Command"})
    public void genSharedBaseMenu() {
        this.sharedMenu = new BaseMenu();
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values less than the chunk size.
     */
    @Test(groups = { "Chunking" })
    public void chunkSizeLessThanByOne() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize - 1);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 1);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize - 1);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values less than the chunk size.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeLessThanByTwo() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize - 2);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 1);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize - 2);
    }
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values equal to the chunking size.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeEqualSize() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 1);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values more than the chunk size.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeMoreThanByOne() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize + 1);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 2);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
        Assert.assertEquals(chunks.get(1).length, 1);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values more than the chunk size.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeMoreThanByTwo() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize + 2);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 2);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
        Assert.assertEquals(chunks.get(1).length, 2);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values more than the chunk size by 2x.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeEqualsDouble() {
	Map<String, ItemType> testMap= this.generateMapByLength(this.chunkSize * 2);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 2);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
        Assert.assertEquals(chunks.get(1).length, this.chunkSize);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values more than the chunk size by 2x.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeEqualsDoublePlusOne() {
	Map<String, ItemType> testMap= this.generateMapByLength((this.chunkSize * 2) + 1);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 3);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
        Assert.assertEquals(chunks.get(1).length, this.chunkSize);
        Assert.assertEquals(chunks.get(2).length, 1);
    }
    
    /**
     * A test using Boundary Value Analysis
     * to verify the chunking method works correctly
     * for values slightly less than than 2x the chunk size.
     */
    @Test(groups = { "Chunking" })
    public void testChunkSizeEqualsDoubleMinusOne() {
	Map<String, ItemType> testMap = this.generateMapByLength((this.chunkSize * 2) - 1);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        ArrayList<String[]> chunks = new ArrayList<String[]>(testMenu.getChunkedItems());
        
        Assert.assertEquals(chunks.size(), 2);
        Assert.assertEquals(chunks.get(0).length, this.chunkSize);
        Assert.assertEquals(chunks.get(1).length, this.chunkSize - 1);
    }
    
    /**
     * A test to make sure the getBorder works for
     * "thick" borders.
     */
    @Test(groups = { "Border" })
    public void testGetBorderThick() {
	String result = this.sharedMenu.getBorder(false, 2);
	
	Assert.assertEquals(result.length(), 3);
	Assert.assertEquals(result, "==\n");	        
    }
    
    /**
     * A test to make sure the .getBorder method works for
     * "thin" borders.
     */
    @Test(groups = { "Border" })
    public void testGetBorderThin() {
	String result = this.sharedMenu.getBorder(true, 2);
	
	Assert.assertEquals(result.length(), 3);
	Assert.assertEquals(result, "--\n");	        
    }
    
    /**
     * A Boundary Value Analysis
     * test to make sure the .getBorder method works for
     * a border with 0 length.
     */
    @Test(groups = { "Border" })
    public void testGetBorderZeroLength() {
	String result = this.sharedMenu.getBorder(true, 0);
	
	Assert.assertEquals(result.length(), 1);
	Assert.assertEquals(result, "\n");	        
    }
    
    /**
     * A Boundary Value Analysis
     * test to make sure the .getBorder method works for
     * a border with negative length.
     */
    @Test(groups = { "Border" })
    public void testGetBorderNegativeLength() {
	String result = this.sharedMenu.getBorder(true, -1);
	
	Assert.assertEquals(result.length(), 1);
	Assert.assertEquals(result, "\n");	        
    }
    
    /**
     * A test to make sure the .getBoxedLine
     * method works for valid values.
     */
    @Test(groups = { "Boxed Line" })
    public void testGetBoxedLineValid() {
	String result = this.sharedMenu.getBoxedLine("test");
	
	Assert.assertEquals(result.length(), 9);
	Assert.assertEquals(result, "| test |\n");	        
    }
    
    /**
     * A Boundary Value Analysis
     * test to make sure the .getBoxedLine method works for
     * a box with negative length.
     */
    @Test(groups = { "Boxed Line" })
    public void testGetBoxedLineZeroLength() {
	String result = this.sharedMenu.getBoxedLine("");
	
	Assert.assertEquals(result.length(), 5);
	Assert.assertEquals(result, "|  |\n");	        
    }
    
    /**
     * A test to verify the .getPrettyPrintPrompt
     * method works for valid values.
     */
    @Test(groups = { "Prompt" })
    public void testGetPrettyPrintPromptValid() {
	String result = this.sharedMenu.getPrettyPrintPrompt("test");
	
	//Build the Expected Results String.
	StringBuilder expectedResult = new StringBuilder();
	expectedResult.append("\n--------\n");
	expectedResult.append("| test |\n");
	expectedResult.append("--------\n");

	Assert.assertEquals(result, expectedResult.toString());
    }
    
    /**
     * A BVA test to make sure the .getPrettyProintPrompt
     * method works even if the input is of zero length.
     */
    @Test(groups = { "Prompt" })
    public void testGetPrettyPrintZeroLength() {
	String result = this.sharedMenu.getPrettyPrintPrompt("");
	
	// Build the Expected Results String.
	StringBuilder expectedResult = new StringBuilder();
	expectedResult.append("\n----\n");
	expectedResult.append("|  |\n");
	expectedResult.append("----\n");
	
	Assert.assertEquals(result, expectedResult.toString());
	
    }
    
    /**
     * A test to make sure the .getPrettyPrintCommand method
     * has the correct output structure for valid values.
     */
    @Test(groups = { "Command" })
    public void testGetPrettyPrintCommandValid() {
	String result = this.sharedMenu.getPrettyPrintCommand("test");
	
	// Build the Expected Results String.
	StringBuilder expectedResult = new StringBuilder();
	expectedResult.append("\n========\n");
	expectedResult.append("|      |\n");
	expectedResult.append("| test |\n");
	expectedResult.append("|      |\n");
	expectedResult.append("========\n\n");
	
	Assert.assertEquals(result, expectedResult.toString());	
    }
    
    /**
     * A test to make sure the .getPrettyPrintCommand method
     * has the correct output structure for valid values.
     */
    @Test(groups = { "Command" })
    public void testGetPrettyPrintCommandZeroLength() {
	String result = this.sharedMenu.getPrettyPrintCommand("");
	
	// Build the Expected Results String.
	StringBuilder expectedResult = new StringBuilder();
	expectedResult.append("\n====\n");
	expectedResult.append("|  |\n");
	expectedResult.append("|  |\n");
	expectedResult.append("|  |\n");
	expectedResult.append("====\n\n");
	
	Assert.assertEquals(result, expectedResult.toString());
    }

    /**
     * Tests that the .setItemMap() method works
     * for non-empty values.
     */
    @Test(groups = { "Setters" })
    public void testSetItemMapValid() {
	Map<String, ItemType> testMap = this.generateMapByLength(1);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        
	Assert.assertEquals(testMenu.getItemMap().size(), 1);
	Assert.assertEquals(testMenu.getItemNameList().size(), 1);
    }
    
    /**
     * Tests that the .setItemMap() method works
     * for zero-sized values.
     */
    @Test(groups = { "Setters" })
    public void testSetItemMapZeroLength() {
	Map<String, ItemType> testMap = this.generateMapByLength(0);
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemMap(testMap);
        
	Assert.assertEquals(testMenu.getItemMap().size(), 0);
	Assert.assertEquals(testMenu.getItemNameList().size(), 0);
    }
    
    /**
     * Tests that the .setItemNameList() method works
     * for non-empty values.
     */
    @Test(groups = { "Setters" })
    public void testSetItemNameListValid() {
	ArrayList<String> testNameList = new ArrayList<>();
	testNameList.add("Test Name 2");
	testNameList.add("Test Name 1");
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemNameList(testNameList);
        
	Assert.assertEquals(testMenu.getItemNameList().size(), 2);
	Assert.assertEquals(testMenu.getChunkedItems().size(), 1);
	Assert.assertEquals(testMenu.getItemNameList().get(0), "Test Name 1");
	Assert.assertEquals(testMenu.getItemNameList().get(1), "Test Name 2");
    }
    
    /**
     * Tests that the .setItemNameList() method works
     * for zero-lengths values.
     */
    @Test(groups = { "Setters" })
    public void testSetItemNameListZeroLength() {
	ArrayList<String> testNameList = new ArrayList<>();
        BaseMenu testMenu = new BaseMenu();
        testMenu.setItemNameList(testNameList);
        
	Assert.assertEquals(testMenu.getItemNameList().size(), 0);
	Assert.assertEquals(testMenu.getChunkedItems().size(), 0);
    }
}
