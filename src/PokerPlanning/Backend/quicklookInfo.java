package PokerPlanning.Backend;

import java.util.List;

import org.bson.types.ObjectId;

public class quicklookInfo {
    // will contain the all info for quick look such as
    // description title, other info and past and present rating
    public String title;
	String otherInfo;
	String desc;
    List<Integer> pastRating;
    Integer presentRating;
    ObjectId id;
    Rate userRate;
    int rating;

    public quicklookInfo(ObjectId id, String title, String otherInfo, String desc, List<Integer> pastRating,
            Integer presentRating, Rate userRate) { //pastRating is a list of list make it a list
        this.title = title;
        this.otherInfo = otherInfo;
        this.desc = desc;
        this.pastRating = pastRating;
        this.presentRating = presentRating;
        this.id = id;
        this.presentRating = 0;
        this.userRate  = userRate; //link usersname to rating so one user can't have different rating
        for(Integer rate: pastRating)
        {
        	presentRating += rate;
        }
        presentRating = presentRating/pastRating.size();
    }


	public Rate getUserRate() {
		return userRate;
	}


	public void setUserRate(Rate userRate) {
		this.userRate = userRate;
	}


	public String getDesc() {
        return desc;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public List<Integer> getPastRating() {
        return pastRating;
    }

    public void setPastRating(List<Integer> pastRating) {
        this.pastRating = pastRating;
    }

    public Integer getPresentRating() {
        return presentRating;
    }

    public void setPresentRating(Integer presentRating) {
        this.presentRating = presentRating;
    }

}
