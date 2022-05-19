package exceptions;

/**
 * Exception class for catching a invalid target error.
 */
@SuppressWarnings("serial")
public class InvalidTargetException extends Exception {
	
	/**
	 * Create a new InvalidTargetException.
	 */
	public InvalidTargetException() {}
	
	
	/**
	 * Create a new InvalidTargetException using the given message.
	 * @param message the given message
	 */
	public InvalidTargetException(String message) {
		super(message);
	}
}
