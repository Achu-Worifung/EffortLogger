package EffortEditor;

import java.awt.TextArea;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button UpdateEntry;

    @FXML
    private Button clearLog;

    @FXML
    private MenuButton cycleStep;

    @FXML
    private DatePicker date;

    @FXML
    private Button deleteEntry;

    @FXML
    private MenuButton effortCat;

    @FXML
    private MenuButton effortentry;

   

    @FXML
    private MenuButton plan;
    @FXML
    private MenuButton projects;

    @FXML
    private Button split;

   

    @FXML
    private Button toConsole;
    
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
