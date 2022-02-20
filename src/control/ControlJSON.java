package control;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import entity.Airplane;
import entity.Consts;
import entity.Flight;
import entity.Seat;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.SeatClass;

public class ControlJSON {

	public static ArrayList<Flight> flightsList = new ArrayList<Flight>();
	public static ArrayList<Airplane> airplanesList = new ArrayList<Airplane>();
	public static ArrayList<Seat> seatsList = new ArrayList<Seat>();
	private static ControlJSON instance;
	private static ControlFlight controlFlight;

	public static ControlJSON getInstance() {
		if (instance == null)
			instance = new ControlJSON();
		return instance;
	}
	
	public static void exportToJSON(java.sql.Date today) 
	{

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(
							Consts.FLIGHTS_FROM_DATE)){
					stmt.setDate(1, today);
					ResultSet rs = stmt.executeQuery(); {
				JsonArray updatedFlights = new JsonArray();
				while (rs.next()) {
					JsonObject updatedF = new JsonObject();

					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
						updatedF.put(rs.getMetaData().getColumnName(i), rs.getString(i));

					updatedFlights.add(updatedF);
				}

				JsonObject doc = new JsonObject();
				doc.put("Flights", updatedFlights);

				File file = new File("json/Flights.json");
				file.getParentFile().mkdir();

				try (FileWriter writer = new FileWriter(file)) {
					writer.write(Jsoner.prettyPrint(doc.toJson()));
					writer.flush();
					 Alert alert = new Alert(AlertType.INFORMATION, "Flights data imported successfully!");
					 alert.setHeaderText("Success");
					 alert.setTitle("Success Data Import");
					 alert.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
					}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	// this method will import from json file to our java - help method
	public static ArrayList<Flight> importFlightFromJson() {
		boolean check = true;
		int counterUpdate = 0;
		int counterInsert = 0;

		try (FileReader reader = new FileReader(new File("json/Flights.json"))) {
			JsonObject doc = (JsonObject) Jsoner.deserialize(reader);
			JsonArray jo = (JsonArray) doc.get("Flights");
			
			Iterator<Object> iterator = jo.iterator();
			while (iterator.hasNext()) {
				JsonObject item = (JsonObject) iterator.next();
				Timestamp DepartureTime;
				Timestamp arrivalTime;
				// Flight
				String origin = (String) item.get("ORIGINAIRPORTID");
				int originAirportID = Integer.parseInt(origin);
				String dest = (String) item.get("DESTINATIONAIRPORTID");
				int destinationAirportID = Integer.parseInt(dest);

				String flightNum = (String) item.get("FLIGHTSERIALNUMBER");
				String departure = (String) item.get("FLIGHTDEPARTURE");
				System.out.println(departure);

				DepartureTime = Timestamp.valueOf(departure);
				String arrival = (String) item.get("FLIGHTARRIVAL");
				System.out.println(departure);

				arrivalTime = Timestamp.valueOf(arrival);

				String status = (String) item.get("STATUS");
				String airplane = (String) item.get("AIRPLANESERIALNUMBER");


				
				//Seats
				JsonArray seats = (JsonArray) item.get("Seats");
				Iterator<Object> iter = seats.iterator();
				
				while(iter.hasNext()) {
					JsonObject seat = (JsonObject) iter.next();
					String columnLetter = (String) seat.get("COLUMNLETTER");
					String row = (String) seat.get("ROWNUMBER");
					int rowNumber = Integer.parseInt(row);
					String classType = (String) seat.get("CLASS");
					Airplane ap = new Airplane(airplane);
					SeatClass cType = null;
					if(classType == "Low-Cost") {
						cType = SeatClass.LowCost;
					}
					if(classType == "First-Class") {
						cType = SeatClass.FirstClass;
					}
					if(classType == "Business") {
						cType = SeatClass.Business;
					}
					
        			Seat s = new Seat(rowNumber,columnLetter,ap,cType);
					seatsList.add(s);

				}
				
				Airplane ap = new Airplane(airplane);
				airplanesList.add(ap);
				boolean fl = isExistAirplane(ap);
				System.out.println(fl);
				if(!fl) {
					check = ControlFlight.getInstance().createNewAirplane(airplane, seatsList.size());
					if(check) {
						System.out.println("new plane");
					}
				}
				

                
                Flight f = new Flight(flightNum,DepartureTime,arrivalTime,
                		airplane, status,originAirportID,destinationAirportID);
				flightsList.add(f);
				boolean flag = checkifFlightExist(f);
				if(!flag) {
					//flight is new to the customer DB, create it
					check = ControlFlight.getInstance().createNewFlight(flightNum, DepartureTime, arrivalTime, airplane,
							status, originAirportID, destinationAirportID);
					counterInsert++;
				}
				else {
					//flight is in the customer DB, update it

					check = ControlFlight.getInstance().updateFlight(flightNum, DepartureTime, arrivalTime, airplane,
							status, originAirportID, destinationAirportID);
					counterUpdate++;
				}

			}
			if(!check) {
				Alert alert = new Alert(AlertType.ERROR, "Error");
				alert.setHeaderText("Please try again");
				alert.setTitle("Failed ");
				alert.showAndWait();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION, "We updated "+ counterUpdate+" existing flights."+"\nWe inserted " + counterInsert +" new flights.");
				alert.setHeaderText("Success");
				alert.setTitle("Success");
				alert.showAndWait();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		return flightsList;
	}
	

	////// existing airplane //////
	// this method get airplane and return true if exist
	private static boolean isExistAirplane(Airplane a) {
		String id = a.getAirplaneSerialNumber();
		int length = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement callst = conn.prepareCall(Consts.SQL_GET_AIRPLANE)) {
				int k = 1;
				callst.setString(k++, id);

				ResultSet rs = callst.executeQuery();
				if (rs.next())
					return true;
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}
	////// existing flight //////
	// this method get flight and return true if exist
	private static boolean checkifFlightExist(Flight f) {
		String id = f.getFlightSerialNumber();
		int length = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					CallableStatement callst = conn.prepareCall(Consts.SQL_GET_FLIGHT)) {
				int k = 1;
				callst.setString(k++, id);

				ResultSet rs = callst.executeQuery();
				if (rs.next())
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
