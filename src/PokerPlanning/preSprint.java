package PokerPlanning;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class preSprint {
	String t,d;
    @FXML
    private TextArea description;

    @FXML
    private Button qlookButton;

    @FXML
    private Label rating;

    @FXML
    private Label title;
//	public preSprint(String title, String desc)
//	{
//		this.t = title;
//		this.d = desc;
//	}
    public AnchorPane pane(sample sam, String buttonid) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("preSprint.fxml"));
        AnchorPane pane = loader.load();

        preSprint controller = loader.getController();
        controller.setTitle(sam.title);
        controller.setDescription(sam.description);
        controller.setButtonId(buttonid);
//        controller.qlookButton.setOnAction(new controller2().quicklook(sam));
        
        

        return pane;
    }
    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setButtonId(String buttonId) {
        this.qlookButton.setId(buttonId);
    }


	

}
