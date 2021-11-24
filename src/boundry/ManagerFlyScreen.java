package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import control.ControlFlight;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
	
	
	@FXML
	private Button goToFirst;
	@FXML
	private Button goBack;
	@FXML
	private Button goNext;
	@FXML
	private Button goToLast;
	@FXML
	private TextField numberOfFlight;
	@FXML
	private Button saveFlight;
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
	
	
	/**************************************Airplane Page*****************************************/
	@FXML
	private TextField airplaneID;
	@FXML
	private TextField airplaneSize;
	
	@FXML
	private Button addNewAirplane;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
