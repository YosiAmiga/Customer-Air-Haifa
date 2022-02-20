package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import entity.Airplane;
import entity.Airport;
import entity.Consts;
import entity.Flight;
import entity.Seat;
import entity.Ticket;
import entity.ReportTest;
import utils.SeatClass;

public class ControlFlight {
	
    
    private static ControlFlight _instance;
    public static ControlFlight getInstance() {
        if (_instance == null)
            _instance = new ControlFlight();
        return _instance;
    }
    
    /**
     * GET DATA FROM DB+
     * 
     */
	/*********GET PREMIUM tickets**********/
	public ArrayList<Seat> getAllSeats(String flightID) {
		ArrayList<Seat> seats= new ArrayList<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEATS_BY_FLIGHT);

                    int i=1;
                    stmt.setString(i++, flightID);
                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		seats.add(
    	            			new Seat(
    	            					rs.getInt(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    return seats;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return seats;
	}
	
	/*********GET PREMIUM tickets**********/
	public ArrayList<Seat> getPremiumTickets(String flightID) {
		ArrayList<Seat> seats= new ArrayList<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_PREMIUM_TICKETS_SEATS);

                    int i=1;
                    stmt.setString(i++, flightID);
                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		seats.add(
    	            			new Seat(
    	            					rs.getInt(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    return seats;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return seats;
	}
	
	/*********GET regular tickets**********/
	public ArrayList<Seat> getRegularTickets(String flightID) {
		ArrayList<Seat> seats= new ArrayList<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_REGULAR_TICKETS_SEATS);

                    int i=1;
                    stmt.setString(i++, flightID);
                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		seats.add(
    	            			new Seat(
    	            					rs.getInt(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    return seats;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return seats;
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
	
	
    /**
     * CREATE NEW DATA TO DB
     * 
     */
	
	/*********Creating a new flight**********/
	public boolean updateFlight(String flightNumber, Timestamp flightDeparture,Timestamp flightArrival,String flightAirplane,
			String flightStatus,int originAirport,int destinationAirport) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_FLIGHT);

                    int i=1;
                    Flight f = new Flight(flightNumber,flightDeparture,flightArrival, flightAirplane, flightStatus,originAirport,destinationAirport);

                    if(flightDeparture != null) {
                    	stmt.setTimestamp(i++, f.getFlightDeparture());                   	
                    }
                    if(flightArrival != null) {
                    	stmt.setTimestamp(i++, f.getFlightArrival());                  	
                    }
                    if(flightAirplane != null) {
                    	stmt.setString(i++, f.getAirplane());
                    }
                    if(flightStatus != null) {
                    	stmt.setString(i++, f.getStatus());
                    }
                    if(originAirport > 0) {
                    	stmt.setInt(i++, f.getOriginAirport());
                    }
                    if(destinationAirport > 0) {
                    	stmt.setInt(i++, f.getDestinationAirport());
                    }
                    
                    //adding null pilot ID to flight because it is created without a crew
                    stmt.setNull(i++, java.sql.Types.VARCHAR);
                    stmt.setNull(i++, java.sql.Types.VARCHAR);
                    
                    if(flightNumber != null ) {
                    	stmt.setString(i++, f.getFlightSerialNumber());                    	
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
	
	/*********Creating a new flight**********/
	public boolean createNewFlight(String flightNumber, Timestamp flightDeparture,Timestamp flightArrival,String flightAirplane,
			String flightStatus,int originAirport,int destinationAirport) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_FLIGHT);

                    int i=1;
                    Flight f = new Flight(flightNumber,flightDeparture,flightArrival, flightAirplane, flightStatus,originAirport,destinationAirport);

                    System.out.println(f);
                    if(flightNumber != null ) {
                    	stmt.setString(i++, f.getFlightSerialNumber());                    	
                    }
                    if(flightDeparture != null) {
                    	stmt.setTimestamp(i++, f.getFlightDeparture());                   	
                    }
                    if(flightArrival != null) {
                    	stmt.setTimestamp(i++, f.getFlightArrival());                  	
                    }
                    if(flightAirplane != null) {
                    	stmt.setString(i++, f.getAirplane());
                    }
                    if(flightStatus != null) {
                    	stmt.setString(i++, f.getStatus());
                    }
                    if(originAirport > 0) {
                    	stmt.setInt(i++, f.getOriginAirport());
                    }
                    if(destinationAirport > 0) {
                    	stmt.setInt(i++, f.getDestinationAirport());
                    }
                    //adding null pilot ID to flight because it is created without a crew
                    stmt.setNull(i++, java.sql.Types.VARCHAR);
                    stmt.setNull(i++, java.sql.Types.VARCHAR);
                  
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
	
	/*********Updating an airport**********/
	public boolean updateAirport(int airportID, String country,String city,Double timezone) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_AIRPORT);

                    int i=1;
                    Airport ap = new Airport(airportID, country, city, timezone);
                    
                    if(country != null) {
                    	stmt.setString(i++, ap.getCountry());                   	
                    }
                    if(city != null) {
                    	stmt.setString(i++, ap.getCity());                   	
                    }
                    stmt.setDouble(i++, ap.getTimeZone());                   	
                    if(airportID > 0 ) {
                    		stmt.setInt(i++, ap.getUniqueAirportID());                    	
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
	
	/*********Deleting an airport**********/
	public boolean deleteAirport(int airportNumber) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DELETE_AIRPORT);

                    int i=1;

                    if(airportNumber > 0) {
                    	stmt.setInt(i++, airportNumber);
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
	
	
	/*********Creating a new airplane**********/
	public boolean updateAirplane(String planeNumber, int planeSize) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_AIRPLANE);
                    deleteSeats(planeNumber);
                    
                    int i=1;
                    Airplane p = new Airplane(planeNumber, planeSize);

                    if(planeNumber != null && planeSize > 0 ) {
                    	stmt.setInt(i++, p.getAirplaneSize());                    	
                    	stmt.setString(i++, p.getAirplaneSerialNumber());
                    }
                    stmt.executeUpdate();

                    //seat SQL
                    //only after the plane is created we can create each seat for it
                    int j=0;
                    while(j < planeSize) {
                    	createNewSeat(p);
                    	j++;
                    }

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
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_AIRPLANE);
                    
                    int i=1;
                    Airplane p = new Airplane(planeNumber, planeSize);
                    if(planeNumber != null && planeSize > 0 ) {
                    	stmt.setString(i++, p.getAirplaneSerialNumber());
                    	stmt.setInt(i++, p.getAirplaneSize());                    	
                    }
                    stmt.executeUpdate();

                    //seat SQL
                    //only after the plane is created we can create each seat for it
                    int j=0;
                    while(j < planeSize) {
                    	createNewSeat(p);
                    	j++;
                    }

                    return true;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return false;
	}
	
	/*********Deleting an airplane**********/
	public boolean deleteAirplane(String planeNumber) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DELETE_AIRPLANE);
                    //before deleting the airplane we must delete his seats
                    deleteSeats(planeNumber);

                    if(planeNumber != null) {
                    	stmt.setString(1, planeNumber);
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
	
	
	/*********Deleting the seat of a deleted airplane**********/
	public boolean deleteSeats(String planeNumber) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_DELETE_SEAT);

                    int i=1;

                    if(planeNumber != null) {
                    	stmt.setString(i++, planeNumber);
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
	
	//create a new seat for airplane
	public boolean createNewSeat(Airplane p) {
		
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);

                    //seat SQL
                    PreparedStatement stmt2 = conn.prepareStatement(Consts.SQL_ADD_SEAT);
                    
                    
            		//TODO FIX calculation and ADD SQL to seats into DB
                    
            		/**
            		 * First-Class seats = %10 of plane size
            		 * Business seats = %30 of plane size
            		 * Low-Cost seats = %60 of plane size*/
            		
            		/**
            		 * First-Class seats = %10 of plane size
            		 * Business seats = %30 of plane size
            		 * Low-Cost seats = %60 of plane size
                     * */

                //filling the First-Class seats
            		for (int j = 1; j < ((p.getAirplaneSize())/10) +1; j++) {
            			//set the seat key to be ->
            			Seat s = new Seat(j,"a",p,SeatClass.FirstClass);
            			stmt2.setInt(1, s.getRowNumber());
            			stmt2.setString(2, s.getColumnLetter());
            			stmt2.setString(3, s.getAirplane().getAirplaneSerialNumber());
            			stmt2.setString(4, "First-Class");
            			stmt2.executeUpdate();
            			System.out.println(s);
            		}
            		//filling the Business seats
            		for (int j = 1; j < ((p.getAirplaneSize())/10)*3 +1 ; j++) {
            			//set the seat key to be ->
                        Seat s = new Seat(j,"b",p,SeatClass.Business);
            			stmt2.setInt(1, s.getRowNumber());
            			stmt2.setString(2, s.getColumnLetter());
            			stmt2.setString(3, s.getAirplane().getAirplaneSerialNumber());
            			stmt2.setString(4, "Business");
            			stmt2.executeUpdate();
            			System.out.println(s);
            		}
            		//filling the Low-Cost seats
            		for (int j = 1; j < ((p.getAirplaneSize())/10)*6 +1 ; j++) {
            			//set the seat key to be ->

                        Seat s = new Seat(j,"c",p,SeatClass.LowCost);
            			stmt2.setInt(1, s.getRowNumber());
            			stmt2.setString(2, s.getColumnLetter());
            			stmt2.setString(3, s.getAirplane().getAirplaneSerialNumber());
            			stmt2.setString(4, "Low-Cost");
            			stmt2.executeUpdate();
            			System.out.println(s);
            		}

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
