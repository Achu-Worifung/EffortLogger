package poker2;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.types.ObjectId;

import PokerPlanning.Backend.PokerPlaningRespondsPrototype;
import PokerPlanning.Backend.ServerCheck;
import Universal.FxmlPreLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private StackPane SprintPane;

	@FXML
	private Button VOTE;

	@FXML
	private Button backButton;

	@FXML
	private ScrollPane choseWeightPanel;

	@FXML
	private TableColumn<String, String> defectCol;

	@FXML
	private ScrollPane display;

	@FXML
	private FlowPane displayFlow;

	//	@FXML
	//	private Button newSprint;

	@FXML
	private FlowPane qlookPane;

	@FXML
	private Label quickLookCurrentWeight;

	@FXML
	private TextArea quicklookDescription;

	@FXML
	private TextArea quicklookEfforts;

	@FXML
	private TextArea quicklookOtherInfo;

	@FXML
	private ScrollPane quicklookPanel;

	@FXML
	private TableView<String> quicklookTable;

	@FXML
	private TextField quicklookTitle;

	@FXML
	private TextField searchBar;

	@FXML
	private Button searchButton;

	@FXML
	private AnchorPane searchPane;

	@FXML
	private Button startSprint;

	@FXML
	private Label upcommingRating;

	@FXML
	private Button upcommingStartNow;

	@FXML
	private Label upcommingTime;

	@FXML
	private Label upcommingTitle;

	@FXML
	private TextArea upcommingUserStory;

	@FXML
	private ImageView w0;

	@FXML
	private ImageView w1;

	@FXML
	private ImageView w2;

	@FXML
	private ImageView w3;

	//	-----------------------RETURNING TO THE MAIN CONSOLE--------------------
	private Scene scene;
	private Stage stage;

	//    -------------------DATE VARIABLE--------------
	List<RetrieveAll> allInformation;
	HashMap<String, String>  startEnd;
	QuickLook workingOn; //the current sprint being mod
	List<Rate> userRates; //keep track of who voted and what
	//  ----------------------------FORMATTER-------------------------
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	//	----------------------QUICKLOOK: THE USER STORY CURRENTLY WORKING ON----------------------

	//	------------------PREV SPRINT CONTROLLER----------------------
	PreviouseSprintController preController;
	//	--------------------SINGLETON INSTANCE----------------------
	SingleTon singletonInstance = SingleTon.getInstance();
	FxmlPreLoader loadInstance;

	//	------------------ARE WE CREATING A NEW SPRINT OR WORKING ON A PREVIOUSE SPRINT---------
	boolean newSprint;
	//	-------------------------BACK TO CONSOLE OR HISTORICAL DATA---------------------------
	boolean toConsole;
	
//	--------------------------WILL STORE EVERY EFFORT START DATES--------------------
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			userRates = new ArrayList<>();
			populate();
			//			loadInstance = FxmlPreLoader.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		startSprint.setOnMouseClicked((event) ->{
			StartTheSprint(event);
		});
	}

	private void populate() throws IOException {
		VOTE.setDisable(true);
		//		----------------------------CLEARING THE PANEL--------------------------
		for (int i = displayFlow.getChildren().size() - 1; i >= 2; i--) {
			displayFlow.getChildren().remove(i);
		}
		upcommingStartNow.setDisable(true);
		upcommingStartNow.setOnMouseClicked((event)->
		{
			//			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			//			scene = new Scene(loadInstance.getEffortConsole());
			//			stage.setScene(scene);
			//			stage.show();

			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/EffortConsole/Console.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		VOTE.setOnMouseClicked((event)->
		{
			choseWeightPanel.toFront();
		});
		//		--------------------CHECK FOR ONGOING SPRINT--------------------
		QuickLook tempLook = new PokerPlaningRespondsPrototype().getQuick();
		String startTime = null;
		if(tempLook != null) 
		{
			startTime = tempLook.start;
			setUpcommingsprint(startTime,tempLook);
		}
		allInformation = singletonInstance.getAllInformation();
		System.out.println(allInformation.toString());
		int i = 0;
		for (RetrieveAll all:allInformation)
		{
			QuickLook qLook = all.getqLook();

			//			setUpcommingsprint(startTime,qLook);

			addingPreviousSprint(qLook, Integer.toString(i));

			i++;


		}
	}
	private void addingPreviousSprint(QuickLook qlook, String index) throws IOException
	{
		//		---------------LOADING PREV SPRINT FXML-----------------
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("preSprint.fxml"));
		AnchorPane sprint = loader.load();
		preController = loader.getController();
		preController.preSprintTitle.setText(qlook.getTitle());
		preController.PreSprintUserStory.setText(qlook.getUserStory());
		preController.previouseSprintRating.setText(Integer.toString(qlook.getRating()));
		Button qlookButton = preController.getPreSprintlookButton();
		qlookButton.setId(index);
		qlookButton.setOnMouseClicked((event)->{
			populateQuickLookPane(index);
		});
		displayFlow.getChildren().add(sprint);
	}
	private void populateQuickLookPane(String index) {
		toConsole = true;
		System.out.println("populating quicklook " +index );
		for (int i = 0;i< 3;i++)
		{
			System.out.println(allInformation.get(i).getEffort().toString());
		}
		RetrieveAll info = allInformation.get(Integer.parseInt(index));
		System.out.println(info);
		workingOn = info.getqLook();
		System.out.println(info.getEffort().getStartDate());
		List<String> startDates = info.getEffort().getStartDate();
		System.out.println(startDates.toString());
		workingOn.setDate(startDates.get(startDates.size()-1));
		quicklookTitle.setText(workingOn.getTitle());
		quicklookDescription.setText(workingOn.getUserStory());
		quicklookOtherInfo.setText(workingOn.getOtherInfo());

		Thread getTimes = new Thread(()->
		{
			startEnd = new PokerPlaningRespondsPrototype().getTimes(workingOn.getId());
			// Clear existing text in the text area
			quicklookEfforts.clear();

			// Use StringBuilder for concatenating strings
			StringBuilder resultBuilder = new StringBuilder();

			for (String startTime : startEnd.keySet()) {
				try {
					LocalTime startLocalTime = LocalTime.parse(startTime, formatter);
					LocalTime endLocalTime = LocalTime.parse(startEnd.get(startTime), formatter);
					Duration duration = Duration.between(startLocalTime, endLocalTime);

					resultBuilder.append(String.format("From: %s - To: %s : %s%n", startTime, startEnd.get(startTime), duration.toMinutes()+" Minutes"));
				} catch (DateTimeParseException e) {
					// Handle the exception (e.g., log it, show an error message)
					System.err.println("Error parsing time: " + e.getMessage());
				}
			}

			// Set the text to the text area
			quicklookEfforts.setText(resultBuilder.toString());
		});
		getTimes.setDaemon(true);
		getTimes.start();

	}

	private void StartTheSprint(MouseEvent event) {
		if(!upcommingUserStory.getText().isBlank())
		{
			//another sprint has been started
			return;
		}
		String userStory = quicklookDescription.getText();
		String otherInfo = quicklookOtherInfo.getText();
		String user = singletonInstance.getUser();
		workingOn.setDate(LocalDate.now().toString());

		if (workingOn == null)
		{
			workingOn = new QuickLook(new ObjectId(), "In Progress","",otherInfo,""
					,0, userRates);
		}
		//		workingOn.setStatus("In Progress");
		//		------------------IF TITLE IS THE SAME: WORKING ON THE SAME SPRINT--------------------
		newSprint = false;
		if( workingOn.getTitle().equals(quicklookTitle.getText()) || workingOn.getUserStory().equalsIgnoreCase(quicklookDescription.getText()))
		{
			workingOn.setOtherInfo(otherInfo);
			Rate userRate = new Rate(user, 0);
			userRates.add(userRate);
			workingOn.setUserRates(userRates);
			workingOn.setNewSprint(false);
			new PokerPlaningRespondsPrototype().writeQuickLookInfo(workingOn);
			setUpcommingsprint(workingOn.getStart(),workingOn);
			
		}

		//		--------------------TITLE IS DIFF: CREATE A NEW SPRINT-------------------------
		else 
		{
			newSprint = true;
			workingOn.setNewSprint(true);
			workingOn.setTitle(quicklookTitle.getText());
			workingOn.setUserStory(userStory);
			workingOn.setOtherInfo(otherInfo);
			Rate userRate = new Rate(user, 0);
			userRates.add(userRate);
			workingOn.setUserRates(userRates);
			workingOn.setRating(0);
			setUpcommingsprint(workingOn.start,workingOn);

		}
		if(newSprint) {
			VOTE.setDisable(false);
			upcommingStartNow.setDisable(false);
			singletonInstance.setInfo(workingOn);
			new PokerPlaningRespondsPrototype().writeQuickLookInfo(workingOn);
			setUpcommingsprint(LocalTime.now().toString(),workingOn);
		}
	}

	//	----------------------THIS IS FOR UPCOMMING ANCHORPANE------------------
	public void setUpcommingsprint(String startTime,QuickLook qLook)
	{
		SprintPane.toFront();
		upcommingTitle.setText(qLook.getTitle());
		upcommingRating.setText(Integer.toString(qLook.getRating()));
		upcommingUserStory.setText(qLook.getUserStory());
		LocalTime timeStarted = LocalTime.parse(startTime);
		timeStarted = timeStarted.plusMinutes(10);
		Duration duration= Duration.between(LocalTime.now(), timeStarted);
		System.out.println(duration.toSeconds());
		//		int timeLeft = (int)duration.getSeconds()*60;
		System.out.println((int)duration.toMinutes());
		countDown((int)duration.toMinutes(), LocalDate.parse(qLook.getDate()));

	}
	void countDown(int startTime, LocalDate startDate) {
		int[] timeLeft = new int[1]; 
		timeLeft[0] = startTime;
		VOTE.setDisable(true);
		upcommingStartNow.setDisable(true);

		Thread countDownThread = new Thread(() -> {
			while (startDate.compareTo( LocalDate.now()) ==0 &&  timeLeft[0] > 0) {
				upcommingStartNow.setDisable(false);
				VOTE.setDisable(false);

				Platform.runLater(() -> {
					upcommingTime.setText("Time Left: " + timeLeft[0] + " minutes");
				});

				try {
					// Sleep for 1 minute (60000 milliseconds)
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Decrement the timeLeft
				timeLeft[0]--;
			}

			// After the countdown is complete, you can perform additional actions if needed
			Platform.runLater(() -> {
				upcommingTime.setText("All votes accounted start Effort");
			});
		});

		countDownThread.setDaemon(true);
		// Start the countdown thread
		countDownThread.start();

	}
	//	-----------------------------------SEARCH ANCHORPANE--------------------------
	public void search(ActionEvent event) throws IOException
	{
		toConsole = false;
		//clearing display panel
		for (int i = displayFlow.getChildren().size()-1; i >=2;i--)
		{
			displayFlow.getChildren().remove(i);
		}
		String searchText = searchBar.getText();
		for (int i = 0; i < allInformation.size(); i++) {
			QuickLook info = allInformation.get(i).getqLook();
			if (info.title.toLowerCase().contains(searchText.toLowerCase()) ||
					info.getUserStory().toLowerCase().contains(searchText.toLowerCase()) ||
					info.getOtherInfo().toLowerCase().contains(searchText.toLowerCase()) ||
					Integer.toString(info.getRating()).toLowerCase().contains(searchText.toLowerCase())) {
				addingPreviousSprint( info, Integer.toString(i));
			}
		}

	}
	public void back(ActionEvent event) throws IOException
	{
		if(toConsole)
		{
			try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("/EffortConsole/Console.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else 
		{
			populate();
		}
	}




	public void weightClicked(MouseEvent event)
	{
		//		-----------------------DONT FORGET TO KILL THE THREAT WHEN YOU EXIT POKER--------------
		Thread serverCheck = null;
		//		--------------------------CHECKING IF USER HAS ALREAD VOTED-------------------
		boolean notvoted = true;
		int i,  choice;
		List<Rate> userRates = new PokerPlaningRespondsPrototype().getAllUserRates();
		int allVotes = userRates.size();

		if(event.getSource() == w0)
		{
			choice = 0;
		}else if(event.getSource() == w1)
		{
			choice = 1;

		}else if(event.getSource() == w2)
		{
			choice = 2;

		}else 
		{
			choice = 3;
		}
		//		--------------------WILL KEEP CHECKING SERVER FOR OTHER'S VOTE---------------
		if(serverCheck== null || !serverCheck.isAlive()) {
			serverCheck=new Thread(()->{
				while (true)
				{

					List<Rate> newUserRates = new ServerCheck().getAllUserRates();
					System.out.println("Checking...");
					int sum = 0;
					if(newUserRates.size() != allVotes)
					{

						for(Rate rate:newUserRates)
						{
							sum +=rate.getRate();
						}
					}
					final int finalSum = sum;
					Platform.runLater(() -> {

						upcommingRating.setText(Integer.toString((finalSum/newUserRates.size())));
						System.out.println("updated...");

					});
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			});
		}
		int sum = 0;
		for(i = 0; i < userRates.size(); i++)
		{
			System.out.println("here");
			if(singletonInstance.getUser().equalsIgnoreCase(userRates.get(i).getUser()))
			{
				notvoted = false;
				new PokerPlaningRespondsPrototype().updateRate(new Rate(singletonInstance.getUser(), choice));
				continue;
			}
			sum+=userRates.get(i).getRate();
		}
		if(notvoted) {
			System.out.println("note voted");
			boolean done = new PokerPlaningRespondsPrototype().toUserRate(new Rate(singletonInstance.getUser(), choice));
			System.out.println(done);
			notvoted= false;
		}
		choseWeightPanel.toBack();
		//		--------------MIGHT NEED TO CHECK THIS AVERAGE----------------
		int average = (sum + choice) / (userRates.size() + 1);
		upcommingRating.setText(Integer.toString(average));

		//		----------------------STARTING THE THREAD---------------------------
		serverCheck.setDaemon(true);
		serverCheck.start();
	}

}
