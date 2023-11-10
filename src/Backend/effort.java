package Backend;


public class effort {
    String startTime, endTime, projectType, startDate;

    // just get the start and end time for now
    public effort(String s, String e, String p, String d) {
        this.startTime = s;
        this.endTime = e;
        this.projectType = p;
        this.startDate = d;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}
