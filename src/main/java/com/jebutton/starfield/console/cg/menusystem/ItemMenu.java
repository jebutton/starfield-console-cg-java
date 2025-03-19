/**
 * A class representing the simplest of Menus.
 */
package com.jebutton.starfield.console.cg.menusystem;
import java.util.Map;
import java.util.Scanner;

import com.jebutton.starfield.console.cg.datatypes.*;

/**
 * A class representing the simplest of Menus.
 */
public class ItemMenu extends BaseMenu{
    
    /**
     * Constructor.
     * @param itemMap a Map<String, ItemType> of items.
     */
    public ItemMenu(Map<String, ItemType> itemMap) {
	super();
	this.setItemMap(itemMap);
	chunkMenu();
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
        	    String itemPrompt ="Type Item Name or 'next', 'menu', or 'quit':";
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
                	    	case "quit":
                	    	    valid = true;
                	    	    done = true;
                	    	    exit = true;
                	    	    break;
                	    	case "menu":
                	    	    valid = true;
                	    	    done = true;
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
    

    

}
