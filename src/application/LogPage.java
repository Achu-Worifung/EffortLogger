package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogPage extends Application {

	@Override
	public void start(Stage stage) throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("LogPage2.fxml"));
		Scene scene = new Scene(root, 900,900);
		stage.setScene(scene);
		
		stage.getIcons().add(new Image("C:\\Users\\achuw\\eclipse-workspace\\search\\src\\application\\Screenshot 2023-09-19 140838-modified (1).png"));
		stage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
