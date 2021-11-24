package entity;

public class Airport {

	private String uniqueAirportID;
	private String country;
	private String city;
	private Double timeZone;
	
	
	/**
	 * @param uniqueAirportID
	 * @param country
	 * @param city
	 * @param timeZone
	 */
	public Airport(String uniqueAirportID, String country, String city, Double timeZone) {
		super();
		this.uniqueAirportID = uniqueAirportID;
		this.country = country;
		this.city = city;
		this.timeZone = timeZone;
	}
	
	
	/**
	 * @return the uniqueAirportID
	 */
	public String getUniqueAirportID() {
		return uniqueAirportID;
	}
	/**
	 * @param uniqueAirportID the uniqueAirportID to set
	 */
	public void setUniqueAirportID(String uniqueAirportID) {
		this.uniqueAirportID = uniqueAirportID;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the timeZone
	 */
	public Double getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(Double timeZone) {
		this.timeZone = timeZone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		result = prime * result + ((uniqueAirportID == null) ? 0 : uniqueAirportID.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Airport))
			return false;
		Airport other = (Airport) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		if (uniqueAirportID == null) {
			if (other.uniqueAirportID != null)
				return false;
		} else if (!uniqueAirportID.equals(other.uniqueAirportID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Airport [uniqueAirportID=" + uniqueAirportID + ", country=" + country + ", city=" + city + ", timeZone="
				+ timeZone + "]";
	}
	
	

}
