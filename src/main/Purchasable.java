package main;

public interface Purchasable {
	
	/**
     * Buy an purchasable from the shop and add it to the player inventory.
     * @param purchasable
     */
	public void buy(Purchasable purchasable);
	
	/**
     * Sell an purchasable to the shop and removes it from the player inventory.
     * @param purchasable
     */
	public void sell(Purchasable purchasable);
}
