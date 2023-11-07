
import java.util.ArrayList;
import java.util.List;

public class sample {
	String title, description;
	sample(String t, String d)
	{
		this.description = d;
		this.title = t;
	}
	sample()
	{
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<sample> getlist()
	{
		List<sample > sam = new ArrayList<>();
		sam.add(new sample("Criteria Acceptance", "As a user actively participating "
				+ "in a sprint planning session that incorporates planning poker, I require a"
				+ " robust tool that empowers me with the capability to finely tune project "
				+ "criteria prior to the commencement of the planning poker session. "
				+ "This is essential for gaining a comprehensive understanding of the"
				+ " project's specific requirements and constraints, ensuring that only "
				+ "the most relevant historical projects are considered during the estimation"
				+ " process."));
		sam.add(new sample("Weight endowment", "Weight Endowment, a powerful tool integrated "
				+ "into the EffortLogger system, revolutionizes the planning poker process,"
				+ " offering a seamless means to swiftly assign weights to user stories. This"));
		sam.add(new sample("Weight endowment", "Weight Endowment, a powerful tool integrated "
				+ "into the EffortLogger system, revolutionizes the planning poker process,"
				+ " offering a seamless means to swiftly assign weights to user stories. This"));
		sam.add(new sample("Weight endowment", "Weight Endowment, a powerful tool integrated "
				+ "into the EffortLogger system, revolutionizes the planning poker process,"
				+ " offering a seamless means to swiftly assign weights to user stories. This"));
		sam.add(new sample("Weight endowment", "Weight Endowment, a powerful tool integrated "
				+ "into the EffortLogger system, revolutionizes the planning poker process,"
				+ " offering a seamless means to swiftly assign weights to user stories. This"));
		sam.add(new sample("Weight endowment", "Weight Endowment, a powerful tool integrated "
				+ "into the EffortLogger system, revolutionizes the planning poker process,"
				+ " offering a seamless means to swiftly assign weights to user stories. This"));
		return sam;
	}

}
