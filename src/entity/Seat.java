package entity;

import utils.SeatClass;

public class Seat {

	private int rowNumber;
	private String columnLetter;
	private SeatClass seatClass;
	private Airplane airplane;
	
	/**
	 * @param rowNumber
	 * @param columnLetter
	 * @param seatClass
	 * @param airplane
	 */
	public Seat(int rowNumber, String columnLetter, SeatClass seatClass, Airplane airplane) {
		super();
		this.rowNumber = rowNumber;
		this.columnLetter = columnLetter;
		this.seatClass = seatClass;
		this.airplane = airplane;
	}
	
	/**
	 * @return the rowNumber
	 */
	public int getRowNumber() {
		return rowNumber;
	}
	/**
	 * @param rowNumber the rowNumber to set
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	/**
	 * @return the columnLetter
	 */
	public String getColumnLetter() {
		return columnLetter;
	}
	/**
	 * @param columnLetter the columnLetter to set
	 */
	public void setColumnLetter(String columnLetter) {
		this.columnLetter = columnLetter;
	}
	/**
	 * @return the seatClass
	 */
	public SeatClass getSeatClass() {
		return seatClass;
	}
	/**
	 * @param seatClass the seatClass to set
	 */
	public void setSeatClass(SeatClass seatClass) {
		this.seatClass = seatClass;
	}
	/**
	 * @return the airplane
	 */
	public Airplane getAirplane() {
		return airplane;
	}
	/**
	 * @param airplane the airplane to set
	 */
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airplane == null) ? 0 : airplane.hashCode());
		result = prime * result + ((columnLetter == null) ? 0 : columnLetter.hashCode());
		result = prime * result + rowNumber;
		result = prime * result + ((seatClass == null) ? 0 : seatClass.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Seat))
			return false;
		Seat other = (Seat) obj;
		if (airplane == null) {
			if (other.airplane != null)
				return false;
		} else if (!airplane.equals(other.airplane))
			return false;
		if (columnLetter == null) {
			if (other.columnLetter != null)
				return false;
		} else if (!columnLetter.equals(other.columnLetter))
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		if (seatClass != other.seatClass)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Seat [rowNumber=" + rowNumber + ", columnLetter=" + columnLetter + ", seatClass=" + seatClass
				+ ", airplane=" + airplane + "]";
	}
	
	

}
