package com.pachong.interfaces.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.EmptyStackException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.pachong.interfaces.Connections;
import com.stocks.common.CONSTANT;

public class XueQiuOneDayConnectionImpl extends Connections {

	private static final Logger LOGGER = Logger.getLogger(XueQiuOneDayConnectionImpl.class);

	public String url = "https://xueqiu.com/stock/forchartk/stocklist.json";
	
	public String referURL="https://xueqiu.com/S/";

	public String outputpath = CONSTANT.HISTORY.XUEQIU_ONEDAY_PATH;

	public void getConnection(Map<String, String> param) {
		// TODO Auto-generated method stub
		System.out.println("URL"+url);
		myConnection = Jsoup.connect(url).ignoreContentType(true);
		File httpHeaders = new File(CONSTANT.HEADER.XUEQIU);
		if (httpHeaders.exists()) {
			BufferedReader in;
			try {
				in = new BufferedReader(new FileReader(httpHeaders));
				String line = null;
				while ((line = in.readLine()) != null) {
					if (line.contains("Cookie:")) {
						String[] cookies = line.substring(line.indexOf(":") + 1).trim().split(";");
						for (String cookie : cookies) {
							myConnection.cookie(cookie.substring(0, cookie.indexOf("=")).trim(),
									cookie.substring(cookie.indexOf("=") + 1).trim());
						}
					} else {
						myConnection.header(line.substring(0, line.indexOf(":")).trim(),
								line.substring(line.indexOf(":") + 1).trim());
					}
				}
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		myConnection.maxBodySize(0);
		myConnection.data("period", "1day");
		myConnection.data("begin", "492804712000");
		myConnection.data("callback", "jsonp2");
		myConnection.data(param);
		myConnection.header("Referer", referURL+param.get("symbol"));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (!undoURL.isEmpty()) {
			LOGGER.info("Thread Started! Length: " + undoURL.size());

			Map<String, String> param = null;
			Response rs = null;
			try {
				while ((param = undoURL.poll()) != null) {
					try {
						
						File outFile = new File(outputpath + param.get("type") + "/" + param.get("symbol") + ".txt");
						if (outFile.exists()) {
							continue;
						}
						else {
							System.out.println(outFile.getAbsolutePath());
						}
						LOGGER.info(param.get("symbol") + " " + param.get("type") + " start!");
						getConnection(param);
						rs = myConnection.execute();
						String content = rs.body();
						FileOutputStream out = (new FileOutputStream(outFile));
						out.write(content.substring(content.indexOf("(") + 1, content.lastIndexOf(")")).getBytes());
						out.close();
						LOGGER.info(param.get("symbol") + " " + param.get("type") + " done!");
						Thread.sleep(sleepTimeAfterSuccessConnection);
						// System.out.println(rs.body().length());
					} catch (EmptyStackException e) {
						e.printStackTrace();
						failedURL.add(param);
						handleException();
						// TODO: handle exception
					} catch (SocketTimeoutException e) {
						e.printStackTrace();
						failedURL.add(param);
						handleException();
					} catch (IOException e) {
						e.printStackTrace();
						failedURL.add(param);
						handleException();
					}
				}
				LOGGER.info("Thread Done!");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}

	}

	@Override
	public void handleException() {

		exceptionTimes++;
		if (exceptionTimes >= timesStartSleeping) {
			try {
				LOGGER.info("Start sleeping");
				Thread.sleep(sleepTime);
				synchronized (XueQiuOneDayConnectionImpl.class) {
					if (exceptionTimes >= timesStartSleeping) {
						exceptionTimes = 0;
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}