package com.stocks.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.stocks.common.CONSTANT;

public class DateService {
	public static String getTime(String pattern) {
		return getTime(pattern, System.currentTimeMillis(),CONSTANT.DATA_MARKET);
	}

	public static String getTime(String pattern, Long timestamp,String type) {

		switch (type) {
		case "CN":
			return getTime(pattern, timestamp, "GMT+8");
		case "US":
			return getTime(pattern, timestamp, "GMT-5");
		default:
			Date date = new Date(timestamp);
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.setTimeZone(TimeZone.getTimeZone(type));
			String resultTime = dateFormat.format(date);
			return resultTime;
		}
	}
}
