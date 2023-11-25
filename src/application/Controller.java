//package application;
//
//import javafx.animation.TranslateTransition;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.util.Duration;
//
//public class Controller {
//
//	   @FXML
//	    private ComboBox<?> box_role;
//
//	    @FXML
//	    private Button btn_csignup;
//
//	    @FXML
//	    private Button btn_csingin;
//
//	    @FXML
//	    private Button btn_lcreate;
//
//	    @FXML
//	    private Button btn_llogin;
//
//	    @FXML
//	    private AnchorPane create;
//
//	    @FXML
//	    private AnchorPane lcreate;
//
//	    @FXML
//	    private AnchorPane login;
//
//	    @FXML
//	    private AnchorPane logsignin;
//
//	    @FXML
//	    private AnchorPane signup;
//
//	    @FXML
//	    private TextField txt_cfname;
//
//	    @FXML
//	    private PasswordField txt_cpass;
//
//	    @FXML
//	    private TextField txt_cuname;
//
//	    @FXML
//	    private PasswordField txt_lpass;
//
//	    @FXML
//	    private TextField txt_luname;
//
//	    @FXML
//	    private AnchorPane uplogin;
//    
//    public void switchForm(ActionEvent event)
//    
//    {
//    	//doesn't work fully
//    	TranslateTransition slider = new TranslateTransition();
//    	
//
//    	if(event.getSource() == btn_lcreate) // lcreate = create button in login
//    	{
//    		slider.setNode(lcreate);
//        	slider.setDuration(Duration.seconds(.4));
//    	    slider.setToX(300);
//    	    
//    	    slider.setOnFinished((ActionEvent e) ->{
//    	        uplogin.setVisible(true);
//    	        lcreate.setVisible(false);
//    	        lcreate.setDisable(true);
//    	        slider.setAutoReverse(true);
//    	        
////    	        slider.setToX(0); // Set it back to the original position
//    	    });
//
//    	    slider.play();
//    	}
//    	else 
//    	{
//    	    slider.setNode(uplogin);
//    	    slider.setToX(-300);
//    	    slider.setDuration(Duration.seconds(.4));
//    	    slider.setOnFinished((ActionEvent e) ->{
//    	    	uplogin.setVisible(false);
//    	        lcreate.setVisible(true);
//    	        slider.setAutoReverse(true);
//    	    });
//
//    	    slider.play();
//    	}
//    }
//}
