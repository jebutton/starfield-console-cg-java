/**
 * A program to be able to output console codes
 * for the Video Game Starfield.
 * 
 * Created by Jacqueline Button.
 */

import datahandlers.DataFileReader;


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
        System.out.println(dataset.getAResource("Argon"));
    }

}
