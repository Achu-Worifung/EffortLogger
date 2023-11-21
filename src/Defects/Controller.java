package Defects;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private Button clearLog;
	@FXML
	private TextField defectName;

	@FXML
	private Button close;

	@FXML
	private ListView<String> defectCategory;

	@FXML
	private Button delDefect;

	@FXML
	private ComboBox<String> fix;

	@FXML
	private Button newProject;

	@FXML
	private Button reopen;

	@FXML
	private ComboBox<String> selectDefect;

	@FXML
	private ComboBox<String> selectProject;

	@FXML
	private ListView<String> stepInj;

	@FXML
	private ListView<String> stepRem;

	@FXML
	private Button toConsole;
	@FXML
	private Label howmany;
	@FXML
	private Label saved;
	@FXML
	private Label status;
	@FXML
	private TextArea defectSymptop;

	@FXML
	private Button updateDefect;
	List<Document> defects;
	List<String> defectCat;
	boolean change, newDefect;
	int selectedDefect; //keep track of which specific defect currently working on

	//--------------------------------THESE ARE TEMP VALUE ARRAY------------------------------------------
	List<String> stepWhenInjected = Arrays.asList(
			"Planning", "Information Gathering", "Information Understanding",
			"Verifying", "Outlining", "Drafting", "Finalizing",
			"Team Meeting", "Stakeholder Meeting"
			);
	List<String> stepWhenRemoved = Arrays.asList(
			"Planning", "Information Gathering", "Information Understanding",
			"Verifying", "Outlining", "Drafting", "Finalizing",
			"Team Meeting", "Stakeholder Meeting"
			);

	//-----------------------FOR LIST VIEW------------------------------------
	String injection, removed, cat;




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
		selectProject.getItems().add("Business Project");
		selectProject.getItems().add("Development Project");


		defectCat = new Request().getDefectCategory();
		for(String s: defectCat)
		{
			defectCategory.getItems().add(s);
		}
		change = false;
		newDefect = false;

	}
	public void seletedProject(ActionEvent event) {
		//clear everything out
		System.out.println(newDefect);
		if(newDefect) return;
		String selectedProject = selectProject.getValue();

		selectDefect.getItems().clear();
		Thread getDefects = new Thread(() -> {
			// Getting the defects for the selected value
			defects = new Request().getDefects(selectedProject);


			// Adding defects to the defect dropdown
			int i = 0;
			for (Document doc : defects) {
				i++;
				final int index = i;
				Platform.runLater(() -> {
					selectDefect.getItems().add(index + "." + doc.getString("Name"));
					fix.getItems().add(index + "." + doc.getString("Name"));
				});
			}

			// Update UI text using Platform.runLater
			Platform.runLater(() -> {
				howmany.setText(defects.size() + " Effort Log Entry for this Project");
			});
		});

		getDefects.setDaemon(true);
		getDefects.start();
		//specifying the steps when injected steps wehn removed and defect category

		for(String s: stepWhenInjected)
		{
			stepInj.getItems().add(s);
			stepRem.getItems().add(s);
		}
	}

	public void selectDefect(ActionEvent event)
	{
		if(newDefect) return;
		//getting the selected defect index
		String getSelectedvalue = selectDefect.getValue();
		char index = getSelectedvalue.charAt(0);
		selectedDefect = (index-'0')-1;

		//getting the selected document
		Document doc = defects.get(selectedDefect);
		
		String injectionValueToSelect = doc.getString("Injection Step");
		String removedValueToSelect = doc.getString("Removed Step");
		String catValueToSelect = doc.getString("Defect Category");
		//getting the index of each list view
		
//		int indexWhenInjected = stepWhenInjected.indexOf(injectionValueToSelect);
//		stepInj.getSelectionModel().select(indexWhenInjected);
//		
//		int indexWhenRemoved = stepWhenRemoved.indexOf(removedValueToSelect);
//		stepRem.getSelectionModel().select(indexWhenRemoved);
//		
//		int indexcat = defectCat.indexOf(catValueToSelect);
//		defectCategory.getSelectionModel().select(indexcat);
		System.out.println(removedValueToSelect);
		System.out.println(catValueToSelect);
		defectCategory.getSelectionModel().select(removedValueToSelect);
		stepInj.getSelectionModel().select(injectionValueToSelect);
		stepRem.getSelectionModel().select(catValueToSelect);
		

		//Populating all things
		defectName.setText(doc.getString("Name"));
		defectSymptop.setText(doc.getString("Symptom"));
		status.setText(doc.getString("Status"));

		change = true; //will let user know that they've made changes
		newDefect = false;
	}
	public void change()
	{
//		if(!change) return;
		saved.setStyle("-fx-background-color: RED;");
	}

	//tackling buttons
	public void clearDefectLog(ActionEvent e)
	{
		Alert alert;
		if(selectProject.getValue() == null)
		{
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Clear Defect Log");
			alert.setHeaderText("Please first select a project type.");
			alert.show();
			return;
		}
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Clear Defect Log");
		alert.setHeaderText("Are you sure you would like to clear this Defect Log. Warning! "
				+ "THIS CANNOT BE UNDONE");
		Optional<ButtonType> result = alert.showAndWait(); //show and wait for response
		if(result.isPresent() && result.get() == ButtonType.OK)
		{
			new Request().clearDefectLog(selectDefect.getValue());
		}
	}
	public void status(ActionEvent e)
	{
		if(e.getSource() == close && !(status.getText().equals("Closed")))
		{
			status.setText("Closed");
			change=true;
			change();
		}else if(e.getSource() == reopen && !(status.getText().equals("Open")))
		{
			status.setText("Open");
			change=true;
			change();
		}
	}
	public void deleteCurrentDefect(ActionEvent e)
	{
		Alert alert;
		boolean deleted;
		if(selectDefect.getValue() == null )
		{
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Delete Current Defect");
			alert.setHeaderText("Please first select a project type.");
			alert.show();
			return;
		}else if(selectDefect.getValue() == null)
		{
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Clear Defect Log");
			alert.setHeaderText("Please first select a Defect.");
			alert.show();
			return;
		}
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Current Defect");
		alert.setHeaderText("Are you sure you would like to Delete this defect. Warning! "
				+ "THIS CANNOT BE UNDONE");
		Optional<ButtonType> result = alert.showAndWait(); //show and wait for response
		if(result.isPresent() && result.get() == ButtonType.OK)
		{
			//getting the current defect
			Document doc = defects.get(selectedDefect);
			deleted = new Request().deleteDefect(doc.getObjectId("_id"));
		}else 
		{
			return;
		}

		if(deleted)
		{
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Current Defect");
			alert.setContentText(" Defect was deleted successfully");
			alert.show();
		}else 
		{
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Delete Current Defect");
			alert.setContentText("ERROR! Defect was NOT deleted");
			alert.show();

		}
	}
	public void createNewDefect(ActionEvent e)
	{
		newDefect = true;
		selectDefect.setValue("-new defect-");
		defectName.setText("-new defect-");
		stepInj.getItems().clear();
		for(String s: stepWhenInjected)
		{
			stepInj.getItems().add(s);
			stepRem.getItems().add(s);
		}
	}
	public void select()
	{
		// Assuming your ListView contains String items
		injection = (String) stepInj.getSelectionModel().getSelectedItem();
		removed = (String) stepRem.getSelectionModel().getSelectedItem();
		cat  = (String) defectCategory.getSelectionModel().getSelectedItem();
		change = true;
		change(); //changes were made

	}



	public void updateDefect(ActionEvent event)
	{
		//getting all the data
		HashMap<String, String> updateDefects = new HashMap<>();
		updateDefects.put("Project Type", selectProject.getValue());
		updateDefects.put("Previous Name", selectDefect.getValue());
		updateDefects.put("Name", defectName.getText());
		updateDefects.put("Symptom",defectSymptop.getText());
		updateDefects.put("Status", status.getText());
		updateDefects.put("Injection Step", injection);
		updateDefects.put("Removed Step", removed);
		updateDefects.put("Defect Category", cat);
		for (Entry<String, String> entry : updateDefects.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(value == null)
            {
            	Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Updating Effort");
    			alert.setHeaderText("Please Ensure that "+ key+ " is not Null.");
    			alert.show();
            	return;
            }
        }
		new Send().newDefect(updateDefects);
		
//		String injection = stepInj.sel
	}
}
