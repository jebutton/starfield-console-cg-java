/**
 * A class that contains methods for menus
 * that need to be used outside of other menu classes.
 */
package com.jebutton.starfield.console.cg.menusystem;

public class MenuUtils {

    public static void clearScreen() {
	String os = getOperatingSystem();
	switch (os) {
	    case "Windows 10":
		clearScreenWin();
		break;
	    case "Windows 11":
		clearScreenWin();
		break;
	    default:
		clearScreenOther();
		break;
	}
    }
    
    public static void clearScreenWin() {
	System.out.println("\033[H\033[2J");
    }
    
    public static void clearScreenOther() {
	System.out.print("\033[H\033[2J");  
	System.out.flush();
    }
    public static String getOperatingSystem() {
	    String os = System.getProperty("os.name");
	    return os;
    }
}
