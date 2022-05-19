package exceptions;

/**
 * Exception class for catching an insufficient funds error.
 */
@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	
	/**
	 * Create a new insufficientFundsException.
	 */
	public InsufficientFundsException () {}
	
	
	/**
	 * Create a new InsufficientFundsException using the given message.
	 * @param message the given message
	 */
    public InsufficientFundsException (String message) {
    	super(message);
    };
}
