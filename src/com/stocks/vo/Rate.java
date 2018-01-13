package com.stocks.vo;

import org.apache.log4j.Logger;

import com.stocks.common.CONSTANT;

public class Rate {
	private int total = 0;
	private int HitSize = 0;
	private Double expectedRate = 0.0;
	private Double hitRate = 0.0;
	private String currentType;
	private String source = CONSTANT.DATA_SOURCE;
	private Double lowestRate = 0.0;
	private int continousDay = 0;
	private String market;
	private String startPeriod;
	private String endPeriod;
	
	
	
	private static final Logger LOGGER = Logger.getLogger(Rate.class);

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the hitSize
	 */
	public int getHitSize() {
		return HitSize;
	}

	/**
	 * @param hitSize
	 *            the hitSize to set
	 */
	public void setHitSize(int hitSize) {
		HitSize = hitSize;
	}

	/**
	 * @return the hitRate
	 */
	public Double getHitRate() {
		return hitRate;
	}

	/**
	 * @param hitRate
	 *            the hitRate to set
	 */
	public void setHitRate(Double rate) {
		this.hitRate = rate;
	}

	/**
	 * @return the currentTypes
	 */
	public String getCurrentType() {
		return currentType;
	}

	/**
	 * @param currentTypes
	 *            the currentTypes to set
	 */
	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the lowestRate
	 */
	public Double getLowestRate() {
		return lowestRate;
	}

	/**
	 * @param lowestRate
	 *            the lowestRate to set
	 */
	public void setLowestRate(Double lowestRate) {
		this.lowestRate = lowestRate;
	}

	/**
	 * @return the continousDay
	 */
	public int getContinousDay() {
		return continousDay;
	}

	/**
	 * @param continousDay
	 *            the continousDay to set
	 */
	public void setContinousDay(int continousDay) {
		this.continousDay = continousDay;
	}

	/**
	 * @return the expectedRate
	 */
	public Double getExpectedRate() {
		return expectedRate;
	}

	/**
	 * @param expectedRate
	 *            the expectedRate to set
	 */
	public void setExpectedRate(Double expectedRate) {
		this.expectedRate = expectedRate;
	}
	
	
	

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	
	
	/**
	 * @return the startPeriod
	 */
	public String getStartPeriod() {
		return startPeriod;
	}

	/**
	 * @param startPeriod the startPeriod to set
	 */
	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	/**
	 * @return the endPeriod
	 */
	public String getEndPeriod() {
		return endPeriod;
	}

	/**
	 * @param endPeriod the endPeriod to set
	 */
	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rate [total=" + total + ", HitSize=" + HitSize + ", expectedRate=" + expectedRate + ", hitRate="
				+ hitRate + ", currentType=" + currentType + ", source=" + source + ", lowestRate=" + lowestRate
				+ ", continousDay=" + continousDay + ", market=" + market + ", startPeriod=" + startPeriod
				+ ", endPeriod=" + endPeriod + "]";
	}
	
	
	public void print() {
		LOGGER.info(toString());
	}
	
	
}
