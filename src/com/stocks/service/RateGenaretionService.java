package com.stocks.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.stocks.vo.AfterXDaysJiHe;
import com.stocks.vo.LianXuQuJian;

public class RateGenaretionService {
	private static final Logger LOGGER = Logger.getLogger(RateGenaretionService.class);

	/**
	 * 连续出现X天第后X+1天的数据
	 * 
	 * @param 满足连续出现的所有range
	 * @param 连续出现的天数
	 * @return
	 */
	public static AfterXDaysJiHe getOneDayPriceAfterXContinousIntervalDay(List<LianXuQuJian> ranges, int Xday) {
		AfterXDaysJiHe results = new AfterXDaysJiHe();
		for (LianXuQuJian currentRange : ranges) {
			if (currentRange.getLength() >= Xday) {
				results.add(currentRange.getRateOfSpecificDay(Xday + 1));
			}
		}
		return results;
	}
}
