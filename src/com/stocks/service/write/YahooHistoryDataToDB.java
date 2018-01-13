package com.stocks.service.write;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import com.stocks.common.CONSTANT;
import com.stocks.service.IO.DBService;
import com.stocks.service.IO.FileService;

/**
 * Hello world!
 *
 */
public class YahooHistoryDataToDB {
	private static final Logger LOGGER = Logger.getLogger(YahooHistoryDataToDB.class);
	static {
		DBService.initial();
	}

	public static String dataSource = "Yahoo";

	public static void loadYahooDataFromFiles() {
		String fileNames[] = FileService.readDirectory(CONSTANT.HISTORY.DATA_PATH);
		for (String filename : fileNames) {
			String symbol = filename.replace(".csv", "");
			List<String[]> oneSymbol = FileService.readCSV(true, CONSTANT.HISTORY.DATA_PATH + filename, ",");

			for (int i = 0; i < oneSymbol.size() - 1; i++) {
				// double rate=
				Float curentHighPoint = Float.valueOf(oneSymbol.get(i)[2]);
				Float lastDayClose = Float.valueOf(oneSymbol.get(i + 1)[4]);
				Float grwoth = curentHighPoint / lastDayClose - 1;
				String sql = "INSERT INTO `history` (`Symbol`,`Date`, `Open`, `High`, `Low`, `Close`, `Volume`, `AdjClose`, `growth`, `source`) "
						+ "VALUES ('" + symbol + "', '" + oneSymbol.get(i)[0] + "', '" + oneSymbol.get(i)[1] + "', '"
						+ oneSymbol.get(i)[2] + "', '" + oneSymbol.get(i)[3] + "', '" + oneSymbol.get(i)[4] + "', '"
						+ oneSymbol.get(i)[5] + "', '" + oneSymbol.get(i)[6] + "', '" + grwoth + "', '" + dataSource
						+ "')";
				DBService.executeInsert(sql);
			}

			String sql = "INSERT INTO `history` (`Symbol`,`Date`, `Open`, `High`, `Low`, `Close`, `Volume`, `AdjClose`, `growth`, `source`) "
					+ "VALUES ('" + symbol + "', '" + oneSymbol.get(oneSymbol.size() - 1)[0] + "', '"
					+ oneSymbol.get(oneSymbol.size() - 1)[1] + "', '" + oneSymbol.get(oneSymbol.size() - 1)[2] + "', '"
					+ oneSymbol.get(oneSymbol.size() - 1)[3] + "', '" + oneSymbol.get(oneSymbol.size() - 1)[4] + "', '"
					+ oneSymbol.get(oneSymbol.size() - 1)[5] + "', '" + oneSymbol.get(oneSymbol.size() - 1)[6] + "', '"
					+ 0 + "', '" + dataSource + "')";
			DBService.executeInsert(sql);

		}
	}

	public static void main(String[] args) throws ParseException {
		loadYahooDataFromFiles();
	}
}