package boundry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.commons.lang3.math.NumberUtils;

import Exceptions.EmptyTextFieldException;
import Exceptions.IllegalCustomerException;
import Exceptions.IllegelPasswordException;
import Exceptions.IllegelUserNameException;
import Exceptions.IncorrectPasswordException;
import control.ControlCustomer;
import control.Sounds;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import entity.*;


public class MainSystem implements Initializable{

	
	private ControlCustomer control = new ControlCustomer();
	
	@FXML
	private Button videoTest;
	
//	CustomerMainPageController controlCustomer = new CustomerMainPageController();

	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private TextField username;

	@FXML
	private Button login;
	
	@FXML
	private PasswordField password;
	@FXML
	private AnchorPane pane;

	@FXML
	private Button signUpButton;

	@FXML
	private Button backPage;
	
//	public void toVideo(ActionEvent e) throws IOException {
//		
//		StackPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\videoPage.fxml"));
//		pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
//		mainPane.getChildren().removeAll(mainPane.getChildren());
//		mainPane.getChildren().add(pane);
//	}
	
	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	@FXML
	private MediaView video;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		file= new File ("vid.mp4");
		media = new Media (file.toURI().toString());
		mediaPlayer= new MediaPlayer(media);
		mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
		video.setMediaPlayer(mediaPlayer);
		mediaPlayer.setMute(true);
		mediaPlayer.play();
		
		
	}
	
	public void signUpToSystem(ActionEvent e) throws IOException{
		goodSound();
		try {
			goodSound();
			AnchorPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder\\SignUpPage.fxml"));
			pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
			mainPane.getChildren().removeAll(mainPane.getChildren());
			mainPane.getChildren().add(pane);			
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void login(ActionEvent e) throws IOException {
		try {
			goodSound();
			/*if the user input for both username and password is manager, then login as an manager*/
			//works, only let you get into manager page if you enter correctly 
//			if(username.getText().isBlank()) {
//				throw new EmptyTextFieldException();
//			}
//			if(password.getText().isBlank()) {
//				throw new IllegelPasswordException();
//			}
//			
			//TODO DELETE!
			if(username.getText().equals("") && password.getText().equals("")) {
				loginSound();
				StackPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder//CustomerFlyMainPage.fxml"));
				pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
				mainPane.getChildren().removeAll(mainPane.getChildren());
				mainPane.getChildren().add(pane);
			}
			//check if the user name is valid - user name must be the passport number, so only valid integer numbers!
			else {
				if(!NumberUtils.isParsable(username.getText())){
					throw new IllegelUserNameException();
				}
				
				//if it is valid, get the correct password to check if entered correctly
				

				HashMap<Integer, String> customers = control.getAllCustomers();
				int customerID = Integer.parseInt(username.getText());
				//save the customer password from the database to check if it matches the one that was entered in the input
				String pass = password.getText();

				//if no password was entered
				if(pass.equals(null)) {
					throw new IncorrectPasswordException();
				}
				//if the given username is a legit customer passport id, check the value
				if(customers.containsKey(customerID)) {
					//check if it's the correct password
					
					if(customers.get(customerID).equals(pass)) {
						//save the customer object in AirHaifaMainScreen with his data
						System.out.println(CustomerFlyMainScreen.customer);
						CustomerFlyMainScreen.customer = control.getCustomer(customerID);
						System.out.println(CustomerFlyMainScreen.customer);
						
						loginSound();						
						StackPane pane=FXMLLoader.load(getClass().getResource("fxmlFolder//CustomerFlyMainPage.fxml"));
						pane.setPrefSize(mainPane.getWidth(), mainPane.getHeight());
						mainPane.getChildren().removeAll(mainPane.getChildren());
						mainPane.getChildren().add(pane);

					}
					else {
						throw new IncorrectPasswordException();						

					}
				}


			}
			
			
		}
		catch(NumberFormatException e1) {
			failLogin("Username must be an Integer (the ID of the user)!");
		}
		catch (IllegalCustomerException e1) {
			failLogin(e1.toString());
		}
		catch(EmptyTextFieldException e1) {
			failLogin(e1.toString());
		}
		catch(IncorrectPasswordException e1) {
			failLogin(e1.toString());
		}
		catch(IllegelPasswordException e1) {
			failLogin(e1.toString());
		}
		catch(IllegelUserNameException e1) {
			failLogin(e1.toString());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	



    
	/***After action sound and alert***/

	public void failLogin(String header) {
		badSound();
		Alert al = new Alert(Alert.AlertType.ERROR);
		al.setContentText("Faild to login");
		al.setHeaderText(header);
		al.setTitle("Fail");
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
    
    
    
	/******Sounds*****/
	
	public void loginSound() {
		Sounds s = new Sounds();
		try {
			s.loginSound();
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
	
	public void goodSound() {
		Sounds s = new Sounds();
		try {
			s.successSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
    

}