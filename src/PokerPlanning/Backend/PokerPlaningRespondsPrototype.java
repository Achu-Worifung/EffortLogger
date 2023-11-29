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
	MongoClient mongoClient=MongoClients.create(uri);;
	MongoDatabase database = mongoClient.getDatabase("EffortLoggerv2");
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // used to format
	
	private static PokerPlaningRespondsPrototype pokerInstance;
	
	private PokerPlaningRespondsPrototype()
	{
		
	}
	
	public static PokerPlaningRespondsPrototype getInstance()
	{
		if (pokerInstance == null)
		{
			pokerInstance = new PokerPlaningRespondsPrototype();
		}
		return pokerInstance;
	}

	defect defectinfo;
	effort effortInfo;

	public void close() {
		if (mongoClient != null) {
			try {
				mongoClient.close();
			} catch (Exception e) {
				// Handle any exceptions that may occur during the closing process
				e.printStackTrace();
			} finally {
				mongoClient = null;
				database = null;
				collection = null;
			}
		}
	}


	public void reopen(String collectionName) {
		try {
//			mongoClient = 
//			database = mongoClient.getDatabase("EffortLoggerv2");
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
		}finally
		{
			//close();
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
				System.out.println("rating from info "+info.getRating());
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
			//close();

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
		reopen("Efforts");
		List<Document> ratings = new ArrayList<>();
		try {
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
		}catch(MongoException me)
		{
			me.printStackTrace();
		}finally
		{
			//close();
		}

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
		reopen("EffortsOnHold");
		Document first = collection.find().first();
		if(first != null)
		{
			Efforts eff = new Efforts(first.getString("Project"), first.getList("Start Time", String.class), first.getList("End Time", String.class),
					first.getList("Start Date", String.class), first.getList("Life Cycle", String.class), first.getList("effort Cat", String.class),
					first.getList("rand", String.class));
			System.out.println(eff.toString());
			return eff;
		}
		return null;
	}
	public QuickLook getQuick()
	{
		reopen("Quicklook");
		Document first = collection.find().first();
		if(first != null) {
			return new QuickLook(first.getObjectId("_id"),first.getString("Status"), first.getString("Title"), first.getString("Other Information"), first.getString("User Story")
					,first.getInteger("Rating"), first.getString("Start Time"), first.getString("Start Date"), first.getBoolean("New Sprint", false));
		}else 
		{
			return null;
		}
	}
	public boolean writeEffortInfo(Efforts effort)
	{
		reopen("EffortsOnHold");
		if(collection.find().first() != null) return false;
		Document doc = new Document();
		doc.append("Project", effort.getProjectType())
		.append("Start Time", effort.getStartTime())
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
		if(collection.find().first() != null) return false;
		Document document = new Document();
		//		if(newSprint) {
		document.append("_id", info.getId())
		.append("Status", info.getStatus())
		.append("Title", info.getTitle())
		.append("User Story", info.getUserStory())
		.append("Other Information", info.getOtherInfo())
		.append("Rating", info.getRating())
		.append("Start Time", info.getStart())
		.append("Start Date", info.getDate())
		.append("New Sprint", info.isNewSprint())
		;
		
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
		
		//close();
		reopen("Efforts");
		ObjectId docId = info.getqLook().getId();
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Other Information", info.getqLook().otherInfo)));
		//		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Rating", eff.getInfo().getPresentRating())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Project",info.getEffort().getProjectType())));
		//updating the arrays
		Bson filter = Filters.eq("_id", docId); // Assuming you're using '_id' as the
		
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
		

		update = Updates.push("Random Value", info.getEffort().getRand().get(0));
		collection.updateOne(filter, update);

	

		return true;
	}


	public boolean stopSprint() {
		//close();
		reopen("Efforts");

		// Finding the document with an empty or null "End Time" field
		Document doc = collection.find(Filters.or(
				Filters.eq("End Time", ""),
				Filters.eq("End Time", null)
				)).first();

		if (doc == null) {
			//close();
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

		//close();
		return true;
	}

	public boolean writeToquickLook(quicklookInfo qlook) {
		//close();
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

		//close();
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
