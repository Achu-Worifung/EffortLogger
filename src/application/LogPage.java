package application;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LogPage extends Application {

	@Override
	public void start(Stage stage) throws IOException 
	{
		Parent root = FXMLLoader.load(getClass().getResource("LogPage2.fxml"));
		Scene scene = new Scene(root, 900,800);
//		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.getIcons().add(new Image("/application/logo.png"));
		stage.show();
//		------------------------CENTERING THE STAGE------------------------------------


		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
