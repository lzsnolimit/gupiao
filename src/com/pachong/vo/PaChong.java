package com.pachong.vo;

import java.util.ArrayList;
import java.util.List;

import com.pachong.interfaces.Connections;

public abstract class PaChong {
	protected int cycleRetryTimes = 0;
	protected List<Connections> threads = new ArrayList<Connections>();
	protected int numberOfThread = 0;

	public int getCycleRetryTimes() {
		return cycleRetryTimes;
	}

	public void setCycleRetryTimes(int cycleRetryTimes) {
		this.cycleRetryTimes = cycleRetryTimes;
	}

	public List<Connections> getThreads() {
		return threads;
	}

	public void setThreads(List<Connections> threads) {
		this.threads = threads;
	}

	public int getThreadsNumber() {
		return numberOfThread;
	}

	public void setThreadsNumber(int numberOfThread) {
		this.numberOfThread = numberOfThread;
	}

	public abstract void start();
	
	public abstract void initial();
	
	public abstract void startOneCycle();
	
}