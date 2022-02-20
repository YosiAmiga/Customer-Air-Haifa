package Exceptions;

public class NoSuchFlightException extends Exception{

	@Override
	public String toString() {
		return "No such flight in Database!";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoSuchFlightException() {
		super("No such flight in Database!");
	}
}
