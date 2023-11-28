package PokerPlanning.Backend;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import poker2.Efforts;
import poker2.QuickLook;
import poker2.Rate;

public class ServerCheck {
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient=MongoClients.create(uri);;
	MongoDatabase database = mongoClient.getDatabase("EffortLoggerv2");
	MongoCollection<Document> collection;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // used to format

	public void reopen(String collectionName) {
		try {
			mongoClient = MongoClients.create(uri);
			database = mongoClient.getDatabase("EffortLoggerv2");
			collection = database.getCollection(collectionName);
		} catch (MongoException e) {
			System.out.println("connection failed");
		}

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
		}
		return null;
	}
}
