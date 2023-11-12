package PokerPlanning.Backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
	//retrive all info from the collection
	public List<effort> retrieveAll() {
		reopen("Efforts"); // Reopen the collection

		// Retrieve all documents from the collection
		MongoCursor<Document> cursor = collection.find().iterator();
		List<effort> effortList = new ArrayList<>();
		Rate userRate = new Rate();
		quicklookInfo quickInfo;

		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				List<Document> ratings = (List<Document>) doc.get("Ratings");
				if(ratings != null) {
					for (Document rating : ratings) {
						String user = rating.getString("user");
						Integer userRating = rating.getInteger("rating");
						userRate = new Rate(userRating, user); //rate object
					}
				}
				//quickinfo object
				quickInfo = new quicklookInfo(doc.getObjectId("_id"),doc.getString("Title"),doc.getString("Other Information"),
						doc.getString("User Story"), doc.getList("Prev Rating", Integer.class),doc.getInteger("Rating"), userRate);
				//effort object
				//start and end times are list not strings
				effortList.add(new effort(doc.getString("Status")  ,doc.getList("Start Time", String.class), doc.getList("End Time", String.class), doc.getString("Project"),doc.getList("Start Date", String.class)
						, doc.getList("Life Cycle Step", String.class), doc.getList("Effort Category", String.class), quickInfo));
			}
		} catch(MongoException e) {
			System.err.println("Error retrieving documents: " + e.getMessage());
			return null;
		} finally {
			cursor.close();
		}

		return effortList;
	}
	public void printAllEfforts() {
		List<effort> effortList = retrieveAll();
		if (effortList != null) {
			for (effort eff : effortList) {
				System.out.println(eff.toString());
			}
		} else {
			System.out.println("No efforts found or an error occurred.");
		}
	}





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
	public boolean writeTo(effort eff) {
		close();
		reopen("Efforts");

		// Creating the document with all fields and values
		collection.insertOne( new Document()
				//				.append("_id", eff.getInfo().id)
				.append("Status", eff.getStatus())
				.append("Title", eff.getInfo().getTitle())
				.append("Project", eff.getProjectType())
				.append("User Story", eff.getInfo().desc)
				.append("Other Information", eff.getInfo().getOtherInfo())
				.append("Rating", eff.getInfo().getPresentRating())
				.append("Start Dates",eff.getStartDate())
				.append("Prev Rating", eff.getInfo().getPastRating()) //you dont need this
				.append("Start Time", eff.getStartTime())
				.append("End Time", eff.getEndTime())
				.append("Life Cycle Step", eff.getLifeCycle())
				.append("Number Of Entries", 0)
				.append("User", Arrays.asList(eff.getInfo().getUserRate().getUser()))
				.append("Ratings", Arrays.asList(
						new Document("user", eff.getInfo().getUserRate().getUser()).append("rating", eff.getInfo().getUserRate().getRating())
						)));

		//	        .append("User", Arrays.asList(eff.getInfo().getUserRate().getUser())) // assuming getUserRate returns a Rate object
		//	        .append("Rate", Arrays.asList(eff.getInfo().getUserRate().getRating())));
		//	        .append("Hey", "")); // not sure what this is forget about the random no important

		// Inserting the document into the collection
		//	    collection.insertOne(doc);

		return true;
	}

	boolean updatenew(effort eff)
	{
		close();
		reopen("Efforts");
		ObjectId docId = eff.getInfo().getId();
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Title", eff.getInfo().getTitle())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Status", eff.getStatus())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("User Story", eff.getInfo().desc)));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Other Information", eff.getInfo().getOtherInfo())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Rating", eff.getInfo().getPresentRating())));
		collection.updateOne(eq("_id", docId), new Document("$set", new Document("Project", eff.getProjectType())));
		//updating the arrays
		Bson filter = Filters.eq("_id", docId); // Assuming you're using '_id' as the
		// unique identifier
		//Define the update operation to push a new item to the array
		//mistake you are pushing in the array not the value
		Bson update = Updates.push("Past Rating", eff.getInfo().getPastRating().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Start Dates", eff.getStartDate().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Start Time", eff.getStartTime().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("End Time", eff.getEndTime().get(0));
		collection.updateOne(filter, update);

		update = Updates.push("Effort Category", eff.getEffortCat().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Life Cycle Step", eff.getLifeCycle().get(0));
		collection.updateOne(filter, update);
		update = Updates.push("Prev Rating", eff.getInfo().getPresentRating());
		collection.updateOne(filter, update);

		Document document = collection.find(eq("_id", docId)).first();
		List<String> users = document.getList("User", String.class);

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).equals(eff.getInfo().getUserRate().getUser())) {
				filter = Filters.and(
						Filters.eq("_id", docId),
						Filters.eq("Ratings.user", eff.getInfo().getUserRate().getUser())
						);
				update = Updates.set("Ratings.$.rating", eff.getInfo().getUserRate().getRating());
				collection.updateOne(filter, update);
				return true;
			}
		}

		//else push the new user and his rating
		update = Updates.push("User", eff.getInfo().getUserRate().getUser());
		collection.updateOne(filter, update);
		update = Updates.push("Rating", eff.getInfo().getUserRate().getRating());
		collection.updateOne(filter, update);

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
	    collection.updateOne(eq("_id", doc.getObjectId("_id")), new Document("$set", new Document("Status","Completed")));
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
