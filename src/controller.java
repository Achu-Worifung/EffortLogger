
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


  


public class controller implements Initializable {
    @FXML
    private FlowPane histdata;

    @FXML
    private ScrollPane historicalDate;

    @FXML
    private Button search;

    @FXML
    private TextField searchInput;

    @FXML
    private ScrollPane searchResult;

    @FXML
    private FlowPane searchdata;

    @FXML
    private Label searchlabel;

    @FXML
    private FlowPane qlookPane;
    List<sample> myList;
    public void search(ActionEvent e)
	{
		//clearing previous search
    	// Start from the end of the list
    	for (int i = searchdata.getChildren().size() - 1; i >= 1; i--) {
    	    searchdata.getChildren().remove(i);
    	    System.out.println("cleared");
    	}

//		searchdata.getChildren().clear();
		String searchItem = searchInput.getText();
		System.out.println(searchItem);
		int i= -1;
		for(sample list: myList)
		{
			i ++;
			//if word is found in title or description add it to the result
			if(list.title.contains(searchItem) || list.description.contains(searchItem))
			{
				Button qbutton = new Button("Quick Look");	
				qbutton.setPrefSize(105, 25);
				qbutton.setId(Integer.toString(i));
				
				//creating an anchor to add it to t
				AnchorPane anchorPane = new AnchorPane();
		        anchorPane.setPrefSize(415, 250);
		        
		      //setting the position of the qbutton
		        AnchorPane.setTopAnchor(qbutton, 10.0);
		        AnchorPane.setRightAnchor(qbutton, 9.0);

		        // Create a title label
		        Label titleLabel = new Label(list.getTitle());
		        titleLabel.setStyle("-fx-font-size: 24;");

		        // Create a description label
		        TextArea descriptionText = new TextArea(list.getDescription());
		        descriptionText.setWrapText(true);
		        descriptionText.setEditable(true);
//		        Label descriptionLabel = new Label(list.getDescription());

		        // Set the position of the title label
		        AnchorPane.setTopAnchor(titleLabel, 10.0);
		        AnchorPane.setLeftAnchor(titleLabel, 10.0);
		        
		        //descriptionText.setTextOverrun(OverrunStyle.ELLIPSIS);

		        // Set the position of the description label
		        AnchorPane.setTopAnchor(descriptionText, 50.0);
		        AnchorPane.setLeftAnchor(descriptionText, 10.0);
		        AnchorPane.setRightAnchor(descriptionText, 10.0);
		        
		      //actionevent for quicklook button
				qbutton.setOnMouseClicked(event -> {
					qlook(list);

		        });
		     // Add labels to the AnchorPane
		        anchorPane.getChildren().addAll(titleLabel, descriptionText, qbutton);
		        
		        //adding it to the anchor
		        searchdata.getChildren().add(anchorPane);
				}
			}
		//do something if no result match
//		if(searchdata.getChildren().size() == 0)
//		{
//			
//		}
		//pussing search scroll pan to the front thus the serach pane
		searchResult.toFront();
		
	}
	public void cancel(ActionEvent e)
	{
		//if the cancel button is pushed send the searchResut to the back
				searchResult.toBack();
	}
	public void populate(ActionEvent e)
	{
		//adding each sample to the panel
		 myList = new sample().getlist();
		 int i = -1;
		for (sample list: myList) {
			i++; //increment for the qbutton id
		//creating a quick look button
		Button qbutton = new Button("Quick Look");	
		qbutton.setPrefSize(105, 25);
		qbutton.setId(Integer.toString(i)); //giving each quick look button an id from the index in the array
		
		
		//actionevent for quicklook button
		qbutton.setOnMouseClicked(event -> {
			qlook(list);

        });
		
		
		//creating an anchor to add it to t
		AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(415, 250);

        // Create a title label
        Label titleLabel = new Label(list.getTitle());
        titleLabel.setStyle("-fx-font-size: 24;");

        // Create a description label
        TextArea descriptionText = new TextArea(list.getDescription());
        descriptionText.setWrapText(true);
        descriptionText.setEditable(true);
//        Label descriptionLabel = new Label(list.getDescription());

        // Set the position of the title label
        AnchorPane.setTopAnchor(titleLabel, 10.0);
        AnchorPane.setLeftAnchor(titleLabel, 10.0);
        
        //setting the position of the qbutton
        AnchorPane.setTopAnchor(qbutton, 10.0);
        AnchorPane.setRightAnchor(qbutton, 9.0);
        
        //descriptionText.setTextOverrun(OverrunStyle.ELLIPSIS);

        // Set the position of the description label
        AnchorPane.setTopAnchor(descriptionText, 50.0);
//        AnchorPane.setLeftAnchor(descriptionText, 10.0);
//        AnchorPane.setRightAnchor(descriptionText, 8.0);
        AnchorPane.setLeftAnchor(descriptionText, 10.0);
        AnchorPane.setRightAnchor(descriptionText, 10.0);
     // Add labels to the AnchorPane
        anchorPane.getChildren().addAll(titleLabel, descriptionText, qbutton);
//        anchorPane.setStyle("-fx-background-color: black;");//
        //adding it to the anchor
        histdata.getChildren().add(anchorPane);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populate(null);
	}
	public void qlook(sample sample)
	{
		//check if there is already something in the qlook panel
		if(qlookPane.getChildren().size() != 1) qlookPane.getChildren().remove(1); //leave the title only remove the poker
		 String title = sample.getTitle();       // Get title from the specific sample object
	        String description = sample.getDescription();
	        System.out.println(title);
	        System.out.println(description);
        //move the data into the detail sections of the split pane
		FlowPane fPane = new FlowPane();
		fPane.setMaxWidth(340);
		fPane.setPrefWidth(340);
		//setting the size of the anchor pane to the same as parent
		AnchorPane.setLeftAnchor(fPane, 0.0);
		AnchorPane.setRightAnchor(fPane, 0.0);
		AnchorPane.setTopAnchor(fPane, 0.0);
		AnchorPane.setLeftAnchor(fPane, 0.0);
		fPane.setAlignment(Pos.BASELINE_LEFT);

		// Create a title label
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 24;");
        AnchorPane.setLeftAnchor(titleLabel, 10.0);
        AnchorPane.setRightAnchor(titleLabel, 10.0);
        
        //AnchorPane.setTopAnchor(titleLabel, 10.0);
        // Create a description label
        TextArea descriptionText = new TextArea(description);
        descriptionText.setWrapText(true);
        descriptionText.setEditable(true);
        descriptionText.setMaxWidth(344);
        AnchorPane.setLeftAnchor(descriptionText, 10.0);
        AnchorPane.setRightAnchor(descriptionText, 10.0);
//        fPane.setStyle("-fx-background-color: black;");
//        qlookPane.getChildren().addAll(titleLabel,descriptionText);
        fPane.getChildren().addAll(titleLabel,descriptionText);
        qlookPane.getChildren().add(fPane);
	}

}
