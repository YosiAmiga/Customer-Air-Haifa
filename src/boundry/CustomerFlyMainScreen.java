package boundry;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import control.ControlReports;
import entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class CustomerFlyMainScreen implements Initializable{
	//the customer currently logged in
	static Customer customer;
	
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Text fName;
	@FXML
	private Text lName;
	@FXML
	private StackPane mainPane;
	@FXML
	private ToggleButton add;
	@FXML
	private ToggleButton reports;
    @FXML
    private ToggleButton settings;
	@FXML
	private ToggleButton log;
	@FXML
	private ToggleButton exit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fName.setText(customer.getFirstName());
		lName.setText(customer.getLastName());

		
	}
	
	/****Enter the adding,removing, and updating page****/
	//works
	public void loadAdd(ActionEvent e)
	{
		try {
			if(add.isSelected())
			{
				CustomerFlyTabsScreen.customer = customer;
				TabPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder//CustomerFlyTabsPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	//works
	public void loadReport(ActionEvent e)
	{
		try {
			if(reports.isSelected())
			{
				ControlReports.customer = customer;
				Pane pane=FXMLLoader.load(getClass().getResource("fxmlFolder//ManagerReportsPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	/*change customer settings*/
	public void loadSettings(ActionEvent e)
	{
		try {
			if(settings.isSelected())
			{
				Settings.customer = customer;
				AnchorPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\SettingsPage.fxml"));
				pane.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
				rootPane.getChildren().removeAll(rootPane.getChildren());
				rootPane.getChildren().add(pane);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	/*log out from the system and back to the AirHaifa main page*/
	public void logOutSys(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to log out of the system?");
		al.setTitle("Log out");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			
//			exitSound();
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("fxmlFolder//CustomerFly.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/***Exit the  program***/
	public void exitProgram(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to exit the program?");
		al.setTitle("Exit Program");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			System.exit(0);
		}
	}

}
