/**
 * A class for menus that aren't based on ItemType objects.
 */
package com.jebutton.starfield.console.cg.menusystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * A class for menus that aren't based on ItemType objects.
 */
public class NonItemMenu extends BaseMenu {

    private ArrayList<String> lowerCaseOptions;

    /**
     * Constructor.
     * @param menuOptions a List<String> of options to display.
     */
    public NonItemMenu(List<String> menuOptions) {
	super();
	this.setItemNameListNonSort(menuOptions);
	
	lowerCaseOptions = new ArrayList<>();
	for (String option : this.getItemNameList()) {
	    lowerCaseOptions.add(option.toLowerCase());
	}
    }

    /**
     * Prints and handles input for a menu
     * that isn't based around maps of ItemType objects.
     * @param input Scanner the Shared Scanner instance for the app.
     * @param title String the title to be displayed at the top of the menu.
     * @return A String representing the selected menu option.
     */
    public String printNonItemMenu(Scanner input, String title) {	
	MenuUtils.clearScreen();
	System.out.println(this.getPrettyPrintPrompt(title));	
	String selection = "";
	boolean done = false;
	String result = null;
	for (String[] chunk : this.getChunkedItems()) {
	    if (!done) {
        	    for (String item : chunk) {
        		System.out.println(item);
        	    }
        	    boolean valid = false;
        	    
        	    while (!valid) {
                	    String itemPrompt ="Type Menu Option or 'end' to quit:";
        		    System.out.println(this.getPrettyPrintPrompt(itemPrompt));
                	    selection = input.nextLine().toLowerCase();
                	    switch (selection) {
                	    	case "end":
                	    	    valid = true;
                	    	    done = true;
                	    	    result = "end";
                	    	    break;
                	    	case "next":
                	    	    valid = true;
                	    	    break;
                	    	default:
                	    	    if (this.lowerCaseOptions.contains(selection)) {
                	    		valid = true;
                	    		done = true;
                	    		result = selection;
                	    		MenuUtils.clearScreen();
                	    		System.out.println(this.getPrettyPrintPrompt(title));
                	    		break;
                	    	    }
                	    }
                	    if (!valid) {
   	            	   	System.out.println("Invalid Input");
                	    }
        	}
            }
	}
	return (result);
    }
    
    /**
     * Sets the itemNameList variable.
     * @param itemNameList
     */
    public void setItemNameListNonSort(List<String> itemNameList) {
	this.itemNameList = new ArrayList<>(itemNameList);
	this.chunkMenu();
    }
}
