package EffortConsole;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class Controller {
	 @FXML
	    private MenuButton chooseproject;

	    @FXML
	    private AnchorPane clockoff;

	    @FXML
	    private AnchorPane clockon;

	    @FXML
	    private Button defects;

	    @FXML
	    private Button defination;

	    @FXML
	    private Button editor;

	    @FXML
	    private Button effortanddefectlogs;

	    @FXML
	    private MenuButton effortcat;

	    @FXML
	    private MenuButton lifecycle;

	    @FXML
	    private Button startactivity;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
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
    	
    	clockoff.setStyle("-fx-background-color: GREEN;");
    }
    public void stopActivity(ActionEvent event)
    {
    	
    	clockoff.setStyle("-fx-background-color: RED;");
    
    }
    
    

}
