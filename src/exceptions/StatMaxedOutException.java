package exceptions;

/**
 * Exception class for catching a stat already maxed out error.
 */
@SuppressWarnings("serial")
public class StatMaxedOutException extends Exception {
	
	/**
	 * Create a new StatMaxedOutException.
	 */
	public StatMaxedOutException () {}
	
	
	/**
	 * Create a new StatMaxedOutException using the given message.
	 * @param message the given message
	 */
    public StatMaxedOutException (String message) {
    	super(message);
    };
}
