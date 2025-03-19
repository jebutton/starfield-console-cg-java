/**
 * 
 */
package com.jebutton.starfield.console.cg.menusystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jebutton.starfield.console.cg.datatypes.ItemType;

/**
 * 
 */
public class BaseMenu {
    protected Map<String, ItemType> itemMap;
    static final int MAXCHUNKSIZE = 12;
    protected ArrayList<String> itemNameList;
    protected ArrayList<String[]> chunkedItems;

    public BaseMenu () {
	this.itemNameList = new ArrayList<>();
	this.itemMap = new LinkedHashMap<>();
	this.chunkedItems = new ArrayList<>();
    }
    /**
     * Splits the menu into chunks.
     */
    protected void chunkMenu() {
	ArrayList<String[]> tempChunks = new ArrayList<>();
	for(int i=0;i<itemNameList.size();i+=MAXCHUNKSIZE){
	   tempChunks.add(Arrays.copyOfRange(
			    this.itemNameList.toArray(new String[0]),
			    i,
			    Math.min(itemNameList.size(),
			    i+MAXCHUNKSIZE)));
	   
	}
	this.setChunkedItems(tempChunks);
    }
    
    /**
     * Generates a text border.
     * @param thin a boolean setting whether to use "-" versus "=".
     * @param length The length of the border to generate.
     * @return a String of a text border.
     */
    public String getBorder(boolean thin, int length) {
	StringBuilder border = new StringBuilder();
	String borderCharacter;	
	
	if (thin) {
	    borderCharacter = "-";
	} else {
	    borderCharacter = "=";
	}
	for (int i=0; i < length; i++) {
	    border.append(borderCharacter);
	}

	border.append("\n");
	return border.toString();
    }
    
    /**
     * Generates a String of a line bordered by pipes.
     * @param text The text to wrap in pipes.
     * @return A String of a line bordered by pipes.
     */
    public String getBoxedLine(String text) {
	StringBuilder output = new StringBuilder();
	output.append("| ");
	output.append(text);
	output.append(" |\n");
	return output.toString();
    }
    
    /**
     * Prints a prompt.
     * @param prompt The text to prompt.
     */
    public String getPrettyPrintPrompt(String prompt) {
	StringBuilder output = new StringBuilder();
	String border = this.getBorder(true, prompt.length() + 4);
	output.append("\n");
	output.append(border);
	output.append(this.getBoxedLine(prompt));
	output.append(border);
	return(output.toString());
    }
    
    /**
     * Handles input when an amount of items need to be specified.
     * @param inputScanner a Scanner to accept input from.
     * @return an Integer of how many items you want.
     */
    public Integer specifyItem(Scanner inputScanner) {
	System.out.println(this.getPrettyPrintPrompt("How Many Items?"));
	boolean isValid = false;
	String invalidMsg = "Not a valid Integer. Please Try Again.";
	Integer amount = null;
	String input = "";
	while (!isValid) {
        	try {
        	    if (inputScanner.hasNext()) {
                	    input = inputScanner.nextLine();
                	    if (input != null) {
                		amount = Integer.valueOf(input);
                		isValid = true;
                	    }
        	    }
        	} catch (Exception e) {
        	    System.out.println(invalidMsg);
        	}
	}
	return amount;
    }
    
    /**
     * Creates a string of text in a block format
     * with borders and spacing.
     * @param command the String of text to display.
     * @return a String of text to print.
     */
    public String getPrettyPrintCommand(String command) {
	StringBuilder output = new StringBuilder();
	int length = command.length();
	StringBuilder emptyLine = new StringBuilder();
	String border = this.getBorder(false, length + 4);
	for (int i=0; i<length; i++) {
	    emptyLine.append(" ");
	}
	output.append("\n");
	output.append(border);
	output.append(this.getBoxedLine(emptyLine.toString()));
	output.append(this.getBoxedLine(command));
	output.append(this.getBoxedLine(emptyLine.toString()));
	output.append(border);
	output.append("\n");
	
	return output.toString();
    }
    
    /**
     * Returns the list of chunked menus. 
     * @return List<String[]> of chunked menus.
     */
    public List<String[]> getChunkedItems() {
	return this.chunkedItems;
    }
    
    /**
     * Gets the itemMap()
     * @return a Map<String, ItemType> of the items.
     */
    public Map<String, ItemType> getItemMap() {
	return this.itemMap;
    }
    
    /**
     * Gets the itemNameList
     * @return a List<String> of the item names.
     */
    public List<String> getItemNameList() {
	return this.itemNameList;
    }
    
    public void setChunkedItems(List<String[]> chunkedItems) {
	this.chunkedItems = new ArrayList<>(chunkedItems);
    }
    /**
     * Sets the itemMap variable.
     * @param itemMap
     */
    public void setItemMap(Map<String, ItemType> itemMap) {
	this.itemMap = itemMap;
	ArrayList<String> tempList = new ArrayList<>();
	for ( Map.Entry<String, ItemType> entry : itemMap.entrySet()) {
	    tempList.add(entry.getValue().getItemName());
	}
	this.setItemNameList(tempList);
	this.chunkMenu();
	
    }
    
    /**
     * Sets the itemNameList variable.
     * @param itemNameList
     */
    public void setItemNameList(List<String> itemNameList) {
	this.itemNameList = new ArrayList<>(itemNameList);
	Collections.sort(this.itemNameList);
	this.chunkMenu();
    }
}
