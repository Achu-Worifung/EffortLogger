package Universal;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FxmlPreLoader {
	//	--------------------------------THIS IS AN ATTEMP TO IMPROVE PERFORMANCE--------------------------
	//	    ---------THIS WILL BE DONE BY PRE-EMPTIVELY LOADING EACH PANEL AT THE START-----------
	private static Parent effortConsole, definitionConsole,defectConsole,pokerConsole,editorConsole;

	//	class instance
	private static FxmlPreLoader preLoader;

	public  Parent getEffortConsole() {
		return effortConsole;
	}

	public  void setEffortConsole(Parent effortConsole) {
		FxmlPreLoader.effortConsole = effortConsole;
	}

	public  Parent getDefinitionConsole() {
		return definitionConsole;
	}

	public  void setDefinitionConsole(Parent definitionConsole) {
		FxmlPreLoader.definitionConsole = definitionConsole;
	}

	public  Parent getDefectConsole() {
		return defectConsole;
	}

	public  void setDefectConsole(Parent defectConsole) {
		FxmlPreLoader.defectConsole = defectConsole;
	}

	public  Parent getPokerConsole() {
		return pokerConsole;
	}

	public  void setPokerConsole(Parent pokerConsole) {
		FxmlPreLoader.pokerConsole = pokerConsole;
	}

	public  Parent getEditorConsole() {
		return editorConsole;
	}

	public  void setEditorConsole(Parent editorConsole) {
		FxmlPreLoader.editorConsole = editorConsole;
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
//			----SETTING EVERY PANEL----
			  FXMLLoader loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortConsole/Console.fxml"));
			    effortConsole = loader.load();

			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/Defects/Defects.fxml"));
			    defectConsole = loader.load();

			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/EffortEditor/EditorConsole.fxml"));
			    editorConsole = loader.load();

			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/definitions/console2.fxml"));
			    definitionConsole = loader.load();

			    loader = new FXMLLoader(FxmlPreLoader.class.getResource("/poker2/console.fxml"));
			    pokerConsole = loader.load();
			    
//			    -----SETTING THE PANELS-----
			    preLoader.setEffortConsole(effortConsole);
			    preLoader.setDefinitionConsole(definitionConsole);
			    preLoader.setDefectConsole(defectConsole);
			    preLoader.setEditorConsole(editorConsole);
			    preLoader.setPokerConsole(pokerConsole);
			    
		}
		return preLoader;
	}


}
