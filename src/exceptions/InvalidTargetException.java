package exceptions;

/**
 * Class InvalidTargetException
 * Extends Exception:
 * Exception class for catching a invalid target error.
 */
@SuppressWarnings("serial")
public class InvalidTargetException extends Exception {
	
	public InvalidTargetException() {}
		
		public InvalidTargetException(String message) {
			super(message);
		}
}
