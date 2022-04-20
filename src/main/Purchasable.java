package main;

public interface Purchasable {
	
	/**
     * Buy an purchasable from the shop and add it to the player inventory.
	 * @return 
	 * @throws InventoryFullException 
	 * @throws InsufficientFundsException 
	 * @throws PurchasableNotFoundException 
     */
	public String buy() throws InsufficientFundsException, InventoryFullException, PurchasableNotFoundException;
	
	
	/**
     * Sell an purchasable to the shop and removes it from the player inventory.
	 * @return 
	 * @throws PurchasableNotFoundException 
     */
	public String sell() throws PurchasableNotFoundException;

}
