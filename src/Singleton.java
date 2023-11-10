import Backend.quicklookInfo;

public class Singleton {
	
    quicklookInfo quicklook; //object for quicklook
//    effort effort;
    private Singleton() {
        // Initialization code (if any)
    }

    
    private static Singleton instance;

    
    public quicklookInfo getQuicklook() {
		return quicklook;
	}


	public void setQuicklook(quicklookInfo quicklook) {
		this.quicklook = quicklook;
	}


	public static Singleton getInstance() {
        
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    
}
