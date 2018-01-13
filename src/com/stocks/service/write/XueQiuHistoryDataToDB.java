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
public class XueQiuHistoryDataToDB {
	private static final Logger LOGGER = Logger.getLogger(XueQiuHistoryDataToDB.class);
	static {
		DBService.initial();
	}
	public static String dataSource = "XueQiu";

	public static void loadXueQiuDataFromFiles() {
		String[] types = { "before", "normal", "after" };
		for (String type : types) {
			String fileNames[] = FileService.readDirectory(CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH + type);
			for (String filename : fileNames) {
				String symbol = filename.replace(".txt", "");
				JSONObject historyPriceDetail = FileService
						.readJsonObject(CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH + type + "/" + filename);

				if (historyPriceDetail == null || historyPriceDetail.getJSONArray("chartlist").length() == 0) {
					continue;
				}
				JSONArray daysPriceDetail = historyPriceDetail.getJSONArray("chartlist");

				// String sql = "INSERT INTO `history` (`Symbol`,`Date`, `Open`, `High`, `Low`,
				// `Close`, `Volume`, `AdjClose`, `growth`, `source`) "
				// + "VALUES ('" + symbol + "', '" + date + "', '"
				// + oneSymbol.get(oneSymbol.size() - 1)[1] + "', '" +
				// oneSymbol.get(oneSymbol.size() - 1)[2]
				// + "', '" + oneSymbol.get(oneSymbol.size() - 1)[3] + "', '"
				// + oneSymbol.get(oneSymbol.size() - 1)[4] + "', '" +
				// oneSymbol.get(oneSymbol.size() - 1)[5]
				// + "', '" + oneSymbol.get(oneSymbol.size() - 1)[6] + "', '" + 0 + "', '" +
				// dataSource + "')";
				{
					Timestamp timestamp = new Timestamp(daysPriceDetail.getJSONObject(0).getLong("timestamp"));
					String date = "'" + new Date(timestamp.getTime()) + "'";
					if (CONSTANT.DB.CONNECTION_TYPE.equals("Oracle")) {
						date = "TO_DATE('" + new Date(timestamp.getTime()) + "', 'yyyy-mm-dd')";
					}

					Double volume = daysPriceDetail.getJSONObject(0).getDouble("volume") * 100;
					Double open = daysPriceDetail.getJSONObject(0).getDouble("open");
					Double high = daysPriceDetail.getJSONObject(0).getDouble("high");
					Double close = daysPriceDetail.getJSONObject(0).getDouble("close");
					Double low = daysPriceDetail.getJSONObject(0).getDouble("low");
					Double changePrice = daysPriceDetail.getJSONObject(0).getDouble("chg");
					Double changePercent = daysPriceDetail.getJSONObject(0).getDouble("percent");
					Double huanshoulv = daysPriceDetail.getJSONObject(0).getDouble("turnrate");
					Double ma5 = daysPriceDetail.getJSONObject(0).getDouble("ma5");
					Double ma10 = daysPriceDetail.getJSONObject(0).getDouble("ma10");
					Double ma20 = daysPriceDetail.getJSONObject(0).getDouble("ma20");
					Double ma30 = daysPriceDetail.getJSONObject(0).getDouble("ma30");
					Double dif = daysPriceDetail.getJSONObject(0).getDouble("dif");
					Double dea = daysPriceDetail.getJSONObject(0).getDouble("dea");
					Double macd = daysPriceDetail.getJSONObject(0).getDouble("macd");
					Double chengjiaozhi = daysPriceDetail.getJSONObject(0).getDouble("lot_volume");
					String sql = "INSERT INTO history (Symbol, Open, High, Low, Close, Volume, AdjType, RIQI, source, growth, AddedTime, changePrice, changePercent, huanshoulv, ma5, ma10,ma20, ma30, dif, dea, macd, chengjiaozhi) "
							+ "VALUES ('" + symbol + "', '" + open + "', '" + high + "', '" + low + "', '" + close
							+ "', '" + volume + "', '" + type + "', " + date + ", '" + dataSource + "', '" + 0
							+ "', CURRENT_TIMESTAMP, '" + changePrice + "', '" + changePercent + "', '" + huanshoulv
							+ "', '" + ma5 + "', '" + ma10 + "', '" + ma20 + "', '" + ma30 + "', '" + dif + "', '" + dea
							+ "', '" + macd + "', '" + chengjiaozhi + "')";
					DBService.executeInsert(sql);
				}

				for (int i = 1; i < daysPriceDetail.length() - 1; i++) {
					// double rate=
					Timestamp timestamp = new Timestamp(daysPriceDetail.getJSONObject(i).getLong("timestamp"));
					String date = "'" + new Date(timestamp.getTime()) + "'";
					if (CONSTANT.DB.CONNECTION_TYPE.equals("Oracle")) {
						date = "TO_DATE('" + new Date(timestamp.getTime()) + "', 'yyyy-mm-dd')";
					}

					Double volume = daysPriceDetail.getJSONObject(i).getDouble("volume") * 100;
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
					Double lastClose = daysPriceDetail.getJSONObject(i - 1).getDouble("close");
					Double growth = 0.0;
					if (lastClose != 0) {
						growth = high / lastClose - 1;
					}
					String sql = "INSERT INTO history (Symbol, Open, High, Low, Close, Volume, AdjType, RIQI, source, growth, AddedTime, changePrice, changePercent, huanshoulv, ma5, ma10,ma20, ma30, dif, dea, macd, chengjiaozhi) "
							+ "VALUES ('" + symbol + "', '" + open + "', '" + high + "', '" + low + "', '" + close
							+ "', '" + volume + "', '" + type + "', " + date + ", '" + dataSource + "', '" + growth
							+ "', CURRENT_TIMESTAMP, '" + changePrice + "', '" + changePercent + "', '" + huanshoulv
							+ "', '" + ma5 + "', '" + ma10 + "', '" + ma20 + "', '" + ma30 + "', '" + dif + "', '" + dea
							+ "', '" + macd + "', '" + chengjiaozhi + "')";
					DBService.executeInsert(sql);
				}

			}
		}
	}

	public static void main(String[] args) throws ParseException {
		loadXueQiuDataFromFiles();
	}
}