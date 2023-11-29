package poker2;


import java.time.LocalTime;
import java.util.List;

import org.bson.types.ObjectId;


public class QuickLook {

    public String title, otherInfo, UserStory, status;
    ObjectId id;
    int rating;
    List<Rate> userRates;
    String start, date;
    boolean newSprint;
    
    public QuickLook(ObjectId id,String status, String title, String otherInfo, String userStory,
            Integer presentRating, List<Rate> userRates) { 
        this.title = title;
        this.otherInfo = otherInfo;
        this.UserStory = userStory;
        this.id = id;
        this.status = status;
        this.userRates = userRates;
        this.rating = presentRating;
    }
    public QuickLook(ObjectId id,String status, String title, String otherInfo, String userStory,
            Integer presentRating, String start,String date, boolean newSprint) { 
        this.title = title;
        this.otherInfo = otherInfo;
        this.UserStory = userStory;
        this.id = id;
        this.status = status;
        this.start = start;
        this.date = date;
        this.newSprint = newSprint;
    }


	public boolean isNewSprint() {
		return newSprint;
	}
	public void setNewSprint(boolean newSprint) {
		this.newSprint = newSprint;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Rate> getUserRates() {
		return userRates;
	}


	public void setUserRates(List<Rate> userRates) {
		this.userRates = userRates;
	}


	public String getUserStory() {
		return UserStory;
	}

	public void setUserStory(String userStory) {
		this.UserStory = userStory;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

  

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

}
