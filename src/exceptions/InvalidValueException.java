package exceptions;

/**
 * Class InvalidValueException
 * Extends Exception:
 * Exception class for catching an invalid value error.
 */
@SuppressWarnings("serial")
public class InvalidValueException extends Exception {
	
	public InvalidValueException() {}
	
	public InvalidValueException(String message) {
		super(message);
	}
}
