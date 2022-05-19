package exceptions;

/**
 * Exception class for catching an invalid value error.
 */
@SuppressWarnings("serial")
public class InvalidValueException extends Exception {
	
	/**
	 * Create a new InvalidValueException.
	 */
	public InvalidValueException() {}
	
	
	/**
	 * Create a new InvalidValueException using the given message.
	 * @param message the given message
	 */
	public InvalidValueException(String message) {
		super(message);
	}
}
