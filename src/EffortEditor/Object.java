package EffortEditor;

import org.bson.types.ObjectId;

public class Object {
	ObjectId id;
	String pjt;
	String date,  start,  end, lifeCycleStep, effortCat, random;
	Object(ObjectId id, String date, String start, String end, String lifeCycleStep, String effortCat, String random)
	{
		this.id = id;
		this.date = date;
		this.start = start;
		this.end = end;
		this.lifeCycleStep = lifeCycleStep;
		this.effortCat = effortCat;
		this.random = random;
	}

}
