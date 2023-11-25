package poker2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class UpcommingController {

    @FXML
    private StackPane SprintPane;

    @FXML
    private ScrollPane noSprint;

    @FXML
    private StackPane noSprintPane;

    @FXML
    private Button upcommingNewSprint;

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
    
    public void setUpcommingsprint(sample sample)
    {
    	upcommingRating.setText(Integer.toString(sample.weight));
    	upcommingTitle.setText(sample.title);
    	upcommingUserStory.setText(sample.description);
    }

}
