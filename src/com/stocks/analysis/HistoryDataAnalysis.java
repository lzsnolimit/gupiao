package com.stocks.analysis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.stocks.common.CONSTANT;
import com.stocks.service.ContinuousIntervalRangeService;
import com.stocks.service.RateGenaretionService;
import com.stocks.service.read.ReadOneDayDetailsTableFromFiles;
import com.stocks.service.write.XueQiuOneDayRateToDB;
import com.stocks.vo.AfterXDaysJiHe;
import com.stocks.vo.LianXuQuJian;
import com.stocks.vo.OneDayPrice;
import com.stocks.vo.Rate;

public class HistoryDataAnalysis {

	public static String startDate;
	public static String endDate;

	public static Map<String, List<OneDayPrice>> read(String currentType, String start, String end) {
		List<String> symbols = ReadOneDayDetailsTableFromFiles.readXueQiuSymbolList(currentType);
		Map<String, List<OneDayPrice>> allStocks = new HashMap<>();
		for (String symbol : symbols) {
			// List<OneDayPrice> stockLifePrices =
			// ReadOneDayDetailsTableFromFiles.readAllXueQiuBySymbol(symbol,
			// currentType);
			List<String> dataTypes = new ArrayList<>();
			dataTypes.add("growth");
			List<OneDayPrice> stockLifePrices = ReadOneDayDetailsTableFromFiles.readXueQiuBySymbol(symbol, currentType,
					dataTypes, new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
			allStocks.put(symbol, stockLifePrices);
		}
		// LOGGER.info(allStocks.size() + " loaded!");
		return allStocks;
	}

	private static final Logger LOGGER = Logger.getLogger(HistoryDataAnalysis.class);

	public static void main(String[] args) throws ParseException {

		startDate = args[0];
		endDate = args[1];
		CONSTANT.DATA_MARKET = args[2];

		final Double highestGrowth = 1.1;
		final int minContinousDays = 4;
		String[] currentTypes = { "normal", "after", "before" };
		List<Double> lowestRates = new ArrayList<>();
		List<Double> expectedRates = new ArrayList<>();
		List<Integer> continousDays = new ArrayList<>();
		for (Double i = 20.0; i <= 112.0; i++) {
			lowestRates.add(i / 1000.0);
		}
		for (Double i = 20.0; i <= 112.0; i++) {
			expectedRates.add(i / 1000.0);
		}
		for (int i = minContinousDays; i < 20; i++) {
			continousDays.add(i);
		}

		List<String> dataTypes = new ArrayList<>();
		dataTypes.add("growth");

		for (String currentType : currentTypes) {
			// 读取数据
			Map<String, List<OneDayPrice>> allStocks = ContinuousIntervalRangeService.readHis(currentType, startDate,
					endDate, dataTypes);
			// 分析数据

			for (Double lowestRate : lowestRates) {
				List<LianXuQuJian> results = new LinkedList<>();
				for (String symbol : allStocks.keySet()) {
					List<OneDayPrice> stockLifePrices = allStocks.get(symbol);
					// LOGGER.info("stockLifePrices size
					// "+stockLifePrices.size());
					// LOGGER.info("highestGrowth "+highestGrowth+" lowestRate
					// "+lowestRate);
					List<LianXuQuJian> pricesRanges = ContinuousIntervalRangeService
							.getAllContinuousIntervalRang(stockLifePrices, minContinousDays, highestGrowth, lowestRate);
					// LOGGER.info("pricesRanges size "+pricesRanges.size());
					results.addAll(pricesRanges);
				}

				// LOGGER.info("results size "+results.size());
				if (results != null) {
					for (Double expectedRate : expectedRates) {
						for (int continousDay : continousDays) {

							AfterXDaysJiHe jiHe = RateGenaretionService
									.getOneDayPriceAfterXContinousIntervalDay(results, continousDay);

							if (jiHe.size() > 10) {
								// LOGGER.info(jiHe.size());
								Rate rate = jiHe.getTheRateGrowthHigherThan(expectedRate, currentType,
										CONSTANT.DATA_SOURCE, lowestRate, continousDay);
								rate.setStartPeriod(startDate);
								rate.setEndPeriod(endDate);
								XueQiuOneDayRateToDB.writeToOracle(rate);
								// LOGGER.info("连续天数：" + rate.getContinousDay()
								// + " 期待的概率：" + rate.getExpectedRate()
								// + " 区间最低概率：" + rate.getLowestRate() + " 概率："
								// + rate.getHitRate());
							}

						}
					}
				}
			}
		}

	}
}
