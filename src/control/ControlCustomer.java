package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import entity.Airplane;
import entity.Airport;
import entity.Consts;
import entity.Customer;
import entity.EntertainmentPiece;
import entity.Flight;
import entity.Order;
import entity.Pilot;
import entity.Seat;
import utils.SeatClass;

public class ControlCustomer {
	
    
    private static ControlCustomer _instance;
    public static ControlCustomer getInstance() {
        if (_instance == null)
            _instance = new ControlCustomer();
        return _instance;
    }
    
    /*********Creating a new premium ticket**********/
	public boolean createNewPremiumTicket(int passport, int orderID, String flight, String meal,
			String seatClass, int price, int row, String col, String airplane,
			int maxWeight, String request1,String request2,String request3) {
		
		try {
            Class.forName(Consts.JDBC_STR);           
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_PREMIUM_TICKET);
                    //Creating the new family member and adding to the database
                    int i=1;
                    stmt.setInt(i++, passport);
                    stmt.setInt(i++, orderID);
                    stmt.setString(i++, flight); 
                    stmt.setString(i++, meal);
                    stmt.setString(i++, seatClass); 
                    stmt.setInt(i++, price); 
                    stmt.setInt(i++, row);
                    stmt.setString(i++, col);
                    stmt.setString(i++, airplane);                   	              
                    stmt.setInt(i++, maxWeight);
                    stmt.setString(i++, request1);
                    stmt.setString(i++, request2);
                    stmt.setString(i++, request3);
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
    /*********Creating a new regular ticket**********/
	public boolean createNewTicket(int passport, int orderID, String flight, String meal,
			String seatClass, int price, int row, String col, String airplane) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_TICKET);
                    //Creating the new family member and adding to the database
                    int i=1;
                    stmt.setInt(i++, passport);
                    stmt.setInt(i++, orderID);
                    stmt.setString(i++, flight); 
                    stmt.setString(i++, meal);
                    stmt.setString(i++, seatClass); 
                    stmt.setInt(i++, price); 
                    stmt.setInt(i++, row);
                    stmt.setString(i++, col);
                    stmt.setString(i++, airplane);                   	              
                    
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
	
	/*********Creating a new order**********/
	public boolean createNewOrder(String payment) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_ORDER);
                    //Creating the new family member and adding to the database
                    int i=1;
                    java.sql.Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
                    stmt.setDate(i++, today);	
                    stmt.setString(i++, payment);                   	              
                    
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
	
	/*********get latest order**********/
	public int getLatestOrder() {
		ArrayList<Order> orders= new ArrayList<>();
		int maxOrderIsNewest = 0;
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ORDERS);


                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		orders.add(
    	            			new Order(
    	            					rs.getInt(j++),
    	            					rs.getDate(j++),
    	            					rs.getString(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    //return the max order id= the newest order!
                    for(Order o : orders) {
                    	if(o.getOrderID() > maxOrderIsNewest) {
                    		maxOrderIsNewest = o.getOrderID();
                    	}
                    }
                    
                    return maxOrderIsNewest;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return maxOrderIsNewest;
	}
	/*********Search Entertainment pieces**********/
	public ArrayList<EntertainmentPiece> searchEntertainmentPieces(String flight) {
		ArrayList<EntertainmentPiece> pieces= new ArrayList<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_FLIGHT_ENTERTAINMENT);

                    int i=1;
                    stmt.setString(i++, flight);

                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		pieces.add(
    	            			new EntertainmentPiece(
    	            					rs.getString(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    return pieces;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return pieces;
	}
	
    
	/*********Search Flights**********/
	public ArrayList<Flight> searchFlights(int originID, int destID) {
		ArrayList<Flight> flights= new ArrayList<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_FLIGHTS_BY_SEARCH);

                    int i=1;
                    stmt.setInt(i++, originID);
                    stmt.setInt(i++, destID);
                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	            		int j = 1;
    	            		flights.add(
    	            			new Flight(
    	            					rs.getString(j++),
    	            					rs.getTimestamp(j++),
    	            					rs.getTimestamp(j++),
    	            					rs.getString(j++),
    	            					rs.getString(j++),
    	            					rs.getInt(j++),
    	            					rs.getInt(j++)
    	            					));	    	            		
    	            		}
                    	
                    }
                    return flights;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return flights;
	}
	
    /******GET Customer******/
	//using SQL query to get data from Access file
	public Customer getCustomer(int id) throws Exception{
		 
		Customer custToReturn = null;
		ArrayList<Customer> customers= new ArrayList<>();
	        try {
	            Class.forName(Consts.JDBC_STR);
	            //calling the GET query of all the flights
	            try (
	            		Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	    	            PreparedStatement stmt =  conn.prepareStatement(Consts.SQL_GET_CUSTOMERS);	

	            		ResultSet rs = stmt.executeQuery()){  
	    	            	while (rs.next()) {
	    	            		int i =1;
	    	            		customers.add( 
	    	            				new Customer(
	    	            				rs.getInt(i++),
	    	            				rs.getString(i++),
	    	            				rs.getString(i++),
	    	            				rs.getString(i++),
	    	            				rs.getString(i++),
	    	            				rs.getDate(i++),
	    	            				rs.getString(i++)
	    	            				));
	    	            		
//	    	    	            		customer.setPassportNumber();
//	    	    	            		customer.setFirstName();;
//	    	    	            		customer.setLastName(rs.getString(i++));
//	    	    	            		customer.setMainCitizenShip(rs.getString(i++));
//	    	    	            		customer.setDateOfBirth();
//	    	    	            		customer.setPassword(rs.getString(i++));
	    	            	}
	            		}
	            

	             catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        for(Customer c : customers) {
	        	if(c.getPassportNumber() == id) {
	        		custToReturn = c;
	        	}
	        }
	        
	        return custToReturn;
	}
    
    /**
     * GET DATA FROM DB
     * 
     */
	
	/********get family members to combo box for ticket use**********/
	public ArrayList<Integer> getFamilyMembers(int customerID) {
		ArrayList<Integer> familyID = new ArrayList<>();
		HashSet<Integer> familyIDSet = new HashSet<>();

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);

                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_FAMILY);
                    stmt.setInt(1, customerID);
                    
                    try(ResultSet rs = stmt.executeQuery())
                    {
    	            	while (rs.next()) {
    	                    System.out.println(rs.getInt(1));
    	                    System.out.println(rs.getInt(2));
    	                    familyIDSet.add(rs.getInt(1));
    	                    familyIDSet.add(rs.getInt(2));

    	            	}
                    	
                    }
                    familyID.addAll(familyIDSet);
                    System.out.println("family ID:");
                    System.out.println(familyID);

                    return familyID;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return familyID;
	}
	
	/*********Linking family**********/
	public boolean linkFamily(Customer c, int customerID) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    //After creating the family member, create the family link
                    PreparedStatement stmt2 = conn.prepareStatement(Consts.SQL_ADD_FAMILY_LINK);
                    int j=1;
                    
                    stmt2.setInt(j++, c.getPassportNumber());
                    stmt2.setInt(j++, customerID);
                    stmt2.executeUpdate();
                    
                    return true;
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return false;
	}
	
	/*********Creating a new family**********/
	public boolean createNewFamily(Customer c, int customerID) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_FAMILY_MEMBER);
                    //Creating the new family member and adding to the database
                    int i=1;
              
                    System.out.println(c);
                    if(c.getPassportNumber() > 0 ) {
                    	stmt.setInt(i++, c.getPassportNumber());                    	
                    }
                    if(c.getFirstName() != null) {
                    	stmt.setString(i++, c.getFirstName());                   	
                    }
                    if(c.getLastName() != null) {
                    	stmt.setString(i++, c.getLastName());                   	              
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
	/*********Update a customer**********/
	public boolean updateCustomer(Customer c) {

		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
            		//plane SQL
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_CUSTOMER);

                    int i=1;
//                  Customer c = new Customer(id, fName, lName, mail, citizen, bDay, password);
                  //if bDay is LocalDate: use this
//                  Date date = java.sql.Date.valueOf(c.getDateOfBirth());
                  Date date = c.getDateOfBirth();

                  System.out.println(c);
                  if(c.getFirstName() != null) {
                  	stmt.setString(i++, c.getFirstName());                   	
                  }
                  if(c.getLastName() != null) {
                  	stmt.setString(i++, c.getLastName());                   	
                  }
                  if(c.getEmail() != null) {
                  	stmt.setString(i++, c.getEmail());                   	
                  }
                  if(c.getMainCitizenShip() != null) {
                  	stmt.setString(i++, c.getMainCitizenShip());                   	
                  }
                  if(date != null) {
                  	stmt.setDate(i++, date);
                  }
                  if(c.getPassword() != null) {
                  	stmt.setString(i++, c.getPassword());                   	
                  }
                  //the passport number should be last, using it to search the customer table with the query
                  if(c.getPassportNumber() > 0 ) {
                	  stmt.setInt(i++, c.getPassportNumber());                    	
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
	
	/*********Creating a new customer**********/
	public boolean createNewCustomer(Customer c) {
		try {
            Class.forName(Consts.JDBC_STR);
            
            try {
            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_ADD_CUSTOMER);

                    int i=1;
//                    Customer c = new Customer(id, fName, lName, mail, citizen, bDay, password);
                    //if bDay is LocalDate: use this
//                    Date date = java.sql.Date.valueOf(c.getDateOfBirth());
                    Date date = c.getDateOfBirth();

                    System.out.println(c);
                    if(c.getPassportNumber() > 0 ) {
                    	stmt.setInt(i++, c.getPassportNumber());                    	
                    }
                    if(c.getFirstName() != null) {
                    	stmt.setString(i++, c.getFirstName());                   	
                    }
                    if(c.getLastName() != null) {
                    	stmt.setString(i++, c.getLastName());                   	
                    }
                    if(c.getEmail() != null) {
                    	stmt.setString(i++, c.getEmail());                   	
                    }
                    if(c.getMainCitizenShip() != null) {
                    	stmt.setString(i++, c.getMainCitizenShip());                   	
                    }
                    if(date != null) {
                    	stmt.setDate(i++, date);
                    }
                    if(c.getPassword() != null) {
                    	stmt.setString(i++, c.getPassword());                   	
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
    /******GET PASSWORDS******/
	//using SQL query to get data from Access file
	public HashMap<Integer, String> getAllCustomers() throws Exception{
		 //key is customer id, value is password
		 HashMap<Integer, String> customersPasswords = new HashMap<>();
	        try {
	            Class.forName(Consts.JDBC_STR);
	            //calling the GET query of all the flights
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	    	            PreparedStatement stmt =  conn.prepareStatement(Consts.SQL_GET_CUSTOMERS_PASSWORDS2);	            		
	    	            ResultSet rs = stmt.executeQuery()){  
	    	            	while (rs.next()) {
	    	            		int i = 1;
	    	            		customersPasswords.put(rs.getInt(i++), rs.getString(i++));	    	            		
	    	            	}
	            		}

	             catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        return customersPasswords;
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
            		
            		//filling the First-Class seats
            		for (int j = 1; j < (p.getAirplaneSize() + 1)/5 ; j++) {
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
            		for (int j = (p.getAirplaneSize() + 1)/5; j < (p.getAirplaneSize() + 1)/2 ; j++) {
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
            		for (int j = (p.getAirplaneSize() + 1)/2; j < (p.getAirplaneSize() + 1) ; j++) {
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
