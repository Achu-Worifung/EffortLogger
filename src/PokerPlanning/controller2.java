//package PokerPlanning;
//
//import java.io.IOException;
//import java.net.URL;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import org.bson.types.ObjectId;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import poker2.Efforts;
//import javafx.scene.Node;
//
//
//import PokerPlanning.Backend.*;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.StackPane;
//
//public class controller2 implements Initializable{
//
//	// Create a drop shadow effect for the glowing effect
//	upcommingPane upcommingPan;
//	AnchorPane effortConsole; //going back to the console
//	DropShadow glowEffect = new DropShadow();
//	Label dynamicLabel;
//	boolean inProgress;
//
//	@FXML
//	private Button back1;
//
//	@FXML
//	private Button back11;
//
//	@FXML
//	private ScrollPane display;
//
//	@FXML
//	private AnchorPane pokercards;
//
//	@FXML
//	protected FlowPane qlookPane;
//
//	@FXML
//	private ScrollPane result;
//	@FXML
//	private ScrollPane scrollpoker;
//
//	@FXML
//	private ScrollPane qlookScroll;
//
//	@FXML
//	private Button search1;
//
//	@FXML
//	private Button search11;
//
//	@FXML
//	private TextField searchBar1;
//
//	@FXML
//	private TextField searchBar11;
//
//	@FXML
//	private AnchorPane searchPane;
//
//	@FXML
//	private AnchorPane searchPane1;
//
//	@FXML
//	private ImageView w0;
//
//	@FXML
//	private ImageView w1;
//
//	@FXML
//	private ImageView w2;
//
//	@FXML
//	private ImageView w3;
//
//
//	//	private List<sample> myList;
//
//	@FXML
//	private FlowPane displayFlow;
//	@FXML
//	private FlowPane resultFlow;
//
//	qLook ql; //quick look object; returns quick look anchor pane
//	//--------------------------------------------------QUICKLOOK PANEL COMPONENTS---------------------------------------------------------
//	TextArea qdescriptionTextArea, qeffortsTextArea, qotherInfoTextArea; 
//	Button change, startSprint, createSprint;
//	TextField qlooktitle;
//	
//	Label qassignedWeightLabel;
//	PokerPlanning.Singleton singletonInstance = Singleton.getInstance(); //getting the singleton instance from poker planning
//	@FXML
//	private Label title;
//	boolean indisplay;
//	public PokerPlanning.Backend.quicklookInfo quicklooksample;
//	public PokerPlanning.Backend.quicklookInfo infosample;
//	AnchorPane root; //for the quick loop 
//	List<effort> information;//
//	
////	 FORMATTER------------
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //used to format
//	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//	
//	@FXML
//	void search(ActionEvent event) {
//
//	}
//	public void populate() throws IOException
//	{
//		//addint if there is a sprint of not
//		upcommingPan = new upcommingPane();
//		AnchorPane isSprint = upcommingPan.createUI();
//		displayFlow.getChildren().add(isSprint);
//		information = singletonInstance.getEffortList(); //getting all data stored in design pattern
//		//giving the creat button an actionlisterner
//		Button createSprint =upcommingPan.getNewSprintButton();
//		createSprint.setOnMouseClicked(event -> {
//			//an empty quick look pane
//			quicklook(new quicklookInfo(new ObjectId(),"", "", "", 0, new Rate(0, "")));
//		});
//		for(int i = 0; i < information.size();i++)
//		{
//			PokerPlanning.Backend.quicklookInfo qlookinfo = information.get(i).getInfo();
//			//check if any sprint is in progress
//			//			if(information.get(i).getStatus()!=null&&information.get(i).getStatus().equalsIgnoreCase("In Progress"))
//			//			{
//			//				upcommingPan.getSprintPane().toFront();
//			//				upcommingPan.getTitleLabel().setText(information.get(i).getInfo().getTitle());
//			//				upcommingPan.getRatingLabel().setText("Rating: "+qlookinfo.getPresentRating().toString());
//			//
//			//				//				------------------------IMPORTANT-----------------------------------------------
//			//				//UNCOMMENT LINE BELOW WHEN YOU FIX THE TIMES IN THE DATA BASE
//			//				//				getting the time it started
//			//				List<String> startTimeList = information.get(i).getStartTime();
//			//
//			//				// Check if the startTimeList is not empty
//			//				if (!startTimeList.isEmpty()) {
//			//					// Get the last element from the startTimeList
//			//					String lastStartTimeString = startTimeList.get(startTimeList.size() - 1);
//			//					// Convert the String to LocalTime
//			//					LocalTime timeStarted = LocalTime.parse(lastStartTimeString);
//			//					// Check if the current hour is after the target hour
//			//					Duration duration = Duration.between(timeStarted, LocalTime.now());
//			//					if(duration.toMinutes() > 10) upcommingPan.getTimeLabel().setText("All votes accounted start Effort");
//			//					else countDown(Math.toIntExact((10-duration.toMinutes())));
//			//
//			////					System.out.println(upcommingPan.getStartButton());
//			//
//			//					upcommingPan.getDescTextArea().setText(qlookinfo.getDesc());
//			//					inProgress = true;
//			//				}
//			//				upcommingPan.getStartButton().setOnMouseClicked(event ->
//			//				{
//			//					try {
//			//						startNow(event);
//			//					} catch (IOException e) {
//			//						e.printStackTrace();
//			//					}
//			//				});
//			//				upcommingPan.getVote().setOnMouseClicked(event ->
//			//				{
//			//					try
//			//					{
//			////						quicklooksample = information.get(index).getInfo();
//			////						changeWeight();
//			//					}catch(Exception e)
//			//					{
//			//						e.printStackTrace();
//			//					}
//			//				});
//			//				
//			//				continue;
//			//			}
//			createSprint(displayFlow, qlookinfo, Integer.toString(i));
//
//
//		}
//	}
//	//creating the sprint 
//	public void createSprint(FlowPane pane, PokerPlanning.Backend.quicklookInfo qlookinfo, String i)
//	{
//		AnchorPane preSprint = new AnchorPane();
//		preSprint.setPrefSize(300, 230);
//		preSprint.setMaxSize(300, 230);
//
//		//creating quick look button
//		Button qbutton = new Button("Quick Look");	
//		qbutton.setPrefSize(90, 25);
//		qbutton.setId(i); //giving each quick look button an id from the index in the array
//
//		//actionevent for quicklook button
//		qbutton.setOnMouseClicked(event -> {
//			infosample = qlookinfo;
//			quicklook(qlookinfo);
//
//		});
//		//creating rating label
//		Label rating = new Label("Rating: "+qlookinfo.getPresentRating());
//		rating.setStyle("-fx-font-size: 12;");
//
//		//creating the title label
//		Label titleLabel = new Label(qlookinfo.getTitle());
//		titleLabel.setStyle("-fx-font-size: 14;");
//		//creating description label
//		Label des = new Label("Description:");
//		des.setStyle("-fx-font-size: 12;");
//		//creating decription area
//		TextArea desText = new TextArea(qlookinfo.getDesc());
//		desText.setWrapText(true);
//		desText.setEditable(true);
//		//positioning the elements
//		AnchorPane.setTopAnchor(titleLabel, 6.0);
//		AnchorPane.setLeftAnchor(titleLabel, 5.0);
//
//		//setting the position of the qbutton
//		AnchorPane.setTopAnchor(qbutton, 6.0);
//		AnchorPane.setRightAnchor(qbutton, 5.0);
//		//setting the pos of the description label
//		AnchorPane.setTopAnchor(des, 47.0);
//		AnchorPane.setLeftAnchor(des, 32.0);
//		//positioning the rating label
//		AnchorPane.setTopAnchor(rating, 200.0);
//		AnchorPane.setLeftAnchor(rating, 214.0);
//
//		//setting the pos of the description text area
//		AnchorPane.setTopAnchor(desText, 72.0);
//		AnchorPane.setRightAnchor(desText, 5.0);
//		AnchorPane.setLeftAnchor(desText, 5.0);
//		AnchorPane.setBottomAnchor(desText, 40.0);
//		//setting border
//		preSprint.setStyle("-fx-border-color: black; -fx-border-width: 1;");
//		//adding everything to the anchor
//		preSprint.getChildren().addAll(titleLabel, desText, qbutton, des, rating);
//		pane.getChildren().add(preSprint);
//	}
//	//qlookbutton on action
//	public void quicklook(PokerPlanning.Backend.quicklookInfo qlookinfo)
//	{
//		//check if there is already something in the qlook panel
//		qlookPane.getChildren().clear();
//
//		ql = new qLook();
//		root = ql.getAnchor(qlookinfo);
//		quicklooksample = qlookinfo;
//
//		//adding onclick action to the change button
//		 change = ql.getChangeButton();
//		 startSprint = ql.getStartSprintButton();
//		 createSprint = ql.getCreateNewPrintButton();
//		qdescriptionTextArea = ql.getDescriptionTextArea();
//		qeffortsTextArea = ql.getEffortsTextArea();
//		qotherInfoTextArea = ql.getOtherInfoTextArea();
//		qassignedWeightLabel = ql.getAssignedWeightLabel();
//		qlooktitle = ql.getTitle();
//
//		startSprint.setOnMouseClicked(event -> {
//			try {
//				startSPrint(event);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		});
//		createSprint.setOnMouseClicked(event -> {
//			
//			try {
//				startSPrint(event);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
//		dynamicLabel = ql.getAssignedWeightLabel();
//		change.setOnMouseClicked(event -> {
//			changeWeight();
//
//		});
//
//		//adding to the quicklook scroll
//		qlookPane.getChildren().add(root);
//	}
//
//	private void changeWeight() {
//		// TODO Auto-generated method stub
//		scrollpoker.toFront();
//		System.out.println("to the front");
//
//	}
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//
//		try {
//			populate();
//			Thread load = new Thread(() -> {
//				try {
//					effortConsole = FXMLLoader.load(getClass().getResource("/EffortConsole/Console.fxml"));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//			load.setDaemon(true);
//			load.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		indisplay = true; //for the search textfield and button(you have 2)
//	}
//	public void back(ActionEvent e)
//	{
//		display.toFront();
//		back11.setDisable(true);
//		back1.setDisable(true);
//		indisplay = true;
//	}
//	public void searchSprint(ActionEvent e) throws IOException
//	{
//		back11.setDisable(false);
//		back1.setDisable(false);
//		//deleting any node present in flow
//		for (int i = resultFlow.getChildren().size() - 1; i >= 1; i--) {
//			resultFlow.getChildren().remove(i);
//		}
//		String search;
//		if(indisplay)
//		{
//			search = searchBar11.getText();
//			searchBar1.setText(search);
//			result.toFront();
//			indisplay = false;
//		}
//		else {
//			search = searchBar1.getText();
//			searchBar11.setText(search);
//		}
//		//		int i = -1;
//		for (int i = 0; i < information.size(); i++) {
//			quicklookInfo info = information.get(i).getInfo();
//			if (info.title.toLowerCase().contains(search.toLowerCase()) ||
//					info.getDesc().toLowerCase().contains(search.toLowerCase()) ||
//					info.getOtherInfo().toLowerCase().contains(search.toLowerCase()) ||
//					info.getPresentRating().toString().toLowerCase().contains(search.toLowerCase())) {
//				createSprint(resultFlow, info, Integer.toString(i));
//			}
//		}
//
//
//
//	}
//
//	//on hover envets(dont worry about it)
//	public void w0(MouseEvent e)
//	{
//		letItGlow(w0);
//	}
//	public void w1(MouseEvent e)
//	{
//		letItGlow(w1);
//	}
//	public void w2(MouseEvent e)
//	{
//		letItGlow(w2);
//	}
//	public void w3(MouseEvent e)
//	{
//		letItGlow(w3);
//	}
//	public void letItGlow(ImageView imageView)
//	{
//
//		glowEffect.setColor(javafx.scene.paint.Color.RED); // Set the color of the glow
//		// Add event handlers for hover effect
//		imageView.setOnMouseEntered(e -> imageView.setEffect(glowEffect));
//		imageView.setOnMouseExited(e -> imageView.setEffect(null));
//	}
//
//	//changing the weight
//	public void w0Clicked(MouseEvent e)
//	{
//		quicklooksample.setPresentRating((quicklooksample.getPresentRating())/2);
//		int len = dynamicLabel.getText().length();
//		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getPresentRating());
//		qlookScroll.toFront();
//	}
//	public void w1Clicked(MouseEvent e)
//	{
//
//		quicklooksample.setPresentRating((quicklooksample.getPresentRating()+1)/2);
//		int len = dynamicLabel.getText().length();
//		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getPresentRating());
//		qlookScroll.toFront();
//
//	}
//	public void w2Clicked(MouseEvent e)
//	{
//
//		quicklooksample.setPresentRating((quicklooksample.getPresentRating()+2)/2);
//		int len = dynamicLabel.getText().length();
//		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getPresentRating());
//		qlookScroll.toFront();
//
//	}
//	public void w3Clicked(MouseEvent e)
//	{
//		System.out.println("here");
//		quicklooksample.setPresentRating((quicklooksample.getPresentRating()+3)/2);
//		int len = dynamicLabel.getText().length();
//		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getPresentRating());
//		qlookScroll.toFront();
//
//	}
//
//	void countDown(int startTime) {
//		// Using an array to make 'timeLeft' effectively final
//		int[] timeLeft = new int[1]; // Start from 60 minutes
//		timeLeft[0] = startTime;
//
//		Thread countDownThread = new Thread(() -> {
//			// Loop until timeLeft becomes 0
//			while (timeLeft[0] > 0) {
//				// Update the label on the JavaFX Application Thread
//				Platform.runLater(() -> {
//					upcommingPan.getTimeLabel().setText("Time Left: " + timeLeft[0] + " minutes");
//				});
//
//				try {
//					// Sleep for 1 minute (60000 milliseconds)
//					Thread.sleep(60000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//				// Decrement the timeLeft
//				timeLeft[0]--;
//			}
//
//			// After the countdown is complete, you can perform additional actions if needed
//			Platform.runLater(() -> {
//				upcommingPan.getTimeLabel().setText("All votes accounted start Effort");
//				// Additional actions after countdown completes
//			});
//		});
//
//		countDownThread.setDaemon(true);
//		// Start the countdown thread
//		countDownThread.start();
//
//	}
//	public void startNow(MouseEvent event)throws IOException
//	{
//		//go to the main console do something to load in in the initializer then just show it here for improved performance
//		System.out.println("HERE AM I ");
//		singletonInstance.setQuicklook(infosample);
//		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
//		Scene scene = new Scene(effortConsole);
//		stage.setScene(scene);
//		stage.show();
//	}
//	public void startSPrint(MouseEvent event)throws IOException
//	{
//
//
//		//modifying the upcomming pane
//		if(!inProgress) //remember to place it back to t!inProgress
//
//		{
//			upcommingPan.getSprintPane().toFront();
//			//changing the informaiont
//			infosample.setDesc(ql.getDescriptionLabel().getText());
//			infosample.setTitle(ql.getTitle().getText());
//			infosample.setOtherInfo(ql.getOtherInfoLabel().getText());	
//			infosample.setPresentRating(infosample.getPresentRating());
//			infosample.setUserRate(new Rate(infosample.getPresentRating(), "Achu")); //in due time replace 'achu' with singletonInstance.user
//			upcommingPan.getTitleLabel().setText(infosample.getTitle());
//			upcommingPan.getDescTextArea().setText(infosample.getDesc());
//			upcommingPan.getRatingLabel().setText("Rating: "+infosample.getPresentRating());
//			upcommingPan.getStartButton().setOnMouseClicked((MouseEvent mouseEvent) -> {
//				try {
//					startNow(mouseEvent);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//			upcommingPan.getVote().setOnMouseClicked((MouseEvent mouseEvent) -> {
//				try {
//					//bring out the poker cards
//					startNow(mouseEvent);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//
//			countDown(10);
//			inProgress = true;
////			-------------WRITING TO THE DATABASE:-STARTING THE CLOCK -------------
//			if(event.getSource() == createSprint) infosample.setId(new ObjectId());
//			List<String> startTime = new ArrayList<>(Arrays.asList(LocalTime.now().plusMinutes((long) 10.0).format(formatter)));
//			List<String> startDate = new ArrayList<>(Arrays.asList(LocalDate.now().format(dateformatter)));
//			List<String>endTime = new ArrayList<>(Arrays.asList(""));
//			List<String>lifeCycle = new ArrayList<>(Arrays.asList(""));
//			List<String>effortCat = new ArrayList<>(Arrays.asList(""));
//			List<String>rand = new ArrayList<>(Arrays.asList(""));
//			String project = "";
//			new PokerPlaningRespondsPrototype().writeEffortInfo(new Efforts("In Progress", startTime, endTime,
//					project, startDate,lifeCycle,effortCat,rand,infosample));
//		}else 
//		{
//			//alert when trying to start a clock when one is already running
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.setTitle("Error Starting new Sprint");
//			alert.setHeaderText("Sprint in progress");
//			alert.show();
//
//			return;
//		}
//
//
//
//	}
//
//}
