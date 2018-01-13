package com.pachong.interfaces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.jsoup.Connection;

public abstract class Connections extends Thread {
	public Long sleepTime=(long) 600000;
	public int timesStartSleeping=10;
	public long sleepTimeAfterSuccessConnection=0;
	public static int exceptionTimes = 0;
	public Connection myConnection;
	public String url;
	public String outputpath;
	public static Queue<Map<String, String>> undoURL = new LinkedList<Map<String, String>>();
	public static List<Map<String, String>> failedURL = new ArrayList<Map<String, String>>();

	public abstract void getConnection(Map<String, String> param);

	public abstract void run();

	public abstract void handleException();

	public Connection getMyConnection() {
		return myConnection;
	}

	public void setMyConnection(Connection myConnection) {
		this.myConnection = myConnection;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOutputpath() {
		return outputpath;
	}

	public void setOutputpath(String outputpath) {
		this.outputpath = outputpath;
	}

	public static Queue<Map<String, String>> getUndoURL() {
		return undoURL;
	}

	public static void setUndoURL(Queue<Map<String, String>> undoURL) {
		Connections.undoURL = undoURL;
	}



	public static List<Map<String, String>> getFailedURL() {
		return failedURL;
	}

	public static void setFailedURL(List<Map<String, String>> failedURL) {
		Connections.failedURL = failedURL;
	}

	public Long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(Long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getTimesStartSleeping() {
		return timesStartSleeping;
	}

	public void setTimesStartSleeping(int timesStartSleeping) {
		this.timesStartSleeping = timesStartSleeping;
	}

	public static int getExceptionTimes() {
		return exceptionTimes;
	}

	public static void setExceptionTimes(int exceptionTimes) {
		Connections.exceptionTimes = exceptionTimes;
	}

}
