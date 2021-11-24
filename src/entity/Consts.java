package entity;

import java.net.URLDecoder;

public class Consts {
	public static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://"+DB_FILEPATH+";COLUMNORDER=DISPLAY";
	
	//insert queries
	  public static final String SQL_INS_AIRPLANE ="INSERT INTO  Airplane( AirplaneSerialNumber,AirplaneSize)\n" +
			  "VALUES(?,?,?,?,?,?);";
	//selection queries
	
	  public static final String SQL_GET_ALL_AIRPLANES= "SELECT * FROM Airplane;";

	//update queries

	  
	  
	private static String getDBPath() {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf('/'));
		return decoded + "/database/AirHaifa.accdb";
		} else {
		 decoded = decoded.substring(0, decoded.lastIndexOf("AirHaifa/"));
		return decoded + "DesignEX2/src//entity/AirHaifa.accdb";
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
