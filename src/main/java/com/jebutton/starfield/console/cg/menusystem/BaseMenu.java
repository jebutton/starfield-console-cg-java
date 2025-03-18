/**
 * A class representing the simplest of Menus.
 */
package com.jebutton.starfield.console.cg.menusystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.jebutton.starfield.console.cg.datatypes.*;

/**
 * A class representing the simplest of Menus.
 */
public class BaseMenu {
    protected Map<String, ItemType> itemMap;
    protected ArrayList<String> itemNameList;
    static final int MAXCHUNKSIZE = 12;
    protected ArrayList<String[]> chunkedItems;
    
    
    /**
     * Constructor.
     * @param itemMap a Map<String, ItemType> of items.
     */
    public BaseMenu(Map<String, ItemType> itemMap) {
	this.itemMap = itemMap;
	this.itemNameList = new ArrayList<>();
	for ( Map.Entry<String, ItemType> entry : itemMap.entrySet()) {
	    itemNameList.add(entry.getValue().getItemName());
	}
	
	Collections.sort(itemNameList);	
	
	this.chunkedItems = new ArrayList<>();
	chunkMenu();
    }
    
    public BaseMenu(List<String> itemNameList) {
	this.itemNameList = new ArrayList<>(itemNameList);
	this.chunkedItems = new ArrayList<>();
	chunkMenu();
    }
    
    /**
     * Splits the menu into chunks.
     */
    protected void chunkMenu() {
	for(int i=0;i<itemNameList.size();i+=MAXCHUNKSIZE){
	   this.chunkedItems.add(Arrays.copyOfRange(
			    this.itemNameList.toArray(new String[0]),
			    i,
			    Math.min(itemNameList.size(),
			    i+MAXCHUNKSIZE)));
	   
	}
    }

    /**
     * Prints a simple menu and handles input.
     * @param title A String Title For the Menu.
     */
    public boolean printSimpleMenu(Scanner input, String title) {
	String selection = "";
	boolean done = false;
	boolean exit = false;
	ItemType result = null;
	for (String[] chunk : chunkedItems) {
	    
	    
	    if (!done) {
		    MenuUtils.clearScreen();
		    System.out.println(this.getPrettyPrintPrompt(title));
        	    for (String item : chunk) {
        		System.out.println(item);
        	    }
        	    boolean valid = false;
        	    String itemPrompt ="Type Item Name or 'next' to see more or 'end' to quit:";
        	    while (!valid) {
                	    if (chunk == chunkedItems.get(chunkedItems.size() - 1)) {
                		itemPrompt = "Type Item Name or 'end' to quit:";
                	    }
        		    System.out.println(this.getPrettyPrintPrompt(itemPrompt));
                	    selection = input.nextLine();
                	    selection = selection.toLowerCase();
                	    switch (selection) {
                	    	case  "next":
                	    	    if (chunk != chunkedItems.get(chunkedItems.size() - 1)) {
                	    		valid = true;
                	    	    }                	    	    
                	    	    break;
                	    	case "end":
                	    	    valid = true;
                	    	    done = true;
                	    	    exit = true;
                	    	    break;
                	    	default:
                	    	    if (this.itemMap.containsKey(selection)) {
                	    		valid = true;
                	    		done = true;
                	    		result = itemMap.get(selection);
                	    		System.out.println(
                	    			this.getPrettyPrintCommand(
                	    				result.getCommand(
                	    					this.specifyItem(input)
                	    			)));
                	    	    }
                	    	    break;
                	    }
                	    if (!valid) {
                		System.out.println("Invalid Input");
                	    }
        	    }
    		}
        	else {
        	    break;
        	}
	}
	return (exit);
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
}
