package exceptions;

/**
 * Exception class for catching not found in the inventory error.
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	
	/**
	 * Create a new NotFoundException.
	 */
	public NotFoundException () {}
	
	
	/**
	 * Create a new NotFoundException using the given message.
	 * @param message the given message
	 */
    public NotFoundException (String message) {
    	super(message);
    };
}
