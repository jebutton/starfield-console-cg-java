/**
 * A Class for Items that will be inherited from.
 */
package com.jebutton.starfield.console.cg.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing an item in the game, like a resource
 * or a weapon.
 */
public class ItemType {
    private String idValue;
    private String itemName;
    private String itemCategory;
    private boolean isDLC;
    
    /**
     * Default Constructor.
     */
    public ItemType() {
        this.idValue = "";
        this.itemName = "";
        this.itemCategory = "";
    }
    
    /**
     * Alternate Constructor.
     * @param idValue String the Id Value for the item.
     * @param itemName String the Item's name.
     */
    public ItemType(String idValue, String itemName) {
        this.setIdValue(idValue);
        this.setItemName(itemName);
        this.processIDValue();
    }
    
    /**
     * Alternate Constructor for 3 Values.
     * @param idValue String the Id Value for the item.
     * @param itemName String the Item's name.
     */
    public ItemType(String idValue, String itemName, String itemCategory) {
        this.setIdValue(idValue);
        this.setItemName(itemName);
        this.setItemCategory(itemCategory);
        this.processIDValue();
    }

    /**
     * Converts the class to a string.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("itemName: ");
        output.append(this.itemName);
        output.append(" | idValue: ");
        output.append(this.idValue);
        output.append(" | itemCategory: ");
        output.append(this.itemCategory);
        return output.toString();
    }
    
    /**
     * Returns the item's ID Value.
     * @return String The Item's ID Value.
     */
    public String getIdValue() {
        return this.idValue;
    }

    /**
     * Returns the item's category.
     * @return A String of the item's category.
     */
    public String getItemCategory() {
        return this.itemCategory;
    }
    
    /**
     * Returns the item's name.
     * @return String, the items name.
     */
    public String getItemName() {
        return this.itemName;
    }
    
    /**
     * Returns whether or not the item is a DLC Item.
     * @return boolean, whether the item is DLC.
     */
    public boolean getDLCFlag() {
        return this.isDLC;
    }

    /**
     * Generates the actual console command to
     * add the item to the player's inventory.
     * @param amount The amount of items to add.
     * @return A Starfield Console Command.
     */
    public String getCommand(int amount) {
	StringBuilder result = new StringBuilder();
	result.append("player.additem ");
	result.append(this.getIdValue());
	result.append(" ");
	result.append(amount);
	return result.toString();
    }

    /**
     * Sets the Item's ID Value.
     * @param idValue String, the Item's Value.
     */
    public void setIdValue(String idValue) {
        this.idValue = idValue;
        this.processIDValue();
    }
    
    /**
     * Sets the Item's Category.
     * @param itemCategory A String containing the item's Category.
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * Sets the Item's Name.
     * @param itemName String, the Item's Name.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets whether the item is a DLC item or not.
     * @param isDLC boolean, whether the item is a DLC item or not.
     */
    public void setDLCFlag(boolean isDLC) {
        this.isDLC = isDLC;
        this.processIDValue();
    }

    /**
     * Normalizes the ID value to a
     * 8 character hex code like it should
     * be in the game.
     */
    private void processIDValue() {
	try {
	    this.idValue = this.idValue.toUpperCase();           
	    int existingLength = this.idValue.length();
	    
	    if (this.isDLC) {
    	    	String tempID = this.idValue;
    	    	tempID = tempID.replace("X", "0");
    	    	this.idValue = tempID;
	    }
	    if (existingLength < 8) {
		StringBuilder newID = new StringBuilder();
		for (int i = existingLength - 1 ; i < 8; i++) {
		    newID.append("0");
		}
		newID.append(this.idValue);
		this.idValue = newID.toString();
	    }
	    if (existingLength > 8) {
		StringBuilder newID = new StringBuilder(this.idValue);
		newID.delete(0, existingLength - 8);
		this.idValue = newID.toString();
	    }
	    
	} catch (NullPointerException e) {
	    System.out.println("ID Value is null for item " + this.itemName);
	    e.printStackTrace();
	}
    }
}
