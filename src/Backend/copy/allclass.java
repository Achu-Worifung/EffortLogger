package Backend.copy;

public class allclass {
    defect defect;
    effort effort;
    quicklookInfo quicklookInfo;

    public allclass(defect d, effort e, quicklookInfo q) {
        this.defect = d;
        this.effort = e;
        this.quicklookInfo = q;
    }

    public defect getDefect() {
        return defect;
    }

    public void setDefect(defect defect) {
        this.defect = defect;
    }

    public effort getEffort() {
        return effort;
    }

    public void setEffort(effort effort) {
        this.effort = effort;
    }

    public quicklookInfo getQuicklook() {
        return quicklookInfo;
    }

    public void setQuicklook(quicklookInfo quicklook) {
        this.quicklookInfo = quicklook;
    }

}
