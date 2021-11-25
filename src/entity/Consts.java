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

	//update queries

	  
	  
	private static String getDBPath() {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf('/'));
		 System.out.println("bababa");
		return decoded + "src/entity/AirHaifa.accdb";
		} else {
			System.out.println("lalala");
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
