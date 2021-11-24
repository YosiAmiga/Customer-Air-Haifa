package boundry;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import control.AirplaneView;
import control.ControlFlight;
import entity.Airplane;
import entity.Airport;
import entity.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.FlightStatus;

public class ManagerFlyScreen implements Initializable {
	
	private ControlFlight control;
	
	/**************************************Flight Page*****************************************/
	
	@FXML
	private TextField flightNumber;
	@FXML
	private DatePicker departureDate;
	@FXML
	private TextField departureHour;
	@FXML
	private TextField departureMinute;
	@FXML
	private DatePicker arrivalDate;
	@FXML
	private TextField arrivalHour;
	@FXML
	private TextField arrivalMinute;
	@FXML
	private ComboBox<String> originAirports;
	@FXML
	private ComboBox<String> destAirports;
	@FXML
	private ComboBox<String> firstPilot;
	@FXML
	private ComboBox<String> secondPilot;
	
	/****************Flight Table****************/
	
	@FXML
	private TableView<Flight> flightTable;
	@FXML
	private TableColumn<Flight,String> fNumber;
	@FXML
	private TableColumn<Flight, Timestamp> departure;
	@FXML
	private TableColumn<Flight,Timestamp> arrival;
	@FXML
	private TableColumn<Flight,String> airplane;
	@FXML
	private TableColumn<Flight, String> origin;
	@FXML
	private TableColumn<Flight, String> destination;
	@FXML
	private TableColumn<Flight, FlightStatus> status;
	
//	@FXML
//	private TableColumn<Flight,Pilot> p1;
//	@FXML
//	private TableColumn<Flight, Pilot> p2;
	
	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> airplanes=FXCollections.observableArrayList();
	ArrayList<Flight> query;
	try {
		query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
		airplanes.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return airplanes;	
}	
	
	

	@FXML
	private Button createNewFlight;
	
	/**************************************Airport Page*****************************************/
	
	@FXML
	private TextField airportID;
	@FXML
	private TextField country;
	@FXML
	private TextField city;
	@FXML
	private TextField timeZone;
	
	@FXML
	private Button addNewAirport;
	
	/****************Airport Table****************/
	
	@FXML
	private TableView<Airport> airportTable;
	@FXML
	private TableColumn<Airport,Integer> airportColumn;
	@FXML
	private TableColumn<Airport, String> countryColumn;
	@FXML
	private TableColumn<Airport,String> cityColumn;
	@FXML
	private TableColumn<Airport,Double> timeZoneColumn;

	
//	@FXML
//	private TableColumn<Flight,Pilot> p1;
//	@FXML
//	private TableColumn<Flight, Pilot> p2;
	
	/*get all the flights from the database*/
	private ObservableList<Airport> getAirportsToTable(){
	ObservableList<Airport> airplanes=FXCollections.observableArrayList();
	ArrayList<Airport> query;
	try {
		query = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
		airplanes.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return airplanes;	
}	
	
	
	/**************************************Airplane Page*****************************************/
	@FXML
	private TextField airplaneID;
	@FXML
	private TextField airplaneSize;
	
	@FXML
	private Button addNewAirplane;
	
	/****************Airplane Table****************/
	
	@FXML
	private TableView<Airplane> airplaneTable;
	@FXML
	private TableColumn<Airplane,String> airplaneNumber;
	@FXML
	private TableColumn<Airplane, Integer> airPlaneSize;
	
	/**********Getting the data from the database methods********/
	
	
	/*get all the airplanes from the database*/
	private ObservableList<Airplane> getAirplaneToTable(){
	ObservableList<Airplane> airplanes=FXCollections.observableArrayList();
	ArrayList<Airplane> query;
	try {
		query = new ArrayList<Airplane>(ControlFlight.getInstance().getAirplanes());
		airplanes.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return airplanes;	
}	
	
//	@FXML
//	private TableView<Flight> flightTable;
//	@FXML
//	private TableColumn<Flight,String> fNumber;
//	@FXML
//	private TableColumn<Flight, Calendar> departure;
//	@FXML
//	private TableColumn<Flight,Calendar> arrival;
//	@FXML
//	private TableColumn<Flight,Airplane> airplane;
//	@FXML
//	private TableColumn<Flight, Airport> origin;
//	@FXML
//	private TableColumn<Flight, Airport> destination;
//	@FXML
//	private TableColumn<Flight, FlightStatus> status;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//set in flight table
		fNumber.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightSerialNumber"));
		departure.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture2"));
		arrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival2"));
		airplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightAirplane2"));
		origin.setCellValueFactory(new PropertyValueFactory<Flight, String>("originAirport2"));
		destination.setCellValueFactory(new PropertyValueFactory<Flight, String>("destinationAirport2"));
		status.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
		flightTable.setItems(getFlightsToTable());
		
		
		//set in airports table
		airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
		timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
		airportTable.setItems(getAirportsToTable());
		
//		@FXML
//		private TableView<Airport> airportTable;
//		@FXML
//		private TableColumn<Airport,Integer> airportColumn;
//		@FXML
//		private TableColumn<Airport, String> countryColumn;
//		@FXML
//		private TableColumn<Airport,String> cityColumn;
//		@FXML
//		private TableColumn<Airport,Double> timeZoneColumn;
		//set in airplane table
		airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
		airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
		airplaneTable.setItems(getAirplaneToTable());
		try {
			System.out.println(ControlFlight.getInstance().getAirplanes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void btnNewAirplane() {
		control = new ControlFlight();
		if(control.createNewPlane(airplaneID.getText(), Integer.parseInt(airplaneSize.getText()))) {
			System.out.println("yay");
		}
		else {
			System.out.println("not good");
		}
		
	}

}
