package com.stocks.service.write;

import com.stocks.common.CONSTANT;
import com.stocks.service.IO.DBService;
import com.stocks.vo.Rate;

public class XueQiuOneDayRateToDB {
	static {
		DBService.initial();
	}
	public static String dataSource = "XueQiu";

	public static void writeToOracle(Rate rate) {

		String sql = "INSERT INTO " + CONSTANT.DB.RATE_TABLE_NAME;
		sql += "(HITRATE,HITSIZE,EXPECTEDRATE,TOTAL,CURRENTTYPE,MINIRATE,CONTINOUSDAY,SOURCE,MARKET,StartPeriod,EndPeriod) values";
		sql += "(" + rate.getHitRate() + "," + rate.getHitSize() + "," + rate.getExpectedRate() + "," + rate.getTotal()
				+ ",'" + rate.getCurrentType() + "'," + rate.getLowestRate() + "," + rate.getContinousDay() + ",'"
				+ rate.getSource() + "','" + rate.getMarket()+ "','" + rate.getStartPeriod()+ "','" + rate.getEndPeriod() + "')";
		// System.out.println(sql);
		DBService.executeInsert(sql);
	}
}
