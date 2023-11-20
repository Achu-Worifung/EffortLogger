package Defects;

import static com.mongodb.client.model.Filters.eq;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //used to format

	//Closes mongo client
	public void close() {
		if(mongoClient == null)  return;
		mongoClient.close();
		mongoClient = null;
		database = null;
		collection = null;
	}

	//When you open the application query the database and get the collections
	public void reopen(String collectionName) {
		try {
			mongoClient = MongoClients.create(uri);
			database = mongoClient.getDatabase("EffortLoggerv2");
			collection = database.getCollection(collectionName);
		} catch (MongoException e) {
			System.out.println("connection failed");
		}

	}
	public List<Document> getDefects(String ProjectType)
	{
		reopen("Defects");
		List<Document> defects = new ArrayList<>();
		try {
	    	FindIterable<Document> results = collection.find(eq("Project", ProjectType)); //come here if it ever stops working
	    	results.into(defects);
	    	System.out.println(defects.toString());
	       return defects;
	    } catch (MongoException e) {
	        // Handle the exception
	    }
		System.out.println(defects.toString());
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
	                    System.out.println(document.getString("Defect Category"));
	                    if(document.getString("Defect Category").isEmpty()) continue;
	                    defectCat.add(document.getString("Defect Category"));
	                }
	                
	            
	            }catch(MongoException me)
	            {
	            	
	            }
		 }catch(MongoException me)
		 {
			 
		 }
		 return defectCat;
	}
	//for defect log: clear defect log button
		public boolean clearEffortLog(String ProjectType)
		{
			close();
			reopen("Efforts");
			DeleteResult result = collection.deleteMany(eq("Project", ProjectType));


			if(result != null) return true;
			return false;
		}
	
}
