package boundry;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.ControlCustomer;
import entity.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.FlightStatus;

public class FlightsBySearchTable implements Initializable{
	
	private ControlCustomer control;
	
	public static int givenOrigin;
	public static int givenDestination;
	
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
		query = new ArrayList<Flight>(control.searchFlights(givenOrigin, givenDestination));
		flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return flights;	
}	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightSerialNumber"));
		fDeparture.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightDeparture"));
		fArrival.setCellValueFactory(new PropertyValueFactory<Flight, Timestamp>("flightArrival"));
		fAirplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
		fStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
		fOrigin.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("originAirport"));
		fDestination.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("destinationAirport"));
		flightTable.setItems(getFlightsToTable());
		
	}

}
