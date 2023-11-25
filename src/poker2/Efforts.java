package poker2;


import java.util.List;

public class Efforts {
    List<String> startTime, endTime, startDate, lifeCycle, effortCat, rand;
    String projectType;
    
    public Efforts( String projectType,List<String> startTime, List<String>  endTime, List<String>  startDate, List<String>  lifeCycle,
    		List<String>  effortCat, List<String> rand) {
    	super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.projectType = projectType;
		this.startDate = startDate;
		this.lifeCycle = lifeCycle;
		this.effortCat = effortCat;
		this.rand = rand;
	}

	public List<String> getRand() {
		return rand;
	}

	public void setRand(List<String> rand) {
		this.rand = rand;
	}



	public List<String> getStartTime() {
		return startTime;
	}

	public void setStartTime(List<String> startTime) {
		this.startTime = startTime;
	}

	public List<String> getEndTime() {
		return endTime;
	}

	public void setEndTime(List<String> endTime) {
		this.endTime = endTime;
	}

	public List<String> getStartDate() {
		return startDate;
	}

	public void setStartDate(List<String> startDate) {
		this.startDate = startDate;
	}

	public List<String> getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(List<String> lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public List<String> getEffortCat() {
		return effortCat;
	}

	public void setEffortCat(List<String> effortCat) {
		this.effortCat = effortCat;
	}


	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	

 
}
