package Defects;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Send {
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
}
