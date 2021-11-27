package entity;

public class Airport {

	private int uniqueAirportID;
	private String country;
	private String city;
	private Double timeZone;
	
	
	/**
	 * @param uniqueAirportID
	 * @param country
	 * @param city
	 * @param timeZone
	 */
	public Airport(int uniqueAirportID, String country, String city,  Double timeZone) {
		super();
		this.uniqueAirportID = uniqueAirportID;
		this.country = country;
		this.city = city;
		this.timeZone = timeZone;
	}
	
	
	/**
	 * @return the uniqueAirportID
	 */
	public int getUniqueAirportID() {
		return uniqueAirportID;
	}
	/**
	 * @param uniqueAirportID the uniqueAirportID to set
	 */
	public void setUniqueAirportID(int uniqueAirportID) {
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
		return result;
	}


	@Override
	public String toString() {
		return "Airport ID: " + uniqueAirportID + " ,Country: " + country + " ,City: " + city;
	}
	

	
	

}
