package poker2;


import java.util.List;


public class SingleTon {

	String user;
	Efforts effort;
	QuickLook info;
	List<RetrieveAll> allInformation;
	public QuickLook getInfo() {
		return info;
	}


	public void setInfo(QuickLook info) {
		this.info = info;
	}


	public Efforts getEffort() {
		return effort;
	}


	public void setEffort(Efforts effort) {
		this.effort = effort;
	}

	private static SingleTon instance;

	public List<RetrieveAll> getAllInformation() {
		return allInformation;
	}


	public void setAllInformation(List<RetrieveAll> allInformation) {
		this.allInformation = allInformation;
	}


	public String getUser() {
		if(user == null) return"patric";  //make sure to delete this
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	private SingleTon() {
		// Initialization code (if any)
	}

	public static SingleTon getInstance() {

		if (instance == null) {
			instance = new SingleTon();
		}
		return instance;
	}


}
