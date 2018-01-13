package com.stocks.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stocks.common.CONSTANT;

public class AfterXDaysJiHe {
	private static final Logger LOGGER = Logger.getLogger(AfterXDaysJiHe.class);

	private List<OneDayPrice> range = new ArrayList<OneDayPrice>();

	public void add(OneDayPrice oneDayPrice) {
		range.add(oneDayPrice);
	}

	public int size() {
		return range.size();
	}

	public Double getTheRateGrowthHigherThan(Double baseRate) {
		Double currentLength = 0.0;
		if (range == null || range.size() == 0) {
			return 0.0;
		}

		for (OneDayPrice oneDayPrice : range) {
			if (oneDayPrice.getGrowth() >= baseRate) {
				currentLength++;
			}
		}
		LOGGER.info("Total:" + range.size() + " currentLength:" + currentLength);
		return currentLength / new Double(range.size());
	}

	public Rate getTheRateGrowthHigherThan(Double expectedRate, String currentType, String source, Double lowestRate,
			int continousDay) {
		Rate rate = new Rate();
		int currentLength = 0;
		if (range == null || range.size() == 0) {
			return rate;
		}
		for (OneDayPrice oneDayPrice : range) {
			if (oneDayPrice.getGrowth() >= expectedRate) {
				currentLength++;
			}
		}
		rate.setHitSize(currentLength);
		rate.setTotal(range.size());
		rate.setExpectedRate(expectedRate);
		rate.setCurrentType(currentType);
		rate.setSource(source);
		rate.setMarket(CONSTANT.DATA_MARKET);
		rate.setLowestRate(lowestRate);
		rate.setContinousDay(continousDay);
		rate.setHitRate(new Double(currentLength) / new Double(range.size()));
		return rate;
	}
}
