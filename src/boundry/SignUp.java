package boundry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.EmptyComboBoxException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegelInputException;
import Exceptions.IllegelPasswordException;
import Exceptions.PasswordMismatchException;
import Exceptions.ShortIDException;
import Exceptions.SimilarIDInSystemException;
import control.ControlCustomer;
import control.Sounds;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SignUp implements Initializable {

	private ControlCustomer control = new ControlCustomer();
	/*The controller to add and remove everything from the GUI to the database*/


	/**************************************Customer Page*****************************************/
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField customerId;
	@FXML
	private TextField customerFirst;
	@FXML
	private TextField customerLast;
	@FXML
	private TextField customerMail;
	@FXML
	private TextField customerCitizenship;
	@FXML
	private DatePicker customerBirth;
	@FXML
	private TextField customerPass;
	@FXML
	private TextField customerPassVerify;


	@FXML
	private Button getBack;
	@FXML
	private Button addCustomer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}
	


	/**************Add a customer*************/
	//working
	public void addCustomer(ActionEvent e){
		String section = "Customer";
		try {

			if(customerBirth.getValue() == null) {
				throw new EmptyComboBoxException();
			}
			
			int id=Integer.parseInt(customerId.getText());
			if(String.valueOf(id).length() != 9) {
				throw new ShortIDException();
			}
			String password=customerPass.getText();
			String passwordVerify=customerPassVerify.getText();
			if(!password.equals(passwordVerify)) {
				throw new PasswordMismatchException();
			}
			String firstName=customerFirst.getText();
			String LastName=customerLast.getText();
			String mail=customerMail.getText();
			String city=customerCitizenship.getText();
			
//			LocalDate bDay =customerBirth.getValue();
			Date bDay = java.sql.Date.valueOf(customerBirth.getValue());

			Customer c = new Customer(id, firstName, LastName, mail, mail, bDay, password);
			System.out.println(c);

			if(control.createNewCustomer(c)) {
				successAdded(section, "Success");		
			}
			//if could not add customer
			else {
				fail(section,"Could not add customer to system!");
			}


			refreshScreen();

		}

		catch(ShortIDException e1) {
			fail(section, e1.toString());
		}
		catch(PasswordMismatchException e1) {
			fail(section, e1.toString());
		}
//		catch(IllegelPasswordException e1) {
//			fail(section,e1.toString());
//		}
//		catch(IllegalCustomerException e1) {
//			fail(section,e1.toString());
//		}
//		catch(SimilarIDInSystemException e1) {
//			fail(section,e1.toString());
//		}
		catch(NumberFormatException e1) {
			fail(section, "Wrong Input!");
		}
		catch (Exception e1) {
			fail(section, e1.toString());
			e1.printStackTrace();
		}

	}
	
	
	/***************a method to refresh the screen*******************/

	
	public void refreshScreen(){
		customerId.setText(null);
		
		customerFirst.setText(null);
		
		customerLast.setText(null);
		
		customerMail.setText(null);
		
		customerCitizenship.setText(null);

		customerBirth.setValue(null);;

		customerPass.setText(null);

		customerPassVerify.setText(null);
	}
	/*log out from the system and back to the main page*/
	
	
	public void goBack(ActionEvent e) {
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setHeaderText("Are you sure you want to return to the main page");
		al.setTitle("Return");
		al.setResizable(false);
		Optional<ButtonType> result = al.showAndWait();
		if(result.get() == ButtonType.OK)
		{
			goodSound();
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

	public void successUpload() {
		successSound();
		Alert al = new Alert(Alert.AlertType.INFORMATION);
		al.setContentText("Successfully uploaded photo!");
		al.setHeaderText("Upload");
		al.setTitle("Photo");
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
	
	public void failUpdate(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to update : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	public void fail(String content, String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to add : " + content);
		al.setHeaderText(header);
		al.setTitle("Database");
		al.setResizable(false);
		al.showAndWait();
	}
	
	/*sounds methods */
	public void successSound() {
		Sounds s = new Sounds();
		try {
			s.addSound();
		}catch(Exception e2) {
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
	
	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}	
