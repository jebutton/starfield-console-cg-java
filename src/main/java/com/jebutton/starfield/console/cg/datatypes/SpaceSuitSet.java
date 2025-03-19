/**
 * A class for handling Space Suit sets.
 */
package com.jebutton.starfield.console.cg.datatypes;

/**
 * A class for handling Space Suit Sets.
 */

/**
 * A class for handling Space Suit Sets.
 */
public class SpaceSuitSet extends ItemType {
    private ItemType packItem;
    private ItemType helmetItem;
    private ItemType spaceSuitItem;
    
    /**
     * Default constructor.
     */
    public SpaceSuitSet() {
	super();
    }
    
    /**
     * Returns the Boost Pack for this Set.
     * @return the boostPackItem
     */
    public ItemType getPackItem() {
        return packItem;
    }

    /**
     * Returns the Helmet for this Set.
     * @return the helmetItem
     */
    public ItemType getHelmetItem() {
        return helmetItem;
    }
    /**
     * Returns the Space Suit for this Set.
     * @return the spacesuitItem
     */
    public ItemType getSpaceSuitItem() {
        return spaceSuitItem;
    }
    
    /**
     * Sets the Boost Pack for this Set.
     * @param boostPackItem the boostPackItem to set
     */
    public void setPackItem(ItemType packItem) {
        this.packItem = packItem;
    }
    
    /**
     * Sets the Helmet for this Set.
     * @param helmetItem the helmetItem to set
     */
    public void setHelmetItem(ItemType helmetItem) {
        this.helmetItem = helmetItem;
    }
        
    /**
     * Sets the Space Suit for this Set.
     * @param spacesuitItem the spacesuitItem to set
     */
    public void setSpaceSuitItem(ItemType spaceSuitItem) {
        this.spaceSuitItem = spaceSuitItem;
    }
    
    /**
     * Converts the class to a String.
     */
    public String toString() {
	StringBuilder output = new StringBuilder();
	output.append("Name: ");
	output.append(this.getItemName());
	output.append(" | Space Suit: ");
	output.append(this.getSpaceSuitItem());
	output.append(" | Helmet: ");
	output.append(this.getHelmetItem());
	output.append(" | Pack: ");
	output.append(this.getPackItem());
	
	return output.toString();
	
    }
}
