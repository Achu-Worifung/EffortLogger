package application;
import java.net.URL;
import java.util.ResourceBundle;

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
	public boolean checkUsername()
	{
		String username = txt_cfname.getText();
		return false;
	}
	public boolean validPass(String password)
	{
		String specialCharacters  = "@!%$#&()_-+?~";
		if(password.length() < 8) return false;
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isUpperCase(password.charAt((i)))) break;
		}
//		for(int i = 0; i < specialCharacters.length(); i++)
//		{
//			if(password.contains(specialCharacters.charAt(i)) break;
//		}
		
		return false;
	}

}
