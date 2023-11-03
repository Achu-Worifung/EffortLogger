package definitions;

import java.net.URL;
import java.util.ResourceBundle;

import ToDB.Query;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class DefController implements Initializable{
	//Configure the table
	@FXML
    private TableColumn<Break, String> breakColumn;
    @FXML
    private TableColumn<Category, String> categoryColumn;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<User, Integer> column11;
    @FXML
    private TableColumn<User, Integer> column12;
    @FXML
    private TableColumn<User, Integer> column13;
    @FXML
    private TableColumn<User, Integer> column14;
    @FXML
    private TableColumn<User, Integer> column15;
    @FXML
    private TableColumn<User, Integer> column16;
    @FXML
    private TableColumn<User, Integer> column17;
    @FXML
    private TableColumn<User, Integer> column18;
    @FXML
    private TableColumn<User, Integer> column19;
    @FXML
    private TableColumn<User, Integer> column20;
    @FXML
    private TableColumn<User, Integer> column21;
    @FXML
    private TableColumn<User, Integer> column22;
    @FXML
    private TableColumn<User, Integer> column23;
    @FXML
    private TableColumn<User, Integer> column24;
    @FXML
    private TableColumn<User, Integer> column25;
    @FXML
    private TableColumn<User, Integer> columnEight;
    @FXML
    private TableColumn<User, Integer> columnFive;
    @FXML
    private TableColumn<User, Integer> columnFour;
    @FXML
    private TableColumn<User, Integer> columnNine;
    @FXML
    private TableColumn<User, Integer> columnOne;
    @FXML
    private TableColumn<User, Integer> columnSeven;
    @FXML
    private TableColumn<User, Integer> columnSix;
    @FXML
    private TableColumn<User, Integer> columnTen;
    @FXML
    private TableColumn<User, Integer> columnThree;
    @FXML
    private TableColumn<User, Integer> columnTwo;
    @FXML
    private TableColumn<Life, Integer> defaultDColumn;
    @FXML
    private TableColumn<Defect, String> defectColumn;
    @FXML
    private TableView<Defect> defectTableView;
    @FXML
    private TableColumn<Deliverable, String> deliverableColumn;
    @FXML
    private TableView<Deliverable> deliverableTableView;
    @FXML
    private TableColumn<Life, Integer> effortCategoryColumn;
    @FXML
    private TableView<Break> interruptionTableView;
    @FXML
    private TableColumn<Life, String> lifeCycleColumn;
    @FXML
    private TableView<Life> lifeTableView;
    @FXML
    private TableColumn<Plans, String> plansColumn;
    @FXML
    private TableView<Plans> plansTableView;
    @FXML
    private TableColumn<User, String> projectNameColumn;
    @FXML
    private TableView<User> tableView;
    //id column
    @FXML
    private TableColumn<Plans, Integer> plansId;
    @FXML
    private TableColumn<User, Integer> projectId;
    @FXML
    private TableColumn<Deliverable, Integer> deliverableId;
    @FXML
    private TableColumn<Defect, Integer> defectId;
    @FXML
    private TableColumn<Break, Integer> breakId;
    @FXML
    private TableColumn<Category, Integer> categoryId;
    @FXML
    private TableColumn<Life, Integer> lifeCycleId;
    
    
    
    
    
    //This method will allow the user to double click on a cell and update them
     public void changeProjectNameCellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setProjectName(edittedCell.getNewValue().toString());
    }
    public void changeColumnThreeCellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setColThree((Integer) edittedCell.getNewValue());
    }
    public void changeColumn1CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol1((Integer) edittedCell.getNewValue());
    }
    public void changeColumn2CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol2((Integer) edittedCell.getNewValue());
    }
    public void changeColumn4CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol4((Integer) edittedCell.getNewValue());
    }
    public void changeColumn5CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol5((Integer) edittedCell.getNewValue());
    }
    public void changeColumn6CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol6((Integer) edittedCell.getNewValue());
    }public void changeColumn7CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol7((Integer) edittedCell.getNewValue());
    }
    public void changeColumn8CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol8((Integer) edittedCell.getNewValue());
    }
    public void changeColumn9CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol9((Integer) edittedCell.getNewValue());
    }
    public void changeColumn10CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol10((Integer) edittedCell.getNewValue());
    }
    public void changeColumn11CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol11((Integer) edittedCell.getNewValue());
    }
    public void changeColumn12CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol12((Integer) edittedCell.getNewValue());
    }
    public void changeColumn13CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol13((Integer) edittedCell.getNewValue());
    }
    public void changeColumn14CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol14((Integer) edittedCell.getNewValue());
    }
    public void changeColumn15CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol15((Integer) edittedCell.getNewValue());
    }
    public void changeColumn16CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol16((Integer) edittedCell.getNewValue());
    }
    public void changeColumn17CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol17((Integer) edittedCell.getNewValue());
    }
    public void changeColumn18CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol18((Integer) edittedCell.getNewValue());
    }
    public void changeColumn19CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol19((Integer) edittedCell.getNewValue());
    }
    public void changeColumn20CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol20((Integer) edittedCell.getNewValue());
    }
    public void changeColumn21CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol21((Integer) edittedCell.getNewValue());
    }
    public void changeColumn22CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol22((Integer) edittedCell.getNewValue());
    }
    public void changeColumn23CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol23((Integer) edittedCell.getNewValue());
    }
    public void changeColumn24CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol24((Integer) edittedCell.getNewValue());
    }
    public void changeColumn25CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol25((Integer) edittedCell.getNewValue());
    }
    public void changeCategoryNameCellEvent(CellEditEvent edittedCell) {
    	Category categorySelected = categoryTableView.getSelectionModel().getSelectedItem();
    	categorySelected.setCategoryName(edittedCell.getNewValue().toString());
    }
    public void changeBreakNameCellEvent(CellEditEvent edittedCell) {
    	Break breakSelected = interruptionTableView.getSelectionModel().getSelectedItem();
    	breakSelected.setBreakName(edittedCell.getNewValue().toString());
    }
    public void changePlansNameCellEvent(CellEditEvent edittedCell) {
    	Plans plansSelected = plansTableView.getSelectionModel().getSelectedItem();
    	plansSelected.setPlansName(edittedCell.getNewValue().toString());
    }
    public void changeLifeNameCellEvent(CellEditEvent edittedCell) {
    	Life lifeSelected = lifeTableView.getSelectionModel().getSelectedItem();
    	lifeSelected.setLifeName(edittedCell.getNewValue().toString());
    }
    public void changeECNameCellEvent(CellEditEvent edittedCell) {
    	Life lifeSelected = lifeTableView.getSelectionModel().getSelectedItem();
    	lifeSelected.setDefaultEC((Integer) edittedCell.getNewValue());
    }
    public void changeDefaultDNameCellEvent(CellEditEvent edittedCell) {
    	Life lifeSelected = lifeTableView.getSelectionModel().getSelectedItem();
    	lifeSelected.setDefaultDefect((Integer) edittedCell.getNewValue());
    }
    public void changeDefectNameCellEvent(CellEditEvent edittedCell) {
    	Defect defectSelected = defectTableView.getSelectionModel().getSelectedItem();
    	defectSelected.setDefectName(edittedCell.getNewValue().toString());
    }
    public void changeDeliverableNameCellEvent(CellEditEvent edittedCell) {
    	Deliverable deliverableSelected = deliverableTableView.getSelectionModel().getSelectedItem();
    	deliverableSelected.setDeliverableName(edittedCell.getNewValue().toString());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    projectNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("projectName"));
    breakColumn.setCellValueFactory(new PropertyValueFactory<Break, String>("breakName"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryName"));
    lifeCycleColumn.setCellValueFactory(new PropertyValueFactory<Life, String>("lifeName"));
    deliverableColumn.setCellValueFactory(new PropertyValueFactory<Deliverable, String>("deliverableName"));
    defectColumn.setCellValueFactory(new PropertyValueFactory<Defect, String>("defectName"));
    plansColumn.setCellValueFactory(new PropertyValueFactory<Plans, String>("plansName"));
    //setting up id cells
//    
//    private TableColumn<Plans, Integer> plansId;
//    @FXML
//    private TableColumn<Deliverable, Integer> deliverableId;
//    @FXML
//    private TableColumn<Defect, Integer> defectId;
//    @FXML
//    private TableColumn<Break, Integer> breakId;
//    @FXML
//    private TableColumn<Category, Integer> categoryId;
//    @FXML
//    private TableColumn<Life, Integer> lifeCycleId;
    plansId.setCellValueFactory(new PropertyValueFactory<Plans, Integer>("id"));
    deliverableId.setCellValueFactory(new PropertyValueFactory<Deliverable, Integer>("id"));
    defectId.setCellValueFactory(new PropertyValueFactory<Defect, Integer>("id"));
    breakId.setCellValueFactory(new PropertyValueFactory<Break, Integer>("id"));
    categoryId.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
    lifeCycleId.setCellValueFactory(new PropertyValueFactory<Life, Integer>("id"));
    projectId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
    
    //setting up effort cat and d column
    effortCategoryColumn.setCellValueFactory(new PropertyValueFactory<Life, Integer>("defaultEC"));
    defaultDColumn.setCellValueFactory(new PropertyValueFactory<Life, Integer>("defaultDefect"));
    
    //You can delete everything after this line
    columnOne.setCellValueFactory(new PropertyValueFactory<User, Integer>("col1"));
    columnTwo.setCellValueFactory(new PropertyValueFactory<User, Integer>("col2"));
    columnThree.setCellValueFactory(new PropertyValueFactory<User, Integer>("colThree"));
    columnFour.setCellValueFactory(new PropertyValueFactory<User, Integer>("col4"));
    columnFive.setCellValueFactory(new PropertyValueFactory<User, Integer>("col5"));
    columnSix.setCellValueFactory(new PropertyValueFactory<User, Integer>("col6"));
    columnSeven.setCellValueFactory(new PropertyValueFactory<User, Integer>("col7"));
    columnEight.setCellValueFactory(new PropertyValueFactory<User, Integer>("col8"));
    columnNine.setCellValueFactory(new PropertyValueFactory<User, Integer>("col9"));
    columnTen.setCellValueFactory(new PropertyValueFactory<User, Integer>("col10"));
    column11.setCellValueFactory(new PropertyValueFactory<User, Integer>("col11"));
    column12.setCellValueFactory(new PropertyValueFactory<User, Integer>("col12"));
    column13.setCellValueFactory(new PropertyValueFactory<User, Integer>("col13"));
    column14.setCellValueFactory(new PropertyValueFactory<User, Integer>("col14"));
    column15.setCellValueFactory(new PropertyValueFactory<User, Integer>("col15"));
    column16.setCellValueFactory(new PropertyValueFactory<User, Integer>("col16"));
    column17.setCellValueFactory(new PropertyValueFactory<User, Integer>("col17"));
    column18.setCellValueFactory(new PropertyValueFactory<User, Integer>("col18"));
    column19.setCellValueFactory(new PropertyValueFactory<User, Integer>("col19"));
    column20.setCellValueFactory(new PropertyValueFactory<User, Integer>("col20"));
    column21.setCellValueFactory(new PropertyValueFactory<User, Integer>("col21"));
    column22.setCellValueFactory(new PropertyValueFactory<User, Integer>("col22"));
    column23.setCellValueFactory(new PropertyValueFactory<User, Integer>("col23"));
    column24.setCellValueFactory(new PropertyValueFactory<User, Integer>("col24"));
    column25.setCellValueFactory(new PropertyValueFactory<User, Integer>("col25"));
    //To this Line
    //Load data into tables
    tableView.setItems(getPeople());
    categoryTableView.setItems(getCategory());
    lifeTableView.setItems(getLife());
    interruptionTableView.setItems(getInterruption());
    plansTableView.setItems(getPlans());
    defectTableView.setItems(getDefect());
    deliverableTableView.setItems(getDeliverable());
    
    //Allow the table fields to be editable
    tableView.setEditable(true);
    categoryTableView.setEditable(true);
    lifeTableView.setEditable(true);
    interruptionTableView.setEditable(true);
    plansTableView.setEditable(true);
    defectTableView.setEditable(true);
    deliverableTableView.setEditable(true);
    projectNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breakColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    defectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    defaultDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    deliverableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    effortCategoryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    lifeCycleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    plansColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    columnThree.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnOne.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnTwo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnThree.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnFour.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnFive.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnSix.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnSeven.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnEight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnNine.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnTen.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column11.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column12.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column13.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column14.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column15.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column16.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column17.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column18.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column19.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column20.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column21.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column22.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column23.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column24.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    column25.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }
    //Dummy Data
    public ObservableList<User> getPeople(){
    	ObservableList<User> users = FXCollections.observableArrayList();
    	users.addAll(new Query().getProjectType("ProjectTypes"));
    	return users;
    }
    public ObservableList<Category> getCategory(){
    	ObservableList<Category> category = FXCollections.observableArrayList();
    	category.addAll(new Query().getCategory("EffortCategory"));
    	return category;
    }
    public ObservableList<Life> getLife(){
    	ObservableList<Life> life = FXCollections.observableArrayList();
    	life.addAll(new Query().getLife("LifeCycleStep"));
    	
    	return life;
    }
    public ObservableList<Break> getInterruption(){
    	ObservableList<Break> breakCol = FXCollections.observableArrayList();
    	breakCol.addAll(new Query().getBreak("Interruptions"));
    	return breakCol;
    }
    public ObservableList<Plans> getPlans(){
    	ObservableList<Plans> plans = FXCollections.observableArrayList();
    	
    	plans.addAll(new Query().get("Plans"));
    	return plans;
    }
    public ObservableList<Defect> getDefect(){
    	ObservableList<Defect> defect = FXCollections.observableArrayList();
    	defect.addAll(new Query().getDefect("DefectCat"));
    	return defect;
    }
    public ObservableList<Deliverable> getDeliverable(){
    	ObservableList<Deliverable> deliverable = FXCollections.observableArrayList();
    	deliverable.addAll(new Query().getDeli("Deliverables"));
    	return deliverable;
    }
}