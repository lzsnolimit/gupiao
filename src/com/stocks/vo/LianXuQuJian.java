package com.stocks.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 连续区间
 * 
 * @author SDSA
 *
 */
/**
 * @author SDSA
 *
 */
public class LianXuQuJian{
	private static final Logger LOGGER = Logger.getLogger(LianXuQuJian.class);

	private List<OneDayPrice> range = new ArrayList<OneDayPrice>();
	private int length;
	private Double lowestRate;
	private Double highestRate;
	private Double averagRate;

	public LianXuQuJian() {
	}

	public LianXuQuJian(List<OneDayPrice> range) {
		this.range = range;
		refresh();
	}

	public List<OneDayPrice> getRange() {
		return range;
	}

	public void setRange(List<OneDayPrice> range) {
		this.range = range;
		refresh();
	}

	public int getLength() {
		return length;
	}

	public void setLength() {
		this.length = range.size() - 2;
	}

	public Double getLowestRate() {
		return lowestRate;
	}

	public void setLowestRate() {
		Double lowestRate = Double.MAX_VALUE;
		for (int i = 1; i < range.size() - 1; i++) {
			if (range.get(i).getGrowth() <= lowestRate) {
				lowestRate = range.get(i).getGrowth();
			}
		}
		this.lowestRate = lowestRate;
	}

	public Double getHighestRate() {
		return highestRate;
	}

	public void setHighestRate() {
		Double highestRate = Double.MIN_VALUE;
		for (int i = 1; i < range.size() - 1; i++) {
			if (range.get(i).getGrowth() >= highestRate) {
				highestRate = range.get(i).getGrowth();
			}
		}
		this.highestRate = highestRate;
	}

	public Double getAveragRate() {
		return averagRate;
	}

	public void setAveragRate() {
		Double totalRate = 0.0;
		for (int i = 1; i < range.size() - 1; i++) {
			totalRate += range.get(i).getGrowth();
		}
		this.averagRate = totalRate / (range.size() - 2);
	}

	public void refresh() {
		setLength();
		setAveragRate();
		setHighestRate();
		setLowestRate();
	}

	/**
	 * @param highPoint
	 *            区间内涨幅最高
	 * @param lowPoint
	 *            区间内涨幅最低
	 * @return
	 */
	public boolean isContinuousIntervalRang(Double highPoint, Double lowPoint) {
		if (highPoint <= highestRate && lowPoint >= lowestRate) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ContinuousIntervalRange [range=" + range + ", length=" + length + ", lowestRate=" + lowestRate
				+ ", highestRate=" + highestRate + ", averagRate=" + averagRate + "]";
	}

	public void print() {
		LOGGER.info("Ranges:");
		for (OneDayPrice oneDayPrice : range) {
			LOGGER.info("Date:" + oneDayPrice.getRiqi() + "   Growth:" + oneDayPrice.getGrowth());
		}
		LOGGER.info("Length=" + length);
		LOGGER.info("LowestRate=" + lowestRate);
		LOGGER.info("HighestRate=" + highestRate);
		LOGGER.info("AveragRate=" + averagRate);
	}

	/**
	 * Get one day price of specific day.
	 * @param day
	 * @return return null if this day doesn't exist
	 */
	public OneDayPrice getRateOfSpecificDay(int day) {
		try {
			return range.get(day);
		} catch (Exception e) {
			return null;
		}
	}
}
