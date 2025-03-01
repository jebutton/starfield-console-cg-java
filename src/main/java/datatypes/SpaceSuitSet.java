/**
 * A class for handling Space Suit sets.
 */
package datatypes;

/**
 * A class for handling Space Suit Sets.
 */
public class SpaceSuitSet {
	private ItemType boostPackItem;
	private ItemType helmetItem;
	private ItemType spaceSuitItem;
	
	/**
	 * Returns the Boost Pack for this Set.
	 * @return the boostPackItem
	 */
	public ItemType getBoostPackItem() {
		return boostPackItem;
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
	public void setBoostPackItem(ItemType boostPackItem) {
		this.boostPackItem = boostPackItem;
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
}
