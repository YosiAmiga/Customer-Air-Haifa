package Exceptions;

public class IllegelUserNameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IllegelUserNameException() {
		super("Invalid Customer passport ID!");
	}
	@Override
	public String toString() {
		return "Invalid Customer passport ID!";
	}
	
}
