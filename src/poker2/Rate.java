package poker2;

public class Rate
{
	String user;
	int rate;
	public Rate(String user, int rate)
	{
		this.user = user;
		this.rate = rate;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public void setRate(int rate)
	{
		this.rate = rate;
	}
	public String getUser()
	{
		return user;
	}
	public int getRate()
	{
		return rate;
	}
	
}