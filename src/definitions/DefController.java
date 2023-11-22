package definitions;

import java.net.URL;
import java.util.ResourceBundle;

import ToDB.Query;
import javafx.util.converter.DefaultStringConverter;
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
    private TableColumn<User, String> column11;
    @FXML
    private TableColumn<User, String> column12;
    @FXML
    private TableColumn<User, String> column13;
    @FXML
    private TableColumn<User, String> column14;
    @FXML
    private TableColumn<User, String> column15;
    @FXML
    private TableColumn<User, String> column16;
    @FXML
    private TableColumn<User, String> column17;
    @FXML
    private TableColumn<User, String> column18;
    @FXML
    private TableColumn<User, String> column19;
    @FXML
    private TableColumn<User, String> column20;
    @FXML
    private TableColumn<User, String> column21;
    @FXML
    private TableColumn<User, String> column22;
    @FXML
    private TableColumn<User, String> column23;
    @FXML
    private TableColumn<User, String> column24;
    @FXML
    private TableColumn<User, String> column25;
    @FXML
    private TableColumn<User, String> columnEight;
    @FXML
    private TableColumn<User, String> columnFive;
    @FXML
    private TableColumn<User, String> columnFour;
    @FXML
    private TableColumn<User, String> columnNine;
    @FXML
    private TableColumn<User, String> columnOne;
    @FXML
    private TableColumn<User, String> columnSeven;
    @FXML
    private TableColumn<User, String> columnSix;
    @FXML
    private TableColumn<User, String> columnTen;
    @FXML
    private TableColumn<User, String> columnThree;
    @FXML
    private TableColumn<User, String> columnTwo;
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
    	userSelected.setColThree((String) edittedCell.getNewValue());
    }
    public void changeColumn1CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol1((String) edittedCell.getNewValue());
    }
    public void changeColumn2CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol2((String) edittedCell.getNewValue());
    }
    public void changeColumn4CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol4((String) edittedCell.getNewValue());
    }
    public void changeColumn5CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol5((String) edittedCell.getNewValue());
    }
    public void changeColumn6CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol6((String) edittedCell.getNewValue());
    }public void changeColumn7CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol7((String) edittedCell.getNewValue());
    }
    public void changeColumn8CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol8((String) edittedCell.getNewValue());
    }
    public void changeColumn9CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol9((String) edittedCell.getNewValue());
    }
    public void changeColumn10CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol10((String) edittedCell.getNewValue());
    }
    public void changeColumn11CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol11((String) edittedCell.getNewValue());
    }
    public void changeColumn12CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol12((String) edittedCell.getNewValue());
    }
    public void changeColumn13CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol13((String) edittedCell.getNewValue());
    }
    public void changeColumn14CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol14((String) edittedCell.getNewValue());
    }
    public void changeColumn15CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol15((String) edittedCell.getNewValue());
    }
    public void changeColumn16CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol16((String) edittedCell.getNewValue());
    }
    public void changeColumn17CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol17((String) edittedCell.getNewValue());
    }
    public void changeColumn18CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol18((String) edittedCell.getNewValue());
    }
    public void changeColumn19CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol19((String) edittedCell.getNewValue());
    }
    public void changeColumn20CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol20((String) edittedCell.getNewValue());
    }
    public void changeColumn21CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol21((String) edittedCell.getNewValue());
    }
    public void changeColumn22CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol22((String) edittedCell.getNewValue());
    }
    public void changeColumn23CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol23((String) edittedCell.getNewValue());
    }
    public void changeColumn24CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol24((String) edittedCell.getNewValue());
    }
    public void changeColumn25CellEvent(CellEditEvent edittedCell) {
    	User userSelected = tableView.getSelectionModel().getSelectedItem();
    	userSelected.setCol25((String) edittedCell.getNewValue());
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
    columnOne.setCellValueFactory(new PropertyValueFactory<User, String>("col1"));
    columnTwo.setCellValueFactory(new PropertyValueFactory<User, String>("col2"));
    columnThree.setCellValueFactory(new PropertyValueFactory<User, String>("colThree"));
    columnFour.setCellValueFactory(new PropertyValueFactory<User, String>("col4"));
    columnFive.setCellValueFactory(new PropertyValueFactory<User, String>("col5"));
    columnSix.setCellValueFactory(new PropertyValueFactory<User, String>("col6"));
    columnSeven.setCellValueFactory(new PropertyValueFactory<User, String>("col7"));
    columnEight.setCellValueFactory(new PropertyValueFactory<User, String>("col8"));
    columnNine.setCellValueFactory(new PropertyValueFactory<User, String>("col9"));
    columnTen.setCellValueFactory(new PropertyValueFactory<User, String>("col10"));
    column11.setCellValueFactory(new PropertyValueFactory<User, String>("col11"));
    column12.setCellValueFactory(new PropertyValueFactory<User, String>("col12"));
    column13.setCellValueFactory(new PropertyValueFactory<User, String>("col13"));
    column14.setCellValueFactory(new PropertyValueFactory<User, String>("col14"));
    column15.setCellValueFactory(new PropertyValueFactory<User, String>("col15"));
    column16.setCellValueFactory(new PropertyValueFactory<User, String>("col16"));
    column17.setCellValueFactory(new PropertyValueFactory<User, String>("col17"));
    column18.setCellValueFactory(new PropertyValueFactory<User, String>("col18"));
    column19.setCellValueFactory(new PropertyValueFactory<User, String>("col19"));
    column20.setCellValueFactory(new PropertyValueFactory<User, String>("col20"));
    column21.setCellValueFactory(new PropertyValueFactory<User, String>("col21"));
    column22.setCellValueFactory(new PropertyValueFactory<User, String>("col22"));
    column23.setCellValueFactory(new PropertyValueFactory<User, String>("col23"));
    column24.setCellValueFactory(new PropertyValueFactory<User, String>("col24"));
    column25.setCellValueFactory(new PropertyValueFactory<User, String>("col25"));
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
    columnThree.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnOne.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnTwo.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnThree.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnFour.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnFive.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnSix.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnSeven.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnEight.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnNine.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    columnTen.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column11.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column12.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column13.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column14.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column15.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column16.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column17.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column18.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column19.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column20.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column21.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column22.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column23.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column24.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
    column25.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
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