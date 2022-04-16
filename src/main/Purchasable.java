package main;

public interface Purchasable {
	
	/**
     * Buy an purchasable from the shop and add it to the player inventory.
	 * @throws InventoryFullException 
	 * @throws InsufficientFundsException 
     */
	public void buy() throws InsufficientFundsException, InventoryFullException;
	
	/**
     * Sell an purchasable to the shop and removes it from the player inventory.
	 * @throws PurchasableNotFoundException 
     */
	public void sell() throws PurchasableNotFoundException;
}
