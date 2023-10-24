package application;

import com.mongodb.*;
import static com.mongodb.client.model.Filters.eq;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class AddUserToDb {
	
	String uri = "mongodb+srv://achuworifung:QqgHwlf9hnQl53fW@cluster0.fodlvul.mongodb.net/";
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	public AddUserToDb()
	{
		try {
		mongoClient = MongoClients.create(uri);
		database = mongoClient.getDatabase("EffortLoggerv2");
		collection = database.getCollection("Users");
		}catch(MongoException e)
		{
			System.out.println("connection failed");
		}
	}
	
	public boolean addUserTODB(String fullName, String userName, String Password, String role, String jobTitle)
	{
		try
		{
			
			
			//check that userName doesn't already exist
			Document document = collection.find(eq("User_Name", userName)).first();
			if(document == null)
			{
				try {
					//inserting new user
					InsertOneResult result = collection.insertOne(new Document()
							.append("_id", new ObjectId())
							.append("FullName", Encrypt(fullName))
							.append("UserName", userName)
							.append("Job Title", jobTitle)
							.append("role", role)
							.append("Password", Encrypt(Password)));
					database.getCollection("Full Names").insertOne(new Document().append("Full Name", fullName));
				}catch(MongoException insert)
				{
					System.out.println("failed to insert user into database");
					return false;
				}
			}
			
		}catch(MongoException e)
		{
			System.out.println("failed to connect to database");
			return false;
		}
		finally
		{
			close();
		}
		return true;
	}
	
	private String Encrypt(String password) {
		//Password hashing to encrypt user password
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashByte = md.digest(password.getBytes());
			
			StringBuilder hexString = new StringBuilder();
			for(byte b: hashByte)
			{
				String hex = Integer.toHexString(0xff & b);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	public boolean authen(String username, String Password)
	{
		reopen();
		System.out.println("username :" + username);
		System.out.println("password :" + Password);
		Document document = collection.find(eq("UserName", username)).first();
		if(document == null) 
		{
			System.out.println("username not in db");
			close();
			return false;
		}
		String hashedPassword = document.getString("Password");
		if(Encrypt(Password).contentEquals(hashedPassword))
		{
			close();
			return true;
		}
		close();
		return false;
	}
	public void close() {
		if(mongoClient == null)  return;
        mongoClient.close();
        mongoClient = null;
        database = null;
        collection = null;
    }

    public void reopen() {
        try {
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("EffortLoggerv2");
            collection = database.getCollection("Users");
        } catch (MongoException e) {
            System.out.println("connection failed");
        }
    }

}
