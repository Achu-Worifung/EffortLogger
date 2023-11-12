package PokerPlanning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class hello extends Application {

    @Override
    public void start(Stage primaryStage) {
    	AnchorPane root = new AnchorPane();
		Label titleLabel = new Label();
		AnchorPane.setLeftAnchor(titleLabel, 5.0);
		AnchorPane.setTopAnchor(titleLabel, 7.0);

		Label descriptionLabel = new Label("description:");
		AnchorPane.setLeftAnchor(descriptionLabel, 5.0);
		AnchorPane.setTopAnchor(descriptionLabel, 64.0);

		TextArea descriptionTextArea = new TextArea();
		AnchorPane.setLeftAnchor(descriptionTextArea, 5.0);
		AnchorPane.setRightAnchor(descriptionTextArea, 5.0);
		AnchorPane.setTopAnchor(descriptionTextArea, 104.0);
		descriptionTextArea.setPrefWidth(340.0);
		descriptionTextArea.setPrefHeight(113.0);

		Label assignedWeightLabel = new Label("Assigned weight: ");
		AnchorPane.setLeftAnchor(assignedWeightLabel, 15.0);
		AnchorPane.setTopAnchor(assignedWeightLabel, 262.0);

		Button changeButton = new Button("change");
		AnchorPane.setRightAnchor(changeButton, 15.0);
		AnchorPane.setTopAnchor(changeButton, 262.0);
		changeButton.setPrefSize(125, 25);

		TableView<String> tableView = new TableView<>();
		TableColumn<String, String> pastWeightColumn = new TableColumn<>("past weight");
		pastWeightColumn.setPrefWidth(75.0);
		TableColumn<String, String> defectsColumn = new TableColumn<>("defects");
		defectsColumn.setPrefWidth(254.0);
		tableView.getColumns().addAll(pastWeightColumn, defectsColumn);
		AnchorPane.setLeftAnchor(tableView, 5.0);
		AnchorPane.setRightAnchor(tableView, 5.0);
		AnchorPane.setTopAnchor(tableView, 336.0);
		tableView.setPrefWidth(344.0);
		tableView.setPrefHeight(151.0);

		Label effortsLabel = new Label("Efforts");
		AnchorPane.setLeftAnchor(effortsLabel, 12.0);
		AnchorPane.setTopAnchor(effortsLabel, 499.0);

		TextArea effortsTextArea = new TextArea();
		AnchorPane.setLeftAnchor(effortsTextArea, 1.0);
		AnchorPane.setRightAnchor(effortsTextArea, 9.0);
		AnchorPane.setTopAnchor(effortsTextArea, 531.0);
		effortsTextArea.setPrefWidth(354.0);
		effortsTextArea.setPrefHeight(113.0);

		Label otherInfoLabel = new Label("Other info");
		AnchorPane.setLeftAnchor(otherInfoLabel, 11.0);
		AnchorPane.setTopAnchor(otherInfoLabel, 654.0);

		TextArea otherInfoTextArea = new TextArea();
		AnchorPane.setLeftAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setRightAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setTopAnchor(otherInfoTextArea, 686.0);
		otherInfoTextArea.setPrefWidth(344.0);
		otherInfoTextArea.setPrefHeight(113.0);

		Button createNewPrintButton = new Button("create new print");
		AnchorPane.setLeftAnchor(createNewPrintButton, 5.0);
		AnchorPane.setBottomAnchor(createNewPrintButton, 40.0);
		createNewPrintButton.setPrefSize(125, 25);

		Button startSprintButton = new Button("start sprint");
		AnchorPane.setRightAnchor(startSprintButton, 5.0);
		AnchorPane.setBottomAnchor(startSprintButton, 40.0);
		startSprintButton.setPrefSize(125, 25);

		root.getChildren().addAll(
				titleLabel, descriptionLabel, descriptionTextArea, assignedWeightLabel, changeButton,
				tableView, effortsLabel, effortsTextArea, otherInfoLabel, otherInfoTextArea,
				createNewPrintButton, startSprintButton
				);
	

        Scene scene = new Scene(root, 354, 895);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXML to Java Code Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
