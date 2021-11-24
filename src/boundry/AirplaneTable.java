package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.AirplaneView;
import entity.Airplane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AirplaneTable implements Initializable{
	
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
		ArrayList<Airplane> query=new ArrayList<Airplane>(AirplaneView.getInstance().getAirplanes());
		airplanes.addAll(query);
		return airplanes;	
	}	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		airplaneNumber.setCellValueFactory(new PropertyValueFactory<Airplane, String>("airplaneSerialNumber"));
		airPlaneSize.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("airplaneSize"));
		airplaneTable.setItems(getAirplaneToTable());
		
	}

}
