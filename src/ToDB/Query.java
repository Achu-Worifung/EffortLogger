package ToDB;

import com.mongodb.*;
import static com.mongodb.client.model.Filters.eq;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Query {
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

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
	public boolean StartEfforts(String projectType, LocalDate date, LocalTime startTime, String lifeCycleStep, String effortCategory )
	
	{
		//close current mongodb client
		
		close();
		reopen("Efforts");
		try {
			collection.insertOne(new Document()
					.append("id", new ObjectId())
					.append("Project", projectType)
					.append("Date", date.toString())
					.append("Start Time", startTime.toString())
					.append("End Time", "")
					.append("Time Spend", "")
					.append("Life Cyle Step", lifeCycleStep)
					.append("NUmber of Entries", 0)
					.append("effort Category", effortCategory));
		}catch(MongoException e)
		{
			System.out.println("Failed to Insert Effort");
			return false;
		}
		return true;
		
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
			collection.updateOne(eq("_id", document.getObjectId("_id")), new Document("$set", new Document("End Time", endTime.toString())));
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
	public Document getEffortLog(String project)
	{
		close();
		reopen("Efforts");
		try {
			return collection.findOneAndDelete(eq("Project", project));
		}catch(MongoException e)
		{
			return null;
		}
	}


}

