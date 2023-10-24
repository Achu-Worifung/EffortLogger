package EffortConsole;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable{
	@FXML
	private ComboBox<String> chooseproject;

	@FXML
	private Label clock;
	@FXML
	private Label randLable;

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
	private ComboBox<String> randdrop;

	@FXML
	private Button startactivity;
	@FXML
	private Label hide;

	@FXML
	private TextField hideText;

	private Stage stage;
	private Scene scene;
	private Parent root;
	boolean ranbycode;

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

	@FXML
	void DynamicSwitch(ActionEvent event) {
		//dynamic switch depending on the life cyle
		ranbycode = true;
		System.out.println("ran");
		pickEffortCat(); //sets text of effort category drop down depending on life cycle choice
		setRandom(); //sets text of random drop down depending on life cycle choice
		setRandomDrop(); //set the choice available in random depending on life cycle choice
		String text = effortcat.getValue() == "Plans"? "Plans":"Deliverables";
		randLable.setText(text);
		ranbycode = false;

	}
	@FXML
	void DynamicSwitchByEffortCat(ActionEvent event)  {
		System.out.println("effortcat ran");
		hide.setVisible(false);
		hideText.setVisible(false);
		//setting the random value accordingly
		randdrop.getItems().clear(); //clearing the dropdown options
		String text = effortcat.getValue();
		String[] choice;
		randLable.setText(text);
		//setting up the dropdown for random
		if(!ranbycode) {
			if(text == "Plan")
			{
				choice =new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
				for(String s: choice)
				{
					randdrop.getItems().add(s); //add all times in choice to the dropdown
					randdrop.setValue(choice[0]);
				}
				return;
			}
			if(text == "Deliverables")
			{
				choice =new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Other"};
				for(String s: choice)
				{
					randdrop.getItems().add(s); //add all times in choice to the dropdown
					randdrop.setValue(choice[0]);
				}
				return;
			}
		}
		if(text == "Interruptions")
		{
			choice = new String[] {"Break","Phone", "Visitor","Other"};
			for(String s: choice)
			{
				randdrop.getItems().add(s); //add all times in choice to the dropdown
				randdrop.setValue(choice[0]);
			}
			return;
		}
		if(text == "Defects")
		{
			//might want to do this in the initialize method for faster respond
			List<String> defect = new Query().getDefects(); //get defects from db


			for(String s: defect)
			{
				randdrop.getItems().add(s); //add all times in choice to the dropdown
			}
			randdrop.setValue(defect.get(0));
			return;
		}if(text == "Others") {
			//set the hidden label visible
			hide.setVisible(true);
			hideText.setVisible(true);
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






	}
	//	public String[] random(String effortCat)
	//	{
	//		//the life cycle steps controlls the effort category which controlls the random
	//		String choice[];
	//		switch(effortCat)
	//		{
	//		case "Plans":
	//			 choice = new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
	//			break;
	//		case "deliverables":
	//			choice = new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Other"};
	//			break;
	//		case "interruptions":
	//			choice = new String[]{"Break", "Phone", "Teammate", "Visitor", "Other"};
	//			break;
	//		case "defect":
	//			choice = new String[] {"-no defect selected-"};
	//			break;
	//			default:
	//				choice = new String[] {""};
	//		}
	//		return choice;
	//	
	//	}
	public void setRandomDrop()
	{
		String choice[];
		randdrop.getItems().clear(); //removing all current items
		if(lifecycle.getValue() == "Planning")
		{
			choice = new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
			for (String item: choice)
			{
				randdrop.getItems().add(item); //add all times in choice to the dropdown
			}
			return; //stop method execution
		}
		if(lifecycle.getValue() == "Information Gathering"|| lifecycle.getValue() == "Information Understanding"||lifecycle.getValue() == "Verifying")
		{
			choice = new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Other"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			return;
		}
		if(lifecycle.getValue() == "Outlining")
		{
			choice = new String[] {"Outline", "Draft", "Report","User Defined","Other"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			return;
		}
		if(lifecycle.getValue() == "Drafting")
		{
			choice = new String[]{"Drafting", "Report", "User Defined", "Others"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			return;
		}
		if(lifecycle.getValue() == "Finalizing")
		{
			choice = new String[]{"Report", "User Defined", "Others"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			return;
		}
		if(lifecycle.getValue() == "Team Meeting"||lifecycle.getValue() == "Coach Meeting"||lifecycle.getValue() == "Stakeholder Meeting")
		{
			choice = new String[]{"Conceptual Desing", "Detailed Design", "Test Case","Solution", "Reflection", "OUtline", "Draft", "Report", "User Defined", "Other"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			return;
		}
	}
	public void pickEffortCat()
	{
		if(lifecycle.getValue() == "Planning")
		{
			effortcat.setValue("Plans"); 
		}
		else 
			effortcat.setValue("Deliverables"); 
	}
	public void setRandom()
	{
		//set the dropdown for random accordingly
		if(lifecycle.getValue() == "Planning")
		{
			randdrop.setValue("Project Plan"); 
			return;
		}
		if(lifecycle.getValue() == "Information Gathering" || lifecycle.getValue() == "Information Understanding"|| lifecycle.getValue() == "Verifying")
		{
			randdrop.setValue("Information Gathering"); 
			return;
		}
		if(lifecycle.getValue() == "Outlining")
		{
			randdrop.setValue("Outline"); 
			return;
		}
		if(lifecycle.getValue() == "Drafting")
		{
			randdrop.setValue("Drafts"); 
			return;
		}
		if(lifecycle.getValue() == "Finalizing")
		{
			randdrop.setValue("Report"); 
			return;
		}
		if(lifecycle.getValue() == "Team Meeting"||lifecycle.getValue() == "Coach Meeting"||lifecycle.getValue() == "Stakeholder Meeting")
		{
			randdrop.setValue("Conceptual Design"); 
			return;
		}
	}


}
