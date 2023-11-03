package ToDB;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.List;

public class ProjectTypeMend {
    static String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
    static MongoClient mongoClient;
    static MongoDatabase database;
    static MongoCollection<Document> collection;

    public static void main(String[] args) {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("EffortLoggerv2");
        collection = database.getCollection("ProjectTypes");
        List<Integer> bussness = Arrays.asList(17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        List<Integer> dev = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        List<Integer> none = Arrays.asList();

        try {
            for (int i = 1; i <= 10; i++) {
                Document doc;
                if (i == 1) {
                    doc = new Document()
                            .append("Id", i)
                            .append("Project", "Business Project")
                            .append("ProjectStep", bussness);
                } else if (i == 2) {
                    doc = new Document()
                            .append("Id", i)
                            .append("Project", "Development Project")
                            .append("ProjectStep", dev);
                } else {
                    doc = new Document()
                            .append("Id", i)
                            .append("Project", "")
                            .append("ProjectStep", none);
                }

                collection.insertOne(doc);
            }
            System.out.println("success");
        } catch (MongoException me) {
            System.out.println("error");
        }
    }
}
