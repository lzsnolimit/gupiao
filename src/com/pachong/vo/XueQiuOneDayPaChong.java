package com.pachong.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pachong.interfaces.Connections;
import com.pachong.interfaces.impl.XueQiuOneDayConnectionImpl;
import com.stocks.common.CONSTANT;
import com.stocks.service.IO.FileService;
import com.sun.java_cup.internal.runtime.Symbol;

public class XueQiuOneDayPaChong extends PaChong {
	protected int cycleRetryTimes = 4;
	protected int numberOfThread = 1;
	private static final Logger LOGGER = Logger.getLogger(XueQiuOneDayPaChong.class);
	private List<Connections> threads = new ArrayList<Connections>();

	@Override
	public void start() {
		// TODO Auto-generated method stub
		while (cycleRetryTimes >= 0) {
			startOneCycle();
			cycleRetryTimes--;

		}
	}

	@Override
	public void initial() {
		// TODO Auto-generated method stub

		switch (CONSTANT.SYMBOL_LIST_SOURCE) {
		case "bianli":
			for (int i = 600000; i < 602000; i++) {
				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", "SH" + i);
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", "SH" + i);
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", "SH" + i);
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);

			}
			for (int i = 603000; i < 604000; i++) {
				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", "SH" + i);
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", "SH" + i);
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", "SH" + i);
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);
			}

			for (int i = 900000; i < 901000; i++) {
				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", "SH" + i);
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", "SH" + i);
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", "SH" + i);
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);
			}
			for (int i = 100000; i < 110000; i++) {

				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", "SZ" + "0" + String.valueOf(i).substring(1));
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", "SZ" + "0" + String.valueOf(i).substring(1));
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", "SZ" + "0" + String.valueOf(i).substring(1));
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);
			}
			for (int i = 300000; i < 301000; i++) {
				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", "SZ" + i);
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", "SZ" + i);
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", "SZ" + i);
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);
			}
			break;

		case "list":
			List<String> symbols = FileService.readSymbolsList(CONSTANT.HISTORY.SYMBOL_LIST);
			for (String symbol : symbols) {
				Map<String, String> paramBefore = new HashMap<String, String>();
				paramBefore.put("symbol", symbol);
				paramBefore.put("type", "before");
				Connections.undoURL.add(paramBefore);
				Map<String, String> paramNormal = new HashMap<String, String>();
				paramNormal.put("symbol", symbol);
				paramNormal.put("type", "normal");
				Connections.undoURL.add(paramNormal);
				Map<String, String> paramAfter = new HashMap<String, String>();
				paramAfter.put("symbol", symbol);
				paramAfter.put("type", "after");
				Connections.undoURL.add(paramAfter);
			}
			break;

		default:
			break;
		}

		// for (int i = 600000; i < 600002; i++) {
		// Map<String, String> paramBefore = new HashMap<String, String>();
		// paramBefore.put("symbol", "SH" + i);
		// paramBefore.put("type", "before");
		// Connections.undoURL.offer(paramBefore);
		// Map<String, String> paramNormal = new HashMap<String, String>();
		// paramNormal.put("symbol", "SH" + i);
		// paramNormal.put("type", "normal");
		// Connections.undoURL.offer(paramNormal);
		// Map<String, String> paramAfter = new HashMap<String, String>();
		// paramAfter.put("symbol", "SH" + i);
		// paramAfter.put("type", "after");
		// Connections.undoURL.offer(paramAfter);
		// }

	}

	@Override
	public void startOneCycle() {
		// TODO Auto-generated method stub
		Connections.undoURL.addAll(Connections.failedURL);
		Connections.failedURL.clear();
		LOGGER.info("Start a cycle, Undo stack length " + Connections.undoURL.size());
		threads.clear();
		for (int i = 0; i < numberOfThread; i++) {
			XueQiuOneDayConnectionImpl connectionImpl = new XueQiuOneDayConnectionImpl();
			threads.add(connectionImpl);
			threads.get(i).start();
		}
		for (int i = 0; i < numberOfThread; i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
