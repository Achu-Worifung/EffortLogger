package EffortConsole;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ToDB.Query;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class Controller implements Initializable{
    @FXML
    private ComboBox<String> chooseproject;

    @FXML
    private Label clock;

    @FXML
    private Button defects;

    @FXML
    private Button defination;

    @FXML
    private Button editor;

    @FXML
    private Button effortanddefectlogs;

    @FXML
    private ComboBox<String> effortcat;

    @FXML
    private ComboBox<String> lifecycle;

    @FXML
    private ComboBox<String> plan;

    @FXML
    private Button startactivity;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //query object
    Query q;
    @FXML
	void switchForm(ActionEvent event) throws IOException 
	{
    	if(event.getSource() == defects)
    	{
    		root = FXMLLoader.load(getClass().getResource("/Defects/Defects.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
    	if(event.getSource() == editor)
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("/EffortEditor/EditorConsole.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
    	
	}
    public void startActivity(ActionEvent event)
    {
    	clock.setText("Clock Is ON");
    	clock.setStyle("-fx-background-color: GREEN;");
    	
    }
    public void stopActivity(ActionEvent event)
    {
    	//project drop down
    	clock.setText("Clock Is OFF");
    	clock.setStyle("-fx-background-color: RED;");
    
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// adding drop down menu options
		if (new Query().openEffort())
		{
			startActivity(null);
		}else stopActivity(null);
		chooseproject.getItems().add("Business Project");
		chooseproject.getItems().add("Development Project");
		
		String[] lifeCycleItems = {"Planning", "Information Gathering", "Information Understanding", "Verifying",
				"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
		for (String item: lifeCycleItems)
		{
			lifecycle.getItems().add(item);
		}
		String[] effortCat = {"Plan","Deliverables", "Interruptions", "Defects", "Others"};
		for (String item: effortCat)
		{
			effortcat.getItems().add(item);
		}
		String[] planList = {"Project Plan", "Risk Management Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
		for (String item: planList)
		{
			plan.getItems().add(item);
		}
		

		
	}
    
    

}
