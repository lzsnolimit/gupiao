package com.stocks.service.write;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.stocks.common.CONSTANT;
import com.stocks.service.IO.DBService;
import com.stocks.service.IO.FileService;

/**
 * Hello world!
 *
 */
public class XueQiuTodaySummarizedToDB {
	private static final Logger LOGGER = Logger.getLogger(XueQiuTodaySummarizedToDB.class);
	static {
		DBService.initial();
	}
	public static String dataSource = "XueQiu";

	public static void loadXueQiuTodayFromFiles() {
		
		
	}

}