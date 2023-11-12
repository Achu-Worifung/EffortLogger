package Backend.copy;

public class Rate {
	int rating;
	String user;
	public Rate()
	{
		
	}
	public Rate(int r, String u)
	{
		this.rating = r;
		this.user = u;
	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	  public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

}
