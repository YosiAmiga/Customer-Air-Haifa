package entity;

import utils.MealType;

public class PremiumTicket extends Ticket{
	
	private double maxWeight;
	private String firstRequest;
	private String secondRequest;
	private String thirdRequest;
	
	public PremiumTicket(int passportNumber, int orderID, String flightSerialNumber, MealType mealType, Seat seat,
			int price) {
		super(passportNumber, orderID, flightSerialNumber, mealType, seat, price);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param passportNumber
	 * @param orderID
	 * @param flightSerialNumber
	 * @param mealType
	 * @param seat
	 * @param price
	 * @param maxWeight
	 * @param firstRequest
	 * @param secondRequest
	 * @param thirdRequest
	 */
	public PremiumTicket(int passportNumber, int orderID, String flightSerialNumber, MealType mealType, Seat seat,
			int price, double maxWeight, String firstRequest, String secondRequest, String thirdRequest) {
		super(passportNumber, orderID, flightSerialNumber, mealType, seat, price);
		this.maxWeight = maxWeight;
		this.firstRequest = firstRequest;
		this.secondRequest = secondRequest;
		this.thirdRequest = thirdRequest;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getFirstRequest() {
		return firstRequest;
	}

	public void setFirstRequest(String firstRequest) {
		this.firstRequest = firstRequest;
	}

	public String getSecondRequest() {
		return secondRequest;
	}

	public void setSecondRequest(String secondRequest) {
		this.secondRequest = secondRequest;
	}

	public String getThirdRequest() {
		return thirdRequest;
	}

	public void setThirdRequest(String thirdRequest) {
		this.thirdRequest = thirdRequest;
	}

	@Override
	public String toString() {
		return "PremiumTicket [maxWeight=" + maxWeight + ", firstRequest=" + firstRequest + ", secondRequest="
				+ secondRequest + ", thirdRequest=" + thirdRequest + "]";
	}
	
	

}
