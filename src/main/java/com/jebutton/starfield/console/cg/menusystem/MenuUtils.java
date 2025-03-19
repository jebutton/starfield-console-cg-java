/**
 * A class that contains methods for menus
 * that need to be used outside of other menu classes.
 */
package com.jebutton.starfield.console.cg.menusystem;

/**
 * A utility class full of static methods for 
 * help with displaying text.
 */
public class MenuUtils {

    /**
     * Private Constructor.
     */
    private MenuUtils() {
    }
    
    /**
     * Clears the console screen based on OS.
     */
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
    
    /**
     * Clears the console screen in Windows.
     */
    public static void clearScreenWin() {
	System.out.println("\033[H\033[2J");
    }
    
    /**
     * Clears the screen in other platforms.
     */
    public static void clearScreenOther() {
	// TODO test this in Linux.
	System.out.print("\033[H\033[2J");  
	System.out.flush();
    }
    
    /**
     * Gets the operating system.
     * @return a String of the OS name.
     */
    public static String getOperatingSystem() {
	    String os = System.getProperty("os.name");
	    return os;
    }
}
