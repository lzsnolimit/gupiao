package com.stocks.predict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stocks.service.ContinuousIntervalRangeService;
import com.stocks.vo.OneDayPrice;

public class nextdayHighPointPredict {
	public static void main(String[] arg) throws ParseException {
		List<String> dataTypes = new ArrayList<>();
		dataTypes.add("growth");
		dataTypes.add("high");
		dataTypes.add("riqi");
		Map<String, List<OneDayPrice>> allStocks = ContinuousIntervalRangeService.readHis("normal", "20170101", "20180101", dataTypes);
		for (String symbol : allStocks.keySet()) {
			Date predictDate=new SimpleDateFormat("yyyyMMdd").parse("20170302");
			ContinuousIntervalRangeService.caculateRateBySymbol(allStocks.get(symbol), "normal", symbol, predictDate);
		}
	}
}
