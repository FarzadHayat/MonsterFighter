package main;

/**
 * An interface to that handles purchasable classes such as monster and item.
 * @author Farzad and Daniel
 */
public interface Purchasable {
	
	/**
	 * @return the Purchasable name
	 */
	public String getName();
	
	/**
	 * @return the Purchasable cost
	 */
	public int getCost();
	
	/**
	 * @return the Purchasable refundAmount
	 */
	public double getRefundAmount();
	
	/**
	 * Instantiate a new Purchasable object
	 * @return the new object
	 */
	public Purchasable clone();
}
