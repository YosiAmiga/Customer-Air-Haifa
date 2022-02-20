package boundry;
	
import control.Sounds;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("fxmlFolder//CustomerFly.fxml"));
			programOnSound();

			Scene scene = new Scene(root,1300,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Manager Fly system");
			primaryStage.setResizable(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void programOnSound() {
		Sounds s = new Sounds();
		try {
			s.programOnSound();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

