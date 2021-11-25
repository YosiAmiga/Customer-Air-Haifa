package entity;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import utils.FlightStatus;

public class Flight {

	private Calendar flightDeparture;
	private Calendar flightArrival;
	private Airport originAirport;
	private Airport destinationAirport;
	private Airplane flightAirplane;
	private FlightStatus status;
	private Pilot firstPilotID;
	private Pilot secondPilotID;
	
	
	private String flightSerialNumber;
	private Timestamp flightDeparture2;
	private Timestamp flightArrival2;
	private String airplane2;
	private String status2;
	private int originAirport2;
	private int destinationAirport2;
	
	private int firstPilotID2;
	private int secondPilotID2;
	private HashMap<Integer , FlightAttendant> flightAttendantsInFlight;
	
	//without pilots
	public Flight(
			String flightSerialNumber,			
			Timestamp flightDeparture,
			Timestamp flightArrival,
			String flightAirplane,
			String flightStatus,
			int originAirport,
			int destinationAirport
			
			
			) {
		super();
		this.flightSerialNumber = flightSerialNumber;
		this.flightDeparture2 = flightDeparture;
		this.flightArrival2 = flightArrival;
		this.originAirport2 = originAirport;
		this.destinationAirport2 = destinationAirport;
		this.airplane2 = flightAirplane;
		this.status2 = flightStatus;	
		
		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
	}
	
	public Flight(
			String flightStatus,
			String flightSerialNumber,
			String flightAirplane,
			
			int originAirport,
			int destinationAirport,
			int firstPilotID2,
			int secondPilotID2,
			
			Timestamp flightArrival,
			Timestamp flightDeparture
			) {
		super();
		this.flightSerialNumber = flightSerialNumber;
		this.flightDeparture2 = flightDeparture;
		this.flightArrival2 = flightArrival;
		this.originAirport2 = originAirport;
		this.destinationAirport2 = destinationAirport;
		this.airplane2 = flightAirplane;
		this.firstPilotID2 = firstPilotID2;
		this.secondPilotID2 = secondPilotID2;
		this.status2 = flightStatus;	
		
		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
	}
	
	/**
	 * @param flightSerialNumber
	 * @param flightDeparture
	 * @param flightArrival
	 * @param originAirport
	 * @param destinationAirport
	 * @param flightAirplane
	 * @param status
	 */
	
	public Flight(String flightSerialNumber, Calendar flightDeparture, Calendar flightArrival, Airport originAirport,
			Airport destinationAirport, Airplane flightAirplane) {
		super();
		this.flightSerialNumber = flightSerialNumber;
		this.flightDeparture = flightDeparture;
		this.flightArrival = flightArrival;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.flightAirplane = flightAirplane;
		this.status = FlightStatus.OnTime;	
		
		flightAttendantsInFlight = new HashMap<Integer , FlightAttendant>();
	}

	// methods to link flight attendants to flights
	public boolean linkFlightAttendants(FlightAttendant fa) {
		if(fa != null && !flightAttendantsInFlight.containsKey(fa.getId())) {
			return true;
		}
		return false;
	}
	
	public boolean removeFlightAttendants(FlightAttendant fa) {
		
		if(flightAttendantsInFlight.containsKey(fa.getId())) {
			flightAttendantsInFlight.remove(fa);
			return true;
		}
		return false;
	}

	public String getFlightSerialNumber() {
		return flightSerialNumber;
	}


	public void setFlightSerialNumber(String flightSerialNumber) {
		this.flightSerialNumber = flightSerialNumber;
	}


	public Calendar getFlightDeparture() {
		return flightDeparture;
	}


	public void setFlightDeparture(Calendar flightDeparture) {
		this.flightDeparture = flightDeparture;
	}


	public Calendar getFlightArrival() {
		return flightArrival;
	}


	public void setFlightArrival(Calendar flightArrival) {
		this.flightArrival = flightArrival;
	}


	public Airport getOriginAirport() {
		return originAirport;
	}


	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}


	public Airport getDestinationAirport() {
		return destinationAirport;
	}


	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}


	public Airplane getFlightAirplane() {
		return flightAirplane;
	}


	public void setFlightAirplane(Airplane flightAirplane) {
		this.flightAirplane = flightAirplane;
	}


	public FlightStatus getStatus() {
		return status;
	}


	public void setStatus(FlightStatus status) {
		this.status = status;
	}


	public Pilot getFirstPilotID() {
		return firstPilotID;
	}


	public void setFirstPilotID(Pilot firstPilotID) {
		this.firstPilotID = firstPilotID;
	}


	public Pilot getSecondPilotID() {
		return secondPilotID;
	}


	public void setSecondPilotID(Pilot secondPilotID) {
		this.secondPilotID = secondPilotID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationAirport == null) ? 0 : destinationAirport.hashCode());
		result = prime * result + ((firstPilotID == null) ? 0 : firstPilotID.hashCode());
		result = prime * result + ((flightAirplane == null) ? 0 : flightAirplane.hashCode());
		result = prime * result + ((flightArrival == null) ? 0 : flightArrival.hashCode());
		result = prime * result + ((flightDeparture == null) ? 0 : flightDeparture.hashCode());
		result = prime * result + ((flightSerialNumber == null) ? 0 : flightSerialNumber.hashCode());
		result = prime * result + ((originAirport == null) ? 0 : originAirport.hashCode());
		result = prime * result + ((secondPilotID == null) ? 0 : secondPilotID.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (destinationAirport == null) {
			if (other.destinationAirport != null)
				return false;
		} else if (!destinationAirport.equals(other.destinationAirport))
			return false;
		if (firstPilotID == null) {
			if (other.firstPilotID != null)
				return false;
		} else if (!firstPilotID.equals(other.firstPilotID))
			return false;
		if (flightAirplane == null) {
			if (other.flightAirplane != null)
				return false;
		} else if (!flightAirplane.equals(other.flightAirplane))
			return false;
		if (flightArrival == null) {
			if (other.flightArrival != null)
				return false;
		} else if (!flightArrival.equals(other.flightArrival))
			return false;
		if (flightDeparture == null) {
			if (other.flightDeparture != null)
				return false;
		} else if (!flightDeparture.equals(other.flightDeparture))
			return false;
		if (flightSerialNumber == null) {
			if (other.flightSerialNumber != null)
				return false;
		} else if (!flightSerialNumber.equals(other.flightSerialNumber))
			return false;
		if (originAirport == null) {
			if (other.originAirport != null)
				return false;
		} else if (!originAirport.equals(other.originAirport))
			return false;
		if (secondPilotID == null) {
			if (other.secondPilotID != null)
				return false;
		} else if (!secondPilotID.equals(other.secondPilotID))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	
	
	/********* Methods *************/
	
	public double checkTimeBeforeTakeoff() {
		//get the current date (day,month,year)
		Calendar currentDate = Calendar.getInstance();
		//calculate the difference between the current date and the Flight Departure date
		return (daysBetween(currentDate, getFlightDeparture()));

	}
	
	//a method that return the difference in days between 2 dates.
	public static long daysBetween(Calendar startDate, Calendar endDate) {
	    long end = endDate.getTimeInMillis();
	    long start = startDate.getTimeInMillis();
	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}
	
	public boolean checkMinimumCrew() {
		//minimum staff (2p, 2a)
		if(firstPilotID != null && secondPilotID != null && flightAttendantsInFlight.size() >= 2) {
			//stage -> pre sale
			return true;
		}
		return false;
	}
	
	public boolean checkPartialCrew() {
		if(checkMinimumCrew() && !checkFullCrew()) {			
			return true;
		}
		return false;
	}
	
	public boolean checkFullCrew() {
		//1 attendant for 25 seats, plane size/ ammount of attendants = 25 for it to be full.
		if(flightAirplane.getAirplaneSize()/flightAttendantsInFlight.size() == 25) {
			return true;
		}
		return false;
	}
	
	public void checkFlightStage() {
		//init stage -> reset
		
		//minimum staff (2p, 2a)
		if(firstPilotID != null && secondPilotID != null && flightAttendantsInFlight.size() >= 2) {
			//stage -> pre sale
		}
		
		//1 attendant for 25 seats, plane size
		if(checkFullCrew()) {
			//stage -> normal sale
		}
		
	}

	@Override
	public String toString() {
		return "Flight [flightSerialNumber=" + flightSerialNumber + ", flightDeparture2=" + flightDeparture2
				+ ", flightArrival2=" + flightArrival2 + ", originAirport2=" + originAirport2 + ", destinationAirport2="
				+ destinationAirport2 + ", airplane2=" + airplane2 + ", firstPilotID2=" + firstPilotID2
				+ ", secondPilotID2=" + secondPilotID2 + ", status2=" + status2 + "]";
	}
	
//	@Override
//	public String toString() {
//		return "Flight [flightSerialNumber=" + flightSerialNumber + ", flightDeparture=" + flightDeparture
//				+ ", flightArrival=" + flightArrival + ", originAirport=" + originAirport + ", destinationAirport="
//				+ destinationAirport + ", flightAirplane=" + flightAirplane + ", status=" + status + ", firstPilotID="
//				+ firstPilotID + ", secondPilotID=" + secondPilotID + "]";
//	}
	
	
	

}
