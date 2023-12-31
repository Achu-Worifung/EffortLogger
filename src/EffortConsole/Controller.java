package EffortConsole;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import PokerPlanning.Backend.PokerPlaningRespondsPrototype;
import ToDB.Query;
import Universal.FxmlPreLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poker2.Efforts;
import poker2.QuickLook;
import poker2.Rate;
import poker2.RetrieveAll;
import poker2.SingleTon;

public class Controller implements Initializable{
	SingleTon singletonInstance = SingleTon.getInstance();
	@FXML
	private ComboBox<String> chooseproject;

	@FXML
	private Label clock;
	@FXML
	private Label randLable;

	@FXML
	private Button defects;

	@FXML
	private Button poker;

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
	private boolean isOn;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //used to format
    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	FxmlPreLoader loadInstance; //fxmlpreloader instance
	PokerPlaningRespondsPrototype pokerInstance = PokerPlaningRespondsPrototype.getInstance();



	//query object
	Query q;
	@FXML
	void switchForm(ActionEvent event) throws IOException 
	{
		if(event.getSource() == effortanddefectlogs)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Feauture Not Yet Implemented");
			alert.setContentText("Log Manager will be Comming in the NEXT UPDATE");
			alert.show();
			
		}
		else if(event.getSource() == defects)
		{
//			root = FXMLLoader.load(getClass().getResource("/Defects/Defects.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(loadInstance.getDefect());
			stage.setScene(scene);
			stage.show();
		}
		else if(event.getSource() == editor)
		{
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    stage.getScene().setRoot(loadInstance.getEditor());

		    stage.show();
		}

		else if(event.getSource() == defination)
		{
//			Parent root = FXMLLoader.load(getClass().getResource("/definitions/console2.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(loadInstance.getDefinition());
			stage.setScene(scene);

			stage.show();
		}
		else if(event.getSource() == poker)
		{
//			Parent root = FXMLLoader.load(getClass().getResource("/poker2/console.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(loadInstance.getPoker());
			stage.setScene(scene);

			stage.show();
		}
		
	}
	@FXML
	void DynamicSwitchByProject(ActionEvent event) {
		lifecycle.getItems().clear(); //clearing current items

		//adding new appropriate items
		if(chooseproject.getValue() == "Business Project")
		{
			String[] lifeCycleItems = {"Planning", "Information Gathering", "Information Understanding", "Verifying",
					"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
			for (String item: lifeCycleItems)
			{
				lifecycle.getItems().add(item);
			}
			lifecycle.setValue(lifeCycleItems[0]);
		}
		else 
		{
			String[] lifeCycleItems = {"Problem understanding", "Conceptual Design Plan", "Requirements", "Conceptul Design","Conceptual Design Review","Detailed Design Plan",
					"Detailed Design/Prototype","Detailed Design Review","Implementation Plan","Test Case Generation","Solution Specification","Solution Review","Solution Implementaion",
					"Unit/System Test","Reflection","Repository Update"};
			for (String item: lifeCycleItems)
			{
				lifecycle.getItems().add(item);
			}
			lifecycle.setValue(lifeCycleItems[0]);
		}
	}
	@FXML
	void DynamicSwitchByLifeCyle(ActionEvent event) {
		//dynamic switch depending on the life cyle
		ranbycode = true;
		System.out.println("ran");
		pickEffortCat(); //sets text of effort category drop down depending on life cycle choice
		//setRandom(); //sets text of random drop down depending on life cycle choice
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
			if(text == "Plans")
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
				choice =new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Others"};
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
			choice = new String[] {"Break","Phone", "Visitor","Others"};
			for(String s: choice)
			{
				randdrop.getItems().add(s); //add all times in choice to the dropdown
				randdrop.setValue(choice[0]);
			}
			return;
		}
		if (text.equals("Defects")) {
			Thread getDefect = new Thread(() -> {
				List<String> defect = new Query().getDefects(chooseproject.getValue()); // get defects from db
				Platform.runLater(() -> {
					for (String s : defect) {
						randdrop.getItems().add(s); // add all items in choice to the dropdown
					}
					randdrop.setValue(randdrop.getItems().get(0));
				});
			});
			getDefect.setDaemon(true);
			getDefect.start();
			return;

		}if(text == "Others") {
			//set the hidden label visible
			hide.setVisible(true);
			hideText.setVisible(true);
		}
	}

	@FXML
	void DynamicSwitchByRand(ActionEvent event) {
		hide.setVisible(false);
		hideText.setVisible(false);
		String text = randdrop.getValue();
		if(text == "Others")
		{
			hide.setVisible(true);
			hideText.setVisible(true);
		}
	}
	public void startActivity(ActionEvent event)
	{
		
		if(isOn) {
			//alert when trying to start a clock when one is already running
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Start A new  Activity");
			alert.setHeaderText("CLOCK IS ALREADY RUNNING");
			alert.show();

			return; //is the clock is already on do nothing
		}
		if(pokerInstance.getQuick() == null) {
			//alert when trying to start a clock when one is already running
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Start A new  Activity");
			alert.setHeaderText("Please first select or Create a new Sprint");
			alert.show();
			System.out.println("terminated");
			return; //is the clock is already on do nothing
		}
		isOn = true;
		clock.setText("Clock Is ON");
		clock.setStyle("-fx-background-color: GREEN;");
		//getting all infor
		String project = chooseproject.getValue();
		String randdropdown;
		if(randdrop.getValue() == null || randdrop.getValue().isEmpty()|| randdrop.getValue()=="Others")
		{
			randdropdown = hideText.getText();
		}
		else  randdropdown = randdrop.getValue();

		//create a thread for faster performance java concurrancy.
		Thread pushEffort = new Thread(() -> {
//			new Query().StartEfforts(project, LocalDate.now(), LocalTime.now(), lifeCycle, effortCat, randdropdown);
			List<String> startTime = new ArrayList<>();
			startTime.add(LocalTime.now().format(formatter));

			List<String> endTime = new ArrayList<>();
			endTime.add("");

			List<String> startDate = new ArrayList<>();
			startDate.add(LocalDate.now().format(dateformatter));
			
			List<String> lifeCycle = new ArrayList<>();
			lifeCycle.add(lifecycle.getValue());
			
			List<String> effortCat = new ArrayList<>();
			effortCat.add(effortcat.getValue());
			List<String> randVal = new ArrayList<>();
			randVal.add(randdropdown);

			Efforts effort = new Efforts(project,startTime,endTime,startDate,lifeCycle, effortCat, randVal);

			pokerInstance.writeEffortInfo(effort);

		});
		pushEffort.setDaemon(true);//method will exit if only daemon thread is left leading to faster performance
		pushEffort.start();

	}
	public void stopActivity(ActionEvent event)
	{
		//project drop down
		if(!isOn) {
			//alert when trying to stop a clock when none is already running
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Stop Current Activity");
			alert.setHeaderText("THE CLOCK IS NOT RUNNING");
			alert.show();
			return;
		}
		isOn = false;
		clock.setText("Clock Is OFF");
		clock.setStyle("-fx-background-color: RED;");
		Thread stopAction = new Thread(()->{
			
//			PokerPlaningRespondsPrototype pokerPlanning = new PokerPlaningRespondsPrototype();
			Efforts eff = pokerInstance.getEffortOnHold();
			eff.setEndTime(new ArrayList<>(Arrays.asList(LocalTime.now().format(formatter))));
			QuickLook ql = pokerInstance.getQuick();
//		System.out.println(eff.getProjectType());
//		---------------------IF IT IS A NEW SPRINT--------------------
			if(ql.isNewSprint())
			{
//			--------------KEETING ALL THE PEOPLE WHO VOTED AND THEIR VOTES-----------
				List<Rate> userRates = pokerInstance.getAllUserRates();
				double sum = 0.0;

				// Assuming userRates is a List of numeric values
				for (Rate rate : userRates) {
				    sum += rate.getRate();
				}

				double average = userRates.isEmpty() ? 0.0 : sum / userRates.size();

				ql.setRating((int)average);
				ql.setUserRates(userRates);
				pokerInstance.writeTo(new RetrieveAll(eff, ql));
			}else 
			{
				ql.setStatus("In Progress");
				pokerInstance.updatenew(new RetrieveAll(eff, ql));
			}
//		---------------------DELETE INFO IN THE TEMP TABLES--------------
			pokerInstance.delAllUserRates();
			pokerInstance.deleteEffortOnHold();
			pokerInstance.delQuickLookOnHold();
		});
		stopAction.setDaemon(true);
		stopAction.start();
		//stopping an activity
		//create a thread for faster performance java concurrency.
//		Thread pushEffort = new Thread(() -> {
//			new PokerPlaningRespondsPrototype().stopSprint();
//		});
//		pushEffort.setDaemon(true);//method will exit if only daemon thread is left leading to faster performance
//		pushEffort.start();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Thread load = new Thread(()->{
			
			try {
				loadInstance = FxmlPreLoader.getInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Efforts effort = pokerInstance.getEffortOnHold();
			if (effort != null)
			{
				Platform.runLater(() -> {
					clock.setText("Clock Is ON");
					clock.setStyle("-fx-background-color: GREEN;");
				});
				
				isOn = true;
			}else 
			{
				Platform.runLater(() -> {
					clock.setText("Clock Is OFF");
					clock.setStyle("-fx-background-color: RED;");
				});
				
				isOn = false;
			}
		});
		
		// adding drop down menu options
		chooseproject.getItems().add("Business Project");
		chooseproject.getItems().add("Development Project");
		chooseproject.setValue("Business Project");

		String[] lifeCycleItems = {"Planning", "Information Gathering", "Information Understanding", "Verifying",
				"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
		for (String item: lifeCycleItems)
		{
			lifecycle.getItems().add(item);
		}
		lifecycle.setValue(lifeCycleItems[0]);
		String[] effortCat = {"Plans","Deliverables", "Interruptions", "Defects", "Others"};
		for (String item: effortCat)
		{
			effortcat.getItems().add(item);
		}
		effortcat.setValue(effortCat[0]);
		randLable.setText(effortcat.getValue());
		String []choice =new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
		for(String s: choice)
		{
			randdrop.getItems().add(s); //add all times in choice to the dropdown
			randdrop.setValue(choice[0]);
		}
		load.setDaemon(true);
		load.start();
	}

	public void setRandomDrop()
	{
		String choice[];
		randdrop.getItems().clear(); //removing all current items
		String lifeCycleValue = lifecycle.getValue();
		if(lifeCycleValue == "Planning")
		{
			choice = new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
			for (String item: choice)
			{
				randdrop.getItems().add(item); //add all times in choice to the dropdown
			}
			randdrop.setValue(choice[0]); //set the value to the 1st available choice
			return; //stop method execution
		}
		//this will resort in conceptual design 
		String concept[] = {"Detailed Design Review","Detailed Design/Prototype","Information Gathering","Information Understanding","Verifying","Problem understanding","Requirements","Conceptul Design","Conceptual Design Review"};
		//check if selected value is a conceptual design plan
		for (String s: concept)
		{
			if (s == lifeCycleValue)
			{
				choice = new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Others"};
				for (String item: choice)
				{
					randdrop.getItems().add(item);
				}
				randdrop.setValue(choice[0]);
				if(s.equalsIgnoreCase("Detailed Design/Prototype") || s.equalsIgnoreCase("Detailed Design Review"))
				{
					randdrop.getItems().remove(0);
					randdrop.setValue(choice[1]);

				}
				return;
			}
		}

		//this will resort in solutions for random drop
		String solutions[] = {"Repository Update","Reflection","Unit/System Test","Solution Implementaion","Solution Review","Solution Specification", "Test Case Generation"};
		//check if selected value is a solution
		for (String s: solutions)
		{
			if(s == lifeCycleValue)
			{
				choice = new String[]{"Solution","Reflection","Outline","Draft","Report","User Defined","Others"};
				for (String item: choice)
				{
					randdrop.getItems().add(item);
				}
				if(lifeCycleValue == "Test Case Generation")
				{
					randdrop.getItems().add(0, "Test Case");
				}
				randdrop.setValue(randdrop.getItems().get(0));
				return;
			}
		}

		//check if selected value is "Implementation Plan"
		if(lifeCycleValue == "Implementation Plan"||lifeCycleValue == "Conceptual Design Plan"|| lifeCycleValue == "Detailed Design Plan")
		{
			randdrop.getItems().add("Implementation Plan");
			randdrop.setValue("Implementation Plan");
			if(lifeCycleValue == "Conceptual Design Plan")
			{
				randdrop.getItems().add(0, "Conceptual Design Plan");
				randdrop.getItems().add(1, "Detailed Design Plan");
				randdrop.setValue("Conceptual Design Plan");
			}
			if(lifeCycleValue == "Detailed Design Plan")
			{
				randdrop.getItems().add(0, "Detailed Design Plan");
				randdrop.setValue("Detailed Design Plan");

			}
			return;
		}



		if(lifeCycleValue == "Drafting"|| lifeCycleValue == "Finalizing"||lifeCycleValue == "Outlining")
		{
			choice = new String[]{"Drafting", "Report", "User Defined", "Others"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			if(lifeCycleValue == "Finalizing") {
				randdrop.getItems().remove(0);
			}
			if(lifeCycleValue == "Outlining")
			{
				randdrop.getItems().remove(0);
				randdrop.getItems().add(0, "Outline");
				randdrop.getItems().add(1, "Draft");
			}
			randdrop.setValue( randdrop.getItems().get(0));

			return;
		}

		if(lifeCycleValue == "Team Meeting"||lifeCycleValue == "Coach Meeting"||lifeCycleValue == "Stakeholder Meeting")
		{
			choice = new String[]{"Conceptual Design", "Detailed Design", "Test Case","Solution", "Reflection", "OUtline", "Draft", "Report", "User Defined", "Others"};
			for (String item: choice)
			{
				randdrop.getItems().add(item);
			}
			randdrop.setValue(choice[0]);

			return;
		}
	}
	public void pickEffortCat()
	{
		String[] plans = {"Planning", "Conceptual Design Plan","Detailed Design Plan","Implementation Plan"};
		for (String s: plans)
		{
			if (s == lifecycle.getValue())
			{
				effortcat.setValue("Plans"); 
				return;
			}
		}
		effortcat.setValue("Deliverables"); 
	}



}
