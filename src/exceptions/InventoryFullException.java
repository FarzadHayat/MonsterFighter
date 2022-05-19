package exceptions;

/**
 * Exception class for catching an inventory full error.
 */
@SuppressWarnings("serial")
public class InventoryFullException extends Exception {
	
	/**
	 * Create a new InventoryFullException.
	 */
	public InventoryFullException () {}
	
	
	/**
	 * Create a new InventoryFullException using the given message.
	 * @param message the given message
	 */
    public InventoryFullException (String message) {
    	super(message);
    };
}
