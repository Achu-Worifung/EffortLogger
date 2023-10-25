package EffortEditor;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import ToDB.Query;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private Button UpdateEntry;
	@FXML
	private Label entries;

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
	private String projectType;

	private Stage stage;
	private Scene scene;
	private Parent root;
	private List<Document> efforts;

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
		projects.setValue("Development Project");
		//suppose to query the db to get info
		//effortentry may or maynot need the thread
		 efforts = new Query().getEffortLog(projects.getValue());
		entries.setText(efforts.size()+" effort log entries for this project.");

		selectProject(null);

		String[] effortList = {"Plan","Deliverables", "Interruptions", "Defects", "Others"};
		for (String item: effortList)
		{
			effortCat.getItems().add(item);
		}

	}
	//clear project type effort Log
	@FXML
	public void clearLog()
	{
		String projectType = projects.getValue();//getting the project type to be cleared
		//will allow the log to be deleted in the background
		Thread deleteThread = new Thread(() -> {
			boolean done =new Query().clearEffortLog(projectType);
			System.out.println(done);

		});
		deleteThread.setDaemon(true);//method will exit if only daemon thread is left leading to faster performance
		deleteThread.start();
	}
	public void selectProject(ActionEvent event)
	{
		projectType = projects.getValue();
		Thread getEffort = new Thread(() -> {
			 efforts = new Query().getEffortLog(projects.getValue());
			 Platform.runLater(() -> {
			entries.setText(efforts.size()+" effort log entries for this project.");
			 });
			
		});
		//populating entries dropdown untested
		Thread pop = new Thread(() ->{
			while(getEffort.isAlive()) {}//if u r still getting the documents do nothing
			for(int i = 1; i<=efforts.size(); i++)
			{
				Document doc = efforts.get(i);
				effortentry.getItems().add(i+doc.getString("Date")+"("+doc.getString("Start Time")+"-"+doc.getString("End TIme")+doc.getString("Life Cyle Step")+doc.getString("Effort Category")+doc.getString(doc.getString("Effort Category")));
			}
		});
		pop.setDaemon(true);
		pop.start();
		getEffort.setDaemon(true);
		getEffort.start();
		if(cycleStep.getItems() != null) cycleStep.getItems().clear();;
		if(projectType.equalsIgnoreCase("Development Project"))
		{
			String[] lifeCycleItems = {"Problem understanding", "Conceptual Design Plan", "Requirements", "Conceptul Design","Conceptual Design Review","Detailed Design Plan",
					"Detailed Design/Prototype","Detailed Design Review","Implementation Plan","Test Case Generation","Solution Specification","Solution Review","Solution Implementaion",
					"Unit/System Test","Reflection","Repository Update"};
			for (String item: lifeCycleItems)
			{
				cycleStep.getItems().add(item);
			}
		}
		else {
			String[] lifeCycleItems = {"Planning", "Information Gathering", "Information Understanding", "Verifying",
					"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
			for (String item: lifeCycleItems)
			{
				cycleStep.getItems().add(item);
			}
		}
//		 Thread getEffort = new Thread(() -> {
//		        Platform.runLater(() -> {
//		        	projects.setValue(projectType);
//					List<Document> efforts = new Query().getEffortLog(projects.getValue());
//					entries.setText(efforts.size()+" effort log entries for this project.");
//		        });
//		    });
		
		
	}
	public String chooseProject()
	{
		List<Document> result = new Query().getEffortLog(projects.getValue().toString());
		for (Document doc: result)
		{
			effortentry.getItems().add(doc.getString(date)+"("+doc.getString(startTime)+ ")"
					+doc.getString(endTime)+doc.getString(cycleStep)+doc.getString(effortCat)+doc.getString(cycleStep)); //last one meant to be random
		}
		return projects.getValue().toString();
	}


}
