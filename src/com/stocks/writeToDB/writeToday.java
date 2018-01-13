package com.stocks.writeToDB;

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.stocks.common.CONSTANT;
import com.stocks.service.IO.FileService;
import com.stocks.service.read.ReadTodayFromFiles;
import com.stocks.vo.TodayDetails;

public class writeToday {
	public static void main(String[] args) {
		Map<String, String> files = ReadTodayFromFiles.listAllFilesUnderPath(CONSTANT.HISTORY.XUEQIU_TODAY, "Details",
				"20171201");
		
		for (Entry<String, String> pair : files.entrySet()) {
			JSONObject jObject = FileService.readJsonObject(pair.getValue());
			try {
				// System.out.println(pair.getKey());
				// System.out.println(pair.getValue());
				TodayDetails todayDetails = new TodayDetails(jObject);
				todayDetails.writeToDB(CONSTANT.DB.CONNECTION_TYPE);
				// System.out.println(todayDetails.getDetails().size());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
}
