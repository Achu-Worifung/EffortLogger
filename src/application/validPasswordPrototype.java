package application;

import java.util.regex.Pattern;

public class validPasswordPrototype {
    public static void main(String[] args) {
        boolean isvalid = isVAliduser("ssd$");
        System.out.println(isvalid);
    }
    public static boolean isValidPassword(String password)
    {
        if(password.length() < 8)
        {
            System.out.println("password is short");
            return false;
        }

        if(!Pattern.compile("(?=.*[A-Z])").matcher(password).find())
        {
            System.out.println("no upper case");
            return false;
        }
        if(!Pattern.compile("(?=.*[@#$%^&+=!])").matcher(password).find())
        {
            System.out.println("no special char case");
            return false;
        }

        return true;
    }
    public static boolean isVAliduser(String username)
    {
    	if(username.length() < 3) 
		{
			//set the warning label
			System.out.println("len is " + username.length());
			return false;
		}
    	if(!Pattern.compile("^[a-zA-Z0-9$]+$_%#@!&^").matcher(username).find())
    	{
    	    //set the label to display warning
    	    System.out.println("username is " + username);
    	    return true;
    	}

		//when db is only check for uniqueness of the username
		return true;
	}
    
}

