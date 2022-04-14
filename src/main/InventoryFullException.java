package main;

/**
 * Class InventoryFullException
 * Extends Exception:
Exception class for catching an inventory full error.
 */
@SuppressWarnings("serial")
public class InventoryFullException extends Exception {
	
	public InventoryFullException () {}
	
    public InventoryFullException (String message) {
    	super(message);
    };
}
