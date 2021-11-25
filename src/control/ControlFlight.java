package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Airplane;
import entity.Airport;
import entity.Consts;
import entity.Flight;

public class ControlFlight {
	
    
    private static ControlFlight _instance;
    public static ControlFlight getInstance() {
        if (_instance == null)
            _instance = new ControlFlight();
        return _instance;
    }
    
    /******GET FLIGHT******/
	//using SQL query to get data from Access file
	public ArrayList<Flight> getFlights() throws Exception{
		 ArrayList<Flight> flights= new ArrayList<Flight>();
	        try {
	            Class.forName(Consts.JDBC_STR);
	            //calling the GET query of all the flights
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	    	            PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_FLIGHTS);
	    	            ResultSet rs = stmt.executeQuery()){  
	    	            	while (rs.next()) {
	    	            		int i = 1;
	    	            		flights.add(
	    	            			new Flight(
	    	            					rs.getString(i++),
	    	            					rs.getTimestamp(i++),
	    	            					rs.getTimestamp(i++),
	    	            					rs.getString(i++),
	    	            					rs.getString(i++),
	    	            					rs.getInt(i++),
	    	            					rs.getInt(i++)
//	    	            					rs.getInt(i++),
//	    	            					rs.getInt(i++)
	    	            					
	    	            					));	    	            		
	    	            		}

	    	            }


	             catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return flights;
	}
	
	/******GET AIRPORT******/
	//using SQL query to get data from Access file
	public ArrayList<Airport> getAirports() throws Exception{
		 ArrayList<Airport> airports= new ArrayList<Airport>();
	        try {
	            Class.forName(Consts.JDBC_STR);
	            //calling the GET query of all the airplanes
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	    	            PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_AIRPORTS);
	    	            ResultSet rs = stmt.executeQuery()){  
	    	            	while (rs.next()) {
	    	            		int i = 1;
	    	            		airports.add(new Airport(
	    	            				rs.getInt(i++),
	    	            				rs.getString(i++),
	    	            				rs.getString(i++), 	    	
	    	            				rs.getDouble(i++)
	    	            				));
	    	            		}

	    	            }

	             catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return airports;
	}
	
	/******GET AIRPLANE******/
	//using SQL query to get data from Access file
	public ArrayList<Airplane> getAirplanes() throws Exception{
		 ArrayList<Airplane> airplanes= new ArrayList<Airplane>();
	        try {
	            Class.forName(Consts.JDBC_STR);
	            //calling the GET query of all the airplanes
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	    	            PreparedStatement stmt =   conn.prepareStatement(Consts.SQL_GET_ALL_AIRPLANES);
	    	            ResultSet rs = stmt.executeQuery()){  
	    	            	while (rs.next()) {
	    	            		int i = 1;
	    	            		airplanes.add(new Airplane(rs.getString(i++), rs.getInt(i++)));
	    	            		}

	    	            }

	             catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return airplanes;
	}
	
	/*********Creating a new airport**********/
	public boolean createNewAirport(int airportID, String country,String city,Double timezone) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_AIRPORT);

                    int i=1;
                    Airport ap = new Airport(airportID, country, city, timezone);
                    if(airportID > 0 ) {
                    	stmt.setInt(i++, ap.getUniqueAirportID());                    	
                    }
                    if(country != null) {
                    	stmt.setString(i++, ap.getCountry());                   	
                    }
                    if(city != null) {
                    	stmt.setString(i++, ap.getCity());                   	
                    }
                    	stmt.setDouble(i++, ap.getTimeZone());                   	

                    
                    stmt.executeUpdate();
                    return true;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return false;
	}
	
	/*********Creating a new airplane**********/
	public boolean createNewAirplane(String planeNumber, int planeSize) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_AIRPLANE);

                    int i=1;
                    Airplane p = new Airplane(planeNumber, planeSize);
                    if(planeNumber != null && planeSize > 0 ) {
                    	stmt.setString(i++, p.getAirplaneSerialNumber());
                    	stmt.setInt(i++, p.getAirplaneSize());                    	
                    }
                    
                    stmt.executeUpdate();
                    return true;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return false;
	}

}
