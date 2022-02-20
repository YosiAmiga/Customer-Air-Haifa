package boundry;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import control.ControlFlight;
import control.ControlReports;
import control.ReportControl;
import entity.Consts;
import entity.Flight;
import entity.ReportTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.swing.JRViewer;
import utils.FlightStatus;

public class ManagerReportsScreen implements Initializable {

	private ControlReports control;

	/****************Report Table****************/
	
	@FXML
	private TableView<ReportTest> reportTable;
	@FXML
	private TableColumn<ReportTest, Integer> passport;
	@FXML
	private TableColumn<ReportTest,String> fName;
	@FXML
	private TableColumn<ReportTest,String> lName;
	@FXML
	private TableColumn<ReportTest, Integer> morning;
	@FXML
	private TableColumn<ReportTest, Integer> after;
	@FXML
	private TableColumn<ReportTest, Integer> even;
	@FXML
	private TableColumn<ReportTest, Integer> night;
	
	
	/*get all the flights from the database*/
	private ObservableList<ReportTest> getFlightsToTable(){
	ObservableList<ReportTest> flights=FXCollections.observableArrayList();
	ArrayList<ReportTest> query = new ArrayList<ReportTest>();

    try {

    	query = ControlReports.getInstance().ordersReport();
    	flights.addAll(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return flights;	
}	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//set in flight table
		passport.setCellValueFactory(new PropertyValueFactory<ReportTest,Integer>("passportNumber"));
		fName.setCellValueFactory(new PropertyValueFactory<ReportTest,String>("firstName"));
		lName.setCellValueFactory(new PropertyValueFactory<ReportTest,String>("lastName"));
		morning.setCellValueFactory(new PropertyValueFactory<ReportTest, Integer>("morning"));
		after.setCellValueFactory(new PropertyValueFactory<ReportTest, Integer>("afternoon"));
		even.setCellValueFactory(new PropertyValueFactory<ReportTest, Integer>("evning"));
		night.setCellValueFactory(new PropertyValueFactory<ReportTest, Integer>("night"));
		reportTable.setItems(getFlightsToTable());
	}
	
	public void createReport() {
		try {
			JFrame reportFrame = ReportControl.getInstance().produceReport();
			// if(reportFrame!=null)
			reportFrame.setVisible(true);
			}
			
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
