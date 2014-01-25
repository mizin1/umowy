package pl.waw.mizinski.umowy.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
	
	//unused
	public static Date toDate(String date) {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String trim(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		} else {
			return string.trim();
		}
	}
}
