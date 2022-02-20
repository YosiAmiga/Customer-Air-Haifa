package Exceptions;

public class ShortIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "The ID must be 9 digits!";
	}

	public ShortIDException() {
		super("The ID must be 9 digits!");
	}
	
}
