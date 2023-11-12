package Backend.copy;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class pokerPlanningRequestPrototype {
	//retrives information
    String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

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

    // make the panel already have the info just expand it when quicklook is presed
    public List<allclass> quickLookInfo() {
        List<allclass> allInfo = new ArrayList<>();
        List<defect> defect = getDefects();
        List<effort> efforts = getEfforts();
        List<quicklookInfo> qlook = getQuicklook();
        // since defect is empty for now pass it over
        // they should all have the same length
        for (int i = 0; i < defect.size(); i++) {
            allInfo.add(new allclass(defect.get(i), efforts.get(i), qlook.get(i)));
        }
        return allInfo;

    }

    // this is a model
    public List<effort> getEfforts() {
        System.out.println("ran");
        close();
        reopen("Quicklook");
        List<effort> efforts = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Efforts");
            MongoCursor<Document> cursor = collection.find().iterator();
            // change Name to defect eventually
            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String startDate = document.getString("Date"); //
                    String startTime = document.getString("Start Time");
                    String stopTime = document.getString("End Time");
                    String projectType = document.getString("Project");

                    efforts.add(new effort(startTime, stopTime, projectType, startDate));
                }
            } finally {
                cursor.close();
            }
        } catch (MongoException me) {
            // error handling
        }
        return efforts;
    }

    public List<quicklookInfo> getQuicklook() {
        System.out.println("ran");
        close();
        reopen("Quicklook");
        List<quicklookInfo> qlooks = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Quicklook");
            MongoCursor<Document> cursor = collection.find().iterator();
            // change Name to defect eventually
            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    ObjectId id = document.getObjectId("_id");
                    String title = document.getString("Title");
                    String desc = document.getString("Description");
                    String oInfo = document.getString("Other Information");
                    List<Integer> pastRating = (List<Integer>)document.get("Past Rating");
                    
                    Integer presentRating = document.getInteger("Rating");

                    qlooks.add(new quicklookInfo(id, title, oInfo, desc, pastRating, presentRating));
                }
            } finally {
                cursor.close();
            }
        } catch (MongoException me) {
            // error handling
        }
        return qlooks;
    }

    public List<defect> getDefects() {
        System.out.println("ran");
        close();
        reopen("Defects");
        ArrayList<defect> defects = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Defects");
            MongoCursor<Document> cursor = collection.find().iterator();
            // change Name to defect eventually
            try {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String defect = document.getString("Name");
                    defects.add(new defect(defect));// empty for now
                }
            } finally {
                cursor.close();
            }
        } catch (MongoException me) {
            // error handling
        }
        return defects;
    }

}
