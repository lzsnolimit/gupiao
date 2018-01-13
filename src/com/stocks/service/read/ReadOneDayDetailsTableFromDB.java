package com.stocks.service.read;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stocks.service.IO.DBService;
import com.stocks.vo.OneDayPrice;
import com.stocks.vo.Stock;

public class ReadOneDayDetailsTableFromDB {

	private static final Logger LOGGER = Logger.getLogger(ReadOneDayDetailsTableFromDB.class);

	static {
		DBService.initial();
	}

	// public static List<String> readYahooSymbolList() {
	// String sql = "SELECT DISTINCT Symbol FROM history WHERE source='Yahoo'";
	// ResultSet results = DBService.executeQuery(sql);
	// List<String> symbols = new ArrayList<String>();
	// try {
	// while (results.next()) {
	// symbols.add(results.getString("Symbol"));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// LOGGER.info("Read " + symbols.size() + " symbols!");
	// return symbols;
	// }
	//
	// public static List<OneDayPrice> readYahooBySymbol(String symbol) {
	// List<OneDayPrice> lifeTimeData = new ArrayList<OneDayPrice>();
	// String sql = "SELECT * FROM history WHERE source='Yahoo' and Symbol='" +
	// symbol + "' ORDER by Date";
	// ResultSet results = DBService.executeQuery(sql);
	// try {
	// while (results.next()) {
	// OneDayPrice oneDayData = new OneDayPrice();
	// oneDayData.setId(results.getInt("id"));
	// oneDayData.setStock(new Stock(results.getString("Symbol")));
	// oneDayData.setOpen(results.getDouble("Open"));
	// oneDayData.setHigh(results.getDouble("High"));
	// oneDayData.setLow(results.getDouble("Low"));
	// oneDayData.setClose(results.getDouble("Close"));
	// oneDayData.setVolume(results.getLong("Volume"));
	// oneDayData.setAdjclose(results.getDouble("Adjclose"));
	// oneDayData.setGrowth(results.getDouble("growth"));
	// oneDayData.setSource(results.getString("source"));
	// oneDayData.setRiqi(results.getDate("Date"));
	// lifeTimeData.add(oneDayData);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// LOGGER.info("Symbol " + symbol + " has " + lifeTimeData.size() + "
	// records");
	// return lifeTimeData;
	// }

	public static List<String> readXueQiuSymbolList(String type) {
		String sql = "SELECT DISTINCT Symbol FROM history WHERE source='XueQiu' and ADJTYPE = '" + type + "'";
		ResultSet results = DBService.executeQuery(sql);
		List<String> symbols = new ArrayList<String>();
		try {
			while (results.next()) {
				symbols.add(results.getString("Symbol"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Read " + symbols.size() + " symbols!");
		// List<String> symbols = new ArrayList<String>();
		// symbols.add("SH600006");
		// symbols.add("SH600011");
		// symbols.add("SH600017");
		// symbols.add("SH600019");
		return symbols;
	}

	public static List<OneDayPrice> readXueQiuBySymbol(String symbol, String type) {
		List<OneDayPrice> lifeTimeData = new ArrayList<OneDayPrice>();
		String sql = "SELECT * FROM history WHERE source='XueQiu' and ADJTYPE = '" + type + "' and Symbol='" + symbol
				+ "' ORDER BY RIQI";
		ResultSet results = DBService.executeQuery(sql);
		try {
			while (results.next()) {
				OneDayPrice oneDayData = new OneDayPrice();
				oneDayData.setId(results.getInt("ID"));
				oneDayData.setStock(new Stock(results.getString("SYMBOL")));
				oneDayData.setOpen(results.getDouble("OPEN"));
				oneDayData.setHigh(results.getDouble("HIGH"));
				oneDayData.setLow(results.getDouble("LOW"));
				oneDayData.setClose(results.getDouble("CLOSE"));
				oneDayData.setVolume(results.getLong("VOLUME"));
				oneDayData.setAdjType(results.getString("ADJTYPE"));
				oneDayData.setRiqi(results.getDate("RIQI"));
				oneDayData.setGrowth(results.getDouble("GROWTH"));
				oneDayData.setSource(results.getString("SOURCE"));
				oneDayData.setAddedTime(results.getDate("ADDEDTIME"));
				oneDayData.setCHANGEPRICE(results.getDouble("CHANGEPRICE"));
				oneDayData.setCHANGEPERCENT(results.getDouble("CHANGEPERCENT"));
				oneDayData.setHUANSHOULV(results.getDouble("HUANSHOULV"));
				oneDayData.setMA5(results.getDouble("MA5"));
				oneDayData.setMA10(results.getDouble("MA10"));
				oneDayData.setMA20(results.getDouble("MA20"));
				oneDayData.setMA30(results.getDouble("MA30"));
				oneDayData.setDIF(results.getDouble("DIF"));
				oneDayData.setDEA(results.getDouble("DEA"));
				oneDayData.setMACD(results.getDouble("MACD"));
				oneDayData.setCHENGJIAOZHI(results.getDouble("CHENGJIAOZHI"));
				lifeTimeData.add(oneDayData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Symbol " + symbol + " has " + lifeTimeData.size() + " records");
		return lifeTimeData;
	}
}
