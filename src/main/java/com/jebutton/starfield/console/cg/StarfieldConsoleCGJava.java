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
        System.out.println(dataset.getAResource("Iron"));
    }

}
