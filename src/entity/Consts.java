package entity;

import java.net.URLDecoder;

public class Consts {
	public static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://"+DB_FILEPATH;
	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";
	
	//insert queries
	  public static final String SQL_ADD_AIRPLANE =
			  "{ call SQL_ADD_AIRPLANE(?,?) }";
	  public static final String SQL_ADD_AIRPORT =
			  "{ call SQL_ADD_AIRPORT(?,?,?,?) }";

	  public static final String SQL_ADD_FLIGHT =
			  "{ call SQL_ADD_FLIGHT(?,?,?,?,?,?,?,?,?) }";
	  public static final String SQL_ADD_SEAT =
			  "{ call SQL_ADD_SEAT(?,?,?,?) }";
	  
	//selection queries
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

	//update queries
	  public static final String SQL_UPDATE_FLIGHT= "{ call SQL_UPDATE_FLIGHT(?,?,?,?,?,?,?,?,?) }";
	  public static final String SQL_UPDATE_AIRPORT= "{ call SQL_UPDATE_AIRPORT(?,?,?,?) }";
	  public static final String SQL_UPDATE_AIRPLANE= "{ call SQL_UPDATE_AIRPLANE(?,?) }";
	  
	//delete queries
	  public static final String SQL_DELETE_AIRPLANE =
			  "{ call SQL_DELETE_AIRPLANE(?) }";
	  
	  public static final String SQL_DELETE_AIRPORT =
			  "{ call SQL_DELETE_AIRPORT(?) }";
	  public static final String SQL_DELETE_SEAT =
			  "{ call SQL_DELETE_SEAT(?) }";
	  
	  
	private static String getDBPath() {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf('/'));
		return decoded + "src/entity/AirHaifa.accdb";
		} else {
		 decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
		return decoded + "src/entity/AirHaifa.accdb";
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
