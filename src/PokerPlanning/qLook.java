package PokerPlanning;

import java.util.List;

import Backend.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class qLook {
	Label descriptionLabel,assignedWeightLabel,titleLabel,otherInfoLabel,effortsLabel;
	Button changeButton,createNewPrintButton,startSprintButton;
	TextArea descriptionTextArea,effortsTextArea, otherInfoTextArea;
	String presentRating;
	public Label getDescriptionLabel() {
		return descriptionLabel;
	}
	public void setDescriptionLabel(Label descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}
	public Label getAssignedWeightLabel() {
		return assignedWeightLabel;
	}
	public void setAssignedWeightLabel(Label assignedWeightLabel) {
		this.assignedWeightLabel = assignedWeightLabel;
	}
	public Label getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}
	public Label getOtherInfoLabel() {
		return otherInfoLabel;
	}
	public void setOtherInfoLabel(Label otherInfoLabel) {
		this.otherInfoLabel = otherInfoLabel;
	}
	public Label getEffortsLabel() {
		return effortsLabel;
	}
	public void setEffortsLabel(Label effortsLabel) {
		this.effortsLabel = effortsLabel;
	}
	public Button getChangeButton() {
		return changeButton;
	}
	public void setChangeButton(Button changeButton) {
		this.changeButton = changeButton;
	}
	public Button getCreateNewPrintButton() {
		return createNewPrintButton;
	}
	public void setCreateNewPrintButton(Button createNewPrintButton) {
		this.createNewPrintButton = createNewPrintButton;
	}
	public Button getStartSprintButton() {
		return startSprintButton;
	}
	public void setStartSprintButton(Button startSprintButton) {
		this.startSprintButton = startSprintButton;
	}
	public TextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}
	public void setDescriptionTextArea(TextArea descriptionTextArea) {
		this.descriptionTextArea = descriptionTextArea;
	}
	public TextArea getEffortsTextArea() {
		return effortsTextArea;
	}
	public void setEffortsTextArea(TextArea effortsTextArea) {
		this.effortsTextArea = effortsTextArea;
	}
	public TextArea getOtherInfoTextArea() {
		return otherInfoTextArea;
	}
	public void setOtherInfoTextArea(TextArea otherInfoTextArea) {
		this.otherInfoTextArea = otherInfoTextArea;
	}
	public AnchorPane getRoot() {
		return root;
	}
	public void setRoot(AnchorPane root) {
		this.root = root;
	}
	AnchorPane root;
	public AnchorPane getAnchor(PokerPlanning.Backend.quicklookInfo qlookinfo)
	{
		root = new AnchorPane();
		root.setPrefSize(340, 895);
		Label titleLabel = new Label(qlookinfo.getTitle());
		AnchorPane.setLeftAnchor(titleLabel, 5.0);
		AnchorPane.setTopAnchor(titleLabel, 7.0);

		descriptionLabel = new Label("description:");
		AnchorPane.setLeftAnchor(descriptionLabel, 5.0);
		AnchorPane.setTopAnchor(descriptionLabel, 64.0);
		//description text area
		descriptionTextArea = new TextArea(qlookinfo.getDesc());
		descriptionTextArea.setWrapText(true);
		AnchorPane.setLeftAnchor(descriptionTextArea, 5.0);
		AnchorPane.setRightAnchor(descriptionTextArea, 5.0);
		AnchorPane.setTopAnchor(descriptionTextArea, 104.0);
		descriptionTextArea.setPrefWidth(340.0);
		descriptionTextArea.setPrefHeight(113.0);
		//getting the current rating
		presentRating = qlookinfo.getPresentRating().toString();
		assignedWeightLabel = new Label("Assigned weight: "+presentRating);
		AnchorPane.setLeftAnchor(assignedWeightLabel, 15.0);
		AnchorPane.setTopAnchor(assignedWeightLabel, 262.0);
		//change weight button
		 changeButton = new Button("change");
		//		changeButton.setOnMouseClicked(event -> {
		//			changeWeight();
		//
		//		});
		AnchorPane.setRightAnchor(changeButton, 15.0);
		AnchorPane.setTopAnchor(changeButton, 262.0);
		changeButton.setPrefSize(125, 25);
		//weight history and defects
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
		//effort label
		effortsLabel = new Label("Efforts");
		effortsLabel.setWrapText(true);
		AnchorPane.setLeftAnchor(effortsLabel, 12.0);
		AnchorPane.setTopAnchor(effortsLabel, 499.0);
		//effort text area
		effortsTextArea = new TextArea();
		AnchorPane.setLeftAnchor(effortsTextArea, 1.0);
		AnchorPane.setRightAnchor(effortsTextArea, 9.0);
		AnchorPane.setTopAnchor(effortsTextArea, 531.0);
		effortsTextArea.setPrefWidth(354.0);
		effortsTextArea.setPrefHeight(113.0);
		//other information label
		otherInfoLabel = new Label("Other info");
		AnchorPane.setLeftAnchor(otherInfoLabel, 11.0);
		AnchorPane.setTopAnchor(otherInfoLabel, 654.0);
		//other information text area
		otherInfoTextArea = new TextArea(qlookinfo.getOtherInfo());
		otherInfoTextArea.setWrapText(true);
		AnchorPane.setLeftAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setRightAnchor(otherInfoTextArea, 5.0);
		AnchorPane.setTopAnchor(otherInfoTextArea, 686.0);
		otherInfoTextArea.setPrefWidth(344.0);
		otherInfoTextArea.setPrefHeight(113.0);
		//create new sprint button
		createNewPrintButton = new Button("create new sprint");
		AnchorPane.setLeftAnchor(createNewPrintButton, 5.0);
		AnchorPane.setBottomAnchor(createNewPrintButton, 30.0);
		createNewPrintButton.setPrefSize(125, 25);
		
		//start a new sprint button
		startSprintButton = new Button("start sprint");
		AnchorPane.setRightAnchor(startSprintButton, 5.0);
		AnchorPane.setBottomAnchor(startSprintButton, 30.0);
		startSprintButton.setPrefSize(125, 25);

		root.getChildren().addAll(
				titleLabel, descriptionLabel, descriptionTextArea, assignedWeightLabel, changeButton,
				tableView, effortsLabel, effortsTextArea, otherInfoLabel, otherInfoTextArea,
				createNewPrintButton, startSprintButton
				);
		return root;
	}
	public String getPresentRating() {
		return presentRating;
	}
	public void setPresentRating(String presentRating) {
		this.presentRating = presentRating;
	}


}
