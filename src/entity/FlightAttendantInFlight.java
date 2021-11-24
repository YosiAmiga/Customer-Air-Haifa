package entity;

public class FlightAttendantInFlight {
	
	private Flight flight;
	private FlightAttendant flightAttendant;
	
	/**
	 * @param flight
	 * @param flightAttendant
	 */
	public FlightAttendantInFlight(Flight flight, FlightAttendant flightAttendant) {
		super();
		this.flight = flight;
		this.flightAttendant = flightAttendant;
	}
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public FlightAttendant getFlightAttendant() {
		return flightAttendant;
	}
	public void setFlightAttendant(FlightAttendant flightAttendant) {
		this.flightAttendant = flightAttendant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((flightAttendant == null) ? 0 : flightAttendant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FlightAttendantInFlight))
			return false;
		FlightAttendantInFlight other = (FlightAttendantInFlight) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (flightAttendant == null) {
			if (other.flightAttendant != null)
				return false;
		} else if (!flightAttendant.equals(other.flightAttendant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightAttendantInFlight [flight=" + flight + ", flightAttendant=" + flightAttendant + "]";
	}

	


}
