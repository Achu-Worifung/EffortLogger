package PokerPlanning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class upcommingPane extends Application {
	StackPane sprintPane, noSprintPane;
	Label titleLabel,ratingLabel,timeLabel,noSprintLabel;
	Button newSprintButton,startButton, vote;
	TextArea descTextArea;
	AnchorPane anchorPane;

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = createUI();
        Scene scene = new Scene(root, 600, 200);
        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public AnchorPane createUI() {
         anchorPane = new AnchorPane();

        // Pane with upcoming sprint details
        vote = new Button("Vote");
         sprintPane = new StackPane();
        ScrollPane sprintScrollPane = new ScrollPane();
         titleLabel = new Label("upcomingTitle");
        titleLabel.setFont(new Font("System Bold", 16.0));
         startButton = new Button("Start Now");
         descTextArea = new TextArea();
         descTextArea.setMaxSize(546, 86);
         descTextArea.setWrapText(true);
         ratingLabel = new Label("Rating");
         timeLabel = new Label("Starts in:");
      //settng contrainst for pain with  upcomming sprint
        AnchorPane.setLeftAnchor(vote, 169.0);
        AnchorPane.setTopAnchor(vote, 162.0);

        AnchorPane.setLeftAnchor(titleLabel, 287.0);
        AnchorPane.setTopAnchor(titleLabel, 13.0);
        
        AnchorPane.setLeftAnchor(descTextArea, 31.0);
        AnchorPane.setTopAnchor(descTextArea, 46.0);
        AnchorPane.setRightAnchor(descTextArea, 22.0);
        AnchorPane.setBottomAnchor(descTextArea, 66.0);
        
        AnchorPane.setLeftAnchor(ratingLabel, 31.0);
        AnchorPane.setTopAnchor(ratingLabel, 166.0);
        
        AnchorPane.setLeftAnchor(startButton, 443.0);
        AnchorPane.setTopAnchor(startButton, 162.0);
        
        AnchorPane.setLeftAnchor(timeLabel, 255.0);
        AnchorPane.setTopAnchor(timeLabel, 166.0);
        
        sprintScrollPane.setContent(new AnchorPane(titleLabel, vote, startButton, descTextArea, ratingLabel, timeLabel));
        sprintPane.getChildren().add(sprintScrollPane);

        // Pane with no upcoming sprint
         noSprintPane = new StackPane();
        ScrollPane noSprintScrollPane = new ScrollPane();
         noSprintLabel = new Label("No Upcoming Sprint");
        noSprintLabel.setFont(new Font("System Bold", 24.0));
        newSprintButton = new Button("Create New Sprint");
        
        //settng contrainst for pain with no upcomming sprint
        AnchorPane.setLeftAnchor(noSprintLabel, 173.0);
        AnchorPane.setTopAnchor(noSprintLabel, 32.0);
        AnchorPane.setLeftAnchor(newSprintButton, 217.0);
        AnchorPane.setTopAnchor(newSprintButton, 100.0);
        

        noSprintScrollPane.setContent(new AnchorPane(noSprintLabel, newSprintButton));
        noSprintPane.getChildren().add(noSprintScrollPane);

        // Set up layout constraints
        AnchorPane.setTopAnchor(sprintPane, 0.0);
        AnchorPane.setBottomAnchor(sprintPane, 0.0);
        AnchorPane.setLeftAnchor(sprintPane, 0.0);
        AnchorPane.setRightAnchor(sprintPane, 0.0);

        AnchorPane.setTopAnchor(noSprintPane, 0.0);
        AnchorPane.setBottomAnchor(noSprintPane, 0.0);
        AnchorPane.setLeftAnchor(noSprintPane, 0.0);
        AnchorPane.setRightAnchor(noSprintPane, 0.0);
        //setting the size of the anchor pan
        anchorPane.setPrefSize(620, 200);
        anchorPane.setMaxSize(610, 200);

        anchorPane.getChildren().addAll(sprintPane, noSprintPane);

        return anchorPane;
    }

	public StackPane getSprintPane() {
		return sprintPane;
	}

	public void setSprintPane(StackPane sprintPane) {
		this.sprintPane = sprintPane;
	}

	public StackPane getNoSprintPane() {
		return noSprintPane;
	}

	public void setNoSprintPane(StackPane noSprintPane) {
		this.noSprintPane = noSprintPane;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	public Label getRatingLabel() {
		return ratingLabel;
	}

	public void setRatingLabel(Label ratingLabel) {
		this.ratingLabel = ratingLabel;
	}

	public Label getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(Label timeLabel) {
		this.timeLabel = timeLabel;
	}

	public Label getNoSprintLabel() {
		return noSprintLabel;
	}

	public void setNoSprintLabel(Label noSprintLabel) {
		this.noSprintLabel = noSprintLabel;
	}

	public Button getNewSprintButton() {
		return newSprintButton;
	}

	public void setNewSprintButton(Button newSprintButton) {
		this.newSprintButton = newSprintButton;
	}

	public Button getStartButton() {
		return startButton;
	}

	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}

	public Button getVote() {
		return vote;
	}

	public void setVote(Button vote) {
		this.vote = vote;
	}

	public TextArea getDescTextArea() {
		return descTextArea;
	}

	public void setDescTextArea(TextArea descTextArea) {
		this.descTextArea = descTextArea;
	}
}
