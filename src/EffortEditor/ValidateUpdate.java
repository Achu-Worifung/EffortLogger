package EffortEditor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ValidateUpdate {

	public String valide(String date,LocalTime start, LocalTime end)
	{
		if(!(start.getHour()>=0 && start.getHour() <=24) || !(start.getMinute() >=0 && start.getMinute()<=59)||!(start.getSecond() >=0 && start.getSecond()<=59))
		{
			return "Start time is invalid";
		}
		if(!(end.getHour()>=0 && end.getHour() <=24 || end.getMinute() >=0 && end.getMinute()<=59||end.getSecond() >=0 && end.getSecond()<=59))
		{
			return "end time is invalid";
		}
		Duration time = Duration.between(start, end);
		long timeSpent = time.toSeconds();
		if(timeSpent < 0)
		{
			return "Start and end Time imbalance";
		}
		try {
			LocalDate.parse(date);
			return "success";
		} catch (DateTimeParseException e) {
			return "Date is not formatted correctly";
		}

	}

}
