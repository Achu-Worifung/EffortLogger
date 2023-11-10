package Backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
