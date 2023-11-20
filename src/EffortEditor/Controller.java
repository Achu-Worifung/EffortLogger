package EffortEditor;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;
import org.bson.types.ObjectId;

import ToDB.Query;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller implements Initializable{

	ObjectId id;
	@FXML
	private Button UpdateEntry;
	@FXML
	private Label entries;
	@FXML
	private Label change;

	@FXML
	private Button clearLog;

    @FXML
    private Label labelrand;
    @FXML
    private Label hideText;

	@FXML
	private ComboBox<String> cycleStep;
	@FXML
	private ComboBox<String> random;

	@FXML
	private TextField date;
	@FXML
	private TextField hide;

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
	List<String> effortDate;
	List<String> lifeCycle;
	List<String> randVal;
	private String rand;
	List<String> effort;
	List<String> end;
	List<String> start;
	boolean canUpdate;

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

		String[] effortList = {"Plans","Deliverables", "Interruptions", "Defects", "Others"};
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
		//clear effortentry if it already has options
		if(effortentry.getItems() != null) effortentry.getItems().clear();
//		hide.setVisible(false);
//		hideText.setVisible(false);
		//thread to retrive all the project efforts from db
		projectType = projects.getValue();
		Thread getEffort = new Thread(() -> {
			 efforts = new Query().getEffortLog(projects.getValue()); //returns a list of document
			 Platform.runLater(() -> {
			entries.setText(efforts.size()+" effort log entries for this project."); //get the size of the list
			 });
			
		});
		getEffort.setDaemon(true);
		getEffort.start();
		//populating entries dropdown untested
		Thread pop = new Thread(() ->{
			while(getEffort.isAlive()) {System.out.println("waiting...");}//if u r still getting the documents do nothing
			int count = 1;
			a: //label the loop
			for(int i = 0; i<efforts.size(); i++)
			{
				
				Document doc = efforts.get(i);
				//get all info for doc at i
				effortDate = doc.getList("Start Dates", String.class);
				start = doc.getList("Start Time", String.class);
				end = doc.getList("End Time", String.class);
				effort = doc.getList("Effort Category", String.class);
				lifeCycle = doc.getList("Life Cycle Step", String.class);
				randVal = doc.getList("Random Value", String.class);
				
				//get the last effort info(can only edit the last one
				String lastEffortDate = effortDate.get(effortDate.size()-1);
				String lastStartTime = start.get(start.size()-1);
				String lastEnd = end.get(end.size()-1);
				String lastEffortCat = effort.get(effort.size()-1);
				String lastLifeCycle = lifeCycle.get(lifeCycle.size()-1);
				String lastRandVal = randVal.get(randVal.size()-1);
				//skip over any effort with a null value
//				String[] item = {effortDate.get(effortDate.size()-1),start.get(start.size()-1),end.get(end.size()-1),effort.get(effort.size()-1),lifeCycle.get(lifeCycle.size()-1),randVal.get(randVal.size()-1)};
//				for(String s: item) if(s == null) continue a; //skip the current document
				//set the effortentry drop down options
//				effortentry.getItems().add(count+" . "+effortDate+" ( "+start+ " - "+end+" ) "+lifeCycle+effort+ "  "+ randVal);
				effortentry.getItems().add(count+" . "+lastEffortDate+" ( "+lastStartTime+ " - "+lastEnd+" ) "+lastLifeCycle+lastEffortCat+ "  "+ lastRandVal);
				count++;
			}
		});
		pop.setDaemon(true);
		pop.start();
		
		if(cycleStep.getItems() != null) cycleStep.getItems().clear();
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
		//storing the object id
		
		
	}
	public void populateTExtField(ActionEvent event)
	{
		System.out.println("he you");
		//get the selected document
		String getSelectedvalue = effortentry.getValue();
		char index = getSelectedvalue.charAt(0);
		int selectedIndex = (index-'0')-1;
		
		//getting the selected doc
		Document doc = efforts.get(selectedIndex);
		id = doc.getObjectId("_id"); //set id to the selected doc id
		
		String lastEffortDate = doc.getList("Start Dates", String.class).get(doc.getList("Start Dates", String.class).size()-1);
		String lastStartTime = doc.getList("Start Time", String.class).get(doc.getList("Start Time", String.class).size()-1);
		String lastEndTime = doc.getList("End Time", String.class).get(doc.getList("End Time", String.class).size()-1);
		String lastEffortCat = doc.getList("Effort Category", String.class).get(doc.getList("Effort Category", String.class).size()-1);
		String lastLifeCycle = doc.getList("Life Cycle Step", String.class).get(doc.getList("Life Cycle Step", String.class).size()-1);
		String lastRandVal = doc.getList("Random Value", String.class).get(doc.getList("Random Value", String.class).size()-1);
		
		
		
		date.setText(lastEffortDate);
		startTime.setText(lastStartTime);
		endTime.setText(lastEndTime);
		effortCat.setValue(lastEffortCat);
		labelrand.setText(lastEffortCat);
		random.setValue(lastRandVal);
		
		canUpdate = true;
	}
	public void DynamicSwitchByEffortCat(ActionEvent event)  {
		System.out.println("effortcat ran");
		hide.setVisible(false);
		hideText.setVisible(false);
		//setting the random value accordingly
		random.getItems().clear(); //clearing the dropdown options
		String text = effortCat.getValue();
		String[] choice;
		labelrand.setText(text);
		//setting up the dropdown for random
			if(text == "Plans")
			{
				choice =new String[]{"Project plans", "Risk Management", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan"};
				for(String s: choice)
				{
					random.getItems().add(s); //add all times in choice to the dropdown
					random.setValue(choice[0]);
				}
				return;
			}
			if(text == "Deliverables")
			{
				choice =new String[]{"Conceptual Design", "Detailed Design", "Test case", "Solution", "Reflection", "Outline", "Draft", "Report", "User Defined", "Others"};
				for(String s: choice)
				{
					random.getItems().add(s); //add all times in choice to the dropdown
					random.setValue(choice[0]);
				}
				return;
			}
		if(text == "Interruptions")
		{
			choice = new String[] {"Break","Phone", "Visitor","Others"};
			for(String s: choice)
			{
				random.getItems().add(s); //add all times in choice to the dropdown
				random.setValue(choice[0]);
			}
			return;
		}
		if (text.equals("Defects")) {
		    Thread getDefect = new Thread(() -> {
		        List<String> defect = new Query().getDefects(); // get defects from db
		        Platform.runLater(() -> {
		            for (String s : defect) {
		            	random.getItems().add(s); // add all items in choice to the dropdown
		            }
		            random.setValue(random.getItems().get(0));
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
		canUpdate = true;
	}
	public void changed(ActionEvent event)
	{
		//create a boolean that says when we can update
		System.out.println("ran changed");
		if(event.getSource() == effortCat) DynamicSwitchByEffortCat( event);
		if(canUpdate)
			change.setStyle("-fx-background-color: RED;");
	}
	public void update(ActionEvent event)
	{
		//continue here validate the update to ensure no conflicting info
		String newDate = date.getText();
		LocalDate date = LocalDate.parse(newDate);
		String sTime =startTime.getText();
		LocalTime start = LocalTime.parse(sTime);
		String eTime = endTime.getText();
		LocalTime end =LocalTime.parse(eTime);
		String category = effortCat.getValue();
		String lifeCycle = cycleStep.getValue();
		String rand =random.getValue();
		
		
//		String he = date.getText();
//		LocalDate date = LocalDate.parse( he);
//		String st = startTime.getText();
//		LocalTime start = LocalTime.parse(st);
//		String et = endTime.getText();
//		LocalTime end = LocalTime.parse(et);
//		String cat = effortCat.getValue(); 
//		String life = cycleStep.getValue(); 
//		String rand = random.getValue();
		if(rand.isEmpty() || rand == null)
		{
			rand = hide.getText();
		}
		String valid = new ValidateUpdate().valide(newDate, start, end);
		if(!valid.equals("success"))
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Effort Log Editor");
			alert.setHeaderText(valid);
			alert.show();
			//do the alert
			return;
		}
		//calculating the diff in time
		Duration time = Duration.between(start, end);
		long timeSpent = time.toSeconds();
		
		boolean succ =new Query().updateEffort(id, timeSpent, newDate, sTime, eTime, lifeCycle, category, rand);
		if(succ)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Effort Log Editor");
			alert.setHeaderText("Effort Edited Successfully");
			alert.show();
		}else 
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Effort Log Editor");
			alert.setHeaderText("Effort Edited Failee");
			alert.show();
		}
		
		
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
	public void deleteEnt(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete an Effort Log Entry Request");
		alert.setHeaderText("Are you sure you want to delte this effort log entry? IT CANNOT BE UNDONE.");
		if(!(alert.showAndWait().get() == ButtonType.OK))
		{
			return;
		}
		if(id == null)
		{
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Error");
			alert.setHeaderText("You must first select a log before you can delete it.");
			alert.show();
			return;
		}
		new Query().deleteEntry(id);
		//clear things after deletion
	}


}
