package com.jebutton.starfield.console.cg;
/**

 * A program to be able to output console codes
 * for the Video Game Starfield.
 * 
 * Created by jebutton.
 */

import com.jebutton.starfield.console.cg.datahandlers.DataFileReader;


/**
 *  Main Class for the program.
 */
public class StarfieldConsoleCGJava {

    /**
    * Main method for the program.
    * @param args
    */
    public static void main(String[] args) {
        DataFileReader dataset = new DataFileReader();
        
        // Print welcome message.
        System.out.println("Welcome to the Starfield Console Code Generator.");
        System.out.println("Warning: Menu Navigation still hasn't been built yet. ");
        
        // Verify Each Item Type loads. 
        if (dataset.getAResource("Iron").getItemName().equals("Iron")) {
            System.out.println("Resources Loaded");
        }
        if (dataset.getASpaceSuit("Deimos Spacesuit").getItemName().equals("Deimos Spacesuit")) {
            System.out.println("Space Suits Loaded");
        }
        if (dataset.getAHelmet("Deimos Space Helmet").getItemName().equals("Deimos Space Helmet")) {
            System.out.println("Helmets Loaded");
        }
        if (dataset.getAPack("Deimos Pack").getItemName().equals("Deimos Pack")) {
            System.out.println("Packs Loaded");
        }
        
    }

}
