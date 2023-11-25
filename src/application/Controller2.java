package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import PokerPlanning.Singleton;
import Universal.FxmlPreLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Controller2 implements Initializable{


	boolean toCreate = false;
	@FXML
	private ComboBox<String> box_role;
	@FXML
	private ComboBox<String> job_title;
	@FXML
	private ComboBox<String> level;

	@FXML
	private Button btn_csignup;

	@FXML
	private Button btn_switch;

	@FXML
	private Button btn_lcreate;

	@FXML
	private Button btn_llogin1;

	@FXML
	private AnchorPane create;

	@FXML
	private AnchorPane lcreate;

	@FXML
	private TextField txt_cfname;

	@FXML
	private PasswordField txt_cpass;

	@FXML
	private TextField txt_cuname;

	@FXML
	private PasswordField txt_lpass1;

	@FXML
	private TextField txt_luname1;
	@FXML
	private Label prompt;
	@FXML
	private Label cWarning;
	@FXML
	private Label lWarning;

	AddUserToDb adduser;
	PokerPlanning.Singleton singletonInstance = Singleton.getInstance(); //getting the singleton instance from poker planning
	FxmlPreLoader loadInstance; //fxmlpreloader instance
	private Stage stage;
	private Scene scene;



	@FXML
	void switchForm(ActionEvent event) 
	{
		TranslateTransition slider = new TranslateTransition(Duration.seconds(.3));
		slider.setNode(lcreate);
		if(toCreate == false)
		{
			//going to create account
			slider.setToX(350);
			slider.setOnFinished((ActionEvent e) ->{
				btn_switch.setText("Login");
				prompt.setText("Already Have An Account?");


			});
			slider.play();
			toCreate = true;
		}
		else 
		{

			slider.setToX(0);
			slider.setOnFinished((ActionEvent e) ->{
				btn_switch.setText("Creat An Account");
				prompt.setText("Creat An EffortLogger Account");


			});
			slider.play();

			toCreate = false;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Thread preLoad = new Thread(()-> {
			System.out.println("thread started");
			try {
				loadInstance= FxmlPreLoader.getInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
//		preLoad.setDaemon(true);
		preLoad.start();
		box_role.getItems().add("User");
		box_role.getItems().add("Employee");
		box_role.getItems().add("Supervisor");


		job_title.getItems().add("Junior Developer");
		job_title.getItems().add("Senior Developer");

		level.getItems().add("Level 1");
		level.getItems().add("Level 2");
		level.getItems().add("Level 3");
		//connecting to the db
		this.adduser = new AddUserToDb();

	}
	public boolean isValidUsername(String username)
	{

		cWarning.setText(" ");
		if(username.length() < 3) 
		{
			System.out.println("len is " + username.length());
			cWarning.setText("Username length must be greater than 3");
			return false;
		}
		//all the char allowed in username
		if(!Pattern.compile("^[a-zA-Z0-9$]+$_%#@!&^").matcher(username).find())
		{

			System.out.println("username is " + username);
			return true;
		}
		//when db is only check for uniqueness of the username
		cWarning.setText("Username character not allowed");
		return false;
	}


	public boolean isValidPassword(String password)
	{
		cWarning.setText("");
		System.out.println(password);
		if(password.length() < 8)
		{
			cWarning.setText("Password Must have a Minimum length of 8 characters");
			System.out.println(password);
			return false;
		}

		if(!Pattern.compile("(?=.*[A-Z])").matcher(password).find())
		{

			cWarning.setText("Password Must have At least one uppercase letter");
			return false;
		}
		if(!Pattern.compile("(?=.*[@#$%^&+=!])").matcher(password).find())
		{

			cWarning.setText("Password Must have least one special character");
			return false;
		}


		return true;

	}
	public void createUser(ActionEvent event)
	{
		//check for blanks
		if (txt_cfname.getText().isBlank() || box_role.getValue().isBlank())
		{
			cWarning.setText("No blanks allowed");
			return;
		}
		String un = txt_cuname.getText();
		String pass = txt_cpass.getText();
		if(isValidPassword(pass) && isValidUsername(un))
		{
			System.out.println("valid password");
			// if() {} query db to ensure username is not already taken
			String jobTitle = job_title.getValue().toString()+" "+level.getValue().toString();
			if(!adduser.addUserTODB(txt_cfname.getText(),txt_cuname.getText(), pass, box_role.getValue().toString(), jobTitle))
			{
				event.consume();
				cWarning.setText("User Name alread exist");
				return;
			}

			txt_luname1.setText(un);
			txt_lpass1.setText(pass);
			switchForm(event);
			return;
		}

	}
	public void LogIn(ActionEvent event)
	{
		String username = txt_luname1.getText();
		if(adduser.authen(txt_luname1.getText(), txt_lpass1.getText()))
		{
			//going to the main console
			//performance insane:- might have a problem with memory though
			singletonInstance.setUser(username);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(loadInstance.getEffortConsole());
			stage.setScene(scene);
			stage.show();
			return;
		}
		lWarning.setText("Username or Password is INCORRECT");
		txt_lpass1.setText("");
	}

}
