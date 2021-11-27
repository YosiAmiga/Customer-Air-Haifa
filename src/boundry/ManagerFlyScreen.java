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
import javafx.scene.control.Alert;
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
	private ComboBox<String> airplaneInFlight;
	@FXML
	private ComboBox<String> originAirports;
	@FXML
	private ComboBox<String> destAirports;

	
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
	

	@FXML
	private Button createNewFlight;
	

	
	/*get all the flights from the database*/
	private ObservableList<Flight> getFlightsToTable(){
	ObservableList<Flight> flights=FXCollections.observableArrayList();
	ArrayList<Flight> query;
	try {
		query = new ArrayList<Flight>(ControlFlight.getInstance().getFlights());
		flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    for (Flight f : flights) {
    	System.out.println(f.toString());
    }
	return flights;	
}	
	
	

	
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
	
	@FXML
	private TextField airportToDelete;
	@FXML
	private Button deleteAirport;
	
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

	
	
	/*get all the flights from the database*/
	private ObservableList<Airport> getAirportsToTable(){
	ObservableList<Airport> airplanes=FXCollections.observableArrayList();
	ArrayList<Airport> query;
	ArrayList<String> airportsInFlights = new ArrayList<>();
	try {
		query = new ArrayList<Airport>(ControlFlight.getInstance().getAirports());
		for(Airport ap : query) {
			airportsInFlights.add(ap.toString());
		}
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
	
	@FXML
	private TextField airplaneToDelete;
	@FXML
	private Button deleteAirplane;
	
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


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
		originAirports.setItems(ObservableListAirports);
		destAirports.setItems(ObservableListAirports);
		
		//set in flight table
		fNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
		fDeparture.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
		fArrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
		fAirplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
		fStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
		fOrigin.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
		fDestination.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
		flightTable.setItems(getFlightsToTable());
		
		
		//set in airports table
		airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
		countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
		cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
		timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
		airportTable.setItems(getAirportsToTable());
		
		
		//setting all the airplanes in the flights combo box
		ObservableList<String> ObservableListAirplanes = FXCollections.observableArrayList();		
		ArrayList<Airplane> airplanesQuery;
		ArrayList<String> airplanesInFlights = new ArrayList<>();
		try {
			airplanesQuery = new ArrayList<Airplane>(ControlFlight.getInstance().getAirplanes());
			for(Airplane a : airplanesQuery) {
				airplanesInFlights.add(a.toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableListAirplanes.addAll(airplanesInFlights);
		airplaneInFlight.setItems(ObservableListAirplanes);


		//set in airplane table
		airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
		airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
		airplaneTable.setItems(getAirplaneToTable());

		
	}
	
	
	/**Airplane methods**/
	
	//Adding a new Airplane to system
	public void btnNewAirplane() {
		control = new ControlFlight();
		if(control.createNewAirplane(airplaneID.getText(), Integer.parseInt(airplaneSize.getText()))) {
			successAdded("Airplane", "Adding Airplane");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	
	//deleting an airplane
	public void btnDeleteAirplane() {
		control = new ControlFlight();
		if(control.deleteAirplane(airplaneToDelete.getText())) {
			successRemove("Airplane", "Removing Airplane");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	

	/**Airport methods**/
	
	//Adding a new Airport to system
	public void btnNewAirport() {
		control = new ControlFlight();
		if(control.createNewAirport(Integer.parseInt(airportID.getText()), country.getText(),city.getText(),
				Double.parseDouble(timeZone.getText()))) {
			successAdded("Airport", "Adding Airport");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	
	//deleting an Airport
	public void btnDeleteAirport() {
		control = new ControlFlight();
		if(control.deleteAirport(Integer.parseInt(airportToDelete.getText()))) {
			successRemove("Airplane", "Removing Airport");
			refreshScreen();
		}
		else {
			System.out.println("not good");
		}
		
	}
	/***After action sound and alert***/
	
	public void successUpload() {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded photo!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successRemove(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Removed Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successAdded(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Added Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void successUpdate(String content, String header) {
//		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText(content+" Updated Successfully");
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failSelection(String content, String header) {
//		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to select : " + content);
		al.setHeaderText(header);
		al.setTitle("ComboBox");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void failUpdate(String content, String header) {
//		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	
	/******Sounds*****/
//	
//	public void fail(String content, String header) {
//		badSound();
//		Alert al = new Alert(Alert.AlertType.ERROR);
//		al.setContentText("Faild to add : " + content);
//		al.setHeaderText(header);
//		al.setTitle("Database");
//		al.setResizable(false);
//		al.showAndWait();
//	}
//
//	public void goodSound() {
//		Sounds s = new Sounds();
//		try {
//			s.successSound();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//	}
//
//	public void badSound() {
//		Sounds s = new Sounds();
//		try {
//			s.errorSound();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//	}
//	
//	public void successSound() {
//		Sounds s = new Sounds();
//		try {
//			s.addSound();
//		}catch(Exception e2) {
//			e2.printStackTrace();
//		}
//	}
	
	public void refreshScreen(){
		
		
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


				airportID.setText("");
				country.setText("");
				city.setText("");
				timeZone.setText("");
				airportToDelete.setText("");
				
				//set in airports table
				
				airportColumn.setCellValueFactory(new PropertyValueFactory<Airport,Integer>("uniqueAirportID"));
				countryColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
				cityColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("city"));
				timeZoneColumn.setCellValueFactory(new PropertyValueFactory<Airport,Double>("timeZone"));
				airportTable.setItems(getAirportsToTable());
				
				/**************************************Airplane Page*****************************************/
				airplaneID.setText("");
				airplaneSize.setText("");
				airplaneToDelete.setText("");
				
				//set in airplane table
				airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
				airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
				airplaneTable.setItems(getAirplaneToTable());
		
	}

}
