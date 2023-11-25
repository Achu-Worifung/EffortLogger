package poker2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PreviouseSprintController {

    public TextArea getPreSprintUserStory() {
		return PreSprintUserStory;
	}

	public void setPreSprintUserStory(TextArea preSprintUserStory) {
		PreSprintUserStory = preSprintUserStory;
	}

	public Label getPreSprintTitle() {
		return preSprintTitle;
	}

	public void setPreSprintTitle(Label preSprintTitle) {
		this.preSprintTitle = preSprintTitle;
	}

	public Button getPreSprintlookButton() {
		return preSprintlookButton;
	}

	public void setPreSprintlookButton(Button preSprintlookButton) {
		this.preSprintlookButton = preSprintlookButton;
	}

	public Label getPreviouseSprintRating() {
		return previouseSprintRating;
	}

	public void setPreviouseSprintRating(Label previouseSprintRating) {
		this.previouseSprintRating = previouseSprintRating;
	}

	@FXML
     TextArea PreSprintUserStory;

    @FXML Label preSprintTitle;

    @FXML
     Button preSprintlookButton;

    @FXML
     Label previouseSprintRating;

}
