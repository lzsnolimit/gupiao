package com.stocks.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.stocks.service.read.ReadOneDayDetailsTableFromFiles;
import com.stocks.vo.LianXuQuJian;
import com.stocks.vo.OneDayPrice;

/**
 * @author Zhongshan Lu
 *
 */
public class ContinuousIntervalRangeService {
	private static final Logger LOGGER = Logger.getLogger(ContinuousIntervalRangeService.class);

	/**
	 * 获取一个区间 每天股价涨幅都在一个涨幅区间内
	 * 
	 * @param stockPrices
	 *            原始数据
	 * @param MiniRangeLength
	 *            最短区间长度
	 * @param highPoint
	 *            区间内涨幅最高
	 * @param lowPoint
	 *            区间内涨幅最低
	 * @return 满足条件的所有区间+前后1天股票数据
	 */
	public static List<LianXuQuJian> getAllContinuousIntervalRang(List<OneDayPrice> stockLifePrices,
			int MiniRangeLength, Double highPoint, Double lowPoint) {
		List<LianXuQuJian> CIrange = new ArrayList<LianXuQuJian>();
		int currentLength = 0;
		List<OneDayPrice> temRange = new ArrayList<OneDayPrice>();
		for (int i = 1; i < stockLifePrices.size() - 1; i++) {
			Double growth = Double.valueOf(stockLifePrices.get(i).getGrowth());
			if (growth >= lowPoint && growth <= highPoint) {

				if (currentLength == 0) {
					temRange.add(stockLifePrices.get(i - 1));
				}

				currentLength++;
				temRange.add(stockLifePrices.get(i));
			} else {
				// System.out.println(currentLength);
				if (currentLength >= MiniRangeLength) {
					temRange.add(stockLifePrices.get(i));
					CIrange.add(new LianXuQuJian(temRange));
				}
				currentLength = 0;
				temRange = new ArrayList<OneDayPrice>();
			}
		}
		// System.out.println("Get size:"+CIrange.size());
		return CIrange;
	}

	/**
	 * 按连续区间长度对结果分类
	 * 
	 * @param stocks
	 * @return
	 */
	public static Map<Integer, List<LianXuQuJian>> classifyByLength(List<LianXuQuJian> stocks) {
		Map<Integer, List<LianXuQuJian>> results = new HashMap<Integer, List<LianXuQuJian>>();
		for (LianXuQuJian range : stocks) {
			int length = range.getLength();
			if (results.containsKey(length)) {
				results.get(length).add(range);
			} else {
				results.put(length, new ArrayList<LianXuQuJian>());
				results.get(length).add(range);
			}
		}
		return results;
	}

	/**
	 * 读取历史数据
	 * 
	 * @param currentType
	 *            <'normal','before','after'>
	 * @param startDate
	 *            like 20170101
	 * @param endDate
	 *            like 20180101
	 * @param dataTypes
	 * @return
	 */

	public static Map<String, List<OneDayPrice>> readHis(String currentType, String startDate, String endDate,
			List<String> dataTypes) {
		List<String> symbols = ReadOneDayDetailsTableFromFiles.readXueQiuSymbolList(currentType);
		System.out.println(symbols.size());
		Map<String, List<OneDayPrice>> allStocks = new HashMap<>();

		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		Date start = new Date(Long.MIN_VALUE);
		Date end = new Date(Long.MAX_VALUE);
		try {
			start = formater.parse(startDate);
		} catch (ParseException e) {
		}
		try {
			end = formater.parse(endDate);
		} catch (ParseException e) {
		}

		for (String symbol : symbols) {
			List<OneDayPrice> stockLifePrices = ReadOneDayDetailsTableFromFiles.readXueQiuBySymbol(symbol, currentType,
					dataTypes, start, end);
			allStocks.put(symbol, stockLifePrices);
		}
		LOGGER.info(allStocks.size() + " loaded!");
		return allStocks;
	}

	/**
	 * Caculate rates
	 * 
	 * @param oneDayPrices
	 * @param currentType
	 * @param symbol
	 * @param predictDate
	 */

	public static void caculateRateBySymbol(List<OneDayPrice> oneDayPrices, String currentType, String symbol,
			Date predictDate) {
		// List<OneDayPrice> oneDayPrices=stocks.get(symbol);
		int position = -1;
		System.out.println(oneDayPrices.size());
		for (int i = 0; i < oneDayPrices.size(); i++) {
			if (oneDayPrices.get(i).getRiqi().equals(predictDate)) {
				position = i;
				break;
			}
		}
		if (position == -1) {
			System.out.println("Didn't find predict date " + predictDate + " in " + symbol);
			return;
		}

		List<OneDayPrice> subList = new ArrayList<>();
		if (position >= 10) {
			subList = oneDayPrices.subList(position - 10, position + 1);
		} else {
			subList = oneDayPrices.subList(0, position + 1);
		}

		for (Double lowest = 0.02; lowest < 0.101; lowest = lowest + 0.001) {
			int length = getLengthByLowestGroeth(lowest, subList);
			System.out.println("Symbol:" + symbol + " Lowest:" + lowest + " Length:" + length + " Predict date"
					+ predictDate.toString());
		}

	}

	public static int getLengthByLowestGroeth(Double lowest, List<OneDayPrice> subList) {
		int length = 0;
		while ((subList.size() - length - 1 >= 0) && (subList.get(subList.size() - length - 1).getGrowth() >= lowest)) {
			length++;
		}
		return length;
	}
}