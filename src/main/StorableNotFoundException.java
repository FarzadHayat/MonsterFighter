package main;

/**
 * Class StorableNotFoundException
 * Extends Exception:
 * Exception class for catching a Storable not found in the inventory error.
 */
@SuppressWarnings("serial")
public class StorableNotFoundException extends Exception {
	
	public StorableNotFoundException () {}
	
    public StorableNotFoundException (String message) {
    	super(message);
    };
}
