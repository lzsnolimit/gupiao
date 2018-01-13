package com.pachong.main;

import com.pachong.vo.XueQiuTodayDetailsPaChong;
import com.pachong.vo.XueQiuTodaySummarizedPaChong;
import com.stocks.common.CONSTANT;

public class XueQiuToday {
	public static void main(String[] args) {
		
		try {
			CONSTANT.DATA_MARKET=args[0];
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("kaishi today pa chong! "+CONSTANT.DATA_MARKET);
		
		XueQiuTodayDetailsPaChong detailsPaChong = new XueQiuTodayDetailsPaChong();
		detailsPaChong.initial();
		detailsPaChong.start();

		XueQiuTodaySummarizedPaChong summarizedPaChong = new XueQiuTodaySummarizedPaChong();
		summarizedPaChong.initial();
		summarizedPaChong.start();
	}
}
