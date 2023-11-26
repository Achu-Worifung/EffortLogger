package PokerPlanning.Backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import poker2.Efforts;
import poker2.QuickLook;
import poker2.RetrieveAll;
import poker2.Rate;

public class PokerPlaningRespondsPrototype {
	// writes to the database
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // used to format

	defect defectinfo;
	effort effortInfo;

	public void close() {
		if (mongoClient == null)
			return;
		mongoClient.close();
		mongoClient = null;
		database = null;
		collection = null;
	}

	public void reopen(String collectionName) {
		try {
			mongoClient = MongoClients.create(uri);
			database = mongoClient.getDatabase("EffortLoggerv2");
			collection = database.getCollection(collectionName);
		} catch (MongoException e) {
			System.out.println("connection failed");
		}

	}
	public HashMap<String, String> getTimes(ObjectId id)
	{
		reopen("Efforts"); // Reopen the collection
		Document doc = collection.find(eq("_id", id)).first();
		
		// Retrieve all documents from the collection
		HashMap<String, String> startEnd = new HashMap<String, String>();

		try {
			
				if(doc != null)
				{
					List<String> start = doc.getList("Start Time", String.class);
					List<String> end = doc.getList("End Time", String.class);
					for(int i = 0; i < end.size(); i++)
					{
						startEnd.put(start.get(i), end.get(i));
					}
				}
				
		}catch(MongoException me)
		{
			me.printStackTrace();
		}
		return startEnd;
	}
	//retrive all info from the collection
	public List<RetrieveAll> retrieveAll() {
		reopen("Efforts"); // Reopen the collection

		// Retrieve all documents from the collection
		MongoCursor<Document> cursor = collection.find().iterator();
		List<RetrieveAll> allInformation = new ArrayList<>();
		Efforts effort;
		Rate userRate = null;
		QuickLook info;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				List<Document> ratings = (List<Document>) doc.get("Ratings");
				List<Rate> userRates = new ArrayList<>();
				if(ratings != null) {
					for (Document rating : ratings) {
						String user = rating.getString("user");
						Integer userRating = rating.getInteger("rating");
						userRates.add(new Rate(user, userRating)); //rate object
					}
				}
				//quickinfo object
				info = new QuickLook(doc.getObjectId("_id"),doc.getString("Status"),doc.getString("Title"),doc.getString("Other Information")
						,doc.getString("User Story"),doc.getInteger("Rating"),userRates);
//				(String status, String projectType,List<String> startTime, List<String>  endTime, List<String>  startDate, List<String>  lifeCycle,
//			    		List<String>  effortCat, List<String> rand) 
				effort = new Efforts(doc.getString("Project"),doc.getList("Start Time", String.class)
						,doc.getList("End Time", String.class),doc.getList("Start Dates", String.class),
						doc.getList("Life Cycle Step", String.class), doc.getList("Effort Category", String.class)
						, doc.getList("Random Value", String.class));
//				quickInfo = new quicklookInfo(doc.getObjectId("_id"),doc.getString("Title"),doc.getString("Other Information"),
//						doc.getString("User Story"),doc.getInteger("Rating"), userRate);
				//effort object
				//start and end times are list not strings
				System.out.println(effort.getRand());
				allInformation.add(new RetrieveAll(effort, info));
			}
		} catch(MongoException e) {
			System.err.println("Error retrieving documents: " + e.getMessage());
			return null;
		} finally {
			cursor.close();
		}

		return allInformation;
	}
//	public void printAllEfforts() {
//		List<effort> effortList = retrieveAll();
//		if (effortList != null) {
//			for (effort eff : effortList) {
//				System.out.println(eff.toString());
//			}
//		} else {
//			System.out.println("No efforts found or an error occurred.");
//		}
//	}





	//    public boolean updatequickLook(quicklookInfo qlook) {
	//        close();
	//        reopen("Efforts");
	//
	//        try {
	//            // find doc with the same id and update it
	//            collection.updateOne(eq("_id", qlook.getId()),
	//                    new Document("$set", new Document("Other Information", qlook.getOtherInfo())));
	//            collection.updateOne(eq("_id", qlook.getId()),
	//                    new Document("$set", new Document("Description", qlook.getDesc())));
	//            Document doc = collection.find(eq("_id", qlook.getId())).first();
	//            if (doc.getInteger("Rating") != qlook.getPresentRating()) {
	//                // suppose to find the average
	//                collection.updateOne(eq("_id", qlook.getId()),
	//                        new Document("$set", new Document("Rating", qlook.getPresentRating())));
	//                // Define the filter to identify the document you want to update
	//
	//                Bson filter = Filters.eq("_id", document.getObjectId("_id")); // Assuming you're using '_id' as the
	//                                                                              // unique identifier
	//                // Define the update operation to push a new item to the array
	//                // Bson update = Updates.push("Past Rating", );
	//
	//                // Perform the update
	//                // collection.updateOne(filter, update);
	//
	//            }

	// getting entries number
	//            Integer entries = collection.find(eq("_id", id)).first().getInteger("Number of Entries");
	//
	//            entries++;
	//            collection.updateOne(eq("_id", id), new Document("$set", new Document("Number of Entries", entries)));
	//            return true;
	//        } catch (MongoException e) {
	//            System.out.println("Failed to update Effort");
	//            return false;
	//        }
	//    }

	// create a new quick look
	// creates new quickloo;
	public boolean writeTo(RetrieveAll info) {
		close();
		reopen("EffortsOnHold");
		List<Document> ratings = new ArrayList<>();

		for(int i= 0; i < info.getqLook().getUserRates().size();i++)
		{
			Rate currentRating = info.getqLook().getUserRates().get(i);
			ratings.add(new Document("user", currentRating.getUser()).append("rating", currentRating.getRate()));
		}

		// Creating the document with all fields and values
		collection.insertOne( new Document()
				//				.append("_id", eff.getInfo().id)
				.append("Status", info.getqLook().getStatus())
				.append("Title", info.getqLook().getTitle())
				.append("Project", info.getEffort().getProjectType())
				.append("User Story", info.getqLook().getUserStory())
				.append("Other Information", info.getqLook().getOtherInfo())
				.append("Rating", info.getqLook().getRating())
				.append("Start Dates",info.getEffort().getStartDate())
				.append("Start Time",info.getEffort().getStartTime())
				.append("End Time", info.getEffort().getEndTime())
				.append("Life Cycle Step", info.getEffort().getLifeCycle())
				.append("Random Value", info.getEffort().getRand())
				.append("Effort Category", info.getEffort().getEffortCat())
				.append("Number Of Entries", 0)
				.append("Ratings", ratings));

		//	        .append("User", Arrays.asList(eff.getInfo().getUserRate().getUser())) // assuming getUserRate returns a Rate object
		//	        .append("Rate", Arrays.asList(eff.getInfo().getUserRate().getRating())));
		//	        .append("Hey", "")); // not sure what this is forget about the random no important

		// Inserting the document into the collection
		//	    collection.insertOne(doc);

		return true;
	}
	public boolean updateRate(Rate userRate)
	{
		
		reopen("UserRating");
		collection.updateOne(eq("User", userRate.getUser()), new Document("$set", new Document("Rate",userRate.getRate())));
		return true;
		
	}
	public boolean toUserRate(Rate userRate)
	{
		reopen("UserRating");
		Document doc = new Document();
		doc.append("User", userRate.getUser())
		.append("Rate", userRate.getRate());
		collection.insertOne(doc);
		return true;
	}
	public List<Rate> getAllUserRates()
	{
		reopen("UserRating");
		MongoCursor<Document> cursor = collection.find().iterator();
		List<Rate> userRate = new ArrayList<>();

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				userRate.add(new Rate(doc.getString("User"), doc.getInteger("Rate", 0)));
			}
		}catch(MongoException me)
		{
			
		}
		return userRate;
	}
	public Efforts getEffortOnHold()
	{
		reopen("EffortOnHold");
		Document first = collection.find().first();
		if(first != null)
		{
			return new Efforts(first.getString("Project"), first.getList("Start Time", String.class), first.getList("End Time", String.class),
					first.getList("Start Date", String.class), first.getList("Life Cycle", String.class), first.getList("effort Cat", String.class),
					first.getList("rand Time", String.class));
		}
		return null;
	}
	public QuickLook getQuick()
	{
		reopen("Quicklook");
		Document first = collection.find().first();
		if(first != null) {
		return new QuickLook(first.getObjectId("_id"),first.getString("Status"), first.getString("Title"), first.getString("OtherInfo"), first.getString("User Story")
				,first.getInteger("Rating"), first.getString("Start Time"), first.getString("Start Date"), first.getBoolean("New Sprint", false));
		}else 
		{
			return null;
		}
	}
	public boolean writeEffortInfo(Efforts effort)
	{
		reopen("EffortsOnHold");
		Document doc = new Document();
		doc.append("Project", effort.getProjectType())
		.append("Start TIme", effort.getStartTime())
		.append("Start Date", effort.getStartDate())
		.append("End Time", effort.getEndTime())
		.append("Life Cycle", effort.getLifeCycle())
		.append("effort Cat", effort.getEffortCat())
		.append("rand", effort.getRand());
		collection.insertOne(doc);
		return true;
	}
//-----------------------------------WRITE JUST THE QUICKLOOK INFO INTO THE DATABASE----------------------------
	public boolean writeQuickLookInfo(QuickLook info)
	{
		reopen("Quicklook");
		Document document = new Document();
//		if(newSprint) {
		document.append("Status", info.getStatus())
				.append("Title", info.getTitle())
				.append("User Story", info.getUserStory())
				.append("Other Information", info.getOtherInfo())
				.append("Rating", info.getRating())
				.append("Start Time", LocalTime.now().format(formatter).toString())
				.append("Start Date", info.getDate())
				.append("New Sprint", info.isNewSprint())
				;
//		}else 
//		{
//			ObjectId docId = info.getId();
//			collection.updateOne(eq("_id", docId), new Document("$set", new Document("Status", info.getStatus())));
//			collection.updateOne(eq("_id", docId), new Document("$set", new Document("User Story", info.getUserStory())));
//			collection.updateOne(eq("_id", docId), new Document("$set", new Document("Other Information",info.getOtherInfo())));
//			collection.updateOne(eq("_id", docId), new Document("$set", new Document("Rating", info.getRating())));
//			--------------------WILL DO THIS WHEN VOTING-------------------------------------
//			Document doc = collection.find(eq("_id", docId)).first();
//			List<String> users = doc.getList("User", String.class);
//			Bson update;
//			Bson filter = Filters.eq("_id", docId);
////			--------------UPDATING VOTE FOR THOSE WHO HAVE ALREADY VOTED-------------------------
//			for (int i = 0; i < users.size(); i++) {
//				if (users.get(i).equals( info.getUserRate().getUser())) {
//					filter = Filters.and(
//							Filters.eq("_id", docId),
//							Filters.eq("Ratings.user",  info.getUserRate().getRate())
//							);
//					update = Updates.set("Ratings.$.rating",  info.getUserRate().getRate());
//					collection.updateOne(filter, update);
//					return true;
//				}
//			}
//
////			------------------------ADDING VOTE FOR NEW USERS----------------------
//			update = Updates.push("User",  info.getUserRate().getRate());
//			collection.updateOne(filter, update);
//			update = Updates.push("Rating",  info.getUserRate().getRate());
//			collection.updateOne(filter, update);
//		}
		collection.insertOne(document);
		return true;
	}
	public void deleteEffortOnHold()
	{
		reopen("EffortsOnHold");
		Document doc = collection.find().first();
		if(doc == null ) return;
		collection.deleteOne(doc);
	}
	public void delQuickLookOnHold()
	{
		reopen("Quicklook");
		Document doc = collection.find().first();
		if(doc == null ) return;
		collection.deleteOne(doc);
	}
	public void delAllUserRates()
	{
		reopen("UserRating");
		Document doc;
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext())
		{
			doc = cursor.next();
			collection.deleteOne(doc);
		}
	}
	public boolean updatenew(RetrieveAll info)
	{
		close();
		reopen("Efforts");
		ObjectId docId = info.getqLook().getId();
//		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Title", eff.getInfo().getTitle())));
//		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Status", eff.getStatus())));
//		collection.updateOne(eq("_id", docId), new Document("$set", new Document("User Story", eff.getInfo().desc)));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Other Information", info.getqLook().otherInfo)));
//		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Rating", eff.getInfo().getPresentRating())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Project",info.getEffort().getProjectType())));
		//updating the arrays
		Bson filter = Filters.eq("_id", docId); // Assuming you're using '_id' as the
		// unique identifier
		//Define the update operation to push a new item to the array
		//mistake you are pushing in the array not the value
		Bson update;
//		collection.updateOne(filter, update);
		update = Updates.push("Start Dates", info.getEffort().getStartDate().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Start Time",info.getEffort().getStartTime().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("End Time", info.getEffort().getEndTime().get(0));
		collection.updateOne(filter, update);

		update = Updates.push("Effort Category", info.getEffort().getEffortCat().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Life Cycle Step", info.getEffort().getEffortCat().get(0));
		collection.updateOne(filter, update);
//		update = Updates.push("Prev Rating", eff.getInfo().getPresentRating());
//		collection.updateOne(filter, update);
		
		update = Updates.push("Random Value", info.getEffort().getRand().get(0));
		collection.updateOne(filter, update);

//		Document document = collection.find(eq("_id", docId)).first();
//		List<String> users = document.getList("User", String.class);

//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).equals(eff.getInfo().getUserRate().getUser())) {
//				filter = Filters.and(
//						Filters.eq("_id", docId),
//						Filters.eq("Ratings.user", eff.getInfo().getUserRate().getUser())
//						);
//				update = Updates.set("Ratings.$.rating", eff.getInfo().getUserRate().getRating());
//				collection.updateOne(filter, update);
//				return true;
//			}
//		}
//
//		//else push the new user and his rating
//		update = Updates.push("User", eff.getInfo().getUserRate().getUser());
//		collection.updateOne(filter, update);
//		update = Updates.push("Rating", eff.getInfo().getUserRate().getRating());
//		collection.updateOne(filter, update);

		return true;
	}


	public boolean stopSprint() {
	    close();
	    reopen("Efforts");

	    // Finding the document with an empty or null "End Time" field
	    Document doc = collection.find(Filters.or(
	            Filters.eq("End Time", ""),
	            Filters.eq("End Time", null)
	    )).first();

	    if (doc == null) {
	        close();
	        return false;
	    }

	    // Update the last element in the "End Time" array
	    collection.updateOne(eq("_id", doc.getObjectId("_id")), new Document("$set", new Document("Status", "Completed")));
	    Bson filter = Filters.eq("_id", doc.getObjectId("_id"));
	    List<String> endTimes = doc.getList("End Time", String.class);

	    if (endTimes != null && !endTimes.isEmpty()) {
	        int lastIndex = endTimes.size() - 1;
	        Bson update = Updates.set("End Time." + lastIndex, LocalTime.now().format(formatter));
	        collection.updateOne(filter, update);
	    }

	    close();
	    return true;
	}

	public boolean writeToquickLook(quicklookInfo qlook) {
		close();
		reopen("Efforts");
		try {
			collection.insertOne(new Document()
					.append("Title", qlook.getTitle())
					.append("Other Information", qlook.getOtherInfo())
					.append("Description", qlook.getDesc())
					.append("Rating", qlook.getPresentRating())
					.append("Past Rating", Arrays.asList(qlook.getPastRating())));

		} catch (MongoException e) {
			System.out.println("Failed to Insert Effort");
			return false;
		}
		return true;
	}

	public boolean writeToEffort(String projectType, LocalDate date, LocalTime startTime, String lifeCycleStep,
			String effortCategory, String randomdrop)

	{
		// close current mongodb client

		close();
		reopen("Efforts");
		try {
			collection.insertOne(new Document()
					.append("Project", projectType)
					.append("Date", date.toString())
					.append("Start Time", startTime.format(formatter))
					.append("End Time", "")
					.append("Time Spend", "")
					.append("Life Cyle Step", lifeCycleStep)
					.append("Number of Entries", 0)
					.append(effortCategory, randomdrop) // this is for the one that keeps changing chane it to effortCa
					.append("Effort Category", effortCategory));
		} catch (MongoException e) {
			System.out.println("Failed to Insert Effort");
			return false;
		}
		return true;

	}

	// it can have multiple defects //make defects a sub class of quicklook //also
	// you cant chage defects
	// public void writeToDefect()
}
