package com.stocks.service.read;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.stocks.common.CONSTANT;
import com.stocks.service.DateService;
import com.stocks.service.IO.FileService;
import com.stocks.vo.OneDayPrice;
import com.stocks.vo.Stock;

public class ReadOneDayDetailsTableFromFiles {
	private static final Logger LOGGER = Logger.getLogger(ReadOneDayDetailsTableFromFiles.class);

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

		List<String> symbols = new ArrayList<>();
		String fileNames[] = FileService.readDirectory(CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH + type);
		if (fileNames == null) {
			return symbols;
		}
		for (String filename : fileNames) {
			String symbol = filename.replace(".txt", "");
			symbols.add(symbol);
		}

		// symbols.add("SH600006");
		// symbols.add("SH600011");
		// symbols.add("SH600017");
		// symbols.add("SH600019");
		return symbols;
	}

	/**
	 * 读取全部数据
	 * 
	 * @param symbol
	 * @param type
	 * @return
	 */

	public static List<OneDayPrice> readAllXueQiuBySymbol(String symbol, String type) {
		List<OneDayPrice> lifeTimeData = new ArrayList<OneDayPrice>();
		JSONObject historyPriceDetail = FileService
				.readJsonObject(CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH + type + "/" + symbol + ".txt");
		if (historyPriceDetail == null || historyPriceDetail.getJSONArray("chartlist").length() == 0) {
			return lifeTimeData;
		}
		JSONArray daysPriceDetail = historyPriceDetail.getJSONArray("chartlist");
		for (int i = 0; i < daysPriceDetail.length() - 1; i++) {
			OneDayPrice oneDayData = new OneDayPrice();
			Date riqi = new Date(daysPriceDetail.getJSONObject(i).getLong("timestamp"));
			Long volume = daysPriceDetail.getJSONObject(i).getLong("volume") * 100;
			Double open = daysPriceDetail.getJSONObject(i).getDouble("open");
			Double high = daysPriceDetail.getJSONObject(i).getDouble("high");
			Double close = daysPriceDetail.getJSONObject(i).getDouble("close");
			Double low = daysPriceDetail.getJSONObject(i).getDouble("low");
			Double changePrice = daysPriceDetail.getJSONObject(i).getDouble("chg");
			Double changePercent = daysPriceDetail.getJSONObject(i).getDouble("percent");
			Double huanshoulv = daysPriceDetail.getJSONObject(i).getDouble("turnrate");
			Double ma5 = daysPriceDetail.getJSONObject(i).getDouble("ma5");
			Double ma10 = daysPriceDetail.getJSONObject(i).getDouble("ma10");
			Double ma20 = daysPriceDetail.getJSONObject(i).getDouble("ma20");
			Double ma30 = daysPriceDetail.getJSONObject(i).getDouble("ma30");
			Double dif = daysPriceDetail.getJSONObject(i).getDouble("dif");
			Double dea = daysPriceDetail.getJSONObject(i).getDouble("dea");
			Double macd = daysPriceDetail.getJSONObject(i).getDouble("macd");
			Double chengjiaozhi = daysPriceDetail.getJSONObject(i).getDouble("lot_volume");
			Double lastClose = 0.0;
			try {
				lastClose = daysPriceDetail.getJSONObject(i - 1).getDouble("close");
			} catch (Exception e) {
				lastClose = 0.0;
			}
			Double growth = 0.0;
			if (lastClose != 0) {
				growth = high / lastClose - 1;
			}
			oneDayData.setStock(new Stock(symbol));
			oneDayData.setOpen(open);
			oneDayData.setHigh(high);
			oneDayData.setLow(low);
			oneDayData.setClose(close);
			oneDayData.setVolume(volume);
			oneDayData.setAdjType(type);
			oneDayData.setRiqi(riqi);
			oneDayData.setGrowth(growth);
			oneDayData.setSource("XueQiu");
			oneDayData.setCHANGEPRICE(changePrice);
			oneDayData.setCHANGEPERCENT(changePercent);
			oneDayData.setHUANSHOULV(huanshoulv);
			oneDayData.setMA5(ma5);
			oneDayData.setMA10(ma10);
			oneDayData.setMA20(ma20);
			oneDayData.setMA30(ma30);
			oneDayData.setDIF(dif);
			oneDayData.setDEA(dea);
			oneDayData.setMACD(macd);
			oneDayData.setCHENGJIAOZHI(chengjiaozhi);
			lifeTimeData.add(oneDayData);
		}
		// LOGGER.info("Symbol " + symbol + " has " + lifeTimeData.size() + " records");
		return lifeTimeData;
	}

	/**
	 * 读取部分指定数据
	 * 
	 * @param symbol
	 * @param market_type
	 * @param types
	 * @param startDate
	 * @param endDate
	 * @return
	 */

	public static List<OneDayPrice> readXueQiuBySymbol(String symbol, String market_type, List<String> types,
			Date startDate, Date endDate) {
		List<OneDayPrice> lifeTimeData = new ArrayList<OneDayPrice>();
		JSONObject historyPriceDetail = FileService
				.readJsonObject(CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH + market_type + "/" + symbol + ".txt");
		if (historyPriceDetail == null || historyPriceDetail.getJSONArray("chartlist").length() == 0) {
			return lifeTimeData;
		}
		JSONArray daysPriceDetail = historyPriceDetail.getJSONArray("chartlist");
		for (int i = 0; i < daysPriceDetail.length() - 1; i++) {
			OneDayPrice oneDayData = new OneDayPrice();
			String riqiStr = DateService.getTime("yyyy-MM-dd", daysPriceDetail.getJSONObject(i).getLong("timestamp"),
					market_type);
			Date riqi=new Date();
			try {
				riqi = new SimpleDateFormat("yyyy-MM-dd").parse(riqiStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (riqi.after(endDate) || riqi.before(startDate)) {
				continue;
			}
			Long volume = daysPriceDetail.getJSONObject(i).getLong("volume") * 100;
			Double open = daysPriceDetail.getJSONObject(i).getDouble("open");
			Double high = daysPriceDetail.getJSONObject(i).getDouble("high");
			Double close = daysPriceDetail.getJSONObject(i).getDouble("close");
			Double low = daysPriceDetail.getJSONObject(i).getDouble("low");
			Double changePrice = daysPriceDetail.getJSONObject(i).getDouble("chg");
			Double changePercent = daysPriceDetail.getJSONObject(i).getDouble("percent");
			Double huanshoulv = daysPriceDetail.getJSONObject(i).getDouble("turnrate");
			Double ma5 = daysPriceDetail.getJSONObject(i).getDouble("ma5");
			Double ma10 = daysPriceDetail.getJSONObject(i).getDouble("ma10");
			Double ma20 = daysPriceDetail.getJSONObject(i).getDouble("ma20");
			Double ma30 = daysPriceDetail.getJSONObject(i).getDouble("ma30");
			Double dif = daysPriceDetail.getJSONObject(i).getDouble("dif");
			Double dea = daysPriceDetail.getJSONObject(i).getDouble("dea");
			Double macd = daysPriceDetail.getJSONObject(i).getDouble("macd");
			Double chengjiaozhi = daysPriceDetail.getJSONObject(i).getDouble("lot_volume");
			Double lastClose = 0.0;

			try {
				lastClose = daysPriceDetail.getJSONObject(i - 1).getDouble("close");
			} catch (Exception e) {
				lastClose = 0.0;
			}
			Double growth = 0.0;
			if (lastClose != 0) {
				growth = high / lastClose - 1;
			}

			oneDayData.setStock(new Stock(symbol));
			if (types.contains("open")) {
				oneDayData.setOpen(open);
			}
			if (types.contains("high")) {
				oneDayData.setHigh(high);
			}
			if (types.contains("low")) {
				oneDayData.setLow(low);
			}
			if (types.contains("close")) {
				oneDayData.setClose(close);
			}
			if (types.contains("volume")) {
				oneDayData.setVolume(volume);
			}
			if (types.contains("type")) {
				oneDayData.setAdjType(market_type);
			}
			if (types.contains("riqi")) {
				oneDayData.setRiqi(riqi);
			}
			if (types.contains("growth")) {
				oneDayData.setGrowth(growth);
			}
			if (types.contains("source")) {
				oneDayData.setSource("XueQiu");
			}
			if (types.contains("changePrice")) {
				oneDayData.setCHANGEPRICE(changePrice);
			}
			if (types.contains("changePercent")) {
				oneDayData.setCHANGEPERCENT(changePercent);
			}
			if (types.contains("huanshoulv")) {
				oneDayData.setHUANSHOULV(huanshoulv);
			}
			if (types.contains("ma5")) {
				oneDayData.setMA5(ma5);
			}
			if (types.contains("oneDayData.setMA10(ma10);")) {
				oneDayData.setMA10(ma10);
			}
			if (types.contains("ma20")) {
				oneDayData.setMA20(ma20);
			}

			if (types.contains("ma30")) {
				oneDayData.setMA30(ma30);
			}
			if (types.contains("dif")) {
				oneDayData.setDIF(dif);
			}
			if (types.contains("dea")) {
				oneDayData.setDEA(dea);
			}
			if (types.contains("macd")) {
				oneDayData.setMACD(macd);
			}

			if (types.contains("chengjiaozhi")) {
				oneDayData.setCHENGJIAOZHI(chengjiaozhi);
			}

			lifeTimeData.add(oneDayData);
		}
		// LOGGER.info("Symbol " + symbol + " has " + lifeTimeData.size() + " records");
		return lifeTimeData;
	}

}
