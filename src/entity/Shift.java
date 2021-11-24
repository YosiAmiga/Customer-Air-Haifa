package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Shift {
	
	private Calendar startDate;
	private Calendar endDate;
	private HashMap<Integer ,GroundAttendant> groundAttendants;
	
	/**
	 * @param startDate
	 * @param endDate
	 */
	public Shift(Calendar startDate, Calendar endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		groundAttendants = new HashMap<Integer ,GroundAttendant>();
	}

	
	//Methods to add and remove a ground attendant for the shift	
	public void addGroundAttendantToShift(GroundAttendant g) {
		if(groundAttendants.containsKey(g.getId()))
		groundAttendants.put(g.getId(),g);
	}
	public void removeGroundAttendantFromShift(GroundAttendant g) {
		groundAttendants.remove(g.getId());
	}
	
	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Shift))
			return false;
		Shift other = (Shift) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shift [startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	

}
