package com.stocks.service.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class FileService {

	private static final Logger LOGGER = Logger.getLogger(FileService.class);

	public static List<String[]> readCSV(boolean ignoreHead, String path, String cvsSplitBy) {
		File myFile = new File(path);
		if (myFile == null || !myFile.exists() || myFile.isDirectory() || !myFile.canRead()) {
			return null;
		}
		List<String[]> content = new ArrayList<String[]>();
		BufferedReader br = null;
		try {
			String line = "";
			br = new BufferedReader(new FileReader(myFile));
			if (ignoreHead) {
				line = br.readLine();
			}
			while ((line = br.readLine()) != null) {
				content.add(line.split(cvsSplitBy));
			}
			LOGGER.info(content.size() + " lines read in " + path);
			return content;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static String[] readDirectory(String path) {

		File myFile = new File(path);
		if (myFile == null || !myFile.exists() || !myFile.isDirectory() || !myFile.canRead()) {
			LOGGER.error("Path:" + path + " error!");
			return null;
		}
		LOGGER.info(myFile.list().length + " files read in " + path);
		return myFile.list();
	}

	public static JSONObject readJsonObject(String path) {
		File myFile = new File(path);
		// System.out.println(myFile.getAbsolutePath());
		if (myFile == null || !myFile.exists() || myFile.isDirectory() || !myFile.canRead()) {
			return null;
		}
		JSONObject jsonObject = null;
		try {
			String content = new String(Files.readAllBytes(myFile.toPath()));
			// System.out.println(content);
			jsonObject = new JSONObject(content);
			if (jsonObject != null && jsonObject.length() != 0) {
				// System.out.println(jsonObject);
				return jsonObject;
			}
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}

		return null;
	}

	public static List<String> readSymbolsList(String path) {
		LOGGER.info(path);
		List<String> symbols = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String line = "";
			File myFile = new File(path);
			if (myFile == null || !myFile.exists() || myFile.isDirectory() || !myFile.canRead()) {
				return null;
			}
			br = new BufferedReader(new FileReader(myFile));
			while ((line = br.readLine()) != null) {
				symbols.add(line);
			}
			LOGGER.info(symbols.size() + " lines read in " + path);
			return symbols;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
