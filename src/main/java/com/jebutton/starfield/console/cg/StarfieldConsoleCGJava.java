package com.jebutton.starfield.console.cg;
/**

 * A program to be able to output console codes
 * for the Video Game Starfield.
 * 
 * Created by jebutton.
 */

import java.util.ArrayList;
import java.util.Scanner;

import com.jebutton.starfield.console.cg.datahandlers.DataFileReader;
import com.jebutton.starfield.console.cg.menusystem.ItemMenu;
import com.jebutton.starfield.console.cg.menusystem.NonItemMenu;


/**
 *  Main Class for the program.
 */
public class StarfieldConsoleCGJava {
    static DataFileReader dataset;
    static Scanner input;

    /**
    * Main method for the program.
    * @param args
    */
    public static void main(String[] args) {
        dataset = new DataFileReader();
        input = new Scanner(System.in);
        
        // Print welcome message.
        System.out.println("Welcome to the Starfield Console Code Generator.");       
        
        boolean done = false;
        
        while (!done) {
            done = handleMainMenu();
    	    if (!done) {
        	    System.out.println("Do you want to continue? Y/N");
        	    String result = input.nextLine();
        	    switch (result.toLowerCase()) {
        	    	case "n":
        	    	    done = true;
        	    	    break;
        	    	case "no":
        	    	    done = true;
        	    	    break;
        	    	default:
        	    	    break;
        	    }
    	    }
        }
        input.close();
    }

    public static boolean handleMainMenu() {
	ArrayList<String> menuOptions = new ArrayList<>();
	menuOptions.add("Resources");
	menuOptions.add("Space Suits");
	menuOptions.add("Helmets");
	menuOptions.add("Packs");
	
	String title = "Main Menu";
	NonItemMenu mainMenu = new NonItemMenu(menuOptions);
	String result = mainMenu.printNonItemMenu(input, title);
	boolean done = false;
	
	switch (result.toLowerCase()) {
		case "resources":
		    ItemMenu resourcesMenu = new ItemMenu(dataset.getResources());
		    done = resourcesMenu.printSimpleMenu(input, "List of Resources:");
		    break;
		case "space suits":
		    ItemMenu spaceSuitsMenu = new ItemMenu(dataset.getSpaceSuits());
		    done = spaceSuitsMenu.printSimpleMenu(input, "List of Space Suits:");
		    break;
		case "helmets":
		    ItemMenu helmetsMenu = new ItemMenu(dataset.getHelmets());
		    done = helmetsMenu.printSimpleMenu(input, "List of Helmets:");		    
		    break;
		case "packs":
		    ItemMenu packsMenu = new ItemMenu(dataset.getPacks());
		    done = packsMenu.printSimpleMenu(input, "List of Boost Packs:");		    
		    break;
		case "end":
		    done = true;
		    break;
		default:
		    break;
	}
	return (done);
    }
}
