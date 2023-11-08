import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class controller2 implements Initializable{

	// Create a drop shadow effect for the glowing effect
    DropShadow glowEffect = new DropShadow();
   
    
	@FXML
	private Button back1;

	@FXML
	private Button back11;

	@FXML
	private ScrollPane display;

	@FXML
	private AnchorPane pokercards;

	@FXML
	private FlowPane qlookPane;

	@FXML
	private ScrollPane result;
	@FXML
	private ScrollPane scrollpoker;

	@FXML
	private ScrollPane qlookScroll;

	@FXML
	private Button search1;

	@FXML
	private Button search11;

	@FXML
	private TextField searchBar1;

	@FXML
	private TextField searchBar11;

	@FXML
	private AnchorPane searchPane;

	@FXML
	private AnchorPane searchPane1;

	@FXML
	private ImageView w0;

	@FXML
	private ImageView w1;

	@FXML
	private ImageView w2;

	@FXML
	private ImageView w3;


	private List<sample> myList;

	@FXML
	private AnchorPane Sprint;

	@FXML
	private TextArea descrip;

	@FXML
	private Button newSprint;

	@FXML
	private FlowPane displayFlow;
	@FXML
	private FlowPane resultFlow;

	@FXML
	private AnchorPane noSprint;

	@FXML
	private Label titleLabel;

	//	//this is for the preSprint fxml
	//	@FXML
	//	private TextArea description;
	//
	//	@FXML
	//	private Button qlookButton;
	//
	//	@FXML
	//	private Label rating;

	@FXML
	private Label title;
	boolean indisplay;
	public sample quicklooksample;


	@FXML
	void search(ActionEvent event) {

	}
	public void populate() throws IOException
	{
		//addint if there is a sprint of not
		AnchorPane isSprint = FXMLLoader.load(getClass().getResource("upcomming.fxml"));
		displayFlow.getChildren().add(isSprint);
		myList = new sample().getlist();
		int i = -1;
		for (sample sam : myList)
		{
			i++;
			//			//adding sample data
			//			AnchorPane pastSprint = new preSprint().pane(sam, Integer.toString(i));
			//			//    		title.setText(sam.title);
			//			displayFlow.getChildren().add(pastSprint);

			//creating prev sprint anchor
			createSprint(displayFlow, sam, Integer.toString(i));


		}
	}
	//creating the sprint 
	public void createSprint(FlowPane pane, sample sam, String i)
	{
		AnchorPane preSprint = new AnchorPane();
		preSprint.setPrefSize(300, 230);
		preSprint.setMaxSize(300, 230);

		//creating quick look button
		Button qbutton = new Button("Quick Look");	
		qbutton.setPrefSize(90, 25);
		qbutton.setId(i); //giving each quick look button an id from the index in the array

		//actionevent for quicklook button
		qbutton.setOnMouseClicked(event -> {
			quicklooksample = sam;
			quicklook(sam);

		});

		//creating rating label
		Label rating = new Label("Rating: ");
		rating.setStyle("-fx-font-size: 12;");

		//creating the title label
		Label titleLabel = new Label(sam.getTitle());
		titleLabel.setStyle("-fx-font-size: 14;");
		//creating description label
		Label des = new Label("Description:");
		des.setStyle("-fx-font-size: 12;");
		//creating decription area
		TextArea desText = new TextArea(sam.getDescription());
		desText.setWrapText(true);
		desText.setEditable(true);
		//positioning the elements
		AnchorPane.setTopAnchor(titleLabel, 6.0);
		AnchorPane.setLeftAnchor(titleLabel, 5.0);

		//setting the position of the qbutton
		AnchorPane.setTopAnchor(qbutton, 6.0);
		AnchorPane.setRightAnchor(qbutton, 5.0);
		//setting the pos of the description label
		AnchorPane.setTopAnchor(des, 47.0);
		AnchorPane.setLeftAnchor(des, 32.0);
		//positioning the rating label
		AnchorPane.setTopAnchor(rating, 200.0);
		AnchorPane.setLeftAnchor(rating, 214.0);

		//setting the pos of the description text area
		AnchorPane.setTopAnchor(desText, 72.0);
		AnchorPane.setRightAnchor(desText, 5.0);
		AnchorPane.setLeftAnchor(desText, 5.0);
		AnchorPane.setBottomAnchor(desText, 40.0);
		//setting border
		preSprint.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		//adding everything to the anchor
		preSprint.getChildren().addAll(titleLabel, desText, qbutton, des, rating);
		pane.getChildren().add(preSprint);
	}
	//qlookbutton on action
	public void quicklook(sample sample)
	{
		//check if there is already something in the qlook panel
		qlookPane.getChildren().clear();
		AnchorPane root = new AnchorPane();
		root.setPrefSize(340, 895);
		Label titleLabel = new Label(sample.title);
		AnchorPane.setLeftAnchor(titleLabel, 5.0);
		AnchorPane.setTopAnchor(titleLabel, 7.0);

		Label descriptionLabel = new Label("description:");
		AnchorPane.setLeftAnchor(descriptionLabel, 5.0);
		AnchorPane.setTopAnchor(descriptionLabel, 64.0);
		//description text area
		TextArea descriptionTextArea = new TextArea(sample.description);
		descriptionTextArea.setWrapText(true);
		AnchorPane.setLeftAnchor(descriptionTextArea, 5.0);
		AnchorPane.setRightAnchor(descriptionTextArea, 5.0);
		AnchorPane.setTopAnchor(descriptionTextArea, 104.0);
		descriptionTextArea.setPrefWidth(340.0);
		descriptionTextArea.setPrefHeight(113.0);

		Label assignedWeightLabel = new Label("Assigned weight: "+sample.weight);
		AnchorPane.setLeftAnchor(assignedWeightLabel, 15.0);
		AnchorPane.setTopAnchor(assignedWeightLabel, 262.0);
		//change weight button
		Button changeButton = new Button("change");
		changeButton.setOnMouseClicked(event -> {
			changeWeight();

		});
		AnchorPane.setRightAnchor(changeButton, 15.0);
		AnchorPane.setTopAnchor(changeButton, 262.0);
		changeButton.setPrefSize(125, 25);
		//weight history and defects
		TableView<String> tableView = new TableView<>();
		TableColumn<String, String> pastWeightColumn = new TableColumn<>("past weight");
		pastWeightColumn.setPrefWidth(75.0);
		TableColumn<String, String> defectsColumn = new TableColumn<>("defects");
		defectsColumn.setPrefWidth(254.0);
		tableView.getColumns().addAll(pastWeightColumn, defectsColumn);
		AnchorPane.setLeftAnchor(tableView, 5.0);
		AnchorPane.setRightAnchor(tableView, 5.0);
		AnchorPane.setTopAnchor(tableView, 336.0);
		tableView.setPrefWidth(344.0);
		tableView.setPrefHeight(151.0);
		//effort label
		Label effortsLabel = new Label("Efforts");
		effortsLabel.setWrapText(true);
		AnchorPane.setLeftAnchor(effortsLabel, 12.0);
		AnchorPane.setTopAnchor(effortsLabel, 499.0);
		//effort text area
		TextArea effortsTextArea = new TextArea();
		AnchorPane.setLeftAnchor(effortsTextArea, 1.0);
		AnchorPane.setRightAnchor(effortsTextArea, 9.0);
		AnchorPane.setTopAnchor(effortsTextArea, 531.0);
		effortsTextArea.setPrefWidth(354.0);
		effortsTextArea.setPrefHeight(113.0);
		//other information label
		Label otherInfoLabel = new Label("Other info");
		AnchorPane.setLeftAnchor(otherInfoLabel, 11.0);
		AnchorPane.setTopAnchor(otherInfoLabel, 654.0);
		//other information text area
		TextArea otherInfoTextArea = new TextArea(sample.description);
		otherInfoTextArea.setWrapText(true);
		AnchorPane.setLeftAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setRightAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setTopAnchor(otherInfoTextArea, 686.0);
		otherInfoTextArea.setPrefWidth(344.0);
		otherInfoTextArea.setPrefHeight(113.0);
		//create new sprint button
		Button createNewPrintButton = new Button("create new sprint");
		AnchorPane.setLeftAnchor(createNewPrintButton, 5.0);
		AnchorPane.setBottomAnchor(createNewPrintButton, 30.0);
		createNewPrintButton.setPrefSize(125, 25);
		//start a new sprint button
		Button startSprintButton = new Button("start sprint");
		AnchorPane.setRightAnchor(startSprintButton, 5.0);
		AnchorPane.setBottomAnchor(startSprintButton, 30.0);
		startSprintButton.setPrefSize(125, 25);

		root.getChildren().addAll(
				titleLabel, descriptionLabel, descriptionTextArea, assignedWeightLabel, changeButton,
				tableView, effortsLabel, effortsTextArea, otherInfoLabel, otherInfoTextArea,
				createNewPrintButton, startSprintButton
				);
		//adding to the quicklook scroll
		qlookPane.getChildren().add(root);
	}
	
	private void changeWeight() {
		// TODO Auto-generated method stub
		scrollpoker.toFront();
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			populate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		indisplay = true;
	}
	public void back(ActionEvent e)
	{
		display.toFront();
		back11.setDisable(true);
		back1.setDisable(true);
		indisplay = true;
	}
	public void searchSprint(ActionEvent e) throws IOException
	{
		System.out.println("here");
		//deleting any node present in flow
		for (int i = resultFlow.getChildren().size() - 1; i >= 1; i--) {
			resultFlow.getChildren().remove(i);
		}
		String search;
		if(indisplay)
		{
			search = searchBar11.getText();
			searchBar1.setText(search);
			result.toFront();
			indisplay = false;
		}
		else {
			search = searchBar1.getText();
			searchBar11.setText(search);
		}
		int i = -1;
		for(sample sam: myList)
		{
			i++;
			if(sam.title.contains(search) || sam.description.contains(search))
			{

				createSprint(resultFlow, sam, Integer.toString(i));
			}
		}


	}
	
	//on hover envets
	public void w0(MouseEvent e)
	{
		letItGlow(w0);
	}
	public void w1(MouseEvent e)
	{
		letItGlow(w1);
	}
	public void w2(MouseEvent e)
	{
		letItGlow(w2);
	}
	public void w3(MouseEvent e)
	{
		letItGlow(w3);
	}
	public void letItGlow(ImageView imageView)
	{
		
		 glowEffect.setColor(javafx.scene.paint.Color.GREEN); // Set the color of the glow
        // Add event handlers for hover effect
        imageView.setOnMouseEntered(e -> imageView.setEffect(glowEffect));
        imageView.setOnMouseExited(e -> imageView.setEffect(null));
	}
	
	//on click event
	public void w0Clicked(MouseEvent e)
	{
		quicklooksample.setWeight(0);
		qlookScroll.toFront();
	}
	public void w1Clicked(MouseEvent e)
	{
		
		quicklooksample.setWeight(1);
		qlookScroll.toFront();

	}
	public void w2Clicked(MouseEvent e)
	{
		
		quicklooksample.setWeight(2);
		qlookScroll.toFront();

	}
	public void w3Clicked(MouseEvent e)
	{
		quicklooksample.setWeight(3);
		qlookScroll.toFront();

		
	}
}
