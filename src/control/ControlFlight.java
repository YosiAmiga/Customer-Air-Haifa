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
            for (Flight f : flights) {
            	
            	System.out.println(f.toString());
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
	    	            		airports.add(new Airport(rs.getString(i++),
	    	            				rs.getString(i++), 
	    	            				rs.getDouble(i++),rs.getInt(i++)));
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
	
	public boolean createNewPlane(String planeNumber, int planeSize) {
		
		try {
            Class.forName(Consts.CONN_STR);
            
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_INS_AIRPLANE);
//                    ResultSet rs = stmt.executeQuery(){
//                    	
//                    }
                    int i=1;
            
                    stmt.setString(i++, planeNumber);
                    stmt.setInt(i++, planeSize);
                    stmt.executeUpdate();
                   
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}

}
