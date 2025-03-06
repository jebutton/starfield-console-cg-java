/**
 * A Class for Items that will be inherited from.
 */
package com.jebutton.starfield.console.cg.datatypes;

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
    }

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
     * Sets the Item's ID Value.
     * @param idValue String, the Item's Value.
     */
    public void setIdValue(String idValue) {
        this.idValue = idValue;
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
    }

}
