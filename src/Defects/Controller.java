package Defects;

import java.awt.TextArea;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class Controller {
	
	 @FXML
	    private Button clearLog;

	    @FXML
	    private Button close;

	    @FXML
	    private ListView<?> defectCategory;

	    @FXML
	    private Button delDefect;

	    

	    @FXML
	    private MenuButton fix;

	    @FXML
	    private Button reopen;

	    @FXML
	    private MenuButton selectDefect;

	    @FXML
	    private MenuButton selectProject;

	    @FXML
	    private ListView<?> stepInj;

	    @FXML
	    private ListView<?> stepRem;

	    @FXML
	    private Button toConsole;

	    @FXML
	    private Button updateDefect;

	    
	    private Stage stage;
	    private Scene scene;
	    private Parent root;

	    @FXML
	    void toConsole(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("/EffortConsole/Console.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
}
