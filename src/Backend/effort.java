package Backend;

import java.util.List;

public class effort {
    List<String> startTime, endTime, startDate, lifeCycle, effortCat, randdrop;
    String projectType, status;
    quicklookInfo info;
    
    // just get the start and end time for now
    public effort(String status,List<String> startTime, List<String>  endTime, String projectType, List<String>  startDate, List<String>  lifeCycle,
    		List<String>  effortCat,  quicklookInfo info) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.projectType = projectType;
		this.startDate = startDate;
		this.lifeCycle = lifeCycle;
		this.effortCat = effortCat;
		this.info = info;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<String> getRanddrop() {
		return randdrop;
	}

	public void setRanddrop(List<String> randdrop) {
		this.randdrop = randdrop;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public quicklookInfo getInfo() {
		return info;
	}

	public void setInfo(quicklookInfo info) {
		this.info = info;
	}

 
}
