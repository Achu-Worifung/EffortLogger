package poker2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class QuickLookController {

    @FXML
    private TableColumn<?, ?> defectCol;

    @FXML
    private Button newSprint;

    @FXML
    private Label quickLookCurrentWeight;

    @FXML
    private TextArea quicklookDescription;

    @FXML
    private TextArea quicklookEfforts;

    @FXML
    private TextArea quicklookOtherInfo;

    @FXML
    private TableView<?> quicklookTable;

    @FXML
    private TextField quicklookTitle;

    @FXML
    private Button startSprint;

}
