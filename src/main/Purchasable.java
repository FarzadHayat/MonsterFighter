package main;

public interface Purchasable {
	
	/**
     * Buy an purchasable from the shop and add it to the player inventory.
	 * @return 
	 * @throws InventoryFullException 
	 * @throws InsufficientFundsException 
	 * @throws PurchasableNotFoundException 
	 * @throws InvalidValueException 
     */
	public String buy() throws InsufficientFundsException, InventoryFullException, PurchasableNotFoundException, InvalidValueException;
	
	
	/**
     * Sell an purchasable to the shop and removes it from the player inventory.
	 * @return 
	 * @throws PurchasableNotFoundException 
	 * @throws InvalidValueException 
     */
	public String sell() throws PurchasableNotFoundException, InvalidValueException;

}
