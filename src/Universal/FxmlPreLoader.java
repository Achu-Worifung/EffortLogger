package Universal;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class FxmlPreLoader {
	//	--------------------------------THIS IS AN ATTEMP TO IMPROVE PERFORMANCE--------------------------
	//	    ---------THIS WILL BE DONE BY PRE-EMPTIVELY LOADING EACH PANEL AT THE START-----------
//	private static Parent effortConsole, definitionConsole,defectConsole,pokerConsole,editorConsole;
	private  static ScrollPane defect, effortConsole, editor,definition;
	private static VBox poker;

	//	class instance
	private static FxmlPreLoader preLoader;

	

	public  ScrollPane getDefinition() throws IOException {
		FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/definitions/console2.fxml"));
	    definition = loader.load();
		return definition;
	}
	public static void setDefinition(ScrollPane definition) {
		FxmlPreLoader.definition = definition;
	}
	public  VBox getPoker() throws IOException {
		FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/poker2/console.fxml"));
	    poker = loader.load();
		return poker;
	}
	public static void setPoker(VBox poker) {
		FxmlPreLoader.poker = poker;
	}
	public  ScrollPane getDefect() throws IOException {
		FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/Defects/Defects.fxml"));
	    defect = loader.load();
		return defect;
	}
	public static  void setDefect(ScrollPane defect) {
		FxmlPreLoader.defect = defect;
	}
	public  ScrollPane getEffortConsole() throws IOException {
		 FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortConsole/Console.fxml"));
		    effortConsole = loader.load();
		return effortConsole;
	}
	public static void setEffortConsole(ScrollPane effortConsole) {
		FxmlPreLoader.effortConsole = effortConsole;
	}
	public  ScrollPane getEditor() throws IOException {
		FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortEditor/EditorConsole.fxml"));
		    editor = loader.load();
		return editor;
	}
	public static void setEditor(ScrollPane editor) {
		FxmlPreLoader.editor = editor;
	}
	public  FxmlPreLoader getPreLoader() {
		return preLoader;
	}
	public static void setPreLoader(FxmlPreLoader preLoader) {
		FxmlPreLoader.preLoader = preLoader;
	}
	//	----------SINGLETON CONSTRUCTOR--------
	private FxmlPreLoader()
	{
		
	}
	public static FxmlPreLoader getInstance() throws IOException
	{
		if(preLoader == null)
		{
			preLoader = new FxmlPreLoader();
////			----SETTING EVERY PANEL----
//			  FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortConsole/Console.fxml"));
//			    effortConsole = loader.load();
//
//			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/Defects/Defects.fxml"));
//			    defect = loader.load();
//
//			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortEditor/EditorConsole.fxml"));
//			    editor = loader.load();
//
//			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/definitions/console2.fxml"));
//			    definition = loader.load();
//
//			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/poker2/console.fxml"));
//			    poker = loader.load();
//			    
////			    -----SETTING THE PANELS-----
//			    FxmlPreLoader.setEffortConsole(effortConsole);
//			    FxmlPreLoader.setDefinition(definition);
//			    FxmlPreLoader.setDefect(defect);
//			    FxmlPreLoader.setEditor(editor);
//			    FxmlPreLoader.setPoker(poker);
			    
		}
		return preLoader;
	}


}
