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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable{

	@FXML
	private Button VOTE;

	@FXML
	private Button backButton;

	@FXML
	private ScrollPane choseWeightPanel;

	@FXML
	private TableColumn<?, ?> defectCol;

	@FXML
	private ScrollPane display;

	@FXML
	private FlowPane displayFlow;
	@FXML
	private AnchorPane upcommingPane;

	@FXML
	private VBox qlookPane;

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
	private TableView<?> quicklookTable;

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
	PokerPlaningRespondsPrototype pokerInstance = PokerPlaningRespondsPrototype.getInstance();


	//	------------------ARE WE CREATING A NEW SPRINT OR WORKING ON A PREVIOUSE SPRINT---------
	boolean newSprint;
	//	-------------------------BACK TO CONSOLE OR HISTORICAL DATA---------------------------
	boolean toConsole;

	//	--------------------------CHECK DB FOR ALL VOTES--------------------
	Thread serverCheck = null;
	boolean keepchecking =true;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			userRates = new ArrayList<>();
			populate();
			toConsole = true;
			ServerCheck checkServer = new ServerCheck();
			serverCheck=new Thread(()->{
				while (keepchecking)
				{

					userRates = pokerInstance.getAllUserRates();
					System.out.println("Checking...");
					double sum = 0.0;

					// Assuming userRates is a List of numeric values
					for (Rate rate : userRates) {
						sum += rate.getRate();
					}

					final int average = userRates.isEmpty() ? 0 :(int) sum / userRates.size();
					Platform.runLater(() -> {

						upcommingRating.setText(Integer.toString(average));
						System.out.println("updated...");

					});

					//					--------------------CHECK IF SOMEONE ELSE HAS MADE A SPRINT---------------
					QuickLook ql = checkServer.getQuick();
					if(ql != null && upcommingUserStory.getText().isBlank())
					{
						Platform.runLater(() -> {
							setUpcommingsprint(ql.getStart(), ql);
						});

					}
					else if(!upcommingUserStory.getText().isBlank() && ql == null)
					{
						Platform.runLater(() -> {
							upcommingTitle.setText("UPCOMMING SPRINT TITLE");
							upcommingStartNow.setDisable(true);
							VOTE.setDisable(true);
							upcommingUserStory.setText("");
							upcommingTime.setText("Starts in:");
							upcommingRating.setText("");
						});
						
					}
					try {
						Thread.sleep(15000); //sleeps for 15 seconds
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			});
			serverCheck.setDaemon(true);
			serverCheck.start();
			loadInstance = FxmlPreLoader.getInstance();
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
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			try {
				scene = new Scene(loadInstance.getEffortConsole());
				keepchecking = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage.setScene(scene);
			stage.show();


		});
		VOTE.setOnMouseClicked((event)->
		{
			choseWeightPanel.toFront();
		});
		//		--------------------CHECK FOR ONGOING SPRINT--------------------
		QuickLook tempLook = singletonInstance.getInfo();
//		String startTime = null;
		if(tempLook != null) 
		{
			upcommingStartNow.setDisable(false);
//			startTime = tempLook.start;
//			System.out.println("start time is :" + startTime);
			System.out.println("I am in populate come and get me");
			setUpcommingsprint(tempLook.getStart(),tempLook);
		}
		allInformation = singletonInstance.getAllInformation();
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
		VBox sprint = loader.load();
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
		System.out.println("populating quicklook " +index );
		RetrieveAll info = allInformation.get(Integer.parseInt(index));
		workingOn = info.getqLook();
		//		System.out.println(info.getEffort().getStartDate());
		List<String> startDates = info.getEffort().getStartDate();
		List<String> startTimes = info.getEffort().getStartTime();
		List<String> endTimes = info.getEffort().getEndTime();
		System.out.println("here is the start date "+startDates.get(startDates.size()-1));
		workingOn.setDate(startDates.get(startDates.size()-1));
		workingOn.setStart(startTimes.get(startTimes.size()-1));
		quicklookTitle.setText(workingOn.getTitle());
		quickLookCurrentWeight.setText(Integer.toString(workingOn.rating));
		quicklookDescription.setText(workingOn.getUserStory());
		quicklookOtherInfo.setText(workingOn.getOtherInfo());

		//		Thread getTimes = new Thread(()->
		//		{
		//			startEnd = new PokerPlaningRespondsPrototype().getTimes(workingOn.getId());
		// Clear existing text in the text area
		quicklookEfforts.clear();

		// Use StringBuilder for concatenating strings
		StringBuilder resultBuilder = new StringBuilder();

		for (int i =0;i < startTimes.size();i++) {
			try {
				LocalTime startLocalTime = LocalTime.parse(startTimes.get(i), formatter);
				LocalTime endLocalTime = LocalTime.parse(endTimes.get(i), formatter);
				Duration duration = Duration.between(startLocalTime, endLocalTime);

				resultBuilder.append(String.format("From: %s - To: %s : %s%n", startTimes.get(i), endTimes.get(i), duration.toMinutes()+" Minutes"));
			} catch (DateTimeParseException e) {
				// Handle the exception (e.g., log it, show an error message)
				System.err.println("Error parsing time: " + e.getMessage());
			}
		}

		// Set the text to the text area
		quicklookEfforts.setText(resultBuilder.toString());
		//		});
		//		getTimes.setDaemon(true);
		//		getTimes.start();

	}

	private void StartTheSprint(MouseEvent event) {
		if(!upcommingUserStory.getText().isBlank())
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Start A new sprint");
			alert.setContentText("A sprint has already been planned");
			alert.show();
			return;
		}
		String userStory = quicklookDescription.getText();
		String otherInfo = quicklookOtherInfo.getText();
		String user = singletonInstance.getUser();
		//		workingOn.setDate(LocalDate.now().toString());

		if (workingOn == null)
		{
			workingOn = new QuickLook(new ObjectId(), "In Progress","",otherInfo,""
					,0, userRates);
		}
		//		workingOn.setStatus("In Progress");
		//		------------------IF TITLE IS THE SAME: WORKING ON AN OLD SPRINT--------------------
		newSprint = false;
		if( workingOn.getTitle().equals(quicklookTitle.getText()) || workingOn.getUserStory().equalsIgnoreCase(quicklookDescription.getText()))
		{
			workingOn.setOtherInfo(otherInfo);
			Rate userRate = new Rate(user, 0);
			userRates.add(userRate);
			workingOn.setUserRates(userRates);
			workingOn.setNewSprint(false);
			setUpcommingsprint(workingOn.getStart(),workingOn);
			//			workingOn.setDate(workingOn.getStart());
			pokerInstance.writeQuickLookInfo(workingOn);

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
			singletonInstance.setInfo(workingOn);
			workingOn.setDate(LocalDate.now().toString());
			workingOn.setStart(LocalTime.now().toString());
			setUpcommingsprint(workingOn.start,workingOn);
			pokerInstance.writeQuickLookInfo(workingOn);

		}
		//		if(newSprint) {
		//			VOTE.setDisable(false);
		//			upcommingStartNow.setDisable(false);
		//			setUpcommingsprint(LocalTime.now().toString(),workingOn)/;
		//		}
	}

	//	----------------------THIS IS FOR UPCOMMING ANCHORPANE------------------
	public void setUpcommingsprint(String startTime,QuickLook qLook)
	{
		upcommingTitle.setText(qLook.getTitle());
		upcommingRating.setText(Integer.toString(qLook.getRating()));
		upcommingUserStory.setText(qLook.getUserStory());
		LocalTime timeStarted = LocalTime.parse(startTime);
//		timeStarted = timeStarted.plusMinutes(10);
		Duration duration= Duration.between(LocalTime.now().minusMinutes(10), timeStarted);
		System.out.println(duration.toSeconds());
		//		int timeLeft = (int)duration.getSeconds()*60;
		countDown((int)duration.toMinutes(), LocalDate.parse(qLook.getDate()));

	}
	void countDown(int startTime, LocalDate startDate) {
		int[] timeLeft = new int[1]; 
		timeLeft[0] = startTime;
		VOTE.setDisable(true);

		upcommingStartNow.setDisable(false);
		Thread countDownThread = new Thread(() -> {
			while (startDate.compareTo( LocalDate.now()) == 0 &&  timeLeft[0] > 0) {
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
		System.out.println("in back");
		System.out.println(toConsole);
		if(toConsole)
		{
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(loadInstance.getEffortConsole());
			stage.setScene(scene);
			stage.show();
			keepchecking = false;
		}else 
		{
			populate();
			toConsole = true;
		}
	}




	public void weightClicked(MouseEvent event)
	{
		//		-----------------------DONT FORGET TO KILL THE THREAT WHEN YOU EXIT POKER--------------
		Thread serverCheck = null;
		//		--------------------------CHECKING IF USER HAS ALREAD VOTED-------------------
		boolean notvoted = true;
		int i,  choice;
		List<Rate> userRates = pokerInstance.getAllUserRates();
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
		//		if(serverCheck== null || !serverCheck.isAlive()) {
		//			
		//		}
		for(i = 0; i < userRates.size(); i++)
		{
			int rate = userRates.get(i).getRate();
			System.out.println("here");
			if(singletonInstance.getUser().equalsIgnoreCase(userRates.get(i).getUser()))
			{
				notvoted = false;
				pokerInstance.updateRate(new Rate(singletonInstance.getUser(), choice));
				break;
			}
		}
		if(notvoted) {
			System.out.println("note voted");
			boolean done = pokerInstance.toUserRate(new Rate(singletonInstance.getUser(), choice));
			System.out.println(done);
			notvoted= false;
		}
		choseWeightPanel.toBack();
		//		--------------MIGHT NEED TO CHECK THIS AVERAGE----------------
		userRates = pokerInstance.getAllUserRates();
		double sum = 0.0;

		// Assuming userRates is a List of numeric values
		for (Rate rate : userRates) {
			sum += rate.getRate();
		}

		double average = userRates.isEmpty() ? 0.0 : sum / userRates.size();
		System.out.println("average is "+average);
		upcommingRating.setText(Integer.toString((int)average));

		//		----------------------STARTING THE THREAD---------------------------
		//		serverCheck.setDaemon(true);
		//		serverCheck.start();
	}

}
