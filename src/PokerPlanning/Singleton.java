package PokerPlanning;

import java.util.List;

import Backend.quicklookInfo;
import PokerPlanning.Backend.effort;

public class Singleton {
	
    PokerPlanning.Backend.quicklookInfo quicklook; //object for quicklook
    EffortConsole.Controller control;
    List<effort> effortList;
public List<effort> getEffortList() {
		return effortList;
	}


	public void setEffortList(List<effort> effortList) {
		this.effortList = effortList;
	}


public EffortConsole.Controller getControl() {
		return control;
	}


	public void setControl(EffortConsole.Controller control) {
		this.control = control;
	}


	//    effort effort;
    private Singleton() {
        // Initialization code (if any)
    }

    
    private static Singleton instance;

    
    public quicklookInfo getQuicklook() {
		return quicklook;
	}


	public void setQuicklook(PokerPlanning.Backend.quicklookInfo infosample) {
		this.quicklook = infosample;
	}


	public static Singleton getInstance() {
        
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    
}
