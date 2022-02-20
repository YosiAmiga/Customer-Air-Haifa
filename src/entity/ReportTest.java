package entity;

public class ReportTest {
	private int passportNumber;
	private String firstName;
	private String lastName;
	private int morning;
	private int afternoon;
	private int evning;
	private int night;
	/**
	 * @param passportNumber
	 * @param firstName
	 * @param lastName
	 * @param morning
	 * @param afternoon
	 * @param evning
	 * @param night
	 */
	public ReportTest(int passportNumber, String firstName, String lastName, int morning, int afternoon, int evning,
			int night) {
		super();
		this.passportNumber = passportNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.morning = morning;
		this.afternoon = afternoon;
		this.evning = evning;
		this.night = night;
	}
	public int getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getMorning() {
		return morning;
	}
	public void setMorning(int morning) {
		this.morning = morning;
	}
	public int getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(int afternoon) {
		this.afternoon = afternoon;
	}
	public int getEvning() {
		return evning;
	}
	public void setEvning(int evning) {
		this.evning = evning;
	}
	public int getNight() {
		return night;
	}
	public void setNight(int night) {
		this.night = night;
	}
	@Override
	public String toString() {
		return "ReportTest [passportNumber=" + passportNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", morning=" + morning + ", afternoon=" + afternoon + ", evning=" + evning + ", night=" + night + "]";
	}
}
