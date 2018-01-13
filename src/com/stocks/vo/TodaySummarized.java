package com.stocks.vo;

import org.json.JSONObject;

import com.stocks.service.IO.FileService;

public class TodaySummarized {

	

	public static void main(String[] args) {
		
		JSONObject jsonObject=FileService.readJsonObject("/home/nolimit/workplace/gupiao/files/history/xueqiu/today/US/A/Summarized_20171201.txt").getJSONObject("A");
		for (String string : jsonObject.keySet()) {
			
			System.out.println("private String "+string+";");
		}
	}
	
}
