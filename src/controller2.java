import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class controller2 implements Initializable{

	@FXML
    private Button back1;

    @FXML
    private Button back11;

    @FXML
    private ScrollPane display;

    @FXML
    private AnchorPane pokercards;

    @FXML
    private AnchorPane qlookPane;

    @FXML
    private ScrollPane result;

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
    
    //this is for the preSprint fxml
    @FXML
    private TextArea description;

    @FXML
    private Button qlookButton;

    @FXML
    private Label rating;

    @FXML
    private Label title;


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
    		//adding sample data
    		AnchorPane pastSprint = new preSprint().pane(sam.title,sam.description, Integer.toString(i));
//    		title.setText(sam.title);
    		displayFlow.getChildren().add(pastSprint);
    		
    	}
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
	}

}
