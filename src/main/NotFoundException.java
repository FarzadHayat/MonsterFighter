package main;

/**
 * Class NotFoundException
 * Extends Exception:
 * Exception class for catching not found in the inventory error.
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	
	public NotFoundException () {}
	
    public NotFoundException (String message) {
    	super(message);
    };
}
