package ToDB;

import java.time.LocalDate;
import java.time.LocalTime;

public class Testeffort {
	public static void main(String[] args) {
		Query q = new Query();
		
		//boolean success = q.StartEfforts("development project", LocalDate.now(), LocalTime.now(), "development", "no category selected");
		boolean success = q.endEffort(LocalTime.now());
		//boolean success = q.clearEffortLog();
		
		
		//test defect
		System.out.println(success);
	}

}
