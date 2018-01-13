package com.stocks.service.write;

import com.stocks.service.IO.DBService;
import com.stocks.vo.TodayDetails.Detail;

public class XueQiuTodayToDB {
	static {
		DBService.initial();
	}
	public static String dataSource = "XueQiu";

	public static void writeToMysql(Detail detail, String symbol) {
		String sql = null;
		sql = "INSERT INTO `stocks`.`details`" + "(`volume`," + "`symbol`," + "`avg_price`," + "`current`,"
				+ "`localtime`," + "`timestamp`," + "`market`)" + "VALUES (" + "'" + detail.getVolume() + "','" + symbol
				+ "'," + "'" + detail.getAvg_price() + "'," + "'" + detail.getCurrent() + "'," + "'"
				+ detail.getLocaltime() + "'," + "'" + detail.getTimestamp() + "'," + "'" + dataSource + "');" + "";
		DBService.executeInsert(sql);

	}
}
