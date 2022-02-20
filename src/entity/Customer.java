package entity;

import java.time.LocalDate;
import java.sql.*;
public class Customer {
	
	private int passportNumber;
	private String firstName;
	private String LastName;
	private String email;
	private String mainCitizenShip;
	private Date dateOfBirth;
	private String password;
	
	
	
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}
	
	public int getPassportNumber() {
		return passportNumber;
	}

	/*Constructor for Family member - not a user!*/
	public Customer(int passportNumber, String firstName, String lastName) {
		super();
		this.passportNumber = passportNumber;
		this.firstName = firstName;
		this.LastName = lastName;

	}
	
	/**Constructor with password
	 * @param passportNumber
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param mainCitizenShip
	 * @param dateOfBirth
	 * @param password
	 */
	public Customer(int passportNumber, String firstName, String lastName, String email, String mainCitizenShip,
			Date dateOfBirth, String password) {
		super();
		this.passportNumber = passportNumber;
		this.firstName = firstName;
		this.LastName = lastName;
		this.email = email;
		this.mainCitizenShip = mainCitizenShip;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
	}

	/**
	 * Constructor without password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param mainCitizenShip
	 * @param dateOfBirth
	 */
	public Customer(int passportNumber, String firstName, String lastName, String email, String mainCitizenShip, Date dateOfBirth) {
		super();
		this.passportNumber = passportNumber;
		this.firstName = firstName;
		this.LastName = lastName;
		this.email = email;
		this.mainCitizenShip = mainCitizenShip;
		this.dateOfBirth = dateOfBirth;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainCitizenShip() {
		return mainCitizenShip;
	}

	public void setMainCitizenShip(String mainCitizenShip) {
		this.mainCitizenShip = mainCitizenShip;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((mainCitizenShip == null) ? 0 : mainCitizenShip.hashCode());
		result = prime * result + passportNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (mainCitizenShip == null) {
			if (other.mainCitizenShip != null)
				return false;
		} else if (!mainCitizenShip.equals(other.mainCitizenShip))
			return false;
		if (passportNumber != other.passportNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [passportNumber=" + passportNumber + ", firstName=" + firstName + ", LastName=" + LastName
				+ ", email=" + email + ", mainCitizenShip=" + mainCitizenShip + ", dateOfBirth=" + dateOfBirth
				+ ", password=" + password + "]";
	}


	
	
	
	
	

}
