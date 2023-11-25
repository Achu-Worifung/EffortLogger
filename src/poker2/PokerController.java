package poker2;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
public class PokerController implements Initializable{
//	-------------------CONSOLE COMPONENT--------------------
    @FXML
    private Button backButton;

    @FXML
    private ScrollPane choseWeightPanel;

    @FXML
    private ScrollPane display;

    @FXML
    private FlowPane displayFlow;

    @FXML
    private FlowPane qlookPane;

    @FXML
    private ScrollPane quicklookPanel;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane searchPane1;

    @FXML
    private ImageView w01;

    @FXML
    private ImageView w11;

    @FXML
    private ImageView w21;

    @FXML
    private ImageView w31;
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			populate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    private void populate() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("upcomming.fxml"));
    	AnchorPane upcommingPane = loader.load();
    	displayFlow.getChildren().add(upcommingPane);
//-----------------------THIS IS JUST SAMPLE DATA--------------------------
    	sample sample = new sample("hello", "bonjour madame", 12);
    	UpcommingController upcomming = loader.getController();
    	upcomming.setUpcommingsprint(sample);


		
	}
    @FXML
    void w0(MouseEvent event) {

    }

    @FXML
    void w0Clicked(MouseEvent event) {

    }

    @FXML
    void w1(MouseEvent event) {

    }

    @FXML
    void w1Clicked(MouseEvent event) {

    }

    @FXML
    void w2(MouseEvent event) {

    }

    @FXML
    void w2Clicked(MouseEvent event) {

    }

    @FXML
    void w3(MouseEvent event) {

    }

    @FXML
    void w3Clicked(MouseEvent event) {

    }

	

}
