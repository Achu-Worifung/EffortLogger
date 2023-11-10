import org.bson.types.ObjectId;

import Backend.quicklookInfo;

public class ForConsole {
	Singleton singleton = Singleton.getInstance(); //getting singleton instance
	quicklookInfo quicklook = singleton.getQuicklook(); //getting the quicklook object
	ObjectId id = quicklook.getId(); //give the qucklook and effort the same id for ease ident.
	

}
