package EffortEditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable{

	 @FXML
	    private Button UpdateEntry;

	    @FXML
	    private Button clearLog;

	    @FXML
	    private ComboBox<String> cycleStep;

	    @FXML
	    private DatePicker date;

	    @FXML
	    private Button deleteEntry;

	    @FXML
	    private ComboBox<String> effortCat;

	    @FXML
	    private ComboBox<String> effortentry;

	    @FXML
	    private TextField endTime;

	    @FXML
	    private ComboBox<String> projects;

	    @FXML
	    private Button split;

	    @FXML
	    private TextField startTime;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		projects.getItems().add("Business Project");
		projects.getItems().add("Development Project");
		//suppose to query the db to get info
		//effortentry
		
		String[] lifeCycleItems = {"Planning", "Information Gathering", "Information Understanding", "Verifying",
				"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
		for (String item: lifeCycleItems)
		{
			cycleStep.getItems().add(item);
		}
		String[] effortList = {"Plan","Deliverables", "Interruptions", "Defects", "Others"};
		for (String item: effortList)
		{
			effortCat.getItems().add(item);
		}
		
		
		
	}


}
