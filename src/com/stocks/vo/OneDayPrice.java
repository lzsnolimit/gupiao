package com.stocks.vo;

import java.util.Date;

/**
 * 历史每天价格
 * 
 * @author SDSA
 *
 */
public class OneDayPrice {
	private int id;
	private Stock stock;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double Adjclose;
	private long volume;
	private String adjType;
	private Date riqi;
	private String source;
	private Double growth;
	private Date addedTime;
	private Double CHANGEPRICE;
	private Double CHANGEPERCENT;
	private Double HUANSHOULV;
	private Double MA5;
	private Double MA10;
	private Double MA20;
	private Double MA30;
	private Double DIF;
	private Double DEA;
	private Double MACD;
	private Double CHENGJIAOZHI;

	/**
	 * @return the adjclose
	 */
	public Double getAdjclose() {
		return Adjclose;
	}

	/**
	 * @param adjclose the adjclose to set
	 */
	public void setAdjclose(Double adjclose) {
		Adjclose = adjclose;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the open
	 */
	public Double getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}

	/**
	 * @return the adjType
	 */
	public String getAdjType() {
		return adjType;
	}

	/**
	 * @param adjType
	 *            the adjType to set
	 */
	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	/**
	 * @return the riqi
	 */
	public Date getRiqi() {
		return riqi;
	}

	/**
	 * @param riqi
	 *            the riqi to set
	 */
	public void setRiqi(Date riqi) {
		this.riqi = riqi;
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
	 * @return the growth
	 */
	public Double getGrowth() {
		return growth;
	}

	/**
	 * @param growth
	 *            the growth to set
	 */
	public void setGrowth(Double growth) {
		this.growth = growth;
	}

	/**
	 * @return the addedTime
	 */
	public Date getAddedTime() {
		return addedTime;
	}

	/**
	 * @param addedTime
	 *            the addedTime to set
	 */
	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}

	/**
	 * @return the cHANGEPRICE
	 */
	public Double getCHANGEPRICE() {
		return CHANGEPRICE;
	}

	/**
	 * @param cHANGEPRICE
	 *            the cHANGEPRICE to set
	 */
	public void setCHANGEPRICE(Double cHANGEPRICE) {
		CHANGEPRICE = cHANGEPRICE;
	}

	/**
	 * @return the cHANGEPERCENT
	 */
	public Double getCHANGEPERCENT() {
		return CHANGEPERCENT;
	}

	/**
	 * @param cHANGEPERCENT
	 *            the cHANGEPERCENT to set
	 */
	public void setCHANGEPERCENT(Double cHANGEPERCENT) {
		CHANGEPERCENT = cHANGEPERCENT;
	}

	/**
	 * @return the hUANSHOULV
	 */
	public Double getHUANSHOULV() {
		return HUANSHOULV;
	}

	/**
	 * @param hUANSHOULV
	 *            the hUANSHOULV to set
	 */
	public void setHUANSHOULV(Double hUANSHOULV) {
		HUANSHOULV = hUANSHOULV;
	}

	/**
	 * @return the mA5
	 */
	public Double getMA5() {
		return MA5;
	}

	/**
	 * @param mA5
	 *            the mA5 to set
	 */
	public void setMA5(Double mA5) {
		MA5 = mA5;
	}

	/**
	 * @return the mA10
	 */
	public Double getMA10() {
		return MA10;
	}

	/**
	 * @param mA10
	 *            the mA10 to set
	 */
	public void setMA10(Double mA10) {
		MA10 = mA10;
	}

	/**
	 * @return the mA20
	 */
	public Double getMA20() {
		return MA20;
	}

	/**
	 * @param mA20
	 *            the mA20 to set
	 */
	public void setMA20(Double mA20) {
		MA20 = mA20;
	}

	/**
	 * @return the mA30
	 */
	public Double getMA30() {
		return MA30;
	}

	/**
	 * @param mA30
	 *            the mA30 to set
	 */
	public void setMA30(Double mA30) {
		MA30 = mA30;
	}

	/**
	 * @return the dIF
	 */
	public Double getDIF() {
		return DIF;
	}

	/**
	 * @param dIF
	 *            the dIF to set
	 */
	public void setDIF(Double dIF) {
		DIF = dIF;
	}

	/**
	 * @return the dEA
	 */
	public Double getDEA() {
		return DEA;
	}

	/**
	 * @param dEA
	 *            the dEA to set
	 */
	public void setDEA(Double dEA) {
		DEA = dEA;
	}

	/**
	 * @return the mACD
	 */
	public Double getMACD() {
		return MACD;
	}

	/**
	 * @param mACD
	 *            the mACD to set
	 */
	public void setMACD(Double mACD) {
		MACD = mACD;
	}

	/**
	 * @return the cHENGJIAOZHI
	 */
	public Double getCHENGJIAOZHI() {
		return CHENGJIAOZHI;
	}

	/**
	 * @param cHENGJIAOZHI
	 *            the cHENGJIAOZHI to set
	 */
	public void setCHENGJIAOZHI(Double cHENGJIAOZHI) {
		CHENGJIAOZHI = cHENGJIAOZHI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OneDayPrice [id=" + id + ", stock=" + stock + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", volume=" + volume + ", adjType=" + adjType + ", riqi=" + riqi + ", source="
				+ source + ", growth=" + growth + ", addedTime=" + addedTime + ", CHANGEPRICE=" + CHANGEPRICE
				+ ", CHANGEPERCENT=" + CHANGEPERCENT + ", HUANSHOULV=" + HUANSHOULV + ", MA5=" + MA5 + ", MA10=" + MA10
				+ ", MA20=" + MA20 + ", MA30=" + MA30 + ", DIF=" + DIF + ", DEA=" + DEA + ", MACD=" + MACD
				+ ", CHENGJIAOZHI=" + CHENGJIAOZHI + ", getId()=" + getId() + ", getStock()=" + getStock()
				+ ", getOpen()=" + getOpen() + ", getHigh()=" + getHigh() + ", getLow()=" + getLow() + ", getClose()="
				+ getClose() + ", getVolume()=" + getVolume() + ", getAdjType()=" + getAdjType() + ", getRiqi()="
				+ getRiqi() + ", getSource()=" + getSource() + ", getGrowth()=" + getGrowth() + ", getAddedTime()="
				+ getAddedTime() + ", getCHANGEPRICE()=" + getCHANGEPRICE() + ", getCHANGEPERCENT()="
				+ getCHANGEPERCENT() + ", getHUANSHOULV()=" + getHUANSHOULV() + ", getMA5()=" + getMA5()
				+ ", getMA10()=" + getMA10() + ", getMA20()=" + getMA20() + ", getMA30()=" + getMA30() + ", getDIF()="
				+ getDIF() + ", getDEA()=" + getDEA() + ", getMACD()=" + getMACD() + ", getCHENGJIAOZHI()="
				+ getCHENGJIAOZHI() + "]";
	}

}
