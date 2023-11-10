import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


import Backend.pokerPlanningRequestPrototype;
import Backend.quicklookInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class controller2 implements Initializable{

	// Create a drop shadow effect for the glowing effect
	DropShadow glowEffect = new DropShadow();
	Singleton singleton = Singleton.getInstance(); //getting singleton instance
	Label dynamicLabel;

	@FXML
	private Button back1;

	@FXML
	private Button back11;

	@FXML
	private ScrollPane display;

	@FXML
	private AnchorPane pokercards;

	@FXML
	protected FlowPane qlookPane;

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


	//	private List<sample> myList;

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
	//	private Stage stage;
	//	private Scene scene;
	//	private Parent root;
	//	@FXML
	//	private AnchorPane noSprint;

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
	public quicklookInfo quicklooksample;
	public quicklookInfo infosample;
	AnchorPane root; //for the quick loop 
	List<quicklookInfo> information;//

	@FXML
	void search(ActionEvent event) {

	}
	public void populate() throws IOException
	{
		//addint if there is a sprint of not
		AnchorPane isSprint = FXMLLoader.load(getClass().getResource("upcomming.fxml"));
		displayFlow.getChildren().add(isSprint);
		information = new pokerPlanningRequestPrototype().getQuicklook();
		//		myList = new sample().getlist();
		int i = -1;
		for (quicklookInfo qlook : information)
		{
			i++;
			//			//adding sample data
			//			AnchorPane pastSprint = new preSprint().pane(sam, Integer.toString(i));
			//			//    		title.setText(sam.title);
			//			displayFlow.getChildren().add(pastSprint);

			//creating prev sprint anchor
			createSprint(displayFlow, qlook, Integer.toString(i));


		}
	}
	//creating the sprint 
	public void createSprint(FlowPane pane, quicklookInfo qlook, String i)
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
			infosample = qlook;
			quicklook(qlook);

		});

		//creating rating label
		Label rating = new Label("Rating: "+qlook.getPastRating().get(0));
		rating.setStyle("-fx-font-size: 12;");

		//creating the title label
		Label titleLabel = new Label(qlook.getTitle());
		titleLabel.setStyle("-fx-font-size: 14;");
		//creating description label
		Label des = new Label("Description:");
		des.setStyle("-fx-font-size: 12;");
		//creating decription area
		TextArea desText = new TextArea(qlook.getDesc());
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
	public void quicklook(quicklookInfo qlook)
	{
		//check if there is already something in the qlook panel
		qlookPane.getChildren().clear();

		qLook ql = new qLook();
		root = ql.getAnchor(qlook);
		quicklooksample = qlook;

		//adding onclick action to the change button
		Button change = ql.getChangeButton();
		dynamicLabel = ql.getAssignedWeightLabel();
		change.setOnMouseClicked(event -> {
			changeWeight();

		});

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
		back11.setDisable(false);
		back1.setDisable(false);
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
		//		int i = -1;
		for(int i = 0; i < information.size(); i++)
		{
			quicklookInfo info = information.get(i);
			if(info.title.contains(search) || info.getDesc().contains(search)||info.getOtherInfo().contains(search))
			{
				createSprint(resultFlow, info, Integer.toString(i));
			}
		}
		//		for(quicklookInfo info: information)
		//		{
		//			i++;
		//			
		//		}


	}

	//on hover envets(dont worry about it)
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

	//changing the weight
	public void w0Clicked(MouseEvent e)
	{
		quicklooksample.setRating((quicklooksample.getRating())/2);
		int len = dynamicLabel.getText().length();
		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getRating());
		qlookScroll.toFront();
	}
	public void w1Clicked(MouseEvent e)
	{

		quicklooksample.setRating((quicklooksample.getRating()+1)/2);
		int len = dynamicLabel.getText().length();
		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getRating());
		qlookScroll.toFront();

	}
	public void w2Clicked(MouseEvent e)
	{

		quicklooksample.setRating((quicklooksample.getRating()+2)/2);
		int len = dynamicLabel.getText().length();
		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getRating());
		qlookScroll.toFront();

	}
	public void w3Clicked(MouseEvent e)
	{
		System.out.println("here");
		quicklooksample.setRating((quicklooksample.getRating()+3)/2);
		int len = dynamicLabel.getText().length();
		dynamicLabel.setText( dynamicLabel.getText().substring(0, len-2)+ " "+quicklooksample.getRating());
		qlookScroll.toFront();

	}
	//	public void makeNewSprint(ActionEvent e)
	//	{
	//		//check if there is already something in the qlook panel
	//		qlookPane.getChildren().clear();
	//
	//		qLook ql = new qLook();
	//		AnchorPane root = ql.getAnchor(new sample("","",0));
	//		//adding onclick action to the change button
	//		Button change = ql.getChangeButton();
	//		change.setOnMouseClicked(event -> {
	//			changeWeight();
	//
	//		});
	//
	//		//adding to the quicklook scroll
	//		qlookPane.getChildren().add(root);
	//	}
	//	@FXML
	public void startSPrint(ActionEvent event)throws IOException
	{

		AnchorPane root = FXMLLoader.load(getClass().getResource("/Defects/Defects.fxml")); //connects to the effort console
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		singleton.setQuicklook(infosample); //passing qlook object to singlenton class

	}

}
