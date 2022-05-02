package exceptions;

/**
 * Class InsufficientFundsException
 * Extends Exception:
 * Exception class for catching an insufficient funds error.
 */
@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	
	public InsufficientFundsException () {}
	
    public InsufficientFundsException (String message) {
    	super(message);
    };
}
