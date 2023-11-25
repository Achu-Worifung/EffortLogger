package poker2;


public class RetrieveAll {
    Efforts effort;
    QuickLook qLook;
    
    // just get the start and end time for now
    public RetrieveAll(Efforts effort, QuickLook qLook) {
		this.effort = effort;
		this.qLook = qLook;
	}

	public Efforts getEffort() {
		return effort;
	}

	public void setEffort(Efforts effort) {
		this.effort = effort;
	}

	public QuickLook getqLook() {
		return qLook;
	}

	public void setqLook(QuickLook info) {
		this.qLook = info;
	}
}
