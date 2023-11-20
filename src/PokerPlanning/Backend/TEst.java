//package PokerPlanning.Backend;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.bson.types.ObjectId;
//
//public class TEst {
//	public static void main(String[] args) {
//		PokerPlaningRespondsPrototype pk = new PokerPlaningRespondsPrototype();
//		  List<Integer> l = new ArrayList<>();
//		  l.add(2);
//		  l.add(2);
//		  l.add(2);
//		  Rate r = new Rate(4, "achu");
//		  ObjectId id = new ObjectId();
//		quicklookInfo qt = new quicklookInfo(id, "hello there", "this is some info", "here is my description", l, 4, r);
//		effort eff = new effort("in progress",new ArrayList<>(Arrays.asList("12;12;12")), new ArrayList<>(Arrays.asList("12;12;12")),"Business", new ArrayList<>(Arrays.asList("12/12/12")), new ArrayList<>(Arrays.asList("lice cycle")), new ArrayList<>(Arrays.asList("effort cat")),qt);
//		//writing test
//		if(pk.writeTo(eff ))
//		{
//			System.out.println("done");
//		}else 
//		{
//			System.out.println("wrong");
//		}
//////		//update test
//		   r = new Rate(4, "achu");
//		    qt = new quicklookInfo(id, "hello there", "this is some info", "here is my description", l, 4, r);
//			 eff = new effort("planned",new ArrayList<>(Arrays.asList("12;12;12")), new ArrayList<>(Arrays.asList("12;12;12")),"Business", new ArrayList<>(Arrays.asList("12/12/12")), new ArrayList<>(Arrays.asList("lice cycle")), new ArrayList<>(Arrays.asList("effort cat")),qt);
//			 //for status
//			 //, planned started, ended
//		if(pk.updatenew(eff))
//		{
//			System.out.println("yes");
//		}
//		else 
//		{
//			System.out.println("error");
//		}
//		pk.printAllEfforts();
//	}
//
//}
