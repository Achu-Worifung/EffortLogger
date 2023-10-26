package ToDB;

import com.mongodb.*;
import static com.mongodb.client.model.Filters.eq;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class Query {
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //used to format

	public void close() {
		if(mongoClient == null)  return;
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
	public boolean StartEfforts(String projectType, LocalDate date, LocalTime startTime, String lifeCycleStep, String effortCategory, String randomdrop )
	
	{
		//close current mongodb client
		
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
					.append(effortCategory, randomdrop) //this is for the one that keeps changing chane it to effortCa
					.append("Effort Category", effortCategory));
		}catch(MongoException e)
		{
			System.out.println("Failed to Insert Effort");
			return false;
		}
		return true;
		
	}
	public boolean updateEffort(ObjectId id, long timespent, String date, String startTime,String end, String lifeCycleStep, String effortCategory, String randomdrop) {

	    // close current mongodb client
	    close();
	    reopen("Efforts");

	    try {
	    	//find doc with the same id and update it
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Date", date)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Start Time", startTime)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("End Time", end)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Life Cyle Step", lifeCycleStep)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Effort Category", effortCategory)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document(effortCategory, randomdrop)));
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Time Spend", timespent)));
	    	
	    	//getting entries number
	    	Integer entries = collection.find(eq("_id", id)).first().getInteger("Number of Entries");
	    	
	    	entries++;
	    	collection.updateOne(eq("_id", id), new Document("$set", new Document("Number of Entries", entries)));
	    	return true;
	    } catch (MongoException e) {
	        System.out.println("Failed to update Effort");
	        return false;
	    }
	}

	public boolean endEffort(LocalTime endTime )
	
	{
		//close current mongodb client
		
		close();
		reopen("Efforts");
		try {
			Document document = collection.find(eq("End Time", "")).first();
			if(document == null) return false;
			LocalTime start = LocalTime.parse(document.getString("Start Time"));
			Duration timeSpend = Duration.between(start, endTime);
			long minute = timeSpend.toMinutes(); //this is in minutes
			collection.updateOne(eq("_id", document.getObjectId("_id")), new Document("$set", new Document("End Time", endTime.format(formatter))));
			collection.updateOne(eq("_id", document.getObjectId("_id")), new Document("$set", new Document("Time Spend", minute)));
			
		}catch(MongoException e)
		{
			System.out.println("no Active document found");
			return false;
		}
		return true;
		
	}
	public boolean EffortEditor(String objectId, String projectType, LocalDate date, LocalTime startTime, String lifeCycleStep, String effortCategory )
	
	{
		//close current mongodb client
		
		close();
		reopen("Efforts");
		try {
			collection.updateOne(eq("_id", objectId), new Document("$set", new Document("Project", projectType)));
		}catch(MongoException e)
		{
			System.out.println("Failed to Insert Effort");
			return false;
		}
		return true;
		
	}
	//suggest effort for effortlogger editor
	public List<Document> getEffortLog(String project) {
	    close();
	    reopen("Efforts");
	    try {
	    	FindIterable<Document> results = collection.find(eq("Project", project));
	    	List<Document> resultList = new ArrayList<>();
	    	results.into(resultList);
	       return resultList;
	    } catch (MongoException e) {
	        // Handle the exception
	    }
	    close();
		return null;
	}
	public List<String> getDefects()
	{
		System.out.println("ran");
		close();
		reopen("Defects");
		ArrayList<String> defects = new ArrayList<>();
		try {
			MongoCollection<Document> collection = database.getCollection("Defects");
			MongoCursor<Document> cursor = collection.find().iterator();
			//change Name to defect eventually
			try {
	            while (cursor.hasNext()) {
	            	Document document = cursor.next();
	            	String defect = document.getString("Name");
	                defects.add(defect);
	            }
	        } finally {
	            cursor.close();
	        }
		}catch(MongoException me)
		{
			//error handling
		}
		return defects;
	}

	public boolean openEffort()
	{
		close();
		reopen("Efforts");
		if(collection.find(eq("End Time", "")).first() == null) {
			close();
			return false;
		}
		close();
		return true;
		
	}
	//for effort log editor meant to clear either business/development project logs
	public boolean clearEffortLog(String Project)
	{
		close();
		reopen("Efforts");
		System.out.println(Project);
		DeleteResult result = collection.deleteMany(eq("Project", Project));


		if(result != null) return true;
		return false;
	}
	public boolean deleteEntry()
	{
		return true;
	}


}

