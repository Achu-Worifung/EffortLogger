package Defects;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private TextArea defectSymptop;

	@FXML
	private Button updateDefect;
	List<Document> defects;
	List<String> defectCat;
	boolean change;

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

	}
	public void seletedProject(ActionEvent event) {
		String selectedProject = selectDefect.getValue();
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
		//getting the selected defect
		String getSelectedvalue = selectDefect.getValue();
		char index = getSelectedvalue.charAt(0);
		int selectedDefect = (index-'0')-1;

		//getting the selected document
		Document doc = defects.get(selectedDefect);
		String injectionValueToSelect = doc.getString("Step When Injected");
		String removedValueToSelect = doc.getString("Step When Removed");
		int indexWhenInjected = stepWhenInjected.indexOf(injectionValueToSelect);
		int indexWhenRemoved = stepWhenRemoved.indexOf(removedValueToSelect);
		System.out.println(doc.toString());

		//pupulating all things
		defectName.setText(doc.getString("Name"));
		defectSymptop.setText(doc.getString("Symptoms"));

		stepInj.getSelectionModel().select(indexWhenInjected);
		stepRem.getSelectionModel().select(indexWhenRemoved);
		change = true; //will let user know that they've made changes
	}
	public void change()
	{
		if(!change) return;
		saved.setStyle("-fx-background-color: RED;");
	}
	
	//tackling buttons
	public void clearDefectLog(ActionEvent e)
	{
		System.out.println("here");
		if(selectDefect.getValue() == null)
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Clear Defect Log");
			alert.setHeaderText("Please first select a project type.");
			alert.show();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Clear Defect Log");
		alert.setHeaderText("Are you sure you would like to clear this Defect Log. Warning! "
				+ "THIS CANNOT BE UNDONE");
		Optional<ButtonType> result = alert.showAndWait(); //show and wait for response
		if(result.isPresent() && result.get() == ButtonType.OK)
		{
			new Request().clearEffortLog(selectDefect.getValue());
		}
	}
}
