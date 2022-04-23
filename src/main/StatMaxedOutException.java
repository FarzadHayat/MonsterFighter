package main;

/**
 * Class StatMaxedOutException
 * Extends Exception:
 * Exception class for catching a stat already maxed out error.
 */
@SuppressWarnings("serial")
public class StatMaxedOutException extends Exception {
	
	public StatMaxedOutException () {}
	
    public StatMaxedOutException (String message) {
    	super(message);
    };
}
