package com.stocks.vo;

public class Stock {
	private String symbol;
	
	public Stock(String symbol) {
		this.symbol = symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + "]";
	}
	
	
}
