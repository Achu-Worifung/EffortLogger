package application;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
public class Controller2 implements Initializable{


	boolean toCreate = false;
	@FXML
	private ComboBox<String> box_role;

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
		// TODO Auto-generated method stub
		box_role.getItems().add("User");
		box_role.getItems().add("Employee");
		box_role.getItems().add("Supervisor");
		
	}
	public boolean isValidUsername(String username)
	{
		
		if(username.length() < 3) 
		{
			//set the warning label
			 cWarning.setText("Username length must be greater than 3");
			return false;
		}
		if(!Pattern.matches("^[a-zA-Z0-9]+$", username))
		{
			//set the label to display warning
			cWarning.setText("Only Alpha-Numeric Character are allowed for the Username");
			return false;
		}
		//when db is only check for uniqueness of the username
		return true;
	}
	
	
	public boolean isValidPassword(String password)
	{
		 if(password.length() < 8)
		 {
			 cWarning.setText("Password Must have a Minimum length of 8 characters");
			 return false;
		 }
		 
		 if(!Pattern.matches("(?=.*[A-Z])", password))
		 {
			 cWarning.setText("Password Must have At least one uppercase letter");
			 return false;
		 }
		 if(!Pattern.matches("(?=.*[@#$%^&+=!])", password))
		 {
			 cWarning.setText("Password Must have least one special character");
			 return false;
		 }
		 if(!Pattern.matches("(?=\\\\S+$)", password))
		 {
			 cWarning.setText("Password Must have NO whitespace allowed");
			 //need no white space
			 return false;
		 }
		 return true;
				 
	}
	public void createUser(ActionEvent event)
	{
		String un = txt_cpass.getText();
		String pass = txt_cfname.getText();
		if(!isValidPassword(pass) || !isValidUsername(un))
		{
			// if() {} query db to ensure username is not already taken
			event.consume();
			return;
		}
		//setting username and password login field to the username and password just created
		txt_luname1.setText(un);
		txt_lpass1.setText(pass);
		switchForm(event);
		
		
	}

}
