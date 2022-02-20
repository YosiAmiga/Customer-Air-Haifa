package boundry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Exceptions.EmptyComboBoxException;
import Exceptions.NoSuchFlightException;

import java.sql.*;

import control.ControlCustomer;
import control.ControlFlight;
import control.ControlJSON;
import control.Sounds;
import entity.Airplane;
import entity.Airport;
import entity.Customer;
import entity.EntertainmentPiece;
import entity.Flight;
import entity.Seat;
import entity.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.FlightStatus;

public class CustomerFlyTabsScreen implements Initializable {
	private ControlCustomer controlCustomer;
	private ControlFlight control;
	private ControlJSON controlJson;
	//the customer currently logged in
	static Customer customer;
	
//	@FXML
//	public void doingExport()
//	{
//		//get the current date and pass it to the function
//		java.sql.Date today = java.sql.Date.valueOf(java.time.LocalDate.now());
//		System.out.println(today);
//		controlJson.getInstance().exportToJSON(today);
//	}
	
	
	/**************************************Book an order Tab*****************************************/
	@FXML
	private ComboBox<String> origin;
	@FXML
	private ComboBox<String> destination;
	@FXML
	private Button searchFlights;
	@FXML
	private AnchorPane flightsSearchTableWrapper;
	
	/*a table thats shows all the flights by the given origin and destination*/
	@FXML
	private TableView<Flight> flightTable1;
	@FXML
	private TableColumn<Flight,String> fNumber1;
	@FXML
	private TableColumn<Flight, Timestamp> fDeparture1;
	@FXML
	private TableColumn<Flight, Timestamp> fArrival1;
	@FXML
	private TableColumn<Flight,String> fAirplane1;
	@FXML
	private TableColumn<Flight, FlightStatus> fStatus1;
	@FXML
	private TableColumn<Flight, Integer> fOrigin1;
	@FXML
	private TableColumn<Flight, Integer> fDestination1;
	
	/*a table thats shows all the Entertainment Pieces of the given flight*/
	@FXML
	private TextField flightForPiece;
	@FXML
	private Button searchPieces;
	@FXML
	private TableView<EntertainmentPiece> pieceTable;
	@FXML
	private TableColumn<EntertainmentPiece, String> pieceName;
	@FXML
	private TableColumn<EntertainmentPiece, String> pieceDesctiption;
	@FXML
	private TableColumn<EntertainmentPiece, String> pieceType;
	
	/*********************Tickets section**********************/
	@FXML
	private Button addNewOrder;
	@FXML
	private Label orderIDLabel;

	@FXML
	private Button searchTickets;
	@FXML
	private ComboBox<String> flightsForTickets;
	@FXML
	private ComboBox<String> firstClassCombo;
	@FXML
	private ComboBox<String> businessCombo;
	@FXML
	private ComboBox<String> lowcostCombo;	
	@FXML
	private ComboBox<String> mealsCombo;
	
	@FXML
	private CheckBox premiumCheck;
	@FXML
	private TextField maxWeight;
	@FXML
	private TextField firstRequest;
	@FXML
	private TextField secondRequest;
	@FXML
	private TextField thirdRequest;
	
	@FXML
	private Label totalAmount;
	private int ticketCurrentFee;
	
	@FXML
	private ComboBox<String> customerCombo;
	
	/*to parse the class of the seat*/
	private String classSelected;
	@FXML
	private Button addFirstClass;
	@FXML
	private Button addBusiness;
	@FXML
	private Button addLowcost;
	@FXML
	private Label selectedSeat;
	/*the airplane serial number of the given flight*/
	private String airplaneNumberOfGivenFlight;
	public void firstClassSelected(ActionEvent e) {
		classSelected = "First-Class";
		selectedSeat.setText(firstClassCombo.getValue());
	}
	public void businessSelected(ActionEvent e) {
		classSelected = "Business";
		selectedSeat.setText(businessCombo.getValue());	

	}
	public void lowCostSelected(ActionEvent e) {
		classSelected = "Low-Cost";
		selectedSeat.setText(lowcostCombo.getValue());
	}
	@FXML
	private Button calcPrice;
	//Show the price before sale
	public void calculatePrice(ActionEvent e) {
		getFlightDurationForPrice();
		String meal = mealsCombo.getValue();
		boolean isMeal = true;
		
		if(meal == "Without") {
			isMeal = false;
		}
		
		ticketCurrentFee = 0;
		if(classSelected == "First-Class") {
			if(isMeal) {
				ticketCurrentFee = (int) (600 + 70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (600 + 100*flightForTicketDuration);
			}
		}
		if(classSelected == "Business") {
			if(isMeal) {
				ticketCurrentFee = (int) (300 + 70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (600 + 100*flightForTicketDuration);
			}
		}
		if(classSelected == "Low-Cost") {
			if(isMeal) {
				ticketCurrentFee = (int) (70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (0 + 100*flightForTicketDuration);
			}
		}
		if(premiumCheck.isSelected()) {
			int weight = Integer.parseInt(maxWeight.getText());
			//adding fee of each kilogram for the weight
			ticketCurrentFee = ticketCurrentFee + 20*weight;
		}
		totalAmount.setText(String.valueOf(ticketCurrentFee));
	}
	
	private Double flightForTicketDuration;
	@FXML
	private Button linkPassenger;
	
	/*The method to create a ticket for a given customer with all the data inserted*/
	public void createTicket(ActionEvent e) {
		getFlightDurationForPrice();
		boolean ticketCreation;
		int passportNumber = Integer.parseInt(customerCombo.getValue());
		int orderID = Integer.parseInt(orderIDLabel.getText());
		String flightSerialNumber = flightsForTickets.getValue();
		String meal = mealsCombo.getValue();
		boolean isMeal = true;
		String seatClass = null;
		
		if(meal == "Without") {
			isMeal = false;
		}
		

		ticketCurrentFee = 0;
		if(classSelected == "First-Class") {
			if(isMeal) {
				ticketCurrentFee = (int) (600 + 70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (600 + 100*flightForTicketDuration);
			}
		}
		if(classSelected == "Business") {
			if(isMeal) {
				ticketCurrentFee = (int) (300 + 70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (600 + 100*flightForTicketDuration);
			}
		}
		if(classSelected == "Low-Cost") {
			if(isMeal) {
				ticketCurrentFee = (int) (70 + 100*flightForTicketDuration);
			}
			else {
				ticketCurrentFee = (int) (0 + 100*flightForTicketDuration);
			}
		}
		String seatBefore = selectedSeat.getText();
		String[] seatByRowAndCol = seatBefore.split("-");
		int rowNum = Integer.parseInt(seatByRowAndCol[0]);
		String colLet = seatByRowAndCol[1];
		if(colLet == "a") {
			seatClass = "First-Class";
		}
		if(colLet == "b") {
			seatClass = "Business";
		}
		if(colLet == "c") {
			seatClass = "Low-Cost";
		}
		System.out.println("The given Ticket -> Customer: " + passportNumber+
				"\n order :" + orderID+
				"\n flight :" + flightSerialNumber+
				"\n meal : " + meal+
				"\n class :" +classSelected+
				"\n price :" + ticketCurrentFee+
				"\n seat row&col :"+rowNum+colLet+
				"\n airplane: " + airplaneNumberOfGivenFlight);
		
		//if the customer wants a premium seat
		if(premiumCheck.isSelected()) {
			//for premium ticket use:
			int weight = Integer.parseInt(maxWeight.getText()); 
			String request1 = firstRequest.getText();
			String request2 = secondRequest.getText();
			String request3 = thirdRequest.getText();

			//adding fee of each kilogram for the weight
			ticketCurrentFee = ticketCurrentFee + 20*weight;
			ticketCreation = ControlCustomer.getInstance().createNewPremiumTicket(passportNumber,orderID,
					flightSerialNumber, meal, seatClass, ticketCurrentFee, rowNum, colLet,
					airplaneNumberOfGivenFlight, weight, request1,request2,request3);
			if(ticketCreation) {
				successAdded("Premium Ticket","Customer "+passportNumber);
			}
		}
		else {
			ticketCreation = ControlCustomer.getInstance().createNewTicket(passportNumber,orderID,
					flightSerialNumber, meal, seatClass, ticketCurrentFee, rowNum, colLet,
					airplaneNumberOfGivenFlight);
			if(ticketCreation) {
				successAdded("Regular Ticket","Customer "+passportNumber);
				
			}
		}
	}
	

	
	/*a method to get a specific flight duration to add to price parameter*/
	public void getFlightDurationForPrice() {
		try {
			String originA = origin.getValue();
			//Extract only the Airport ID
			String originAirportNumber= originA.replaceAll("[^0-9]", "");
			int oNum = Integer.parseInt(originAirportNumber);
			//Save the data from the current destination airport combo box
			String destA = destination.getValue();
			//Extract only the Airport ID
			String destAirportNumber= destA.replaceAll("[^0-9]", "");	
			int dNum = Integer.parseInt(destAirportNumber);

			ArrayList<Flight> query=new ArrayList<Flight>();
			query = (ControlCustomer.getInstance().searchFlights(oNum, dNum));
			
			Flight flightForTicket =null;
			/*from all the given flights in the table after search, select the flight that was selected for tickets view*/
			for(Flight f : query) {
				if(flightsForTickets.getValue() == f.getFlightSerialNumber()) {
					flightForTicket = f;
					System.out.println(flightForTicket);
				}
			}
			long milliseconds1 = flightForTicket.getFlightDeparture().getTime();
		    long milliseconds2 = flightForTicket.getFlightArrival().getTime();
		    System.out.println(milliseconds1);
		    System.out.println(milliseconds2);

		    long diff = milliseconds2 - milliseconds1;
		    long diffSeconds = diff / 1000;
		    long diffMinutes = diff / (60 * 1000);
		    long diffHours = diff / (60 * 60 * 1000);
			flightForTicketDuration = (double) diffHours;
			System.out.println("Flight duration in hours: "+flightForTicketDuration);
			
		}
		catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	@FXML
	private Button clearOrders;
	
	public void clearSeatsInOrder(ActionEvent e) {
		firstClassCombo.setValue("First Class");
		businessCombo.setValue("Business");
		lowcostCombo.setValue("Low Cost");
		customerCombo.setValue("Select a customer");
		mealsCombo.setValue("Select a meal");
		premiumCheck.setSelected(false);
		maxWeight.setText("");
		firstRequest.setText("");
		secondRequest.setText("");
		thirdRequest.setText("");
		selectedSeat.setText("SEAT_ID");
		totalAmount.setText("0");

	}
	
	@FXML
	private ComboBox<String> paymentCombo;
	
	/*A method for the customer to select origin and destination and search for all those flights*/
	public void getFlightTickets(ActionEvent e) {
		String flightID = flightsForTickets.getValue();
		ArrayList<Seat> regularSeats = new ArrayList<Seat>();
		ArrayList<Seat> premiumSeats = new ArrayList<Seat>();
		ArrayList<Seat> allSeats = new ArrayList<Seat>();
		ArrayList<Seat> toRemove = new ArrayList<Seat>();

		
		ObservableList<String> firstClass=FXCollections.observableArrayList();
		ObservableList<String> business=FXCollections.observableArrayList();
		ObservableList<String> lowcost=FXCollections.observableArrayList();
		ArrayList<String> first = new ArrayList<String>();
		ArrayList<String> bus = new ArrayList<String>();
		ArrayList<String> low = new ArrayList<String>();
	
		try {
			regularSeats = ControlFlight.getInstance().getRegularTickets(flightID);
			premiumSeats = ControlFlight.getInstance().getPremiumTickets(flightID);
			allSeats = ControlFlight.getInstance().getAllSeats(flightID);
			
			for(Seat s1 : allSeats){
				airplaneNumberOfGivenFlight = s1.getAirplaneNumber();
				for(Seat s2: regularSeats) {
					if(s1.getRowNumber() == s2.getRowNumber()) {
						toRemove.add(s1);
					}
				}
				for(Seat s3: premiumSeats) {
					if(s1.getRowNumber() == s3.getRowNumber()) {
						toRemove.add(s1);
					}
				}
			}

			allSeats.removeAll(toRemove);
			System.out.println("After removal");

			for(Seat seat : allSeats) {
				System.out.println(seat);
				if(seat.getsClass().equals("First-Class")) {
					first.add(seat.getRowNumber()+"-"+seat.getColumnLetter());
				}
				if(seat.getsClass().equals("Business")) {
					bus.add(seat.getRowNumber()+"-"+seat.getColumnLetter());

				}
				if(seat.getsClass().equals("Low-Cost")) {
					low.add(seat.getRowNumber()+"-"+seat.getColumnLetter());

				}
			}
			
			firstClass.addAll(first);
			business.addAll(bus);
			lowcost.addAll(low);
			if(firstClass.size() >= 1) {
				firstClassCombo.setItems(firstClass);				
			}
			if(business.size() >= 1) {
				businessCombo.setItems(business);
			}
			if(lowcost.size() >= 1) {
				lowcostCombo.setItems(lowcost);				
			}

			
		}

		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**************************************Manage orders Tab*****************************************/
	@FXML
	private ComboBox<String> ordersCombo;
	@FXML
	private ComboBox<String> seatInOrderCombo;


	
	/**************************************Flight tab*****************************************/


	
	/****************Flight Table****************/
	
	@FXML
	private TableView<Flight> flightTable;
	@FXML
	private TableColumn<Flight,String> fNumber;
	@FXML
	private TableColumn<Flight, Timestamp> fDeparture;
	@FXML
	private TableColumn<Flight, Timestamp> fArrival;
	@FXML
	private TableColumn<Flight,String> fAirplane;
	@FXML
	private TableColumn<Flight, FlightStatus> fStatus;
	@FXML
	private TableColumn<Flight, Integer> fOrigin;
	@FXML
	private TableColumn<Flight, Integer> fDestination;
	


	

	
	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> flights=FXCollections.observableArrayList();
	ArrayList<Flight> query;

    try {

    	query= controlJson.importFlightFromJson();
    	flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return flights;	
	}	


	/*************Initialize all the combo boxes*************/
	/*initialize all the payments*/
	private void initPayments(){
	ObservableList<String> payments=FXCollections.observableArrayList();
	ArrayList<String> query = new ArrayList<>();
	query.add("Bank transaction");
	query.add("Credit card");
	query.add("PayPal");
	payments.addAll(query);	
	
	paymentCombo.setItems(payments);	
	}	
	
	public void initMeals() {
		ObservableList<String> ObservableListMeals = FXCollections.observableArrayList();		
		ArrayList<String> mealsList = new ArrayList<>();
		mealsList.add("Without");
		mealsList.add("Regular");
		mealsList.add("Vegetarian");
		mealsList.add("Vegan");
		mealsList.add("Kosher");

		ObservableListMeals.addAll(mealsList);
		mealsCombo.setItems(ObservableListMeals);

	}
	public void initCustomerFamily() {
		ObservableList<String> ObservableListFamily = FXCollections.observableArrayList();
		try {
			ArrayList<Integer> family = new ArrayList<Integer>();
			family = (ControlCustomer.getInstance().getFamilyMembers(customer.getPassportNumber()));
			for(Integer i : family) {
				ObservableListFamily.add(String.valueOf(i));
			}
			
			customerCombo.setItems(ObservableListFamily);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initAirports() {
		//setting all the airports in the flights combo box
		ObservableList<String> ObservableListAirports = FXCollections.observableArrayList();		
		ArrayList<Airport> airportsQuery;
		ArrayList<String> airportsInFlights = new ArrayList<>();
		try {
			airportsQuery = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
			for(Airport ap : airportsQuery) {
				airportsInFlights.add(ap.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableListAirports.addAll(airportsInFlights);
		origin.setItems(ObservableListAirports);
		destination.setItems(ObservableListAirports);

		
	}
	
	public void initAirplanes() {

	}
	
	public void initTime() {

	}

	public void initStatus() {
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initAirports();
		initAirplanes();
		initTime();
		initStatus();
		initMeals();
		initPayments();
		initCustomerFamily();
		/**************************************Book a flight Page*****************************************/
		

		
		
		/**************************************Flight Page*****************************************/

		//set in flight table
		fNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
		fDeparture.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
		fArrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
		fAirplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
		fStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
		fOrigin.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
		fDestination.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
		flightTable.setItems(getFlightsToTable());

		
		


		
	}
	
	/*A method for the customer to select origin and destination and search for all those flights*/
	public void createNewOrder(ActionEvent e) {
		try {
			String payment = paymentCombo.getValue();
			int orderID = 0;

			if(payment == null) {
				throw new EmptyComboBoxException();
			}
			if(ControlCustomer.getInstance().createNewOrder(payment)) {
				orderID = ControlCustomer.getInstance().getLatestOrder();
				if(orderID != 0) {
					successAdded("Order number "+orderID,"Move to next page!");	
					orderIDLabel.setText(String.valueOf(orderID));
				}
			}

		}	
		catch(EmptyComboBoxException e1) {
			failSelection("Payment combo box",e1.toString());
		}
		catch (Exception e1) {

			e1.printStackTrace();
		}
	}
	
	/*A method for the customer to select origin and destination and search for all those flights*/
	public void getPiecesBySearch(ActionEvent e) {
		String flightID = flightForPiece.getText();
		ArrayList<Flight> flights;
		boolean flightExist = false;
		//checking if the flight that was given exits
		//TODO FIX EXCEPTION
		try {
			flights = ControlFlight.getInstance().getFlights();
			for(Flight f : flights) {
				if(f.getFlightSerialNumber().equals(flightID)) {
					flightExist = true;
				}
			}
			if(flightExist == false) {
				throw new NoSuchFlightException();
			}
		}
		catch (NoSuchFlightException e1) {
			failSearch(flightID, "Flight");
		}
		catch (Exception e1) {

			e1.printStackTrace();
		}
		
		ObservableList<EntertainmentPiece> pieces=FXCollections.observableArrayList();
		ArrayList<EntertainmentPiece> query;
		
		try {

	    	query= ControlCustomer.getInstance().searchEntertainmentPieces(flightID);			
	    	pieces.addAll(query);
			
	    	pieceName.setCellValueFactory(new PropertyValueFactory<EntertainmentPiece, String>("pieceName"));
	    	pieceDesctiption.setCellValueFactory(new PropertyValueFactory<EntertainmentPiece, String>("description"));
	    	pieceType.setCellValueFactory(new PropertyValueFactory<EntertainmentPiece, String>("Type2"));
			pieceTable.setItems(pieces);
		}

		catch (Exception e1) {

			e1.printStackTrace();
		}
	}
	
	/*A method for the customer to select origin and destination and search for all those flights*/
	public void getFlightsBySearch(ActionEvent e) {
		String originA = origin.getValue();
		//Extract only the Airport ID
		String originAirportNumber= originA.replaceAll("[^0-9]", "");
		int oNum = Integer.parseInt(originAirportNumber);
		//Save the data from the current destination airport combo box
		String destA = destination.getValue();
		//Extract only the Airport ID
		String destAirportNumber= destA.replaceAll("[^0-9]", "");	
		int dNum = Integer.parseInt(destAirportNumber);

		TableView<Flight> pane;
		FlightsBySearchTable.givenOrigin = oNum;
		FlightsBySearchTable.givenDestination = dNum;


		ObservableList<Flight> flights=FXCollections.observableArrayList();
		ArrayList<Flight> query=new ArrayList<Flight>();
		
		try {
//			pane = FXMLLoader.load(getClass().getResource("fxmlFolder\\FlightsBySearchTablePage.fxml"));
//			pane.setPrefSize(flightsSearchTableWrapper.getWidth(), flightsSearchTableWrapper.getHeight());
//			flightsSearchTableWrapper.getChildren().removeAll(flightsSearchTableWrapper.getChildren());
//			flightsSearchTableWrapper.getChildren().add(pane);
			
			query = (ControlCustomer.getInstance().searchFlights(oNum, dNum));
			flights.addAll(query);
			//for table view
			fNumber1.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
			fDeparture1.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
			fArrival1.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
			fAirplane1.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
			fStatus1.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
			fOrigin1.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
			fDestination1.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
			flightTable1.setItems(flights);
			
			//for ticktes combo box
			ObservableList<String> ObservableListFlightForTickets = FXCollections.observableArrayList();
			ArrayList<String> flightsID=new ArrayList<String>();
			for(Flight f : query) {
				flightsID.add(f.getFlightSerialNumber());
			}
			ObservableListFlightForTickets.addAll(flightsID);
			flightsForTickets.setItems(ObservableListFlightForTickets);
		}

		catch (Exception e1) {

			e1.printStackTrace();
		}
	}


	
	public void successUpload() {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successRemove(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successUpdate(String content, String header) {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSearch(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to find : " + content);
		al.setHeaderText(header);
		al.setTitle("Search");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSelection(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failUpdate(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failAdd(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	
	/******Sounds*****/
	
	public void fail(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to add : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}

	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void badSound() {
		Sounds s = new Sounds();
		try {
			s.errorSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void refreshScreen(){
		initAirports();
		initAirplanes();
		initTime();
		initStatus();
		initMeals();
		initPayments();
		initCustomerFamily();

		/**************************************Flight Page*****************************************/

		//set in flight table
		fNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
		fDeparture.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
		fArrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
		fAirplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
		fStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
		fOrigin.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
		fDestination.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
		flightTable.setItems(getFlightsToTable());
		flightTable.setItems(getFlightsToTable());
		
		
		/**************************************Airport Page*****************************************/

	}

}
