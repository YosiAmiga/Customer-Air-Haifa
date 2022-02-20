package entity;

import java.time.LocalDate;

import utils.MealType;
import utils.SeatClass;

public class Ticket {
	
	private int passportNumber;
	private int orderID;
	private String flightSerialNumber;
	private MealType mealType;
	private String mealType2;
	private Seat seat;
	private String seatClass;
	private int rowNum;
	private String colLetter;
	private String airplane;
	private int price;
	
	//for query
	public Ticket(int passportNumber, String flightSerialNumber, 
			String mealType,int orderID,
			String seatClass,int price, int rowNum,String colLetter,String airplane
			) {
		super();
		this.passportNumber = passportNumber;
		this.orderID = orderID;
		this.flightSerialNumber = flightSerialNumber;
		this.mealType2 = mealType;
		this.seatClass = seatClass;
		this.rowNum = rowNum;
		this.colLetter = colLetter;
		this.airplane = airplane;
		this.price = price;
	}
	



	//for query
	public Ticket(int passportNumber, int orderID,
			String flightSerialNumber, String mealType, Seat seat, int price) {
		super();
		this.passportNumber = passportNumber;
		this.orderID = orderID;
		this.flightSerialNumber = flightSerialNumber;
		this.mealType2 = mealType;
		this.seat = seat;	
		this.price = price;
	}
	


	/**
	 * @param passportNumber
	 * @param orderID
	 * @param flightSerialNumber
	 * @param mealType
	 * @param seat
	 * @param price
	 */
	public Ticket(int passportNumber, int orderID, String flightSerialNumber, MealType mealType, Seat seat, int price) {
		super();
		this.passportNumber = passportNumber;
		this.orderID = orderID;
		this.flightSerialNumber = flightSerialNumber;
		this.mealType = mealType;
		this.seat = seat;
		this.price = price;
	}
	public int getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getFlightSerialNumber() {
		return flightSerialNumber;
	}
	public void setFlightSerialNumber(String flightSerialNumber) {
		this.flightSerialNumber = flightSerialNumber;
	}
	public MealType getMealType() {
		return mealType;
	}
	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMealType2() {
		return mealType2;
	}

	public void setMealType2(String mealType2) {
		this.mealType2 = mealType2;
	}
	
	
	public int getRowNum() {
		return rowNum;
	}


	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}


	public String getColLetter() {
		return colLetter;
	}


	public void setColLetter(String colLetter) {
		this.colLetter = colLetter;
	}


	public String getAirplane() {
		return airplane;
	}


	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}
	@Override
	public String toString() {
		return "Ticket [passportNumber=" + passportNumber + ", orderID=" + orderID + ", flightSerialNumber="
				+ flightSerialNumber + ", mealType=" + mealType + ", seat=" + seat + ", price=" + price + "]";
	}
	
	
	

}
