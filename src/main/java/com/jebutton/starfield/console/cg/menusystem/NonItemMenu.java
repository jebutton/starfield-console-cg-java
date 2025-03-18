/**
 * 
 */
package com.jebutton.starfield.console.cg.menusystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
 */
public class NonItemMenu extends BaseMenu {

    ArrayList<String> lowerCaseOptions;
    public NonItemMenu(List<String> menuOptions) {
	super(menuOptions);
	lowerCaseOptions = new ArrayList<>();
	for (String option : menuOptions) {
	    lowerCaseOptions.add(option.toLowerCase());
	}
    }

    public String printNonItemMenu(Scanner input) {
	String title = "Main Menu";
	MenuUtils.clearScreen();
	System.out.println(this.getPrettyPrintPrompt(title));	
	String selection = "";
	boolean done = false;
	String result = null;
	for (String[] chunk : chunkedItems) {
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
}
