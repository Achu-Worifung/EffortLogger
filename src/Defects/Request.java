package Defects;

import static com.mongodb.client.model.Filters.eq;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import definitions.Plans;

public class Request {
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient = MongoClients.create(uri);;
	MongoDatabase database = mongoClient.getDatabase("EffortLoggerv2");;
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //used to format
	static Request request ;

	//Closes mongo client
	public void close() {
		if(mongoClient == null)  return;
		mongoClient.close();
		mongoClient = null;
		database = null;
		collection = null;
	}

	public static  Request getInstance() {
		if(request == null)
		{
			request = new Request();
		}
		return request;
	}
	private Request()
	{
		
	}
	public void newDefect(HashMap<String, String> map )
	{
		reopen("Defects");
		try {
			Document document = new Document();
			 for (Map.Entry<String, String> entry : map.entrySet()) {
	                document.append(entry.getKey(), entry.getValue());
	            }
			 collection.insertOne(document);
		}catch(MongoException me)
		{
			me.printStackTrace();
		}
	}
	public void update(ObjectId id, HashMap<String, String> map )
	{
		reopen("Defects");
		try {
			ObjectId docId = id;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				collection.updateOne(eq("_id", docId), new Document("$set", new Document(entry.getKey(), entry.getValue())));
			}
		}catch(MongoException me)
		{
			me.printStackTrace();
		}
	}

	//When you open the application query the database and get the collections
	public void reopen(String collectionName) {
		try {
			collection = database.getCollection(collectionName);
		} catch (MongoException e) {
			System.out.println("connection failed");
		}

	}
	public List<Document> getDefects()
	{
		reopen("Defects");
		List<Document> defects = new ArrayList<>();
		try {
			MongoCollection<Document> collection = database.getCollection("Defects");
			MongoCursor<Document> cursor = collection.find().iterator();
			while (cursor.hasNext())
			{
				Document doc = cursor.next();
				defects.add(doc);
			}
			//			defects = collection.find(eq("Project Type", ProjectType));

			return defects;
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return defects;
	}
	public List<String> getDefectCategory()
	{
		reopen("Efforts");
		List<String> defectCat = new ArrayList<>();
		try {
			MongoCollection<Document> collection = database.getCollection("DefectCat");
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					Document document = cursor.next();
					if(document.getString("Defect Category").isEmpty()) continue;
					defectCat.add(document.getString("Defect Category"));
				}


			}catch(MongoException me)
			{
				me.printStackTrace();
			}
		}catch(MongoException me)
		{
			me.printStackTrace();

		}
		return defectCat;
	}
	//for defect log: clear defect log button
	public boolean clearDefectLog(String ProjectType)
	{
		reopen("Defects");
		DeleteResult result = collection.deleteMany(eq("Project", ProjectType));


		if(result != null) return true;
		return false;
	}
	public boolean deleteDefect(ObjectId id)
	{
		reopen("Defects");
		System.out.println(id);
		DeleteResult result = collection.deleteOne(eq("_id", id));
		if (result != null) return true;
		return false;
	}

}
