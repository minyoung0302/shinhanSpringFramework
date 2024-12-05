package net.firstzone.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//util.Date -> sql.Date
	public static java.sql.Date convertSqlDate(Date dt) {
		return new java.sql.Date(dt.getTime());
	}
	
	
	public static Date convertDate(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	public static String convertString(Date dt) {
		String s = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s = sdf.format(dt);
		return s;
	}
}
