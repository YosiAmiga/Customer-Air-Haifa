package entity;

import java.net.URLDecoder;

public class Consts {
	public static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://"+DB_FILEPATH;
	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";
	
	//insert queries
	  public static final String SQL_ADD_PREMIUM_TICKET =
			  "{ call SQL_ADD_PREMIUM_TICKET(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	  public static final String SQL_ADD_TICKET =
			  "{ call SQL_ADD_TICKET(?,?,?,?,?,?,?,?,?) }";
	  
	  public static final String SQL_ADD_ORDER =
	  "{ call SQL_ADD_ORDER(?,?) }";
	  public static final String SQL_ADD_AIRPLANE =
			  "{ call SQL_ADD_AIRPLANE(?,?) }";
	  public static final String SQL_ADD_AIRPORT =
			  "{ call SQL_ADD_AIRPORT(?,?,?,?) }";

	  public static final String SQL_ADD_FLIGHT =
			  "{ call SQL_ADD_FLIGHT(?,?,?,?,?,?,?,?,?) }";
	  public static final String SQL_ADD_SEAT =
			  "{ call SQL_ADD_SEAT(?,?,?,?) }";
	  public static final String SQL_ADD_CUSTOMER =
			  "{ call SQL_ADD_CUSTOMER(?,?,?,?,?,?,?) }";
	  public static final String SQL_ADD_FAMILY_LINK =
			  "{ call SQL_ADD_FAMILY_LINK(?,?) }";
	  public static final String SQL_ADD_FAMILY_MEMBER =
			  "{ call SQL_ADD_FAMILY_MEMBER(?,?,?) }";
	  
	  
	//selection queries
	  
	  public static final String SQL_GET_FAMILY= "{ call SQL_GET_FAMILY(?) }";

	  
	  public static final String SQL_GET_ORDERS= "SELECT Order.OrderID, Order.OrderDate, Order.Payment\n"
	  		+ "FROM [Order];\n"
	  		+ "";

	  //those 3 are for available tickets
	  public static final String SQL_SEATS_BY_FLIGHT= "{ call SQL_SEATS_BY_FLIGHT(?) }";
	  public static final String SQL_REGULAR_TICKETS_SEATS= "{ call SQL_REGULAR_TICKETS_SEATS(?) }";
	  public static final String SQL_PREMIUM_TICKETS_SEATS= "{ call SQL_PREMIUM_TICKETS_SEATS(?) }";
	  
	  
	  
	  public static final String SQL_FLIGHT_ENTERTAINMENT= "{ call SQL_FLIGHT_ENTERTAINMENT(?) }";
	  public static final String SQL_GET_FLIGHTS_BY_SEARCH= "{ call SQL_GET_FLIGHTS_BY_SEARCH(?,?) }";
	  
	  public static final String SQL_GET_CUSTOMERS= "SELECT Customer.PassportNumber, Customer.FirstName, Customer.LastName, Customer.Email, Customer.Citizenship, Customer.DateOfBirth, Customer.Password\n"
	  		+ "FROM Customer;\n"
	  		+ "";

	  public static final String SQL_GET_PASSWORD= "{ call SQL_GET_PASSWORD(?) }";
	  
	  public static final String SQL_GET_CUSTOMERS_PASSWORDS2= "SELECT Customer.PassportNumber, Customer.Password\n"
	  		+ "FROM Customer;\n"
	  		+ "";
	  public static final String SQL_GET_CUSTOMERS_PASSWORDS= "{ call SQL_GET_CUSTOMERS_PASSWORDS}";

	  public static final String SQL_GET_FLIGHT= "{ call SQL_GET_FLIGHT(?) }";

	  public static final String SQL_GET_ALL_FLIGHTS= "SELECT FlightSerialNumber,"
	  		+ "FlightDeparture, FlightArrival, "
	  		+ "AirplaneSerialNumber, Status,"
	  		+ "OriginAirportID, DestinationAirportID"
	  		+ "\nFROM Flight";
	  public static final String SQL_GET_ALL_AIRPORTS= "SELECT UniqueAirportID,"
	  		+ " Country,"
	  		+ " City,"
	  		+ "TimeZone"
	  		+ "\nFROM Airport";
	  
	  public static final String SQL_GET_ALL_AIRPLANES= "SELECT * FROM Airplane";
	  public static final String SQL_GET_AIRPLANE= "{ call SQL_GET_AIRPLANE(?) }";
	  public static final String FLIGHTS_FROM_DATE= "{ call FLIGHTS_FROM_DATE(?) }";
	  public static final String GET_SEAT_BY_FLIGHT= "{ call GET_SEAT_BY_FLIGHT(?) }";
	  
	//update queries
	  public static final String SQL_UPDATE_CUSTOMER= "{ call SQL_UPDATE_CUSTOMER(?,?,?,?,?,?,?) }";

	  public static final String SQL_UPDATE_FLIGHT= "{ call SQL_UPDATE_FLIGHT(?,?,?,?,?,?,?,?,?) }";
	  public static final String SQL_UPDATE_AIRPORT= "{ call SQL_UPDATE_AIRPORT(?,?,?,?) }";
	  public static final String SQL_UPDATE_AIRPLANE= "{ call SQL_UPDATE_AIRPLANE(?,?) }";
	  public static final String SQL_UPDATE_FLIGHT_STATUS= "{ call SQL_UPDATE_FLIGHT_STATUS(?,?,?,?,?,?,?,?,?) }";
	  
	//delete queries
	  public static final String SQL_DELETE_AIRPLANE =
			  "{ call SQL_DELETE_AIRPLANE(?) }";
	  
	  public static final String SQL_DELETE_AIRPORT =
			  "{ call SQL_DELETE_AIRPORT(?) }";
	  public static final String SQL_DELETE_SEAT =
			  "{ call SQL_DELETE_SEAT(?) }";
	  
	//reports queries
	  
	  public static final String ORDERS_REPORT= "{ call ORDERS_REPORT }";
	  
	  public static final String ORDERS_REPORT2 = "SELECT Customer.PassportNumber, Customer.FirstName, Customer.LastName, Sum(IIf((Hour(Flight.FlightDeparture) Between 5 And 10),1,0)) AS Morning, Sum(IIf((Hour(Flight.FlightDeparture) Between 11 And 16),1,0)) AS Afternoon, Sum(IIf((Hour(Flight.FlightDeparture) Between 17 And 22),1,0)) AS Evning, Sum(IIf((Hour(Flight.FlightDeparture)> 23 OR Hour(Flight.FlightDeparture) <= 4),1,0)) AS Night\n"
	  		+ "FROM Customer INNER JOIN (Flight INNER JOIN Ticket ON Flight.FlightSerialNumber = Ticket.FlightSerialNumber) ON Customer.PassportNumber = Ticket.PassportNumber\n"
	  		+ "GROUP BY Customer.PassportNumber, Customer.FirstName, Customer.LastName;\n"
	  		+ "";
	  public static final String BIG_FLIGHTS_REPORT =
			  "SELECT Flight.FlightSerialNumber, Airport.Country, Airport.City, Airport_1.Country AS CountryTo, Airport_1.City AS CityTo, Flight.FlightDeparture, Flight.FlightArrival, Flight.Status \n"
			  + "FROM Airport INNER JOIN ((Flight INNER JOIN Airport AS Airport_1 ON Flight.DestinationAirportID = Airport_1.UniqueAirportID) INNER JOIN QueryCountSeat ON Flight.AirplaneSerialNumber = QueryCountSeat.TailNumber) ON TblAirport.Code = TblFlight.CodeDepartureAirport\n"
			  + "WHERE (((TblFlight.DepartureTime)>=[startDate] And (TblFlight.DepartureTime)<=[endDate]) AND ((TblFlight.LandingTime)>=[startDate] And (TblFlight.LandingTime)<=[endDate]) AND ((QueryCountSeat.CountOfSeatID)>=[seats]))\n"
			  + "GROUP BY TblFlight.UniqueSerialNumber, TblAirport.Country, TblAirport.City, TblAirport_1.Country, TblAirport_1.City, TblFlight.DepartureTime, TblFlight.LandingTime, TblFlight.Status\n"
			  + "ORDER BY TblAirport_1.Country DESC , TblAirport_1.City DESC , TblFlight.DepartureTime DESC , TblFlight.LandingTime DESC; }";
	  
	  public static final String BIG_FLIGHTS =
"SELECT TblFlight.UniqueSerialNumber, TblAirport.Country, TblAirport.City, TblAirport_1.Country AS CountryTo, TblAirport_1.City AS CityTo, TblFlight.DepartureTime, TblFlight.LandingTime, TblFlight.Status\n"
+ "FROM TblAirport INNER JOIN ((TblFlight INNER JOIN TblAirport AS TblAirport_1 ON TblFlight.CodeDestinationAirport = TblAirport_1.Code) INNER JOIN QueryCountSeat ON TblFlight.TailNumber = QueryCountSeat.TailNumber) ON TblAirport.Code = TblFlight.CodeDepartureAirport\n"
+ "WHERE (((TblFlight.DepartureTime)>=[startDate] And (TblFlight.DepartureTime)<=[endDate]) AND ((TblFlight.LandingTime)>=[startDate] And (TblFlight.LandingTime)<=[endDate]) AND ((QueryCountSeat.CountOfSeatID)>=[seats]))\n"
+ "GROUP BY TblFlight.UniqueSerialNumber, TblAirport.Country, TblAirport.City, TblAirport_1.Country, TblAirport_1.City, TblFlight.DepartureTime, TblFlight.LandingTime, TblFlight.Status\n"
+ "ORDER BY TblAirport_1.Country DESC , TblAirport_1.City DESC , TblFlight.DepartureTime DESC , TblFlight.LandingTime DESC;";
	  
	  
		private static String getDBPath() {
			 try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
			 decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/entity/CustomerFly.accdb";
			} else {
				 decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
					return decoded + "src/entity/CustomerFly.accdb";
			}
			} catch (Exception e) {
			 e.printStackTrace();
			 return null;
			 
			}
			}
		
		public static String getPath(String s) {
			 try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
			 decoded = decoded.substring(0, decoded.lastIndexOf("/"))+"/"+s;
			return decoded;
			} else {
			 return s;
			}
			} catch (Exception e) {
			 e.printStackTrace();
			 return null;
			 
			}
			}

}
