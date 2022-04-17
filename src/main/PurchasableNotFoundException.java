package main;

/**
 * Class PurchasableNotFoundException
 * Extends Exception:
 * Exception class for catching a Purchasable not found in the inventory error.
 */
@SuppressWarnings("serial")
public class PurchasableNotFoundException extends Exception {
	
	public PurchasableNotFoundException () {}
	
    public PurchasableNotFoundException (String message) {
    	super(message);
    };
}
