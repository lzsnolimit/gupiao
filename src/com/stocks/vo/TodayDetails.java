package com.stocks.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stocks.common.CONSTANT;
import com.stocks.service.write.XueQiuTodayToDB;

public class TodayDetails {

	private Map<Long, Detail> details;
	private String symbol;

	public class Detail {
		private long volume;
		private double avg_price;
		private long timestamp;
		private double current;
		private String market;
		private String loacltime;

		public Detail() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Detail [volume=" + volume + ", avg_price=" + avg_price + ", timestamp=" + timestamp + ", current="
					+ current + ", market=" + market + ", loacltime=" + loacltime + "]";
		}

		public Detail(JSONObject jObject) {

			// System.out.println(jObject);
			volume = jObject.getLong("volume");
			avg_price = jObject.getDouble("avg_price");
			timestamp = jObject.getLong("timestamp");
			current = jObject.getDouble("current");
			loacltime = jObject.getString("time");
			market = CONSTANT.DATA_MARKET;

//			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
//			try {
//				time = sdf.parse(timeStr);
//				System.out.println(time);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			// System.out.println(toString());
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
		 * @return the avg_price
		 */
		public double getAvg_price() {
			return avg_price;
		}

		/**
		 * @param avg_price
		 *            the avg_price to set
		 */
		public void setAvg_price(double avg_price) {
			this.avg_price = avg_price;
		}

		/**
		 * @return the timestamp
		 */
		public long getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp
		 *            the timestamp to set
		 */
		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		/**
		 * @return the current
		 */
		public double getCurrent() {
			return current;
		}

		/**
		 * @param current
		 *            the current to set
		 */
		public void setCurrent(double current) {
			this.current = current;
		}

		public String getMarket() {
			return market;
		}

		public void setMarket(String market) {
			this.market = market;
		}

		public String getLocaltime() {
			return loacltime;
		}

		public void setLocaltime(String loacltime) {
			this.loacltime = loacltime;
		}

		

	}

	public TodayDetails(JSONObject jObject) {
		//System.out.println(jObject);
		setDetails(jObject);
	}

	public TodayDetails() {
	}

	public Map<Long, Detail> getDetails() {
		return details;
	}

	public void setDetails(Map<Long, Detail> details) {
		this.details = details;
	}

	public void setDetails(JSONObject jObject) {
		details = new HashMap<>();
		symbol = jObject.getJSONObject("stock").getString("symbol");
		JSONArray detailsArray = jObject.getJSONArray("chartlist");
		for (int i = 0; i < detailsArray.length(); i++) {
			Detail myDetail = new Detail(detailsArray.getJSONObject(i));
			details.put(myDetail.getTimestamp(), myDetail);
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void writeToDB(String DBType) {
		if (details == null || details.isEmpty()) {
			return;
		}
		switch (DBType) {
		case "Mysql":
			
			for (Entry<Long, Detail> pair : details.entrySet()) {
				//System.out.println(symbol);
				XueQiuTodayToDB.writeToMysql(pair.getValue(),symbol);
			}
			break;
		default:
			break;
		}
	}

}
