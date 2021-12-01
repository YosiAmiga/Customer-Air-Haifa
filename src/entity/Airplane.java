package entity;

import utils.SeatClass;

public class Airplane {

	private String airplaneSerialNumber;
	private int airplaneSize;
	
	/**
	 * @param airplaneSerialNumber
	 * @param airplaneSize
	 */
	public Airplane(String airplaneSerialNumber, int airplaneSize) {
		super();
		this.airplaneSerialNumber = airplaneSerialNumber;
		
//		//TODO FIX calculation and ADD SQL to seats into DB
//		/**
//		 * First-Class seats = %20 of plane size
//		 * Business seats = %30 of plane size
//		 * Low-Cost seats = %50 of plane size*/
//		
//		//filling the First-Class seats
//		for (int i = 1; i < (airplaneSize + 1)/5 ; i++) {
//			//set the seat key to be -> 
//			Seat s = new Seat(i,"a",SeatClass.FirstClass,this);
//			System.out.println(s);
//		}
//		//filling the Business seats
//		for (int i = (airplaneSize + 1)/5; i < (airplaneSize + 1)/2 ; i++) {
//			//set the seat key to be -> 
//			Seat s = new Seat(i,"b",SeatClass.Business,this);
//			System.out.println(s);
//		}
//		//filling the Low-Cost seats
//		for (int i = (airplaneSize + 1)/2; i < (airplaneSize + 1) ; i++) {
//			//set the seat key to be -> 
//			Seat s = new Seat(i,"c",SeatClass.LowCost,this);
//			System.out.println(s);
//		}
		this.airplaneSize = airplaneSize;
	}
	
	/**
	 * @return the airplaneSerialNumber
	 */
	public String getAirplaneSerialNumber() {
		return airplaneSerialNumber;
	}
	/**
	 * @param airplaneSerialNumber the airplaneSerialNumber to set
	 */
	public void setAirplaneSerialNumber(String airplaneSerialNumber) {
		this.airplaneSerialNumber = airplaneSerialNumber;
	}
	/**
	 * @return the airplaneSize
	 */
	public int getAirplaneSize() {
		return airplaneSize;
	}
	/**
	 * @param airplaneSize the airplaneSize to set
	 */
	public void setAirplaneSize(int airplaneSize) {
		this.airplaneSize = airplaneSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airplaneSerialNumber == null) ? 0 : airplaneSerialNumber.hashCode());
		result = prime * result + airplaneSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Airplane))
			return false;
		Airplane other = (Airplane) obj;
		if (airplaneSerialNumber == null) {
			if (other.airplaneSerialNumber != null)
				return false;
		} else if (!airplaneSerialNumber.equals(other.airplaneSerialNumber))
			return false;
		if (airplaneSize != other.airplaneSize)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return airplaneSerialNumber;
	}
	
	

}
